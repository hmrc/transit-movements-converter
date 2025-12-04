/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.transitmovementsconverter.controllers

import cats.data.EitherT
import org.apache.pekko.stream.scaladsl.Keep
import org.apache.pekko.stream.scaladsl.Sink
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.reset
import org.mockito.Mockito.when
import org.scalacheck.Gen
import org.scalatest.BeforeAndAfterEach
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import play.api.http.HeaderNames
import play.api.http.MimeTypes
import play.api.http.Status.*
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.test.FakeHeaders
import play.api.test.FakeRequest
import play.api.test.Helpers.contentAsJson
import play.api.test.Helpers.contentAsString
import play.api.test.Helpers.defaultAwaitTimeout
import play.api.test.Helpers.status
import play.api.test.Helpers.stubControllerComponents
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.config.AppConfig
import uk.gov.hmrc.transitmovementsconverter.controllers.actions.ValidateAcceptRefiner
import uk.gov.hmrc.transitmovementsconverter.models.errors.ConversionError
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.MessageType as V2MessageType
import uk.gov.hmrc.transitmovementsconverter.v3_0.models.MessageType as V3MessageType
import uk.gov.hmrc.transitmovementsconverter.v2_1.services.V2ConverterService
import uk.gov.hmrc.transitmovementsconverter.v3_0.services.V3ConverterService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.xml.Utility.trim
import scala.xml.NodeSeq
import scala.xml.XML

class MessageConversionControllerSpec
    extends AnyFreeSpec
    with Matchers
    with ScalaFutures
    with ScalaCheckDrivenPropertyChecks
    with MockitoSugar
    with BeforeAndAfterEach
    with TestActorSystem {

  val v2MessageTypeGen: Gen[V2MessageType[?]] = Gen.oneOf(V2MessageType.values)
  val v3MessageTypeGen: Gen[V3MessageType[?]] = Gen.oneOf(V3MessageType.values)

  val v2MockXmlToJsonService = mock[V2ConverterService]
  val v3MockXmlToJsonService = mock[V3ConverterService]

  val mockAppConfig = mock[AppConfig]
  when(mockAppConfig.errorLogging).thenReturn(true)

  val sut = new MessageConversionController(
    stubControllerComponents(),
    v2MockXmlToJsonService,
    v3MockXmlToJsonService,
    new ValidateAcceptRefiner(stubControllerComponents()),
    mockAppConfig
  )

  override def beforeEach(): Unit =
    super.beforeEach()
    reset(v2MockXmlToJsonService, v3MockXmlToJsonService)

  "when APIVersion are invalid or missing " - {
    val (matVal, sourceUnderTest) = Source.single(ByteString("<valid></valid>")).alsoToMat(Sink.head)(Keep.right).preMaterialize()

    def reqWithAccept(accept: Option[String]): FakeRequest[Source[ByteString, ?]] =
      FakeRequest(
        method = "POST",
        uri = "/",
        headers = FakeHeaders(
          accept
            .map(
              ac => Seq("APIVersion" -> ac)
            )
            .getOrElse(Seq.empty)
        ),
        body = sourceUnderTest
      )

    "return NOT_ACCEPTABLE when the the APIVersion is None" in {

      val resultMatVal = matVal.map(_.utf8String)

      val sample = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)

      val request = reqWithAccept(None)

      val result = sut.message(sample.name)(request)

      status(result) mustEqual NOT_ACCEPTABLE

      contentAsJson(result) mustEqual Json.obj(
        "code"    -> "NOT_ACCEPTABLE",
        "message" -> "An Accept header is missing."
      )

      whenReady(resultMatVal) {
        _ mustBe "<valid></valid>" // testing we drain the stream
      }
    }
    "return UNSUPPORTED_MEDIA_TYPE error when the the APIVersion is invalid or not supported" in {

      val invalidAPIVersion = Gen.alphaNumStr.sample.getOrElse("invalidHeader")

      val resultMatVal = matVal.map(_.utf8String)

      val sample = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)

      val request = reqWithAccept(Some(invalidAPIVersion))

      val result = sut.message(sample.name)(request)

      status(result) mustEqual UNSUPPORTED_MEDIA_TYPE

      contentAsJson(result) mustEqual Json.obj(
        "code"    -> "UNSUPPORTED_MEDIA_TYPE",
        "message" -> s"The Accept header $invalidAPIVersion is not supported."
      )

      whenReady(resultMatVal) {
        _ mustBe "<valid></valid>" // testing we drain the stream
      }
    }
    "return UNSUPPORTED_MEDIA_TYPE error when the the APIVersion Header is empty" in {

      val emptyAPIVersion = ""

      val resultMatVal = matVal.map(_.utf8String)

      val sample = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)

      val request = reqWithAccept(Some(emptyAPIVersion))

      val result = sut.message(sample.name)(request)

      status(result) mustEqual UNSUPPORTED_MEDIA_TYPE

      contentAsJson(result) mustEqual Json.obj(
        "code"    -> "UNSUPPORTED_MEDIA_TYPE",
        "message" -> s"The Accept header $emptyAPIVersion is not supported."
      )

      whenReady(resultMatVal) {
        _ mustBe "<valid></valid>" // testing we drain the stream
      }

    }
  }
  "when APIVersion is 2.1" - {
    def beforeEach(): Unit =
      super.beforeEach()
      reset(v2MockXmlToJsonService, v3MockXmlToJsonService)

    val v2_1XmlToJsonHeader  = FakeHeaders(Seq("APIVersion" -> "2.1", HeaderNames.CONTENT_TYPE -> MimeTypes.XML, HeaderNames.ACCEPT -> MimeTypes.JSON))
    val v2_1JsonToXmlHeader  = FakeHeaders(Seq("APIVersion" -> "2.1", HeaderNames.CONTENT_TYPE -> MimeTypes.JSON, HeaderNames.ACCEPT -> MimeTypes.XML))
    val v2_1TextToJsonHeader = FakeHeaders(Seq("APIVersion" -> "2.1", HeaderNames.CONTENT_TYPE -> MimeTypes.TEXT, HeaderNames.ACCEPT -> MimeTypes.JSON))
    val v2_1XmlToTextHeader  = FakeHeaders(Seq("APIVersion" -> "2.1", HeaderNames.CONTENT_TYPE -> MimeTypes.XML, HeaderNames.ACCEPT -> MimeTypes.TEXT))

    "with no content type returns a bad request" in {

      val apiVersion                = "2.1"
      val (matVal, sourceUnderTest) = Source.single(ByteString("<valid></valid>")).alsoToMat(Sink.head)(Keep.right).preMaterialize()
      val resultMatVal              = matVal.map(_.utf8String)
      val sample                    = v2MessageTypeGen.sample.getOrElse(V2MessageType.values.head)
      val request                   = FakeRequest[Source[ByteString, ?]](
        method = "POST",
        uri = "/",
        headers = FakeHeaders(Seq("APIVersion" -> apiVersion)),
        body = sourceUnderTest
      )
      val result = sut.message(sample.name)(request)

      status(result) mustEqual UNSUPPORTED_MEDIA_TYPE
      contentAsJson(result) mustEqual Json.obj(
        "code"    -> "UNSUPPORTED_MEDIA_TYPE",
        "message" -> "Content-Type header or Accept header or both were not supplied"
      )
      whenReady(resultMatVal) {
        _ mustEqual "<valid></valid>" // testing we drain the stream
      }
    }

    "with a text content type returns a bad request" in {
      val (matVal, sourceUnderTest) = Source.single(ByteString("<valid></valid>")).alsoToMat(Sink.head)(Keep.right).preMaterialize()
      val resultMatVal              = matVal.map(_.utf8String)
      val sample                    = v2MessageTypeGen.sample.getOrElse(V2MessageType.values.head)
      val request                   = FakeRequest[Source[ByteString, ?]](method = "POST", uri = "/", headers = v2_1TextToJsonHeader, body = sourceUnderTest)
      val result                    = sut.message(sample.name)(request)

      status(result) mustEqual UNSUPPORTED_MEDIA_TYPE
      contentAsJson(result) mustEqual Json.obj(
        "code"    -> "UNSUPPORTED_MEDIA_TYPE",
        "message" -> "Combination of Content-Type header text/plain and Accept header application/json is not supported!"
      )
      whenReady(resultMatVal) {
        _ mustEqual "<valid></valid>" // testing we drain the stream
      }
    }

    "with a text accept returns a bad request" in {
      val (matVal, sourceUnderTest) = Source.single(ByteString("<valid></valid>")).alsoToMat(Sink.head)(Keep.right).preMaterialize()
      val resultMatVal              = matVal.map(_.utf8String)
      val sample                    = v2MessageTypeGen.sample.getOrElse(V2MessageType.values.head)
      val request                   = FakeRequest[Source[ByteString, ?]](method = "POST", uri = "/", headers = v2_1XmlToTextHeader, body = sourceUnderTest)
      val result                    = sut.message(sample.name)(request)

      status(result) mustEqual UNSUPPORTED_MEDIA_TYPE
      contentAsJson(result) mustEqual Json.obj(
        "code"    -> "UNSUPPORTED_MEDIA_TYPE",
        "message" -> "Combination of Content-Type header application/xml and Accept header text/plain is not supported!"
      )
      whenReady(resultMatVal) {
        _ mustEqual "<valid></valid>" // testing we drain the stream
      }
    }

    "with XML and the application/xml content type" - {

      "when provided with valid XML to convert, should return an OK with valid Json" in {

        when(v2MockXmlToJsonService.xmlToJson(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, JsValue](Future.successful(Right(Json.obj("success" -> true)))))

        val sample  = v2MessageTypeGen.sample.getOrElse(V2MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, ?]](method = "POST", uri = "/", headers = v2_1XmlToJsonHeader, body = Source.single(ByteString("<valid></valid>")))
        val result = sut.message(sample.name)(request)

        status(result) mustEqual OK
        contentAsJson(result) mustEqual Json.obj("success" -> true)
      }

      "when provided with invalid XML to convert, should return an BAD_REQUEST" in {

        when(v2MockXmlToJsonService.xmlToJson(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, JsValue](Future.successful(Left(ConversionError.XMLParsingError("error")))))

        val sample  = v2MessageTypeGen.sample.getOrElse(V2MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, ?]](method = "POST", uri = "/", headers = v2_1XmlToJsonHeader, body = Source.single(ByteString("<invalid></invalid>")))
        val result = sut.message(sample.name)(request)

        status(result) mustEqual BAD_REQUEST
        contentAsJson(result) mustEqual Json.obj("code" -> "BAD_REQUEST", "message" -> "error")
      }

      "when an error occurs, should return an INTERNAL_SERVER_ERROR" in {

        when(v2MockXmlToJsonService.xmlToJson(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, JsValue](Future.successful(Left(ConversionError.UnexpectedError(thr = None)))))

        val sample  = v2MessageTypeGen.sample.getOrElse(V2MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, ?]](method = "POST", uri = "/", headers = v2_1XmlToJsonHeader, body = Source.single(ByteString("<invalid></invalid>")))
        val result = sut.message(sample.name)(request)

        status(result) mustEqual INTERNAL_SERVER_ERROR
        contentAsJson(result) mustEqual Json.obj("code" -> "INTERNAL_SERVER_ERROR", "message" -> "Internal server error")
      }
    }

    "with Json and the application/json content type" - {

      "when provided with valid Json to convert, should return an OK with valid XML" in {

        when(v2MockXmlToJsonService.jsonToXml(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, NodeSeq](Future.successful(Right(<test><success>ok</success></test>))))

        val sample  = v2MessageTypeGen.sample.getOrElse(V2MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, ?]](
            method = "POST",
            uri = "/",
            headers = v2_1JsonToXmlHeader,
            body = Source.single(ByteString("""{ "valid": "ok" } """))
          )
        val result = sut.message(sample.name)(request)

        status(result) mustEqual OK
        trim(XML.loadString(contentAsString(result))) mustEqual <test><success>ok</success></test>
      }

      "when provided with invalid XML to convert, should return an BAD_REQUEST" in {

        when(v2MockXmlToJsonService.jsonToXml(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, NodeSeq](Future.successful(Left(ConversionError.JsonParsingError(new IllegalStateException("error"))))))

        val sample  = v2MessageTypeGen.sample.getOrElse(V2MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, ?]](
            method = "POST",
            uri = "/",
            headers = v2_1JsonToXmlHeader,
            body = Source.single(ByteString("""{ "valid": "ok"  """))
          )
        val result = sut.message(sample.name)(request)

        status(result) mustEqual BAD_REQUEST
        contentAsJson(result) mustEqual Json.obj("code" -> "BAD_REQUEST", "message" -> "error")
      }

      "when an error occurs, should return an INTERNAL_SERVER_ERROR" in {

        when(v2MockXmlToJsonService.jsonToXml(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, NodeSeq](Future.successful(Left(ConversionError.UnexpectedError(thr = None)))))

        val sample  = v2MessageTypeGen.sample.getOrElse(V2MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, ?]](
            method = "POST",
            uri = "/",
            headers = v2_1JsonToXmlHeader,
            body = Source.single(ByteString("""{ "valid": "ok"  """))
          )
        val result = sut.message(sample.name)(request)

        status(result) mustEqual INTERNAL_SERVER_ERROR
        contentAsJson(result) mustEqual Json.obj("code" -> "INTERNAL_SERVER_ERROR", "message" -> "Internal server error")
      }
    }

  }
  "when APIVersion is 3.0" - {

    val v3_0XmlToJsonHeader  = FakeHeaders(Seq("APIVersion" -> "3.0", HeaderNames.CONTENT_TYPE -> MimeTypes.XML, HeaderNames.ACCEPT -> MimeTypes.JSON))
    val v3_0JsonToXmlHeader  = FakeHeaders(Seq("APIVersion" -> "3.0", HeaderNames.CONTENT_TYPE -> MimeTypes.JSON, HeaderNames.ACCEPT -> MimeTypes.XML))
    val v3_0TextToJsonHeader = FakeHeaders(Seq("APIVersion" -> "3.0", HeaderNames.CONTENT_TYPE -> MimeTypes.TEXT, HeaderNames.ACCEPT -> MimeTypes.JSON))
    val v3_0XmlToTextHeader  = FakeHeaders(Seq("APIVersion" -> "3.0", HeaderNames.CONTENT_TYPE -> MimeTypes.XML, HeaderNames.ACCEPT -> MimeTypes.TEXT))

    "with no content type returns a bad request" in {

      val apiVersion                = "3.0"
      val (matVal, sourceUnderTest) = Source.single(ByteString("<valid></valid>")).alsoToMat(Sink.head)(Keep.right).preMaterialize()
      val resultMatVal              = matVal.map(_.utf8String)
      val sample                    = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)
      val request                   = FakeRequest[Source[ByteString, ?]](
        method = "POST",
        uri = "/",
        headers = FakeHeaders(Seq("APIVersion" -> apiVersion)),
        body = sourceUnderTest
      )
      val result = sut.message(sample.name)(request)

      status(result) mustEqual UNSUPPORTED_MEDIA_TYPE
      contentAsJson(result) mustEqual Json.obj(
        "code"    -> "UNSUPPORTED_MEDIA_TYPE",
        "message" -> "Content-Type header or Accept header or both were not supplied"
      )
      whenReady(resultMatVal) {
        _ mustEqual "<valid></valid>" // testing we drain the stream
      }
    }

    "with a text content type returns a bad request" in {
      val (matVal, sourceUnderTest) = Source.single(ByteString("<valid></valid>")).alsoToMat(Sink.head)(Keep.right).preMaterialize()
      val resultMatVal              = matVal.map(_.utf8String)
      val sample                    = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)
      val request                   = FakeRequest[Source[ByteString, ?]](method = "POST", uri = "/", headers = v3_0TextToJsonHeader, body = sourceUnderTest)
      val result                    = sut.message(sample.name)(request)

      status(result) mustEqual UNSUPPORTED_MEDIA_TYPE
      contentAsJson(result) mustEqual Json.obj(
        "code"    -> "UNSUPPORTED_MEDIA_TYPE",
        "message" -> "Combination of Content-Type header text/plain and Accept header application/json is not supported!"
      )
      whenReady(resultMatVal) {
        _ mustEqual "<valid></valid>" // testing we drain the stream
      }
    }

    "with a text accept returns a bad request" in {
      val (matVal, sourceUnderTest) = Source.single(ByteString("<valid></valid>")).alsoToMat(Sink.head)(Keep.right).preMaterialize()
      val resultMatVal              = matVal.map(_.utf8String)
      val sample                    = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)
      val request                   = FakeRequest[Source[ByteString, ?]](method = "POST", uri = "/", headers = v3_0XmlToTextHeader, body = sourceUnderTest)
      val result                    = sut.message(sample.name)(request)

      status(result) mustEqual UNSUPPORTED_MEDIA_TYPE
      contentAsJson(result) mustEqual Json.obj(
        "code"    -> "UNSUPPORTED_MEDIA_TYPE",
        "message" -> "Combination of Content-Type header application/xml and Accept header text/plain is not supported!"
      )
      whenReady(resultMatVal) {
        _ mustEqual "<valid></valid>" // testing we drain the stream
      }
    }

    "with XML and the application/xml content type" - {

      "when provided with valid XML to convert, should return an OK with valid Json" in {

        when(v3MockXmlToJsonService.xmlToJson(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, JsValue](Future.successful(Right(Json.obj("success" -> true)))))

        val sample = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)

        val request =
          FakeRequest[Source[ByteString, ?]](method = "POST", uri = "/", headers = v3_0XmlToJsonHeader, body = Source.single(ByteString("<valid></valid>")))

        val result = sut.message(sample.name)(request)

        status(result) mustEqual OK
        contentAsJson(result) mustEqual Json.obj("success" -> true)
      }

      "when provided with invalid XML to convert, should return an BAD_REQUEST" in {

        when(v3MockXmlToJsonService.xmlToJson(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, JsValue](Future.successful(Left(ConversionError.XMLParsingError("error")))))

        val sample  = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, ?]](method = "POST", uri = "/", headers = v3_0XmlToJsonHeader, body = Source.single(ByteString("<invalid></invalid>")))
        val result = sut.message(sample.name)(request)

        status(result) mustEqual BAD_REQUEST
        contentAsJson(result) mustEqual Json.obj("code" -> "BAD_REQUEST", "message" -> "error")
      }

      "when an error occurs, should return an INTERNAL_SERVER_ERROR" in {

        when(v3MockXmlToJsonService.xmlToJson(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, JsValue](Future.successful(Left(ConversionError.UnexpectedError(thr = None)))))

        val sample  = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, ?]](method = "POST", uri = "/", headers = v3_0XmlToJsonHeader, body = Source.single(ByteString("<invalid></invalid>")))
        val result = sut.message(sample.name)(request)

        status(result) mustEqual INTERNAL_SERVER_ERROR
        contentAsJson(result) mustEqual Json.obj("code" -> "INTERNAL_SERVER_ERROR", "message" -> "Internal server error")
      }
    }

    "with Json and the application/json content type" - {

      "when provided with valid Json to convert, should return an OK with valid XML" in {

        when(v3MockXmlToJsonService.jsonToXml(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, NodeSeq](Future.successful(Right(<test><success>ok</success></test>))))

        val sample  = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, ?]](
            method = "POST",
            uri = "/",
            headers = v3_0JsonToXmlHeader,
            body = Source.single(ByteString("""{ "valid": "ok" } """))
          )
        val result = sut.message(sample.name)(request)

        status(result) mustEqual OK
        trim(XML.loadString(contentAsString(result))) mustEqual <test><success>ok</success></test>
      }

      "when provided with invalid XML to convert, should return an BAD_REQUEST" in {

        when(v3MockXmlToJsonService.jsonToXml(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, NodeSeq](Future.successful(Left(ConversionError.JsonParsingError(new IllegalStateException("error"))))))

        val sample  = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, ?]](
            method = "POST",
            uri = "/",
            headers = v3_0JsonToXmlHeader,
            body = Source.single(ByteString("""{ "valid": "ok"  """))
          )
        val result = sut.message(sample.name)(request)

        status(result) mustEqual BAD_REQUEST
        contentAsJson(result) mustEqual Json.obj("code" -> "BAD_REQUEST", "message" -> "error")
      }

      "when an error occurs, should return an INTERNAL_SERVER_ERROR" in {

        when(v3MockXmlToJsonService.jsonToXml(any(), any()))
          .thenReturn(EitherT[Future, ConversionError, NodeSeq](Future.successful(Left(ConversionError.UnexpectedError(thr = None)))))

        val sample  = v3MessageTypeGen.sample.getOrElse(V3MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, ?]](
            method = "POST",
            uri = "/",
            headers = v3_0JsonToXmlHeader,
            body = Source.single(ByteString("""{ "valid": "ok"  """))
          )
        val result = sut.message(sample.name)(request)

        status(result) mustEqual INTERNAL_SERVER_ERROR
        contentAsJson(result) mustEqual Json.obj("code" -> "INTERNAL_SERVER_ERROR", "message" -> "Internal server error")
      }
    }

  }

}

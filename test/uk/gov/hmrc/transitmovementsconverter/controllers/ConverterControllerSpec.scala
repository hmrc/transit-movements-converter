/*
 * Copyright 2022 HM Revenue & Customs
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

import akka.stream.scaladsl.Keep
import akka.stream.scaladsl.Sink
import akka.stream.scaladsl.Source
import akka.util.ByteString
import cats.data.EitherT
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
import play.api.http.Status.BAD_REQUEST
import play.api.http.Status.INTERNAL_SERVER_ERROR
import play.api.http.Status.OK
import play.api.http.Status.UNSUPPORTED_MEDIA_TYPE
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.test.FakeHeaders
import play.api.test.FakeRequest
import play.api.test.Helpers.contentAsJson
import play.api.test.Helpers.defaultAwaitTimeout
import play.api.test.Helpers.status
import play.api.test.Helpers.stubControllerComponents
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.models.MessageType
import uk.gov.hmrc.transitmovementsconverter.models.errors.XmlToJsonError
import uk.gov.hmrc.transitmovementsconverter.services.XmlToJsonService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ConverterControllerSpec
    extends AnyFreeSpec
    with Matchers
    with ScalaFutures
    with ScalaCheckDrivenPropertyChecks
    with MockitoSugar
    with BeforeAndAfterEach
    with TestActorSystem {

  val messageTypeGen: Gen[MessageType[_]] = Gen.oneOf(MessageType.values)

  val mockXmlToJsonService = mock[XmlToJsonService]

  val sut = new ConverterController(
    stubControllerComponents(),
    mockXmlToJsonService
  )

  val xmlHeader  = FakeHeaders(Seq(HeaderNames.CONTENT_TYPE -> MimeTypes.XML))
  val jsonHeader = FakeHeaders(Seq(HeaderNames.CONTENT_TYPE -> MimeTypes.JSON))
  val textHeader = FakeHeaders(Seq(HeaderNames.CONTENT_TYPE -> MimeTypes.TEXT))

  override def beforeEach(): Unit =
    reset(mockXmlToJsonService)

  "converting a message type" - {

    "with no content type returns a bad request" in {

      val (matVal, sourceUnderTest) = Source.single(ByteString("<valid></valid>")).alsoToMat(Sink.head)(Keep.right).preMaterialize()
      val resultMatVal              = matVal.map(_.utf8String)
      val sample                    = messageTypeGen.sample.getOrElse(MessageType.values.head)
      val request                   = FakeRequest[Source[ByteString, _]](method = "POST", uri = "/", headers = FakeHeaders(), body = sourceUnderTest)
      val result                    = sut.convert(sample)(request)

      status(result) mustBe UNSUPPORTED_MEDIA_TYPE
      contentAsJson(result) mustBe Json.obj("code" -> "UNSUPPORTED_MEDIA_TYPE", "message" -> "A content-type header is required!")
      whenReady(resultMatVal) {
        _ mustBe "<valid></valid>" // testing we drain the stream
      }
    }

    "with a text content type returns a bad request" in {
      val (matVal, sourceUnderTest) = Source.single(ByteString("<valid></valid>")).alsoToMat(Sink.head)(Keep.right).preMaterialize()
      val resultMatVal              = matVal.map(_.utf8String)
      val sample                    = messageTypeGen.sample.getOrElse(MessageType.values.head)
      val request                   = FakeRequest[Source[ByteString, _]](method = "POST", uri = "/", headers = textHeader, body = sourceUnderTest)
      val result                    = sut.convert(sample)(request)

      status(result) mustBe UNSUPPORTED_MEDIA_TYPE
      contentAsJson(result) mustBe Json.obj("code" -> "UNSUPPORTED_MEDIA_TYPE", "message" -> "Content-type header text/plain is not supported!")
      whenReady(resultMatVal) {
        _ mustBe "<valid></valid>" // testing we drain the stream
      }
    }

    "with XML and the application/xml content type" - {

      "when provided with valid XML to convert, should return an OK with valid Json" in {

        when(mockXmlToJsonService.convert(any(), any()))
          .thenReturn(EitherT[Future, XmlToJsonError, JsValue](Future.successful(Right(Json.obj("success" -> true)))))

        val sample  = messageTypeGen.sample.getOrElse(MessageType.values.head)
        val request = FakeRequest[Source[ByteString, _]](method = "POST", uri = "/", headers = xmlHeader, body = Source.single(ByteString("<valid></valid>")))
        val result  = sut.convert(sample)(request)

        status(result) mustBe OK
        contentAsJson(result) mustBe Json.obj("success" -> true)
      }

      "when provided with invalid XML to convert, should return an BAD_REQUEST" in {

        when(mockXmlToJsonService.convert(any(), any()))
          .thenReturn(EitherT[Future, XmlToJsonError, JsValue](Future.successful(Left(XmlToJsonError.XMLParsingError("error")))))

        val sample = messageTypeGen.sample.getOrElse(MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, _]](method = "POST", uri = "/", headers = xmlHeader, body = Source.single(ByteString("<invalid></invalid>")))
        val result = sut.convert(sample)(request)

        status(result) mustBe BAD_REQUEST
        contentAsJson(result) mustBe Json.obj("code" -> "BAD_REQUEST", "message" -> "error")
      }

      "when an error occurs, should return an INTERNAL_SERVER_ERROR" in {

        when(mockXmlToJsonService.convert(any(), any()))
          .thenReturn(EitherT[Future, XmlToJsonError, JsValue](Future.successful(Left(XmlToJsonError.UnexpectedError(thr = None)))))

        val sample = messageTypeGen.sample.getOrElse(MessageType.values.head)
        val request =
          FakeRequest[Source[ByteString, _]](method = "POST", uri = "/", headers = xmlHeader, body = Source.single(ByteString("<invalid></invalid>")))
        val result = sut.convert(sample)(request)

        status(result) mustBe INTERNAL_SERVER_ERROR
        contentAsJson(result) mustBe Json.obj("code" -> "INTERNAL_SERVER_ERROR", "message" -> "Internal server error")
      }
    }

  }

}

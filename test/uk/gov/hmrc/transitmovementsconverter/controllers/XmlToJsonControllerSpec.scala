package uk.gov.hmrc.transitmovementsconverter.controllers

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
import play.api.http.Status.BAD_REQUEST
import play.api.http.Status.INTERNAL_SERVER_ERROR
import play.api.http.Status.OK
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

class XmlToJsonControllerSpec extends AnyFreeSpec
  with Matchers
  with ScalaFutures
  with ScalaCheckDrivenPropertyChecks
  with MockitoSugar
  with BeforeAndAfterEach
  with TestActorSystem {

  val messageTypeGen: Gen[MessageType[_]] = Gen.oneOf(MessageType.values)

  val mockXmlToJsonService = mock[XmlToJsonService]
  val sut = new XmlToJsonController(
    stubControllerComponents(),
    mockXmlToJsonService
  )

  override def beforeEach(): Unit =
    reset(mockXmlToJsonService)

  "converting a message type" - {

    "when provided with valid XML to convert, should return an OK with valid Json" in {

      when(mockXmlToJsonService.convert(any(), any())).thenReturn(EitherT[Future, XmlToJsonError, JsValue](Future.successful(Right(Json.obj("success" -> true)))))

      val sample = messageTypeGen.sample.getOrElse(MessageType.values.head)
      val request = FakeRequest[Source[ByteString, _]](method = "POST", uri = "/", headers = FakeHeaders(), body = Source.single(ByteString("<valid></valid>")))
      val result = sut.convert(sample)(request)

      status(result) mustBe OK
      contentAsJson(result) mustBe Json.obj("success" -> true)
    }

    "when provided with invalid XML to convert, should return an BAD_REQUEST" in {

      when(mockXmlToJsonService.convert(any(), any())).thenReturn(EitherT[Future, XmlToJsonError, JsValue](Future.successful(Left(XmlToJsonError.XMLParsingError("error")))))

      val sample = messageTypeGen.sample.getOrElse(MessageType.values.head)
      val request = FakeRequest[Source[ByteString, _]](method = "POST", uri = "/", headers = FakeHeaders(), body = Source.single(ByteString("<invalid></invalid>")))
      val result = sut.convert(sample)(request)

      status(result) mustBe BAD_REQUEST
      contentAsJson(result) mustBe Json.obj("code" -> "BAD_REQUEST", "message" -> "error")
    }

    "when an error occurs, should return an INTERNAL_SERVER_ERROR" in {

      when(mockXmlToJsonService.convert(any(), any())).thenReturn(EitherT[Future, XmlToJsonError, JsValue](Future.successful(Left(XmlToJsonError.UnexpectedError(thr = None)))))

      val sample = messageTypeGen.sample.getOrElse(MessageType.values.head)
      val request = FakeRequest[Source[ByteString, _]](method = "POST", uri = "/", headers = FakeHeaders(), body = Source.single(ByteString("<invalid></invalid>")))
      val result = sut.convert(sample)(request)

      status(result) mustBe INTERNAL_SERVER_ERROR
      contentAsJson(result) mustBe Json.obj("code" -> "INTERNAL_SERVER_ERROR", "message" -> "Internal server error")
    }

  }

}

/*
 * Copyright 2025 HM Revenue & Customs
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

package uk.gov.hmrc.transitmovementsconverter.routing

import org.apache.pekko.stream.scaladsl.Keep
import org.apache.pekko.stream.scaladsl.Sink
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.verify
import org.mockito.Mockito.when
import org.scalacheck.Gen
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import play.api.http.HeaderNames
import play.api.http.Status.NOT_ACCEPTABLE
import play.api.http.Status.OK
import play.api.http.Status.UNSUPPORTED_MEDIA_TYPE
import play.api.libs.json.Json
import play.api.mvc.Results.Ok
import play.api.mvc.Action
import play.api.mvc.ControllerComponents
import play.api.test.Helpers.contentAsJson
import play.api.test.Helpers.contentAsString
import play.api.test.Helpers.defaultAwaitTimeout
import play.api.test.Helpers.status
import play.api.test.Helpers.stubControllerComponents
import play.api.test.FakeHeaders
import play.api.test.FakeRequest
import play.api.test.Helpers
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.v2_1.controllers.MessageConversionController as Vesion2MessageConversionController
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.MessageType
import uk.gov.hmrc.transitmovementsconverter.v3_0.controllers.MessageConversionController as Vesion3MessageConversionController

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class RoutingControllerSpec extends AnyFreeSpec with Matchers with ScalaFutures with ScalaCheckDrivenPropertyChecks with MockitoSugar with TestActorSystem {

  val messageTypeGen: Gen[MessageType[?]] = Gen.oneOf(MessageType.values)

  private val controllerComponents: ControllerComponents = Helpers.stubControllerComponents()

  private val mockVersion2MessageController = mock[Vesion2MessageConversionController]

  private val mockVersion3MessageController = mock[Vesion3MessageConversionController]

  val routingController = new RoutingController(
    controllerComponents,
    mockVersion2MessageController,
    mockVersion3MessageController
  )

  private def actionReturning(marker: String): Action[Source[ByteString, ?]] =
    controllerComponents.actionBuilder.async(routingController.streamFromMemory) {
      _ =>
        Future.successful(Ok(marker))
    }
  private val (matVal, sourceUnderTest) = Source.single(ByteString("<valid></valid>")).alsoToMat(Sink.head)(Keep.right).preMaterialize()

  private def reqWithAccept(accept: Option[String]): FakeRequest[Source[ByteString, ?]] =
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

  "RouteRequest" - {
    "Route to MessageConversionController(V2_1) when the accept header is APIVersion:2.1" in {
      when(mockVersion2MessageController.message(any()))
        .thenReturn(actionReturning("v2.1"))

      val sample = messageTypeGen.sample.getOrElse(MessageType.values.head)

      val apiVersion_2_1 = API_VERSION_2_1.value

      val request = reqWithAccept(Some(apiVersion_2_1))

      val result = routingController.routeRequest(sample)(request)

      status(result) mustBe OK
      contentAsString(result) mustBe "v2.1"

      verify(mockVersion2MessageController).message(any[MessageType[?]])
    }

    "Route to MessageConversionController(V3_0) when the accept header is APIVersion:3.0" in {
      when(mockVersion3MessageController.message(any()))
        .thenReturn(actionReturning("v3.0"))

      val sample = messageTypeGen.sample.getOrElse(MessageType.values.head)

      val apiVersion_3_0 = API_VERSION_3_0.value

      val request = reqWithAccept(Some(apiVersion_3_0))

      val result = routingController.routeRequest(sample)(request)

      status(result) mustBe OK
      contentAsString(result) mustBe "v3.0"

      verify(mockVersion3MessageController).message(any[MessageType[?]])
    }

    "Return NotAccepted when the Accept header is missing" in {

      val sample = messageTypeGen.sample.getOrElse(MessageType.values.head)
      val result = routingController.routeRequest(sample)(reqWithAccept(None))

      status(result) mustBe NOT_ACCEPTABLE

      contentAsJson(result) mustBe Json.obj(
        "code"    -> "NOT_ACCEPTABLE",
        "message" -> "The Accept header is missing."
      )
    }
    "Return NotSupportedMediaTypeError when the AcceptHeader is not recognised" in {
      val invalidHeader = Gen.alphaNumStr.sample.getOrElse("invalidHeader")
      val sample        = messageTypeGen.sample.getOrElse(MessageType.values.head)
      val result        = routingController.routeRequest(sample)(reqWithAccept(Some(invalidHeader)))

      status(result) mustBe UNSUPPORTED_MEDIA_TYPE

      contentAsJson(result) mustBe Json.obj(
        "code"    -> "UNSUPPORTED_MEDIA_TYPE",
        "message" -> "The Accept header is invalid."
      )
    }
  }

}

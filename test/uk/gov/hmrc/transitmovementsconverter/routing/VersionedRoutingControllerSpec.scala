/*
 * Copyright 2024 HM Revenue & Customs
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

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar
import play.api.http.Status.BAD_REQUEST
import play.api.http.Status.OK
import play.api.mvc.Action
import play.api.test.FakeHeaders
import play.api.test.FakeRequest
import play.api.test.Helpers.contentAsString
import play.api.test.Helpers.defaultAwaitTimeout
import play.api.test.Helpers.status
import play.api.test.Helpers.stubControllerComponents
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.config.Constants

import scala.concurrent.ExecutionContext.Implicits.global
import uk.gov.hmrc.transitmovementsconverter.controllers.{MessageConversionController => TransitionalMessageConversionController}
import uk.gov.hmrc.transitmovementsconverter.models
import uk.gov.hmrc.transitmovementsconverter.v2_1.controllers.MessageConversionController
import uk.gov.hmrc.transitmovementsconverter.v2_1.services.ConverterService
import uk.gov.hmrc.transitmovementsconverter.services.{ConverterService => TransitionalConverterService}
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.MessageType
import uk.gov.hmrc.transitmovementsconverter.models.{MessageType => TransitionalMessageType}

import java.nio.charset.StandardCharsets
import scala.concurrent.Future

class VersionedRoutingControllerSpec extends AnyWordSpec with Matchers with MockitoSugar with ScalaFutures {

  "message" should {
    "call the transitional controller when APIVersion non 'final' value has been sent" in new Setup {
      val request = FakeRequest("POST", s"/messages/$validTransitionalMessageType", FakeHeaders(Seq(Constants.APIVersionHeaderKey -> "anything")), source)
      val result  = controller.message(validTransitionalMessageType.name)(request)
      status(result) shouldBe OK
      contentAsString(result) shouldBe "transitional"
    }

    "call the transitional controller when APIVersion when no APIVersion header sent" in new Setup {
      val request = FakeRequest("POST", s"/messages/$validTransitionalMessageType", FakeHeaders(Seq.empty), source)
      val result  = controller.message(validTransitionalMessageType.name)(request)
      status(result) shouldBe OK
      contentAsString(result) shouldBe "transitional"
    }

    "call the final controller when APIVersion 'final' has been sent" in new Setup {
      val request = FakeRequest("POST", s"/messages/$validTransitionalMessageType", FakeHeaders(Seq(Constants.APIVersionHeaderKey -> "final")), source)
      val result  = controller.message(validTransitionalMessageType.name)(request)
      status(result) shouldBe OK
      contentAsString(result) shouldBe "final"
    }

    "return BAD_REQUEST when an invalid message type has been sent for transitional" in new Setup {
      val request = FakeRequest("POST", s"/messages/$validTransitionalMessageType", FakeHeaders(Seq(Constants.APIVersionHeaderKey -> "anything")), source)
      val result  = controller.message("invalid")(request)
      status(result) shouldBe BAD_REQUEST
    }

    "return BAD_REQUEST when an invalid message type has been sent for final" in new Setup {
      val request = FakeRequest("POST", s"/messages/$validTransitionalMessageType", FakeHeaders(Seq(Constants.APIVersionHeaderKey -> "final")), source)
      val result  = controller.message("invalid")(request)
      status(result) shouldBe BAD_REQUEST
    }
  }

  trait Setup {

    implicit val materializer: Materializer = Materializer(TestActorSystem.system)

    val validTransitionalMessageType = TransitionalMessageType.values.head
    val validMessageType             = MessageType.values.head

    val source: Source[ByteString, NotUsed]                            = Source.single(ByteString("someValidSource", StandardCharsets.UTF_8))
    val mockTransitionalConverterService: TransitionalConverterService = mock[TransitionalConverterService]
    val mockConverterService: ConverterService                         = mock[ConverterService]

    val mockTransitionalMessageConversionController: TransitionalMessageConversionController = new TransitionalMessageConversionController(
      stubControllerComponents(),
      mockTransitionalConverterService
    ) {

      override def message(messageType: models.MessageType[_]): Action[Source[ByteString, _]] =
        Action.async(streamFromMemory) {
          _ =>
            Future.successful(Ok("transitional"))

        }
    }

    val mockMessageConversionController: MessageConversionController = new MessageConversionController(
      stubControllerComponents(),
      mockConverterService
    ) {

      override def message(messageType: MessageType[_]): Action[Source[ByteString, _]] =
        Action.async(streamFromMemory) {
          _ =>
            Future.successful(Ok("final"))
        }
    }

    val controller = new VersionedRoutingController(stubControllerComponents(), mockTransitionalMessageConversionController, mockMessageConversionController)
  }
}

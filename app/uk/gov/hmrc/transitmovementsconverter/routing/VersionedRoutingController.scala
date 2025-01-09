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

import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString
import play.api.mvc.Action
import play.api.mvc.ControllerComponents
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController
import uk.gov.hmrc.transitmovementsconverter.config.Constants
import uk.gov.hmrc.transitmovementsconverter.controllers.{MessageConversionController => TransitionalMessageConversionController}
import uk.gov.hmrc.transitmovementsconverter.models.{MessageType => TransistionalMessageType}
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.MessageType
import uk.gov.hmrc.transitmovementsconverter.stream.StreamingParsers
import uk.gov.hmrc.transitmovementsconverter.v2_1.controllers.{MessageConversionController => FinalMessageConversionController}

import javax.inject.Inject
import scala.concurrent.Future

class VersionedRoutingController @Inject() (
  cc: ControllerComponents,
  transitionalController: TransitionalMessageConversionController,
  finalController: FinalMessageConversionController
)(implicit val materializer: Materializer)
    extends BackendController(cc)
    with StreamingParsers {

  def message(messageType: String): Action[Source[ByteString, ?]] =
    Action.async(streamFromMemory) {
      implicit request =>
        request.headers.get(Constants.APIVersionHeaderKey).map(_.trim.toLowerCase) match {
          case Some(Constants.APIVersionFinalHeaderValue) =>
            MessageType.fromName(messageType) match {
              case Some(validMessageType) => finalController.message(validMessageType)(request)
              case None                   => Future.successful(BadRequest(s"Invalid messageType: $messageType"))
            }
          case _ =>
            TransistionalMessageType.fromName(messageType) match {
              case Some(validMessageType) => transitionalController.message(validMessageType)(request)
              case None                   => Future.successful(BadRequest(s"Invalid messageType: $messageType"))
            }
        }
    }
}

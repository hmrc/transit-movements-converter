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

import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.Sink
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString
import play.api.Logging
import play.api.libs.json.Json
import play.api.mvc.*
import uk.gov.hmrc.transitmovementsconverter.routing.VersionHeaderErrorFormats.*
import uk.gov.hmrc.transitmovementsconverter.controllers.MessageConversionController
import uk.gov.hmrc.transitmovementsconverter.models.MessageType
import uk.gov.hmrc.transitmovementsconverter.services.{ConverterService, ConverterServiceFactory}
import uk.gov.hmrc.transitmovementsconverter.stream.StreamingParsers

import javax.inject.Inject
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class RoutingController @Inject() (
  val controllerComponents: ControllerComponents,
  messageConversionController: MessageConversionController,
  converterServiceFactory: ConverterServiceFactory
)(implicit
  ec: ExecutionContext,
  val materializer: Materializer
) extends BaseController
    with StreamingParsers
    with Logging {

  private def checkAcceptHeaders(implicit request: Request[?]): Either[Result, APIVersionHeader] =
    val APIVersion = request.headers.get("APIVersion")
    APIVersion match {
      case None => Left(NotAcceptable(Json.toJson(ApiVersionHeaderError.notAcceptableError("The Accept header is missing."))))
      case Some(value) =>
        APIVersionHeader.fromString(value) match {
          case Right(valid) => Right(valid)
          case _ =>
            Left(UnsupportedMediaType(Json.toJson(ApiVersionHeaderError.unsupportedMediaTypeError("The Accept header is invalid."))))
        }
    }

  def routeRequest(messageType: MessageType[?]): Action[Source[ByteString, ?]] = Action.async(streamFromMemory) {
    implicit request =>
      checkAcceptHeaders match {
        case Left(err) =>
          request.body.runWith(Sink.ignore)
          Future.successful(err)
        case Right(APIVersionHeader.API_VERSION_2_1) => messageConversionController.message(messageType)(request)
        case Right(APIVersionHeader.API_VERSION_3_0) => messageConversionController.message(messageType)(request)
      }
  }
  
}

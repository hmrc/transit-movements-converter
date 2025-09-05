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
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString
import play.api.Logging
import play.api.http.HeaderNames
import play.api.libs.json.Json
import play.api.mvc.*
import uk.gov.hmrc.transitmovementsconverter.routing.VersionHeaderErrorFormats.*
import uk.gov.hmrc.transitmovementsconverter.v2_1.controllers.MessageConversionController as Version2MessageConversionController
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.MessageType
import uk.gov.hmrc.transitmovementsconverter.v2_1.stream.StreamingParsers
import uk.gov.hmrc.transitmovementsconverter.v3_0.controllers.MessageConversionController as Version3MessageConversionController

import javax.inject.Inject
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class RoutingController @Inject() (
  val controllerComponents: ControllerComponents,
  version2_1Controller: Version2MessageConversionController,
  version3_0Controller: Version3MessageConversionController
)(implicit
  ec: ExecutionContext,
  val materializer: Materializer
) extends BaseController
    with StreamingParsers
    with Logging {

  private lazy val validHeaders: Seq[String] = Seq(
    VERSION_HEADER_2_1.value,
    VERSION_HEADER_3_0.value
  )

  private def checkAcceptHeaders(implicit request: Request[?]): Either[Result, VersionHeader] =
    request.headers.get(HeaderNames.ACCEPT) match {
      case None => Left(NotAcceptable(Json.toJson(VersionHeaderError.notAcceptableError("The Accept header is missing."))))
      case Some(value) =>
        VersionHeader(value) match {
          case Right(valid) if validHeaders.contains(valid.value) => Right(valid)
          case _ =>
            Left(UnsupportedMediaType(Json.toJson(VersionHeaderError.unsupportedMediaTypeError("The Accept header is invalid."))))
        }
    }

  def routeRequest(messageType: MessageType[?]): Action[Source[ByteString, ?]] = Action.async(streamFromMemory) {
    implicit request =>
      checkAcceptHeaders match {
        case Left(err)                 => Future.successful(err)
        case Right(VERSION_HEADER_2_1) => version2_1Controller.message(messageType)(request)
        case Right(VERSION_HEADER_3_0) => version3_0Controller.message(messageType)(request)
      }
  }
}

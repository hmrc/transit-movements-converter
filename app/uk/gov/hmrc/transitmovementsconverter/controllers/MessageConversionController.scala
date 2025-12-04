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
import cats.implicits.*
import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.Sink
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString
import play.api.Logging
import play.api.http.HeaderNames
import play.api.http.MimeTypes
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.ControllerComponents
import play.api.mvc.Result
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController
import uk.gov.hmrc.transitmovementsconverter.config.AppConfig
import uk.gov.hmrc.transitmovementsconverter.controllers.actions.ValidateAcceptRefiner
import uk.gov.hmrc.transitmovementsconverter.models
import uk.gov.hmrc.transitmovementsconverter.models.APIVersionHeader
import uk.gov.hmrc.transitmovementsconverter.models.ConversionFormat
import uk.gov.hmrc.transitmovementsconverter.models.errors.ConversionError
import uk.gov.hmrc.transitmovementsconverter.models.errors.PresentationError
import uk.gov.hmrc.transitmovementsconverter.services.ConverterService
import uk.gov.hmrc.transitmovementsconverter.stream.StreamingParsers
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.MessageType as V2MessageType
import uk.gov.hmrc.transitmovementsconverter.v3_0.models.MessageType
import uk.gov.hmrc.transitmovementsconverter.v2_1.services.V2ConverterService
import uk.gov.hmrc.transitmovementsconverter.v3_0.services.V3ConverterService

import javax.inject.Inject
import javax.inject.Singleton
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

@Singleton()
class MessageConversionController @Inject() (
  cc: ControllerComponents,
  v2ConverterService: V2ConverterService,
  v3ConverterService: V3ConverterService,
  validateAcceptRefiner: ValidateAcceptRefiner,
  appConfig: AppConfig
)(implicit
  val materializer: Materializer,
  ec: ExecutionContext
) extends BackendController(cc)
    with StreamingParsers
    with ErrorTranslator
    with Logging {

  private def logError(conversionError: ConversionError): ConversionError = {
    if (appConfig.errorLogging) {
      logger.warn(s"Error converting message: $conversionError")
    }
    conversionError
  }

  def message(messageType: String): Action[Source[ByteString, ?]] = validateAcceptRefiner.async(streamFromMemory) {
    implicit request =>
      val converterService: ConverterService = request.versionHeader match {
        case APIVersionHeader.v2_1 => v2ConverterService
        case APIVersionHeader.v3_0 => v3ConverterService
      }

      val maybeConversionFormat: Option[ConversionFormat[?]] = request.versionHeader match
        case models.APIVersionHeader.v2_1 => V2MessageType.fromName(messageType)
        case models.APIVersionHeader.v3_0 => MessageType.fromName(messageType)

      val result: EitherT[Future, PresentationError, Result] =
        (request.headers.get(HeaderNames.CONTENT_TYPE), request.headers.get(HeaderNames.ACCEPT), maybeConversionFormat) match {
          case (Some(MimeTypes.XML), Some(MimeTypes.JSON), Some(conversionFormat)) =>
            converterService
              .xmlToJson(conversionFormat, request.body)
              .leftMap(logError)
              .asPresentation
              .map(Ok(_))

          case (Some(MimeTypes.JSON), Some(MimeTypes.XML), Some(conversionFormat)) =>
            converterService
              .jsonToXml(conversionFormat, request.body)
              .leftMap(logError)
              .asPresentation
              .map(Ok(_))
          case (_, _, None)                         => EitherT.leftT(PresentationError.notAcceptableError("Error locating message type"))
          case (Some(contentType), Some(accept), _) =>
            request.body.runWith(Sink.ignore)
            EitherT.leftT(
              PresentationError.unsupportedMediaTypeError(
                s"Combination of Content-Type header $contentType and Accept header $accept is not supported!"
              )
            )
          case _ =>
            request.body.runWith(Sink.ignore)
            EitherT.leftT(PresentationError.unsupportedMediaTypeError("Content-Type header or Accept header or both were not supplied"))
        }

      result.valueOr(
        presentationError => Status(presentationError.code.statusCode)(Json.toJson(presentationError))
      )
  }

}

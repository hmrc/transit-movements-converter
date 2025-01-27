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
import cats.implicits.catsStdInstancesForFuture
import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.Sink
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString
import play.api.http.HeaderNames
import play.api.http.MimeTypes
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.ControllerComponents
import play.api.mvc.Result
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController
import uk.gov.hmrc.transitmovementsconverter.models.MessageType
import uk.gov.hmrc.transitmovementsconverter.models.errors.PresentationError
import uk.gov.hmrc.transitmovementsconverter.services.ConverterService
import uk.gov.hmrc.transitmovementsconverter.stream.StreamingParsers

import javax.inject.Inject
import javax.inject.Singleton
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

@Singleton()
class MessageConversionController @Inject() (cc: ControllerComponents, converterService: ConverterService)(implicit
  val materializer: Materializer,
  ec: ExecutionContext
) extends BackendController(cc)
    with StreamingParsers
    with ErrorTranslator {

  def message(messageType: MessageType[?]): Action[Source[ByteString, ?]] = Action.async(streamFromMemory) {
    implicit request =>
      val result: EitherT[Future, PresentationError, Result] = (request.headers.get(HeaderNames.CONTENT_TYPE), request.headers.get(HeaderNames.ACCEPT)) match {
        case (Some(MimeTypes.XML), Some(MimeTypes.JSON)) =>
          converterService
            .xmlToJson(messageType, request.body)
            .asPresentation
            .map(Ok(_))
        case (Some(MimeTypes.JSON), Some(MimeTypes.XML)) =>
          converterService
            .jsonToXml(messageType, request.body)
            .asPresentation
            .map(Ok(_))
        case (Some(contentType), Some(accept)) =>
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

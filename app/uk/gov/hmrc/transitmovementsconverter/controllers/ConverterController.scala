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

import akka.stream.Materializer
import akka.stream.scaladsl.Sink
import akka.stream.scaladsl.Source
import akka.util.ByteString
import cats.implicits.catsStdInstancesForFuture
import play.api.http.HeaderNames
import play.api.http.MimeTypes
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.ControllerComponents
import play.api.mvc.Request
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController
import uk.gov.hmrc.transitmovementsconverter.controllers.stream.StreamingParsers
import uk.gov.hmrc.transitmovementsconverter.models.MessageType
import uk.gov.hmrc.transitmovementsconverter.models.errors.PresentationError
import uk.gov.hmrc.transitmovementsconverter.services.XmlToJsonService

import javax.inject.Inject
import javax.inject.Singleton
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

@Singleton()
class ConverterController @Inject() (cc: ControllerComponents, xmlToJsonService: XmlToJsonService)(implicit
  val materializer: Materializer,
  ec: ExecutionContext
) extends BackendController(cc)
    with StreamingParsers
    with ErrorTranslator {

  def convert(messageType: MessageType[_]): Action[Source[ByteString, _]] = route {
    case (Some(MimeTypes.XML), Some(MimeTypes.JSON)) => xmlToJson(messageType)
  }

  def xmlToJson(messageType: MessageType[_]): Action[Source[ByteString, _]] = Action.async(streamFromMemory) {
    implicit request =>
      if (request.accepts(MimeTypes.JSON)) {
        xmlToJsonService
          .convert(messageType, request.body)
          .asPresentation
          .map(Ok(_))
          .valueOr(
            error => Status(error.code.statusCode)(Json.toJson(error))
          )
      } else if (request.accepts(MimeTypes.XML)) Future.successful(Ok.chunked(request.body))
      else Future.successful(BadRequest(Json.toJson(PresentationError.badRequestError("An invalid Accept header was supplied."))))
  }

  private def route(routes: PartialFunction[(Option[String], Option[String]), Action[_]])(implicit materializer: Materializer): Action[Source[ByteString, _]] =
    Action.async(streamFromMemory) {
      (request: Request[Source[ByteString, _]]) =>
        routes
          .lift((request.headers.get(HeaderNames.CONTENT_TYPE), request.headers.get(HeaderNames.ACCEPT)))
          .map(
            action => action(request).run(request.body)
          )
          .getOrElse {
            // To avoid a memory leak, we need to ensure we run the request stream and ignore it.
            request.body.to(Sink.ignore).run()
            val contentType = request.headers.get(HeaderNames.CONTENT_TYPE).getOrElse("[not supplied]")
            val accept      = request.headers.get(HeaderNames.ACCEPT).getOrElse("[not supplied]")
            Future.successful(
              UnsupportedMediaType(
                Json.toJson(
                  PresentationError.unsupportedMediaTypeError(
                    s"Combination of content-type header $contentType and accept header $accept is not supported!"
                  )
                )
              )
            )
          }
    }

}

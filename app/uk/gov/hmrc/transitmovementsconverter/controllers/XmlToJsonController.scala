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
import cats.data.EitherT
import cats.implicits.catsStdInstancesForFuture
import play.api.http.HeaderNames
import play.api.http.MimeTypes
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.ControllerComponents
import play.api.mvc.Result
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
class XmlToJsonController @Inject() (cc: ControllerComponents, xmlToJsonService: XmlToJsonService)(implicit
  val materializer: Materializer,
  ec: ExecutionContext
) extends BackendController(cc)
    with StreamingParsers
    with ErrorTranslator {

  def convert(messageType: MessageType[_]): Action[Source[ByteString, _]] = Action.async(streamFromMemory) {
    implicit request =>
      val result: EitherT[Future, PresentationError, Result] = request.headers.get(HeaderNames.CONTENT_TYPE) match {
        case Some(MimeTypes.XML) =>
          xmlToJsonService
            .convert(messageType, request.body)
            .asPresentation
            .map(Ok(_))
        // case Some(MimeTypes.JSON) => TODO: Json
        case Some(t) =>
          request.body.runWith(Sink.ignore)
          EitherT.leftT(PresentationError.badRequestError(s"Content-Type $t was provided, but is not valid for this service"))
        case None =>
          request.body.runWith(Sink.ignore)
          EitherT.leftT(PresentationError.badRequestError("No Content-Type was provided"))
      }

      result.valueOr(
        presentationError => Status(presentationError.code.statusCode)(Json.toJson(presentationError))
      )
  }

}

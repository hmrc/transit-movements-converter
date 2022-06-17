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
import akka.stream.scaladsl.Source
import akka.util.ByteString
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController
import play.api.mvc.Action
import play.api.mvc.ControllerComponents
import uk.gov.hmrc.transitmovementsconverter.controllers.service.XmlToJsonService
import uk.gov.hmrc.transitmovementsconverter.controllers.stream.StreamingParsers

import javax.inject.Inject
import javax.inject.Singleton

@Singleton()
class XmlToJsonController @Inject() (cc: ControllerComponents, xmlToJsonService: XmlToJsonService)(implicit val materializer: Materializer)
    extends BackendController(cc)
    with StreamingParsers {

  def convert(): Action[Source[ByteString, _]] = Action.async(streamFromMemory) {
    implicit request =>
      for {
        jsonVal <- xmlToJsonService.convert(request.body)
      } yield Ok(jsonVal)
  }

}

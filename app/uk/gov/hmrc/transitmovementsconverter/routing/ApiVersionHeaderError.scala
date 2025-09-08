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

object ApiVersionHeaderError {

  val MessageFieldName = "message"
  val CodeFieldName    = "code"

  def notAcceptableError(message: String): ApiVersionHeaderError =
    StandardError(message, ErrorCode.NotAcceptable)

  def unsupportedMediaTypeError(message: String): ApiVersionHeaderError =
    StandardError(message, ErrorCode.UnsupportedMediaType)

}

sealed abstract class ApiVersionHeaderError extends Product with Serializable {
  def message: String
  def code: ErrorCode
}

case class StandardError(message: String, code: ErrorCode) extends ApiVersionHeaderError

object VersionHeaderErrorFormats {
  import play.api.libs.json.*

  implicit val errorCodeWrites: Writes[ErrorCode] = Writes {
    errorCode => JsString(errorCode.code)
  }

  implicit val standardErrorWrites: Writes[StandardError] = Json.writes[StandardError]

  implicit val versionHeaderErrorWrites: Writes[ApiVersionHeaderError] = Writes {
    case e: StandardError => standardErrorWrites.writes(e)
  }

}

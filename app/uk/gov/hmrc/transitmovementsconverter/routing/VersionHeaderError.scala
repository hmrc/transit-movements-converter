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

import play.api.http.Status.NOT_ACCEPTABLE
import play.api.libs.json.JsError
import play.api.libs.json.JsString
import play.api.libs.json.JsSuccess
import play.api.libs.json.Reads
import play.api.libs.json.Writes

object VersionHeaderError {

  val MessageFieldName = "message"
  val CodeFieldName    = "code"

  def notAcceptableError(message: String): VersionHeaderError =
    StandardError(message, ErrorCode.NotAcceptable)

}

sealed abstract class VersionHeaderError extends Product with Serializable {
  def message: String
  def code: ErrorCode
}

case class StandardError(message: String, code: ErrorCode) extends VersionHeaderError

sealed abstract class ErrorCode(val code: String, val statusCode: Int) extends Product with Serializable

object ErrorCode {
  case object NotAcceptable extends ErrorCode("NOT_ACCEPTABLE", NOT_ACCEPTABLE)

  lazy val errorCodes: Seq[ErrorCode] = Seq(
    NotAcceptable
  )

  implicit val errorCodeWrites: Writes[ErrorCode] = Writes {
    errorCode => JsString(errorCode.code)
  }

  implicit val errorCodeReads: Reads[ErrorCode] = Reads {
    errorCode =>
      errorCodes
        .find(
          value => value.code == errorCode.asInstanceOf[JsString].value
        )
        .map(
          errorCode => JsSuccess(errorCode)
        )
        .getOrElse(JsError())
  }
}

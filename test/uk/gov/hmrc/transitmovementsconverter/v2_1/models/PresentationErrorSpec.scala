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

package uk.gov.hmrc.transitmovementsconverter.v2_1.models

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.mockito.MockitoSugar
import play.api.libs.json.Json
import uk.gov.hmrc.http.UpstreamErrorResponse
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.errors.PresentationError
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.errors.UpstreamServiceError

class PresentationErrorSpec extends AnyFreeSpec with Matchers with MockitoSugar {

  "Test Json is as expected" - {
    def testStandard(function: String => PresentationError, message: String, code: String) = {
      val sut    = function(message)
      val result = Json.toJson(sut)

      result mustBe Json.obj("message" -> message, "code" -> code)
    }

    "for UnsupportedMediaType" in testStandard(PresentationError.unsupportedMediaTypeError, "unsupported media type", "UNSUPPORTED_MEDIA_TYPE")

    "for BadRequest" in testStandard(PresentationError.badRequestError, "bad request", "BAD_REQUEST")

    Seq(Some(new IllegalStateException("message")), None).foreach {
      exception =>
        val textFragment = exception
          .map(
            _ => "contains"
          )
          .getOrElse("does not contain")
        s"for an unexpected error that $textFragment a Throwable" in {
          val exception = new IllegalStateException("message")
          val sut       = PresentationError.internalServiceError(cause = Some(exception))
          val json      = Json.toJson(sut)

          json mustBe Json.obj("code" -> "INTERNAL_SERVER_ERROR", "message" -> "Internal server error")
        }
    }

    "for an upstream error" in {

      val upstreamErrorResponse = UpstreamErrorResponse("error", 500)
      val sut                   = UpstreamServiceError.causedBy(upstreamErrorResponse)
      val json                  = Json.toJson(sut)

      json mustBe Json.obj("code" -> "INTERNAL_SERVER_ERROR", "message" -> "Internal server error")
    }
  }

}

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

package uk.gov.hmrc.transitmovementsconverter.v2_1.models.errors

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.http.Status._ // Import status codes directly
import play.api.libs.json.Json
import play.api.libs.json.JsString

// Import all ErrorCode objects directly into scope
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.errors.ErrorCode._

class ErrorCodeSpec extends AnyWordSpec with Matchers {

  val allExpectedErrorCodes = Seq(
    BadRequest,
    NotFound,
    Forbidden,
    InternalServerError,
    GatewayTimeout,
    SchemaValidation,
    EntityTooLarge,
    UnsupportedMediaType,
    Unauthorized
  )

  def testErrorCode(errorCode: ErrorCode, expectedCode: String, expectedStatus: Int): Unit =
    s"$expectedCode" should {
      s"have code '$expectedCode'" in {
        errorCode.code shouldBe expectedCode
      }
      s"have statusCode $expectedStatus" in {
        errorCode.statusCode shouldBe expectedStatus
      }
    }

  "ErrorCode" when {

    testErrorCode(BadRequest, "BAD_REQUEST", BAD_REQUEST)
    testErrorCode(NotFound, "NOT_FOUND", NOT_FOUND)
    testErrorCode(Forbidden, "FORBIDDEN", FORBIDDEN)
    testErrorCode(InternalServerError, "INTERNAL_SERVER_ERROR", INTERNAL_SERVER_ERROR)
    testErrorCode(GatewayTimeout, "GATEWAY_TIMEOUT", GATEWAY_TIMEOUT)
    testErrorCode(SchemaValidation, "SCHEMA_VALIDATION", BAD_REQUEST) // Note: Uses BAD_REQUEST status
    testErrorCode(EntityTooLarge, "REQUEST_ENTITY_TOO_LARGE", REQUEST_ENTITY_TOO_LARGE)
    testErrorCode(UnsupportedMediaType, "UNSUPPORTED_MEDIA_TYPE", UNSUPPORTED_MEDIA_TYPE)
    testErrorCode(Unauthorized, "UNAUTHORIZED", UNAUTHORIZED)

    "accessing the errorCodes sequence" should {
      "contain all defined ErrorCode instances" in {
        ErrorCode.errorCodes should contain theSameElementsAs allExpectedErrorCodes
      }

      "have the correct size" in {
        ErrorCode.errorCodes should have size allExpectedErrorCodes.size
        ErrorCode.errorCodes.size shouldBe 9
      }
    }
  }
}

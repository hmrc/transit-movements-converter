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

import play.api.libs.json.Json
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalacheck.Gen
import org.scalatestplus.mockito.MockitoSugar

class VersionHeaderErrorSpec extends AnyFreeSpec with Matchers with MockitoSugar {

  "for not acceptable error" in {
    val gen                = Gen.alphaNumStr.sample.getOrElse("something")
    val notAcceptableError = VersionHeaderError.notAcceptableError(gen)

    val json = Json.toJson(notAcceptableError)

    json mustBe Json.obj(
      "code"    -> "NOT_ACCEPTABLE",
      "message" -> gen
    )
  }

}

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

class MessageTypeSpec extends AnyFreeSpec with Matchers {

  "fromName" - {

    MessageType.values.foreach {
      messageType =>
        s"for a valid value, test we can get ${messageType.name}" in {
          MessageType.fromName(messageType.name) mustBe Some(messageType)
        }
    }

    "for an invalid value, test we get None back" in {
      MessageType.fromName("asdfghjh") mustBe None
    }
  }

}

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

package uk.gov.hmrc.transitmovementsconverter.v3_0.models

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.EitherValues
import play.api.mvc.PathBindable
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.Binders.*
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.*
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.MessageType

class BindersSpec extends AnyWordSpec with Matchers with EitherValues {

  val messageTypeBinder: PathBindable[MessageType[?]] = implicitly[PathBindable[MessageType[?]]]

  "MessageType PathBindable" should {

    "fail to bind a message type string with incorrect case" in {
      val key           = "messageType"
      val value         = "ie004"
      val expectedError = "Error locating message type"

      val result = messageTypeBinder.bind(key, value)

      result.left.value shouldBe expectedError
    }

    "fail to bind an invalid message type string" in {
      val key           = "messageType"
      val value         = "INVALID99"
      val expectedError = "Error locating message type"

      val result = messageTypeBinder.bind(key, value)

      result.left.value shouldBe expectedError
    }

    "fail to bind an empty string" in {
      val key           = "messageType"
      val value         = ""
      val expectedError = "Error locating message type"

      val result = messageTypeBinder.bind(key, value)

      result.left.value shouldBe expectedError
    }

    "successfully unbind a MessageType to its string name" in {
      val key                         = "messageType"
      val messageType: MessageType[?] = MessageType.IE015
      val expectedString              = "IE015"

      val result = messageTypeBinder.unbind(key, messageType)

      result shouldBe expectedString
    }

    "successfully unbind another MessageType to its string name" in {
      val key                         = "messageType"
      val messageType: MessageType[?] = MessageType.IE029
      val expectedString              = "IE029"

      val result = messageTypeBinder.unbind(key, messageType)

      result shouldBe expectedString
    }
  }
}

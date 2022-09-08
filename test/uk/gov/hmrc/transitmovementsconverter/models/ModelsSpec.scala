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

package uk.gov.hmrc.transitmovementsconverter.models

import generated._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import play.api.libs.json.OFormat
import scalaxb.XMLFormat
import uk.gov.hmrc.transitmovementsconverter.base.StreamTestHelpers
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.models.testobjects._

class ModelsSpec extends AnyFreeSpec with ScalaFutures with Matchers with TestActorSystem with StreamTestHelpers {

  "cc004cFormats" - new TestType[CC004CType](CC004CTestMessageType, Models.cc004cFormats)
  "cc009cFormats" - new TestType[CC009CType](CC009CTestMessageType, Models.cc009cFormats)
  "cc013cFormats" - new TestType[CC013CType](CC013CTestMessageType, Models.cc013cFormats)
  "cc014cFormats" - new TestType[CC014CType](CC014CTestMessageType, Models.cc014cFormats)
  "cc015cFormats" - new TestType[CC015CType](CC015CTestMessageType, Models.cc015cFormats)
  "cc019cFormats" - new TestType[CC019CType](CC019CTestMessageType, Models.cc019cFormats)
  "cc028cFormats" - new TestType[CC028CType](CC028CTestMessageType, Models.cc028cFormats)
  "cc029cFormats" - new TestType[CC029CType](CC029CTestMessageType, Models.cc029cFormats)
  "cc035cFormats" - new TestType[CC035CType](CC035CTestMessageType, Models.cc035cFormats)
  "cc045cFormats" - new TestType[CC045CType](CC045CTestMessageType, Models.cc045cFormats)
  "cc051cFormats" - new TestType[CC051CType](CC051CTestMessageType, Models.cc051cFormats)
  "cc170cFormats" - new TestType[CC170CType](CC170CTestMessageType, Models.cc170cFormats)

  case class TestType[T](testObject: TestMessageType, formats: OFormat[T])(implicit xmlFormat: XMLFormat[T]) extends XMLProtocol {
    lazy val model: T = scalaxb.fromXML[T](testObject.xml1)

    "converting model to Json" in {
      formats.writes(model) mustBe testObject.json1
    }

    "converting Json to model" in {
      formats.reads(testObject.json1).get mustBe model
    }
  }

}

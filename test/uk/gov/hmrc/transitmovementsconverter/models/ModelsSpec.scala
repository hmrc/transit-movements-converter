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
import uk.gov.hmrc.transitmovementsconverter.base.StreamTestHelpers
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem

class ModelsSpec extends AnyFreeSpec with ScalaFutures with Matchers with TestActorSystem with StreamTestHelpers {

  "cc015cFormats" - new XMLProtocol {
    val model: CC015CType = scalaxb.fromXML[CC015CType](TestObjects.CC015C.xml1)

    "converting model to Json" in {
      Models.cc015cFormats.writes(model) mustBe TestObjects.CC015C.json1
    }

    "converting Json to model" in {
      Models.cc015cFormats.reads(TestObjects.CC015C.json1).get mustBe model
    }

  }

}

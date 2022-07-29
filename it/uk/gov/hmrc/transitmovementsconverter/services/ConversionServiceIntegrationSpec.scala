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

package uk.gov.hmrc.transitmovementsconverter.services

import org.scalatest.concurrent.IntegrationPatience
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import uk.gov.hmrc.transitmovementsconverter.itbase.StreamTestHelpers
import uk.gov.hmrc.transitmovementsconverter.itbase.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.itbase.TestObjects
import uk.gov.hmrc.transitmovementsconverter.models.MessageType

import scala.concurrent.ExecutionContext.Implicits.global
import scala.xml.Elem
import scala.xml.Utility.trim

class ConversionServiceIntegrationSpec
    extends AnyFreeSpec
    with Matchers
    with ScalaFutures
    with IntegrationPatience
    with TestActorSystem
    with StreamTestHelpers {

  val service = new ConverterServiceImpl

  "converting XML to Json" - {

    "converting CC015C XML to Json is as expected" in {
      val result = service.xmlToJson(MessageType.IE015, createStream(TestObjects.CC015C.xml1))
      whenReady(result.value) {
        either =>
          either.right.get mustBe TestObjects.CC015C.json1
      }
    }

  }

  "converting Json to XML" - {

    "converting CC015C Json to XML is as expected" in {
      val result = service.jsonToXml(MessageType.IE015, createStream(TestObjects.CC015C.json1))
      whenReady(result.value) {
        case Right(x: Elem) => trim(x) mustBe trim(TestObjects.CC015C.xml1)
        case x              => fail(s"$x is not what was expected")
      }
    }

  }

}

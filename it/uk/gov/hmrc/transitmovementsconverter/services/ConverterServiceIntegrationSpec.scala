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
import uk.gov.hmrc.transitmovementsconverter.models.errors.ConversionError

import scala.concurrent.ExecutionContext.Implicits.global
import scala.xml.Elem
import scala.xml.Utility.trim

class ConverterServiceIntegrationSpec extends AnyFreeSpec with Matchers with ScalaFutures with IntegrationPatience with TestActorSystem with StreamTestHelpers {

  val service = new ConverterServiceImpl

  "converting XML to Json" - {

    "converting CC015C XML to Json is as expected" in {
      val result = service.xmlToJson(MessageType.IE015, createStream(TestObjects.CC015C.xml1))
      whenReady(result.value) {
        either =>
          either.right.get mustBe TestObjects.CC015C.json1
      }
    }

    "converting invalid CC015C XML to Json returns an error" in {
      val result = service.xmlToJson(MessageType.IE015, createStream(TestObjects.CC015C.invalidXml1))
      whenReady(result.value) {
        either => either.left.get mustBe a[ConversionError.XMLParsingError]
      }
    }

    "converting CC019C XML to Json is as expected" in {
      val result = service.xmlToJson(MessageType.IE019, createStream(TestObjects.CC019C.xmlValid))
      whenReady(result.value) {
        either =>
          either.right.get mustBe TestObjects.CC019C.jsonValid
      }
    }

    "converting invalid CC019C XML to Json returns an error" in {
      val result = service.xmlToJson(MessageType.IE019, createStream(TestObjects.CC019C.invalidXml))
      whenReady(result.value) {
        either => either.left.get mustBe a[ConversionError.XMLParsingError]
      }
    }

    "converting CC035C XML to Json is as expected" in {
      val result = service.xmlToJson(MessageType.IE035, createStream(TestObjects.CC035C.xmlValid))
      whenReady(result.value) {
        either => either.right.get mustBe TestObjects.CC035C.jsonValid
      }
    }

    "converting invalid CC035C XML to Json returns an error" in {
      val result = service.xmlToJson(MessageType.IE035, createStream(TestObjects.CC035C.invalidXml))
      whenReady(result.value) {
        either => either.left.get mustBe a[ConversionError.XMLParsingError]
      }
    }

    "converting CC044C XML to Json is as expected" in {
      val result = service.xmlToJson(MessageType.IE044, createStream(TestObjects.CC044C.xmlValid))
      whenReady(result.value) {
        either =>
          either.right.get mustBe TestObjects.CC044C.jsonValid
      }
    }

    "converting invalid CC044C XML to Json returns an error" in {
      val result = service.xmlToJson(MessageType.IE044, createStream(TestObjects.CC044C.invalidXml))
      whenReady(result.value) {
        either => either.left.get mustBe a[ConversionError.XMLParsingError]
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

    "converting invalid CC015C Json to XML returns an error" in {
      val result = service.jsonToXml(MessageType.IE015, createStream(TestObjects.CC015C.invalidJson1))
      whenReady(result.value) {
        either =>
          either.left.get mustBe a[ConversionError.JsonParsingError]
      }
    }

    "converting CC019C Json to XML is as expected" in {
      val result = service.jsonToXml(MessageType.IE019, createStream(TestObjects.CC019C.jsonValid))
      whenReady(result.value) {
        case Right(x: Elem) => trim(x) mustBe trim(TestObjects.CC019C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }
    }

    "converting invalid CC019C Json to XML returns an error" in {
      val result = service.jsonToXml(MessageType.IE019, createStream(TestObjects.CC019C.invalidJson))
      whenReady(result.value) {
        either =>
          either.left.get mustBe a[ConversionError.JsonParsingError]
      }
    }

    "converting CC035C Json to XML is as expected" in {
      val result = service.jsonToXml(MessageType.IE035, createStream(TestObjects.CC035C.jsonValid))
      whenReady(result.value) {
        case Right(x: Elem) => trim(x) mustBe trim(TestObjects.CC035C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }
    }

    "converting invalid CC035C Json to XML returns an error" in {
      val result = service.jsonToXml(MessageType.IE035, createStream(TestObjects.CC035C.invalidJson))
      whenReady(result.value) {
        either =>
          either.left.get mustBe a[ConversionError.JsonParsingError]
      }
    }

    "converting CC044C Json to XML is as expected" in {
      val result = service.jsonToXml(MessageType.IE044, createStream(TestObjects.CC044C.jsonValid))
      whenReady(result.value) {
        case Right(x: Elem) => trim(x) mustBe trim(TestObjects.CC044C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }
    }

    "converting invalid CC044C Json to XML returns an error" in {
      val result = service.jsonToXml(MessageType.IE044, createStream(TestObjects.CC044C.invalidJson))
      whenReady(result.value) {
        either =>
          either.left.get mustBe a[ConversionError.JsonParsingError]
      }
    }

  }

}

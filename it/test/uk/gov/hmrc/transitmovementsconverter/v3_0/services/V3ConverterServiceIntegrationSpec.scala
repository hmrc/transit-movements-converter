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

package uk.gov.hmrc.transitmovementsconverter.v3_0.services

import cats.implicits.catsSyntaxEitherId
import org.scalatest.Assertion
import org.scalatest.EitherValues.convertLeftProjectionToValuable
import org.scalatest.concurrent.IntegrationPatience
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import uk.gov.hmrc.transitmovementsconverter.itbase.StreamTestHelpers
import uk.gov.hmrc.transitmovementsconverter.itbase.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.itbase.TestObjects
import uk.gov.hmrc.transitmovementsconverter.models.errors.ConversionError
import uk.gov.hmrc.transitmovementsconverter.v3_0.models.MessageType

import scala.concurrent.ExecutionContext.Implicits.global
import scala.xml.Elem
import scala.xml.Utility.trim

class V3ConverterServiceIntegrationSpec
    extends AnyFreeSpec
    with Matchers
    with ScalaFutures
    with IntegrationPatience
    with TestActorSystem
    with StreamTestHelpers {

  val service = new V3ConverterService

  "converting XML to Json" - {

    "converting CC015C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE015, createStream(TestObjects.CC015C.xml1)).value) {
        _ mustBe TestObjects.CC015C.json1.asRight
      }

    "converting invalid CC015C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE015, createStream(TestObjects.CC015C.invalidXml1)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }

    "converting CC019C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE019, createStream(TestObjects.CC019C.xmlValid)).value) {
        _ mustBe TestObjects.CC019C.jsonValid.asRight
      }

    "converting invalid CC019C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE019, createStream(TestObjects.CC019C.invalidXml)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }

    "converting CC022C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE022, createStream(TestObjects.CC022C.xmlValid)).value) {
        _ mustBe TestObjects.CC022C.jsonValid.asRight
      }

    "converting invalid CC022C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE022, createStream(TestObjects.CC022C.xmlInvalid)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }

    "converting CC025C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE025, createStream(TestObjects.CC025C.xmlValid)).value) {
        _ mustBe TestObjects.CC025C.jsonValid.asRight
      }

    "converting invalid CC025C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE025, createStream(TestObjects.CC025C.xmlInvalid)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }

    "converting CC035C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE035, createStream(TestObjects.CC035C.xmlValid)).value) {
        _ mustBe TestObjects.CC035C.jsonValid.asRight
      }

    "converting invalid CC035C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE035, createStream(TestObjects.CC035C.xmlInvalid)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }

    "converting CC043C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE043, createStream(TestObjects.CC043C.xmlValid)).value) {
        _ mustBe TestObjects.CC043C.jsonValid.asRight
      }

    "converting invalid CC043C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE043, createStream(TestObjects.CC043C.xmlInvalid)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }

    "converting CC044C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE044, createStream(TestObjects.CC044C.xmlValid)).value) {
        _ mustBe TestObjects.CC044C.jsonValid.asRight
      }

    "converting invalid CC044C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE044, createStream(TestObjects.CC044C.xmlInvalid)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }

    "converting CC045C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE045, createStream(TestObjects.CC045C.xmlValid)).value) {
        _ mustBe TestObjects.CC045C.jsonValid.asRight
      }

    "converting invalid CC045C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE045, createStream(TestObjects.CC045C.xmlInvalid)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }

    "converting CC057C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE057, createStream(TestObjects.CC057C.xmlValid)).value) {
        _ mustBe TestObjects.CC057C.jsonValid.asRight
      }

    "converting invalid CC057C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE057, createStream(TestObjects.CC057C.xmlInvalid)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }

    "converting CC140C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE140, createStream(TestObjects.CC140C.xmlValid)).value) {
        _ mustBe TestObjects.CC140C.jsonValid.asRight
      }

    "converting invalid CC140C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE140, createStream(TestObjects.CC140C.xmlInvalid)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }

    "converting CC141C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE141, createStream(TestObjects.CC141C.xmlValid)).value) {
        _ mustBe TestObjects.CC141C.jsonValid.asRight
      }

    "converting invalid CC141C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE141, createStream(TestObjects.CC141C.xmlInvalid)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }

    "converting CC182C XML to Json is as expected" in
      whenReady(service.xmlToJson(MessageType.IE182, createStream(TestObjects.CC182C.xmlValid)).value) {
        _ mustBe TestObjects.CC182C.jsonValid.asRight
      }

    "converting invalid CC182C XML to Json returns an error" in
      whenReady(service.xmlToJson(MessageType.IE182, createStream(TestObjects.CC182C.xmlInvalid)).value) {
        _.left.value mustBe a[ConversionError.XMLParsingError]
      }
  }

  "converting Json to XML" - {

    "converting CC015C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE015, createStream(TestObjects.CC015C.json1)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC015C.xml1)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC015C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE015, createStream(TestObjects.CC015C.invalidJson1)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }

    "converting CC019C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE019, createStream(TestObjects.CC019C.jsonValid)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC019C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC019C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE019, createStream(TestObjects.CC019C.invalidJson)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }

    "converting CC022C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE022, createStream(TestObjects.CC022C.jsonValid)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC022C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC022C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE022, createStream(TestObjects.CC022C.jsonInvalid)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }

    "converting CC025C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE025, createStream(TestObjects.CC025C.jsonValid)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC025C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC025C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE025, createStream(TestObjects.CC025C.jsonInvalid)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }

    "converting CC035C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE035, createStream(TestObjects.CC035C.jsonValid)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC035C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC035C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE035, createStream(TestObjects.CC035C.jsonInvalid)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }

    "converting CC043C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE043, createStream(TestObjects.CC043C.jsonValid)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC043C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC043C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE043, createStream(TestObjects.CC043C.jsonInvalid)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }

    "converting CC044C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE044, createStream(TestObjects.CC044C.jsonValid)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC044C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC044C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE044, createStream(TestObjects.CC044C.jsonInvalid)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }

    "converting CC045C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE045, createStream(TestObjects.CC045C.jsonValid)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC045C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC045C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE045, createStream(TestObjects.CC045C.jsonInvalid)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }

    "converting CC057C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE057, createStream(TestObjects.CC057C.jsonValid)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC057C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC057C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE057, createStream(TestObjects.CC057C.jsonInvalid)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }

    "converting CC140C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE140, createStream(TestObjects.CC140C.jsonValid)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC140C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC140C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE140, createStream(TestObjects.CC140C.jsonInvalid)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }

    "converting CC141C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE141, createStream(TestObjects.CC141C.jsonValid)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC141C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC141C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE141, createStream(TestObjects.CC141C.jsonInvalid)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }

    "converting CC182C Json to XML is as expected" in
      whenReady(service.jsonToXml(MessageType.IE182, createStream(TestObjects.CC182C.jsonValid)).value) {
        case Right(x: Elem) => trim(x) mustEqual trim(TestObjects.CC182C.xmlValid)
        case x              => fail(s"$x is not what was expected")
      }

    "converting invalid CC182C Json to XML returns an error" in
      whenReady(service.jsonToXml(MessageType.IE182, createStream(TestObjects.CC182C.jsonInvalid)).value) {
        _.left.value mustBe a[ConversionError.JsonParsingError]
      }
  }
}

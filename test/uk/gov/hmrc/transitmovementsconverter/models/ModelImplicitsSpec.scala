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

package uk.gov.hmrc.transitmovementsconverter.models

import generated.v2_1.AesNctsP5FunctionalErrorCodes
import generated.v2_1.CountryCodesCustomsOfficeLists
import generated.v2_1.HeaderType01
import generated.v2_1.MessageTypes
import generated.v2_1.Number0
import generated.v2_1.Number1
import generated.v2_1.PhaseIDtype
import generated.v2_1.RepresentativeType01
import org.scalacheck.Gen
import org.scalatest.OptionValues
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import play.api.libs.json.*
import uk.gov.hmrc.transitmovementsconverter.base.StreamTestHelpers
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem

import java.util.GregorianCalendar
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

class ModelImplicitsSpec extends AnyFreeSpec with ScalaFutures with Matchers with OptionValues with TestActorSystem with StreamTestHelpers {

  val xmlGregorianCalendarGen = Gen.calendar
    .map {
      x =>
        val gc = new GregorianCalendar()
        gc.setTimeInMillis(x.getTimeInMillis)
        gc
    }
    .map(
      x => DatatypeFactory.newInstance().newXMLGregorianCalendar(x)
    )

  "phaseIdtypeReads implicit val" - {

    "all valid phase IDs should return their respective phase ID" - PhaseIDtype.values.foreach {
      code =>
        s"for ${code.toString}" in {
          ModelImplicits.phaseIDtypeReads.reads(JsString(code.toString)) mustBe JsSuccess(code)
        }
    }

    // In the stock XSDs, this incorrectly tried to read " NCTS5.1", not "NCTS5.1", hence this test.
    /*
    case object NCTS5u461 extends PhaseIDtype { override def toString = " NCTS5.1" }
    case object NCTS5u460 extends PhaseIDtype { override def toString = "NCTS5.0" }
     */
    /*    "A phase ID of 'NCTS5.1' should be accepted" in {
      ModelImplicits.phaseIDtypeReads.reads(JsString("NCTS5.1")) mustBe JsSuccess(NCTS5u461)
    }*/

    "an invalid code should return an error" in {
      ModelImplicits.phaseIDtypeReads.reads(JsString(Gen.listOfN(3, Gen.alphaNumChar).map(_.mkString).sample.value)) mustBe a[JsError]
    }

  }

  "phaseIdtypeWrites implicit val" - {
    "all valid Phase IDs should return their country codes as a JsString" - PhaseIDtype.values.foreach {
      code =>
        s"for ${code.toString}" in {
          ModelImplicits.phaseIDtypeWrites.writes(code) mustBe JsString(code.toString)
        }
    }

    /*    "A phase ID of 'NCTS5.1' should be written for NCTS5u461" in {
      ModelImplicits.phaseIDtypeWrites.writes(NCTS5u461) mustBe JsString("NCTS5.1")
    }*/
  }

  "countryCodeReads implicit val" - {

    "all valid country codes should return their country codes" - CountryCodesCustomsOfficeLists.values.foreach {
      code =>
        s"for ${code.toString}" in {
          ModelImplicits.countryCodeReads.reads(JsString(code.toString)) mustBe JsSuccess(code)
        }
    }

    "an invalid code should return an error" in {
      ModelImplicits.countryCodeReads.reads(JsString(Gen.listOfN(3, Gen.alphaNumChar).map(_.mkString).sample.value)) mustBe a[JsError]
    }

  }

  "countryCodeWrites implicit val" - {
    "all valid country codes should return their country codes as a JsString" - CountryCodesCustomsOfficeLists.values.foreach {
      code =>
        s"for ${code.toString}" in {
          ModelImplicits.countryCodeWrites.writes(code) mustBe JsString(code.toString)
        }
    }
  }

  "flagReads implicit val" - {

    "the string 0 should return Number0" in {
      ModelImplicits.flagReads.reads(JsString("0")) mustBe JsSuccess(Number0)
    }

    "the string 1 should return Number1" in {
      ModelImplicits.flagReads.reads(JsString("1")) mustBe JsSuccess(Number1)
    }

    "the string 2 should return JsError" in {
      ModelImplicits.flagReads.reads(JsString("2")) mustBe a[JsError]
    }

    "the number 0 should return Number0" in {
      ModelImplicits.flagReads.reads(JsNumber(0)) mustBe JsSuccess(Number0)
    }

    "the number 1 should return Number1" in {
      ModelImplicits.flagReads.reads(JsNumber(1)) mustBe JsSuccess(Number1)
    }

    "JsNull should return JsError" in {
      ModelImplicits.flagReads.reads(JsNull) mustBe a[JsError]
    }

    "JsFalse should return JsError" in {
      ModelImplicits.flagReads.reads(JsFalse) mustBe a[JsError]
    }
  }

  "flagWrites implicit val" - {
    "Number0 should return JsString(0)" in {
      ModelImplicits.flagWrites.writes(Number0) mustBe JsString("0")
    }

    "Number1 should return JsString(1)" in {
      ModelImplicits.flagWrites.writes(Number1) mustBe JsString("1")
    }
  }

  "xmlDateTimeWrites implicit val" - {

    "XMLGregorianCalendar should be converted to a string correctly" in {
      val cal = DatatypeFactory.newInstance().newXMLGregorianCalendar("2022-07-15T12:57:12")
      ModelImplicits.xmlDateTimeWrites.writes(cal) mustBe JsString("2022-07-15T12:57:12")
    }

  }

  "xmlDateTimeReads implicit val" - {

    "A valid XMLGregorianCalendar string should be converted return a JsSuccess" in {
      val time = "2022-07-15T12:58:32"
      val cal  = DatatypeFactory.newInstance().newXMLGregorianCalendar(time)
      ModelImplicits.xmlDateTimeReads.reads(JsString(time)) mustBe JsSuccess(cal)
    }

    "An invalid XMLGregorianCalendar string should be a JsError()" in {
      ModelImplicits.xmlDateTimeReads.reads(JsString("nope")) mustBe JsError()
    }

    "A non-String JSvalue should be a JsError()" in {
      ModelImplicits.xmlDateTimeReads.reads(JsTrue) mustBe JsError()
    }

  }

  "messageTypeReads implicit val" - {

    MessageTypes.values.foreach {
      messageType =>
        s"${messageType.toString} should be returned as a JsSuccess" in {
          ModelImplicits.messageTypesReads.reads(JsString(messageType.toString)) mustBe JsSuccess(messageType)
        }
    }

    "An invalid string should be returned as a JsError" in {
      ModelImplicits.messageTypesReads.reads(JsString("nope")) mustBe JsError()
    }

    "A non string should be returned as a JsError" in {
      ModelImplicits.messageTypesReads.reads(JsTrue) mustBe JsError()
    }

  }

  "messageTypeWrites implicit val" -
    MessageTypes.values.foreach {
      messageType =>
        s"${messageType.toString} should be returned as a JsString" in {
          ModelImplicits.messageTypesWrites.writes(messageType) mustBe JsString(messageType.toString)
        }
    }

  "aesNctsP5FunctionalErrorCodesReads implicit val" - {

    AesNctsP5FunctionalErrorCodes.values.foreach {
      errorCode =>
        s"${errorCode.toString} as a string should be returned as a JsSuccess" in {
          ModelImplicits.aesNctsP5FunctionalErrorCodesReads.reads(JsString(errorCode.toString)) mustBe JsSuccess(errorCode)
        }

        s"${errorCode.toString.toInt} as a number should be returned as a JsSuccess" in {
          ModelImplicits.aesNctsP5FunctionalErrorCodesReads.reads(JsNumber(errorCode.toString.toInt)) mustBe JsSuccess(errorCode)
        }
    }

    "An invalid string should be returned as a JsError" in {
      ModelImplicits.aesNctsP5FunctionalErrorCodesReads.reads(JsString("nope")) mustBe JsError()
    }

    "An invalid number should be returned as a JsError" in {
      ModelImplicits.aesNctsP5FunctionalErrorCodesReads.reads(JsNumber(23674234)) mustBe JsError()
    }

    "A non string should be returned as a JsError" in {
      ModelImplicits.aesNctsP5FunctionalErrorCodesReads.reads(JsTrue) mustBe JsError()
    }

  }

  "aesNctsP5FunctionalErrorCodesWrites implicit val" -
    AesNctsP5FunctionalErrorCodes.values.foreach {
      errorCode =>
        s"${errorCode.toString} should be returned as a JsString" in {
          ModelImplicits.aesNctsP5FunctionalErrorCodesWrites.writes(errorCode) mustBe JsString(errorCode.toString)
        }
    }

  "RepresentativeType01" - {
    "must serialise to json" in {
      val rep            = RepresentativeType01("foo", "bar")
      val expectedResult = Json.parse("""
          |{
          |  "identificationNumber" : "foo",
          |  "status" : "bar"
          |}
          |""".stripMargin)
      val result = Json.toJson(rep)(ModelImplicits.representativeType01Format)
      result mustBe expectedResult
    }

    "must deserialise from json" in {
      val json = Json.parse("""
          |{
          |  "identificationNumber" : "foo",
          |  "status" : "bar"
          |}
          |""".stripMargin)

      val expectedResult = RepresentativeType01("foo", "bar")
      val result         = json.as[RepresentativeType01](ModelImplicits.representativeType01Format)
      result mustBe expectedResult
    }
  }
}

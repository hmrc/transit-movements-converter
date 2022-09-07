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

import generated.CountryCodesCustomsOfficeLists
import generated.MessageTypes
import generated.Number0
import generated.Number1
import org.scalacheck.Gen
import org.scalatest.OptionValues
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import play.api.libs.json.JsError
import play.api.libs.json.JsFalse
import play.api.libs.json.JsNull
import play.api.libs.json.JsNumber
import play.api.libs.json.JsString
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsTrue
import uk.gov.hmrc.transitmovementsconverter.base.StreamTestHelpers
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem

import java.util.GregorianCalendar
import javax.xml.datatype.DatatypeFactory

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

  "messageTypeWrites implicit val" - {

    MessageTypes.values.foreach {
      messageType =>
        s"${messageType.toString} should be returned as a JsString" in {
          ModelImplicits.messageTypesWrites.writes(messageType) mustBe JsString(messageType.toString)
        }
    }

  }

}

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

import generated.MessageTypes
import generated.Number0
import generated.Number1
import org.scalacheck.Gen
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import play.api.libs.json.JsArray
import play.api.libs.json.JsDefined
import play.api.libs.json.JsError
import play.api.libs.json.JsFalse
import play.api.libs.json.JsNull
import play.api.libs.json.JsNumber
import play.api.libs.json.JsString
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsTrue
import play.api.libs.json.JsUndefined
import play.api.libs.json.Json
import uk.gov.hmrc.transitmovementsconverter.base.StreamTestHelpers
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.models.errors.MalformedJsonException

import java.util.GregorianCalendar
import javax.xml.datatype.DatatypeFactory
import scala.util.Failure
import scala.util.Try

class ModelHelpersSpec extends AnyFreeSpec with ScalaFutures with Matchers with TestActorSystem with StreamTestHelpers {

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

  "SeqHelpers implicit class" - {

    "toOption" - {

      "an empty Seq becomes a None" in {
        new ModelHelpers.SeqHelpers(Seq.empty[Any]).toOption mustBe None
      }

      "a non-empty Seq becomes a Some(Seq)" in {
        new ModelHelpers.SeqHelpers(Seq(1)).toOption mustBe Some(Seq(1))
      }

    }

    "entry" - {

      val name = Gen.alphaNumStr.sample.getOrElse("none")

      "an empty Seq remains empty" in {
        new ModelHelpers.SeqHelpers(Seq.empty[Int]).entry(name) mustBe Seq.empty[Int]
      }

      "a Seq with entries gets wrapped in a Seq with the name -> value pair" in {
        new ModelHelpers.SeqHelpers(Seq(1)).entry(name) mustBe Seq(name -> Json.toJsFieldJsValueWrapper(Seq(1)))
      }

    }

  }

  "OptionHelpers implicit class" - {
    "entry" - {

      val name = Gen.alphaNumStr.sample.getOrElse("none")

      "an empty Option returns an empty Seq" in {
        new ModelHelpers.OptionHelpers[Int](None).entry(name) mustBe Seq.empty[Int]
      }

      "a Seq with entries gets wrapped in a Seq with the name -> value pair" in {
        new ModelHelpers.OptionHelpers(Some(1)).entry(name) mustBe Seq(name -> Json.toJsFieldJsValueWrapper(1))
      }

    }
  }

  "JsLookupResultHelpers implicit class" - {

    "asOptionString" - {

      "if an empty string is provided, return None" in {
        new ModelHelpers.JsLookupResultHelpers(JsDefined(JsString(""))).asOptionString mustBe None
      }

      "if a non-empty string is provided, return None" in {
        new ModelHelpers.JsLookupResultHelpers(JsDefined(JsString("1234"))).asOptionString mustBe Some("1234")
      }

      "if a non-JsString value is provided, throw a MalformedJsonException" in {
        Try(new ModelHelpers.JsLookupResultHelpers(JsDefined(JsTrue)).asOptionString) match {
          case Failure(x: MalformedJsonException) => x.message mustBe s"JsValue was a ${JsTrue.getClass.getSimpleName} when expecting a string"
          case _                                  => fail("MalformedJsonException was not thrown")
        }
      }

    }

    "asOption" - {

      "if a lookup was a success, return a Some" in {
        val result = new ModelHelpers.JsLookupResultHelpers(JsDefined(JsString("1234"))).asOption[String]
        result mustBe Some("1234") // on a separate line due to the compiler complaining
      }

      "if a lookup was a failure, return a None" in {
        val result = new ModelHelpers.JsLookupResultHelpers(JsUndefined("not found")).asOption[String]
        result mustBe None
      }

    }

    "asSeq" - {

      "if a lookup was a success, return a Some" in {
        val result = new ModelHelpers.JsLookupResultHelpers(JsDefined(JsArray(Seq(JsString("1234"))))).asSeq[String]
        result mustBe Seq("1234") // on a separate line due to the compiler complaining
      }

      "if a lookup was a failure, return a None" in {
        val result = new ModelHelpers.JsLookupResultHelpers(JsUndefined("not found")).asSeq[String]
        result mustBe Seq.empty[String]
      }

    }

  }

  "JsValueHelpers implicit class" - {

    "asString" - {

      "if the value is a JsString, return the string" in {
        val string = Gen.alphaNumStr.sample.getOrElse("string")
        new ModelHelpers.JsValueHelpers(JsString(string)).asString mustBe string
      }

      "if the value is not a JsString, a MalformedJsonException must be thrown" in {
        Try(new ModelHelpers.JsValueHelpers(JsFalse).asString) match {
          case Failure(x: MalformedJsonException) => x.message mustBe s"JsValue was a ${JsFalse.getClass.getSimpleName} when expecting a string"
          case _                                  => fail("MalformedJsonException was not thrown")
        }
      }

    }

  }

  "flagReads implicit val" - {

    "the string 0 should return Number0" in {
      ModelHelpers.flagReads.reads(JsString("0")) mustBe JsSuccess(Number0)
    }

    "the string 1 should return Number1" in {
      ModelHelpers.flagReads.reads(JsString("1")) mustBe JsSuccess(Number1)
    }

    "the string 2 should return JsError" in {
      ModelHelpers.flagReads.reads(JsString("2")) mustBe a[JsError]
    }

    "the number 0 should return Number0" in {
      ModelHelpers.flagReads.reads(JsNumber(0)) mustBe JsSuccess(Number0)
    }

    "the number 1 should return Number1" in {
      ModelHelpers.flagReads.reads(JsNumber(1)) mustBe JsSuccess(Number1)
    }

    "JsNull should return JsError" in {
      ModelHelpers.flagReads.reads(JsNull) mustBe a[JsError]
    }

    "JsFalse should return JsError" in {
      ModelHelpers.flagReads.reads(JsFalse) mustBe a[JsError]
    }
  }

  "flagWrites implicit val" - {
    "Number0 should return JsString(0)" in {
      ModelHelpers.flagWrites.writes(Number0) mustBe JsString("0")
    }

    "Number1 should return JsString(1)" in {
      ModelHelpers.flagWrites.writes(Number1) mustBe JsString("1")
    }
  }

  "xmlDateTimeWrites implicit val" - {

    "XMLGregorianCalendar should be converted to a string correctly" in {
      val cal = DatatypeFactory.newInstance().newXMLGregorianCalendar("2022-07-15T12:57:12")
      ModelHelpers.xmlDateTimeWrites.writes(cal) mustBe JsString("2022-07-15T12:57:12")
    }

  }

  "xmlDateTimeReads implicit val" - {

    "A valid XMLGregorianCalendar string should be converted return a JsSuccess" in {
      val time = "2022-07-15T12:58:32"
      val cal  = DatatypeFactory.newInstance().newXMLGregorianCalendar(time)
      ModelHelpers.xmlDateTimeReads.reads(JsString(time)) mustBe JsSuccess(cal)
    }

    "An invalid XMLGregorianCalendar string should be a JsError()" in {
      ModelHelpers.xmlDateTimeReads.reads(JsString("nope")) mustBe JsError()
    }

    "A non-String JSvalue should be a JsError()" in {
      ModelHelpers.xmlDateTimeReads.reads(JsTrue) mustBe JsError()
    }

  }

  "messageTypeReads implicit val" - {

    MessageTypes.values.foreach {
      messageType =>
        s"${messageType.toString} should be returned as a JsSuccess" in {
          ModelHelpers.messageTypesReads.reads(JsString(messageType.toString)) mustBe JsSuccess(messageType)
        }
    }

    "An invalid string should be returned as a JsError" in {
      ModelHelpers.messageTypesReads.reads(JsString("nope")) mustBe JsError()
    }

    "A non string should be returned as a JsError" in {
      ModelHelpers.messageTypesReads.reads(JsTrue) mustBe JsError()
    }

  }

  "messageTypeWrites implicit val" - {

    MessageTypes.values.foreach {
      messageType =>
        s"${messageType.toString} should be returned as a JsString" in {
          ModelHelpers.messageTypesWrites.writes(messageType) mustBe JsString(messageType.toString)
        }
    }

  }

}

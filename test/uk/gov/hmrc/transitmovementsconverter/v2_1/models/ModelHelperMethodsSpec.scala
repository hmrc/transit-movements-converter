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

import org.scalacheck.Gen
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import play.api.libs.json.JsArray
import play.api.libs.json.JsDefined
import play.api.libs.json.JsFalse
import play.api.libs.json.JsString
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsTrue
import play.api.libs.json.JsUndefined
import play.api.libs.json.Json
import uk.gov.hmrc.transitmovementsconverter.base.StreamTestHelpers
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.v2_1.models.errors.MalformedJsonException

import scala.util.Failure
import scala.util.Try

class ModelHelperMethodsSpec
    extends AnyFreeSpec
    with ScalaFutures
    with Matchers
    with TestActorSystem
    with StreamTestHelpers
    with ScalaCheckDrivenPropertyChecks {

  case class HelperWithTypeValue(typeValue: String, t: String)

  case class HelperWithoutTypeValue(notTypeValue: String, t: String)

  object Formats {
    val withTypeValueFormat    = Json.format[HelperWithTypeValue]
    val withoutTypeValueFormat = Json.format[HelperWithoutTypeValue]
  }

  "formatWithTypeAdjusted helper method" - {

    "readWithTypeAdjusted" - {

      "where typeValue exists as a field, ensure it parses when the field is actually 'type'" in forAll(Gen.alphaNumStr, Gen.alphaNumStr) {
        (typeValue, t) =>
          ModelHelperMethods
            .formatWithTypeAdjusted(Formats.withTypeValueFormat)
            .reads(
              Json.obj(
                "type" -> typeValue,
                "t"    -> t
              )
            ) mustBe JsSuccess(HelperWithTypeValue(typeValue, t))
      }

      "where typeValue doesn't exist as a field, ensure it parses normally" in forAll(Gen.alphaNumStr, Gen.alphaNumStr) {
        (typeValue, t) =>
          ModelHelperMethods
            .formatWithTypeAdjusted(Formats.withoutTypeValueFormat)
            .reads(
              Json.obj(
                "notTypeValue" -> typeValue,
                "t"            -> t
              )
            ) mustBe JsSuccess(HelperWithoutTypeValue(typeValue, t))
      }

    }

    "writeWithTypeAdjusted" - {
      "where typeValue exists as a field, ensure it parses when the field is actually 'type'" in forAll(Gen.alphaNumStr, Gen.alphaNumStr) {
        (typeValue, t) =>
          ModelHelperMethods
            .formatWithTypeAdjusted(Formats.withTypeValueFormat)
            .writes(HelperWithTypeValue(typeValue, t)) mustBe Json.obj(
            "type" -> typeValue,
            "t"    -> t
          )
      }

      "where typeValue doesn't exist as a field, ensure it parses normally" in forAll(Gen.alphaNumStr, Gen.alphaNumStr) {
        (typeValue, t) =>
          ModelHelperMethods
            .formatWithTypeAdjusted(Formats.withoutTypeValueFormat)
            .writes(HelperWithoutTypeValue(typeValue, t)) mustBe Json.obj(
            "notTypeValue" -> typeValue,
            "t"            -> t
          )
      }
    }

  }

  "SeqHelpers implicit class" - {

    "toOption" - {

      "an empty Seq becomes a None" in {
        new ModelHelperMethods.SeqHelpers(Seq.empty[Any]).toOption mustBe None
      }

      "a non-empty Seq becomes a Some(Seq)" in {
        new ModelHelperMethods.SeqHelpers(Seq(1)).toOption mustBe Some(Seq(1))
      }

    }

    "entry" - {

      val name = Gen.alphaNumStr.sample.getOrElse("none")

      "an empty Seq remains empty" in {
        new ModelHelperMethods.SeqHelpers(Seq.empty[Int]).entry(name) mustBe Seq.empty[Int]
      }

      "a Seq with entries gets wrapped in a Seq with the name -> value pair" in {
        new ModelHelperMethods.SeqHelpers(Seq(1)).entry(name) mustBe Seq(name -> Json.toJsFieldJsValueWrapper(Seq(1)))
      }

    }

  }

  "OptionHelpers implicit class" - {
    "entry" - {

      val name = Gen.alphaNumStr.sample.getOrElse("none")

      "an empty Option returns an empty Seq" in {
        new ModelHelperMethods.OptionHelpers[Int](None).entry(name) mustBe Seq.empty[Int]
      }

      "a Seq with entries gets wrapped in a Seq with the name -> value pair" in {
        new ModelHelperMethods.OptionHelpers(Some(1)).entry(name) mustBe Seq(name -> Json.toJsFieldJsValueWrapper(1))
      }

    }
  }

  "JsLookupResultHelpers implicit class" - {

    "asOptionString" - {

      "if an empty string is provided, return None" in {
        new ModelHelperMethods.JsLookupResultHelpers(JsDefined(JsString(""))).asOptionString mustBe None
      }

      "if a non-empty string is provided, return None" in {
        new ModelHelperMethods.JsLookupResultHelpers(JsDefined(JsString("1234"))).asOptionString mustBe Some("1234")
      }

      "if a non-JsString value is provided, throw a MalformedJsonException" in {
        Try(new ModelHelperMethods.JsLookupResultHelpers(JsDefined(JsTrue)).asOptionString) match {
          case Failure(x: MalformedJsonException) => x.message mustBe s"JsValue was a ${JsTrue.getClass.getSimpleName} when expecting a string"
          case _                                  => fail("MalformedJsonException was not thrown")
        }
      }

    }

    "asOption" - {

      "if a lookup was a success, return a Some" in {
        val result = new ModelHelperMethods.JsLookupResultHelpers(JsDefined(JsString("1234"))).asOption[String]
        result mustBe Some("1234") // on a separate line due to the compiler complaining
      }

      "if a lookup was a failure, return a None" in {
        val result = new ModelHelperMethods.JsLookupResultHelpers(JsUndefined("not found")).asOption[String]
        result mustBe None
      }

    }

    "asSeq" - {

      "if a lookup was a success, return a Some" in {
        val result = new ModelHelperMethods.JsLookupResultHelpers(JsDefined(JsArray(Seq(JsString("1234"))))).asSeq[String]
        result mustBe Seq("1234") // on a separate line due to the compiler complaining
      }

      "if a lookup was a failure, return a None" in {
        val result = new ModelHelperMethods.JsLookupResultHelpers(JsUndefined("not found")).asSeq[String]
        result mustBe Seq.empty[String]
      }

    }

  }

  "JsValueHelpers implicit class" - {

    "asString" - {

      "if the value is a JsString, return the string" in {
        val string = Gen.alphaNumStr.sample.getOrElse("string")
        new ModelHelperMethods.JsValueHelpers(JsString(string)).asString mustBe string
      }

      "if the value is not a JsString, a MalformedJsonException must be thrown" in {
        Try(new ModelHelperMethods.JsValueHelpers(JsFalse).asString) match {
          case Failure(x: MalformedJsonException) => x.message mustBe s"JsValue was a ${JsFalse.getClass.getSimpleName} when expecting a string"
          case _                                  => fail("MalformedJsonException was not thrown")
        }
      }

    }

  }

}

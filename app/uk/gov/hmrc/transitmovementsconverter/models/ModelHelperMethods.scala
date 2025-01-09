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

import play.api.libs.json.JsLookupResult
import play.api.libs.json.JsObject
import play.api.libs.json.JsResult
import play.api.libs.json.JsString
import play.api.libs.json.JsValue
import play.api.libs.json.Json.JsValueWrapper
import play.api.libs.json.OFormat
import play.api.libs.json.OWrites
import play.api.libs.json.Reads
import play.api.libs.json.Writes
import uk.gov.hmrc.transitmovementsconverter.models.errors.MalformedJsonException

object ModelHelperMethods {

  private def readWithTypeAdjusted[A](reads: Reads[A])(in: JsValue): JsResult[A] =
    in match {
      case obj: JsObject =>
        (obj.value.get("type") match {
          case Some(typeVal) => (obj - "type") + ("typeValue" -> typeVal)
          case None          => obj
        }).validate(reads)
      case in: JsValue => reads.reads(in)
    }

  private def writeWithTypeAdjusted[A](writes: OWrites[A])(in: A): JsObject =
    writes
      .transform {
        in =>
          in.value.get("typeValue") match {
            case Some(typeVal) => (in - "typeValue") + ("type" -> typeVal)
            case None          => in
          }
      }
      .writes(in)

  def formatWithTypeAdjusted[A](format: OFormat[A]): OFormat[A] =
    OFormat(readWithTypeAdjusted(format)(_), writeWithTypeAdjusted(format)(_))

  implicit class SeqHelpers[A](val value: Seq[A]) {
    def toOption: Option[Seq[A]] = Option(value).filter(_.nonEmpty)

    def entry(name: String)(implicit w: Writes[A]): Seq[(String, JsValueWrapper)] =
      if (value.nonEmpty) List(name -> value)
      else List.empty
  }

  implicit class OptionHelpers[A](val value: Option[A]) {

    def entry(name: String)(implicit w: Writes[A]): Seq[(String, JsValueWrapper)] =
      if (value.isDefined) Seq(name -> value)
      else Seq.empty
  }

  implicit class JsLookupResultHelpers(val lookup: JsLookupResult) {

    def asOptionString: Option[String] = lookup.toOption.flatMap {
      case JsString(x) if x.isEmpty => None
      case JsString(x)              => Some(x)
      case x                        => throw MalformedJsonException(s"JsValue was a ${x.getClass.getSimpleName} when expecting a string")
    }

    def asOption[A](implicit reads: Reads[A]): Option[A] =
      lookup.toOption.map(_.as[A])

    def asSeq[A](implicit reads: Reads[A]): Seq[A] =
      lookup.toOption.map(_.as[List[A]]).getOrElse(Nil)
  }

  implicit class JsValueHelpers(val value: JsValue) {

    def asString: String = value match {
      case JsString(x) => x
      case x           => throw MalformedJsonException(s"JsValue was a ${x.getClass.getSimpleName} when expecting a string")
    }
  }

}

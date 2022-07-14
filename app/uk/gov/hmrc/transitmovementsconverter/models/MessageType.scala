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
import play.api.libs.json.Reads
import play.api.libs.json.OWrites
import scalaxb.XMLFormat
import uk.gov.hmrc.transitmovementsconverter.models.ModelHelpers._

sealed abstract class MessageType[T](val name: String)
                                    (implicit
                                     override val xmlFormat: XMLFormat[T],
                                     override val jsonReads: Reads[T],
                                     override val jsonWrites: OWrites[T])
  extends ConversionFormat[T]

object MessageType extends XMLProtocol {
  case object CC015C extends MessageType[CC015CType]("CC015C")

  val values = Seq(
    CC015C
  )

  def fromName(value: String): Option[MessageType[_]] = values.find(_.name == value)
}

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
import uk.gov.hmrc.transitmovementsconverter.models.Models._

sealed abstract class MessageType[T](val name: String, val xmlRoot: String)(implicit
  override val xmlFormat: XMLFormat[T],
  override val jsonReads: Reads[T],
  override val jsonWrites: OWrites[T]
) extends ConversionFormat[T]

object MessageType extends XMLProtocol {

  case object IE004 extends MessageType[CC004CType]("IE004", "CC004C")
  case object IE007 extends MessageType[CC007CType]("IE007", "CC007C")
  case object IE009 extends MessageType[CC009CType]("IE009", "CC009C")
  case object IE013 extends MessageType[CC013CType]("IE013", "CC013C")
  case object IE014 extends MessageType[CC014CType]("IE014", "CC014C")
  case object IE015 extends MessageType[CC015CType]("IE015", "CC015C")
  case object IE019 extends MessageType[CC019CType]("IE019", "CC019C")
  case object IE028 extends MessageType[CC028CType]("IE028", "CC028C")
  case object IE029 extends MessageType[CC029CType]("IE029", "CC029C")
  case object IE035 extends MessageType[CC035CType]("IE035", "CC035C")
  case object IE045 extends MessageType[CC045CType]("IE045", "CC045C")
  case object IE051 extends MessageType[CC051CType]("IE051", "CC051C")
  case object IE054 extends MessageType[CC054CType]("IE054", "CC054C")
  case object IE055 extends MessageType[CC055CType]("IE055", "CC055C")
  case object IE056 extends MessageType[CC056CType]("IE056", "CC056C")
  case object IE060 extends MessageType[CC060CType]("IE060", "CC060C")
  case object IE170 extends MessageType[CC170CType]("IE170", "CC170C")
  case object IE906 extends MessageType[CC906CType]("IE906", "CC906C")
  case object IE928 extends MessageType[CC928CType]("IE928", "CC928C")

  val values = Seq(
    IE004,
    IE007,
    IE009,
    IE013,
    IE014,
    IE015,
    IE019,
    IE028,
    IE029,
    IE035,
    IE045,
    IE051,
    IE054,
    IE055,
    IE056,
    IE060,
    IE170,
    IE906,
    IE928
  )

  def fromName(value: String): Option[MessageType[_]] = values.find(_.name == value)
}

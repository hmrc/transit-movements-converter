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

package uk.gov.hmrc.transitmovementsconverter.controllers.models

import cats.syntax.all._
import com.lucidchart.open.xtract.{DefaultXmlReaders, ParseResult, XmlReader, __}
import com.lucidchart.open.xtract.XmlReader.{seq, _}
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads, Writes}

case class CC015C(messageSender: String, consignment: Consignment)

case class Consignment(grossMass: Double, houseConsignments: Seq[HouseConsignment])

case class HouseConsignment(sequenceNumber: Int, grossMass: Double, consignmentItems: Seq[ConsignmentItem])

case class ConsignmentItem(goodsItemNumber: Int, declarationGoodsItemNumber: Int, packaging: Packaging)

case class Packaging(sequenceNumber: Int, typeOfPackages: String)

object CC015C {

  implicit val readerXML: XmlReader[CC015C] =
    (
      (__ \ "messageSender").read[String],
      (__ \ "Consignment").read[Consignment],
      ).mapN(CC015C.apply _)

  implicit val readerJSON: Reads[CC015C] = (
    (JsPath \ "messageSender").read[String] and
      (JsPath \ "Consignment").read[Consignment]
    )(CC015C.apply _)

  implicit val writerJSON: Writes[CC015C] = (
    (JsPath \ "messageSender").write[String] and
      (JsPath \ "Consignment").write[Consignment]
    )(unlift(CC015C.unapply))
}

object Consignment {

  implicit val readerXML: XmlReader[Consignment] =
    (
      (__ \ "grossMass").read[Double],
      (__ \ "HouseConsignment").read(seq[HouseConsignment]),
      ).mapN(Consignment.apply _)

  implicit val readerJSON: Reads[Consignment] = (
      (JsPath \ "grossMass").read[Double] and
      (JsPath \ "HouseConsignment").read[Seq[HouseConsignment]]
    )(Consignment.apply _)

  implicit val writerJSON: Writes[Consignment] = (
    (JsPath \ "grossMass").write[Double] and
      (JsPath \ "HouseConsignment").write[Seq[HouseConsignment]]
    )(unlift(Consignment.unapply))
}

object HouseConsignment {

  implicit val readerXML: XmlReader[HouseConsignment] =
    (
      (__ \ "sequenceNumber").read[Int],
      (__ \ "grossMass").read[Double],
      (__ \ "ConsignmentItem").read(seq[ConsignmentItem]),
      ).mapN(HouseConsignment.apply _)

  implicit val readerJSON: Reads[HouseConsignment] = (
    (JsPath \ "sequenceNumber").read[Int] and
      (JsPath \ "grossMass").read[Double] and
      (JsPath \ "ConsignmentItem").read[Seq[ConsignmentItem]]
    )(HouseConsignment.apply _)

  implicit val writerJSON: Writes[HouseConsignment] = (
    (JsPath \ "sequenceNumber").write[Int] and
      (JsPath \ "grossMass").write[Double] and
      (JsPath \ "ConsignmentItem").write[Seq[ConsignmentItem]]
    )(unlift(HouseConsignment.unapply))
}

object ConsignmentItem {

  implicit val readerXML: XmlReader[ConsignmentItem] = (
    (__ \ "goodsItemNumber").read[Int],
    (__ \ "declarationGoodsItemNumber").read[Int],
    (__ \ "Packaging").read[Packaging]
  ).mapN(ConsignmentItem.apply _)

  implicit val readerJSON: Reads[ConsignmentItem] = (
    (JsPath \ "goodsItemNumber").read[Int] and
    (JsPath \ "declarationGoodsItemNumber").read[Int] and
    (JsPath \ "HouseConsignment" \ "Packaging").read[Packaging]
    )(ConsignmentItem.apply _)

  implicit val writerJSON: Writes[ConsignmentItem] = (
    (JsPath \ "goodsItemNumber").write[Int] and
      (JsPath \ "declarationGoodsItemNumber").write[Int] and
      (JsPath \ "HouseConsignment" \ "Packaging").write[Packaging]
    )(unlift(ConsignmentItem.unapply))
}

object Packaging {

  implicit val readerXML: XmlReader[Packaging] = (
    (__ \ "sequenceNumber").read[Int],
    (__ \ "typeOfPackages").read[String]
  ).mapN(Packaging.apply _)

  implicit val writerJSON: Writes[Packaging] = (
    (JsPath \ "sequenceNumber").write[Int] and
      (JsPath \ "typeOfPackages").write[String]
    )(unlift(Packaging.unapply))

  implicit val readerJSON: Reads[Packaging] = (
    (JsPath \ "sequenceNumber").read[Int] and
      (JsPath \ "typeOfPackages").read[String]
    )(Packaging.apply _)
}

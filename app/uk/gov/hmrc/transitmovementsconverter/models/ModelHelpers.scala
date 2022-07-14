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

import generated.AdditionalSupplyChainActorType
import generated.DepartureTransportMeansType03
import generated.LocationOfGoodsType05
import generated.TransportEquipmentType06
import generated._
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.JsError
import play.api.libs.json.JsLookupResult
import play.api.libs.json.JsNumber
import play.api.libs.json.JsObject
import play.api.libs.json.JsString
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.OFormat
import play.api.libs.json.Reads
import play.api.libs.json.Writes
import play.api.libs.json.__
import scalaxb.DataTypeFactory
import uk.gov.hmrc.transitmovementsconverter.models.errors.MalformedJsonException

import javax.xml.datatype.XMLGregorianCalendar
import scala.util.Try
import scala.xml.TopScope

object ModelHelpers {

  implicit class SeqHelpers[A](val seq: Seq[A]) extends AnyVal {
    def toOption: Option[Seq[A]] = Option(seq).filter(_.nonEmpty)
  }

  implicit class JsLookupResultHelpers(val lookup: JsLookupResult) extends AnyVal {

    def asOptionString: Option[String] = lookup.toOption.flatMap {
      case JsString(x) if x.isEmpty => None
      case JsString(x)              => Some(x)
      case x                        => throw MalformedJsonException(s"JsValue was a ${x.getClass.getSimpleName} when expecting a string")
    }

    def asOption[A](implicit reads: Reads[A]): Option[A] =
      lookup.toOption.map(_.as[A])

    def asSeq[A](implicit reads: Reads[A]): Seq[A] =
      lookup.toOption.map(_.as[Seq[A]]).getOrElse(Nil)
  }

  implicit class JsValueHelpers(val value: JsValue) extends AnyVal {

    def asString: String = value match {
      case JsString(x) => x
      case x           => throw MalformedJsonException(s"JsValue was a ${x.getClass.getSimpleName} when expecting a string")
    }
  }

  implicit lazy val flagReads: Reads[Flag] = Reads {
    case JsString(s)                      => JsSuccess(Flag.fromString(s, TopScope))
    case JsNumber(n) if n.intValue() == 1 => JsSuccess(Number1)
    case JsNumber(n) if n.intValue() == 0 => JsSuccess(Number0)
    case _                                => JsError()
  }

  implicit lazy val flagWrites: Writes[Flag] = Writes {
    flag =>
      JsString(flag.toString)
  }

  // Order sensitive - formats without dependencies should go first.
  implicit lazy val addressType12Format                  = Json.format[AddressType12]
  implicit lazy val commodityCoeType02Fprmat             = Json.format[CommodityCodeType02]
  implicit lazy val dangerousGoodsType01Format           = Json.format[DangerousGoodsType01]
  implicit lazy val goodsMeasureType02Format             = Json.format[GoodsMeasureType02]
  implicit lazy val consigneeType02Format                = Json.format[ConsigneeType02]
  implicit lazy val commodityType06Format                = Json.format[CommodityType06]
  implicit lazy val additionalReferenceType05Format      = Json.format[AdditionalReferenceType05]
  implicit lazy val addressType17Format                  = Json.format[AddressType17]
  implicit lazy val contactPersonType05Format            = Json.format[ContactPersonType05]
  implicit lazy val departureTransportMeansType05        = Json.format[DepartureTransportMeansType05]
  implicit lazy val consignorType07Format                = Json.format[ConsignorType07]
  implicit lazy val consigneeType05Format                = Json.format[ConsigneeType05]
  implicit lazy val sealType05Format                     = Json.format[SealType05]
  implicit lazy val packagingType03Format                = Json.format[PackagingType03]
  implicit lazy val additionalSupplyChainActorTypeFormat = Json.format[AdditionalSupplyChainActorType]
  implicit lazy val previousDocumentType08Format         = Json.format[PreviousDocumentType08]
  implicit lazy val supportingDocumentType05Format       = Json.format[SupportingDocumentType05]
  implicit lazy val transportDocumentType04Format        = Json.format[TransportDocumentType04]
  implicit lazy val additionalInformationType03Format    = Json.format[AdditionalInformationType03]
  implicit lazy val transportChargesTypeFormat           = Json.format[TransportChargesType]
  implicit lazy val consignmentItemType09Format          = Json.format[ConsignmentItemType09]
  implicit lazy val previousDocumentType10               = Json.format[PreviousDocumentType10]
  implicit lazy val additionalReferenceType06Format      = Json.format[AdditionalReferenceType06]
  implicit lazy val houseConsignmentType10Format         = Json.format[HouseConsignmentType10]

  implicit lazy val customsOfficeType02Format    = Json.format[CustomsOfficeType02]
  implicit lazy val gnssTypeFormat               = Json.format[GNSSType]
  implicit lazy val economicOperatorType03Format = Json.format[EconomicOperatorType03]
  implicit lazy val addressType14Format          = Json.format[AddressType14]
  implicit lazy val postcodeAddressType02Format  = Json.format[PostcodeAddressType02]
  implicit lazy val contactPersonType06Format    = Json.format[ContactPersonType06]
  implicit lazy val consignmentItemType09        = Json.format[ConsignmentItemType09]
  implicit lazy val guaranteeReferenceType03     = Json.format[GuaranteeReferenceType03]
  implicit lazy val carrierType04Format          = Json.format[CarrierType04]

  implicit lazy val goodsReferenceType02                      = Json.format[GoodsReferenceType02]
  implicit lazy val transportEquipmentType06Format            = Json.format[TransportEquipmentType06]
  implicit lazy val locationOfGoodsType05Format               = Json.format[LocationOfGoodsType05]
  implicit lazy val departureTransportMeansType03Format       = Json.format[DepartureTransportMeansType03]
  implicit lazy val countryOfRoutingOfConsignmentType01Format = Json.format[CountryOfRoutingOfConsignmentType01]
  implicit lazy val activeBorderTransportMeansType02Format    = Json.format[ActiveBorderTransportMeansType02]
  implicit lazy val placeOfLoadingType03Format                = Json.format[PlaceOfLoadingType03]
  implicit lazy val placeOfUnloadingType01Format              = Json.format[PlaceOfUnloadingType01]
  implicit lazy val previousDocumentType09Format              = Json.format[PreviousDocumentType09]

  implicit lazy val transitOperationType06Format                      = Json.format[TransitOperationType06]
  implicit lazy val authorisationType03Format                         = Json.format[AuthorisationType03]
  implicit lazy val customsOfficeOfDepartureType03Format              = Json.format[CustomsOfficeOfDepartureType03]
  implicit lazy val customsOfficeOfDestinationDeclaredType01Format    = Json.format[CustomsOfficeOfDestinationDeclaredType01]
  implicit lazy val customsOfficeOfTransitDeclaredType03Format        = Json.format[CustomsOfficeOfTransitDeclaredType03]
  implicit lazy val customsOfficeOfExitForTransitDeclaredType02Format = Json.format[CustomsOfficeOfExitForTransitDeclaredType02]
  implicit lazy val holderOfTheTransitProcedureType14Format           = Json.format[HolderOfTheTransitProcedureType14]
  implicit lazy val representativeType05Format                        = Json.format[RepresentativeType05]
  implicit lazy val guaranteeType02Format                             = Json.format[GuaranteeType02]

  // Due to ConsignmentType20 having 24 fields, and that Scala 2.12 only supports Tuples of up to 22 fields,
  // we can't use the standard unapply (as it is not generated).
  //
  // This means we have to do this by constructing a Json object...
  implicit lazy val consignmentType20Reads: Reads[ConsignmentType20] = Reads {
    case x: JsObject =>
      JsSuccess(
        ConsignmentType20(
          (x \ "countryOfDispatch").asOptionString,
          (x \ "countryOfDestination").asOptionString,
          (x \ "containerIndicator").asOption[Flag],
          (x \ "inlandModeOfTransport").asOptionString,
          (x \ "modeOfTransportAtTheBorder").asOptionString,
          (x \ "grossMass").as[BigDecimal],
          (x \ "referenceNumberUCR").asOptionString,
          (x \ "Carrier").asOption[CarrierType04],
          (x \ "Consignor").asOption[ConsignorType07],
          (x \ "Consignee").asOption[ConsigneeType05],
          (x \ "AdditionalSupplyChainActor").asSeq[AdditionalSupplyChainActorType],
          (x \ "TransportEquipment").asSeq[TransportEquipmentType06],
          (x \ "LocationOfGoods").asOption[LocationOfGoodsType05],
          (x \ "DepartureTransportMeans").asSeq[DepartureTransportMeansType03],
          (x \ "CountryOfRoutingOfConsignment").asSeq[CountryOfRoutingOfConsignmentType01],
          (x \ "ActiveBorderTransportMeans").asSeq[ActiveBorderTransportMeansType02],
          (x \ "PlaceOfLoading").asOption[PlaceOfLoadingType03],
          (x \ "PlaceOfUnloading").asOption[PlaceOfUnloadingType01],
          (x \ "PreviousDocument").asSeq[PreviousDocumentType09],
          (x \ "SupportingDocument").asSeq[SupportingDocumentType05],
          (x \ "TransportDocument").asSeq[TransportDocumentType04],
          (x \ "AdditionalReference").asSeq[AdditionalReferenceType06],
          (x \ "AdditionalInformation").asSeq[AdditionalInformationType03],
          (x \ "TransportCharges").asOption[TransportChargesType],
          (x \ "HouseConsignment").asSeq[HouseConsignmentType10]
        )
      )
    case _ => throw MalformedJsonException("ConsignmentType20 is not an object")
  }

  implicit lazy val consignmentType20Writes: Writes[ConsignmentType20] = Writes {
    consignmentType20 =>
      Json.obj(
        "countryOfDispatch"             -> consignmentType20.countryOfDispatch,
        "countryOfDestination"          -> consignmentType20.countryOfDestination,
        "containerIndicator"            -> consignmentType20.containerIndicator,
        "inlandModeOfTransport"         -> consignmentType20.inlandModeOfTransport,
        "modeOfTransportAtTheBorder"    -> consignmentType20.modeOfTransportAtTheBorder,
        "grossMass"                     -> consignmentType20.grossMass,
        "referenceNumberUCR"            -> consignmentType20.referenceNumberUCR,
        "Carrier"                       -> consignmentType20.Carrier,
        "Consignor"                     -> consignmentType20.Consignor,
        "Consignee"                     -> consignmentType20.Consignee,
        "AdditionalSupplyChainActor"    -> consignmentType20.AdditionalSupplyChainActor,
        "TransportEquipment"            -> consignmentType20.TransportEquipment,
        "LocationOfGoods"               -> consignmentType20.LocationOfGoods,
        "DepartureTransportMeans"       -> consignmentType20.DepartureTransportMeans,
        "CountryOfRoutingOfConsignment" -> consignmentType20.CountryOfRoutingOfConsignment,
        "ActiveBorderTransportMeans"    -> consignmentType20.ActiveBorderTransportMeans,
        "PlaceOfLoading"                -> consignmentType20.PlaceOfLoading,
        "PlaceOfUnloading"              -> consignmentType20.PlaceOfUnloading,
        "PreviousDocument"              -> consignmentType20.PreviousDocument,
        "SupportingDocument"            -> consignmentType20.SupportingDocument,
        "TransportDocument"             -> consignmentType20.TransportDocument,
        "AdditionalReference"           -> consignmentType20.AdditionalReference,
        "AdditionalInformation"         -> consignmentType20.AdditionalInformation,
        "TransportCharges"              -> consignmentType20.TransportCharges,
        "HouseConsignment"              -> consignmentType20.HouseConsignment
      )
  }

  implicit lazy val xmlDateTimeWrites: Writes[javax.xml.datatype.XMLGregorianCalendar] = Writes {
    dateTime =>
      JsString(dateTime.toXMLFormat)
  }

  implicit lazy val xmlDateTimeReads: Reads[XMLGregorianCalendar] = Reads {
    case JsString(dateTime) => JsSuccess(DataTypeFactory.initialValue().newXMLGregorianCalendar(dateTime))
    case _                  => JsError()
  }

  implicit lazy val messageTypesReads: Reads[MessageTypes] = Reads {
    case JsString(messageType) =>
      Try(generated.MessageTypes.fromString(messageType, TopScope))
        .map(
          x => JsSuccess(x)
        )
        .getOrElse(JsError())
    case _ => JsError()
  }

  implicit lazy val messageTypesWrites: Writes[MessageTypes] = Writes {
    x =>
      JsString(x.toString)
  }

  // Message Types

  implicit val formats: OFormat[CC015CType] = (
    (__ \ "messageRecipient").format[String] and
      (__ \ "preparationDateAndTime").format[XMLGregorianCalendar] and
      (__ \ "messageIdentification").format[String] and
      (__ \ "messageType").format[MessageTypes] and
      (__ \ "correlationIdentifier").formatNullable[String] and
      (__ \ "TransitOperation").format[TransitOperationType06] and
      (__ \ "Authorisation").formatNullable[Seq[AuthorisationType03]] and
      (__ \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
      (__ \ "CustomsOfficeOfDestinationDeclared").format[CustomsOfficeOfDestinationDeclaredType01] and
      (__ \ "CustomsOfficeOfTransitDeclared").formatNullable[Seq[CustomsOfficeOfTransitDeclaredType03]] and
      (__ \ "CustomsOfficeOfExitForTransitDeclared").formatNullable[Seq[CustomsOfficeOfExitForTransitDeclaredType02]] and
      (__ \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType14] and
      (__ \ "Representative").formatNullable[RepresentativeType05] and
      (__ \ "Guarantee").formatNullable[Seq[GuaranteeType02]] and
      (__ \ "Consignment").format[ConsignmentType20]
  )(
    (
      messageRecipient,
      preparationDateAndTime,
      messageIdentification,
      messageType,
      correlationIdentifier,
      TransitOperation,
      Authorisation,
      CustomsOfficeOfDeparture,
      CustomsOfficeOfDestinationDeclared,
      CustomsOfficeOfTransitDeclared,
      CustomsOfficeOfExitForTransitDeclared,
      HolderOfTheTransitProcedure,
      Representative,
      Guarantee,
      Consignment
    ) =>
      CC015CType(
        MESSAGE_FROM_TRADERSequence(
          None,
          MESSAGE_1Sequence(messageRecipient, preparationDateAndTime, messageIdentification, messageType, correlationIdentifier)
        ),
        TransitOperation,
        Authorisation.getOrElse(Nil),
        CustomsOfficeOfDeparture,
        CustomsOfficeOfDestinationDeclared,
        CustomsOfficeOfTransitDeclared.getOrElse(Nil),
        CustomsOfficeOfExitForTransitDeclared.getOrElse(Nil),
        HolderOfTheTransitProcedure,
        Representative,
        Guarantee.getOrElse(Nil),
        Consignment
      ),
    {
      obj: CC015CType =>
        val seqType = obj.messagE_FROM_TRADERSequence1.messagE_1Sequence2
        (
          seqType.messageRecipient,
          seqType.preparationDateAndTime,
          seqType.messageIdentification,
          seqType.messageType,
          seqType.correlationIdentifier,
          obj.TransitOperation,
          obj.Authorisation.toOption,
          obj.CustomsOfficeOfDeparture,
          obj.CustomsOfficeOfDestinationDeclared,
          obj.CustomsOfficeOfTransitDeclared.toOption,
          obj.CustomsOfficeOfExitForTransitDeclared.toOption,
          obj.HolderOfTheTransitProcedure,
          obj.Representative,
          obj.Guarantee.toOption,
          obj.Consignment
        )
    }
  )

}

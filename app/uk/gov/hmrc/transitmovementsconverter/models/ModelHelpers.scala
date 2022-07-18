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
import play.api.libs.json.Json.JsValueWrapper
import play.api.libs.json.OFormat
import play.api.libs.json.Reads
import play.api.libs.json.Writes
import play.api.libs.json.__
import scalaxb.DataRecord
import scalaxb.DataTypeFactory
import uk.gov.hmrc.transitmovementsconverter.models.errors.MalformedJsonException

import javax.xml.datatype.XMLGregorianCalendar
import scala.util.Try
import scala.xml.TopScope

/** Here be dragons - be very careful when updating this class and make sure you test everything.
  *
  * While we are able to use scalaxb to ingest our XML files and create models, we still need to consider how
  * we're going to convert them into Json. The natural choice is Play Json, but unfortunately, due to the
  * compile time nature of implicits, we need to specify each case class as its own Format[A]. Now,
  * Play Json makes it easy to work with case classes thanks to the way that it uses Scala Macros to use the
  * apply and unapply methods ... mostly. Unfortunately, we need to specify the implicits for ALL classes we use,
  * not just those we directly use. To make matters worse, the ordering of our format implicits is somewhat sensitive
  * (so please be very careful when adding more formats - dependencies of a format go ABOVE that format).
  *
  * However, we cannot use the Json.format[_] macro for everything. For example, ConsignmentType20 has 24 fields,
  * and while a case class can be produced for it, because Scala 2 has a hard limit of 22 entries in a Tuple, it
  * doesn't create the unapply method that Play Json's format macros rely on. As a result, we have to create the
  * reads and writes manually. To add further insult to injury, unlike the autogenerated formats, we have to handle
  * empty optionals manually - which is why OptionHelpers#entry and SeqHelpers#entry exist.
  *
  * As the XSDs are updated, it is very likely that some of the below will break as the generated models will change
  * slightly.
  */
object ModelHelpers {

  implicit class SeqHelpers[A](val value: Seq[A]) extends AnyVal {
    def toOption: Option[Seq[A]] = Option(value).filter(_.nonEmpty)

    def entry(name: String)(implicit w: Writes[A]): Seq[(String, JsValueWrapper)] =
      if (value.nonEmpty) Seq(name -> value)
      else Seq.empty
  }

  implicit class OptionHelpers[A](val value: Option[A]) extends AnyVal {

    def entry(name: String)(implicit w: Writes[A]): Seq[(String, JsValueWrapper)] =
      if (value.isDefined) Seq(name -> value)
      else Seq.empty
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
    case JsString(s) =>
      Try(JsSuccess(Flag.fromString(s, TopScope))).recover {
        case _: RuntimeException => JsError()
      }.get
    case JsNumber(n) if n.intValue() == 1 => JsSuccess(Number1)
    case JsNumber(n) if n.intValue() == 0 => JsSuccess(Number0)
    case _                                => JsError()
  }

  implicit lazy val flagWrites: Writes[Flag] = Writes {
    flag =>
      JsString(flag.toString)
  }

  implicit lazy val phaseIDtypeReads: Reads[PhaseIDtype] = Reads {
    case JsString(x) =>
      Try(JsSuccess(PhaseIDtype.fromString(x, TopScope))).recover {
        case _: RuntimeException => JsError()
      }.get
    case _ => JsError()
  }

  implicit lazy val phaseIDtypeWrites: Writes[PhaseIDtype] = Writes {
    phaseId =>
      JsString(phaseId.toString)
  }

  // Order sensitive - formats without dependencies should go first.

  implicit lazy val addressType12Format                  = Json.format[AddressType12]
  implicit lazy val commodityCoeType02Format             = Json.format[CommodityCodeType02]
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

  // Due to ConsignmentType20 having 24 fields, and that Scala 2.12 (and 2.13) only supports Tuples of up to 22 fields,
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
      val seq: Seq[(String, JsValueWrapper)] = Seq.empty[(String, JsValueWrapper)] ++
        consignmentType20.countryOfDispatch.entry("countryOfDispatch") ++
        consignmentType20.countryOfDestination.entry("countryOfDestination") ++
        consignmentType20.containerIndicator.entry("containerIndicator") ++
        consignmentType20.inlandModeOfTransport.entry("inlandModeOfTransport") ++
        consignmentType20.modeOfTransportAtTheBorder.entry("modeOfTransportAtTheBorder") ++
        Seq[(String, JsValueWrapper)]("grossMass" -> consignmentType20.grossMass) ++
        consignmentType20.referenceNumberUCR.entry("referenceNumberUCR") ++
        consignmentType20.Carrier.entry("Carrier") ++
        consignmentType20.Consignor.entry("Consignor") ++
        consignmentType20.Consignee.entry("Consignee") ++
        consignmentType20.AdditionalSupplyChainActor.entry("AdditionalSupplyChainActor") ++
        consignmentType20.TransportEquipment.entry("TransportEquipment") ++
        consignmentType20.LocationOfGoods.entry("LocationOfGoods") ++
        consignmentType20.DepartureTransportMeans.entry("DepartureTransportMeans") ++
        consignmentType20.CountryOfRoutingOfConsignment.entry("CountryOfRoutingOfConsignment") ++
        consignmentType20.ActiveBorderTransportMeans.entry("ActiveBorderTransportMeans") ++
        consignmentType20.PlaceOfLoading.entry("PlaceOfLoading") ++
        consignmentType20.PlaceOfUnloading.entry("PlaceOfUnloading") ++
        consignmentType20.PreviousDocument.entry("PreviousDocument") ++
        consignmentType20.SupportingDocument.entry("SupportingDocument") ++
        consignmentType20.TransportDocument.entry("TransportDocument") ++
        consignmentType20.AdditionalReference.entry("AdditionalReference") ++
        consignmentType20.AdditionalInformation.entry("AdditionalInformation") ++
        consignmentType20.TransportCharges.entry("TransportCharges") ++
        consignmentType20.HouseConsignment.entry("HouseConsignment")

      Json.obj(seq: _*)
  }

  implicit lazy val xmlDateTimeWrites: Writes[XMLGregorianCalendar] = Writes {
    dateTime =>
      JsString(dateTime.toXMLFormat)
  }

  implicit lazy val xmlDateTimeReads: Reads[XMLGregorianCalendar] = Reads {
    case JsString(dateTime) => Try(JsSuccess(DataTypeFactory.initialValue().newXMLGregorianCalendar(dateTime))).getOrElse(JsError())
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

  // ** CC015C **

  private val cc015cRoot = "CC015C"

  implicit val cc015cFormats: OFormat[CC015CType] = (
    (__ \ cc015cRoot \ "messageRecipient").format[String] and
      (__ \ cc015cRoot \ "preparationDateAndTime").format[XMLGregorianCalendar] and
      (__ \ cc015cRoot \ "messageIdentification").format[String] and
      (__ \ cc015cRoot \ "messageType").format[MessageTypes] and
      (__ \ cc015cRoot \ "correlationIdentifier").formatNullable[String] and
      (__ \ cc015cRoot \ "TransitOperation").format[TransitOperationType06] and
      (__ \ cc015cRoot \ "Authorisation").formatNullable[Seq[AuthorisationType03]] and
      (__ \ cc015cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
      (__ \ cc015cRoot \ "CustomsOfficeOfDestinationDeclared").format[CustomsOfficeOfDestinationDeclaredType01] and
      (__ \ cc015cRoot \ "CustomsOfficeOfTransitDeclared").formatNullable[Seq[CustomsOfficeOfTransitDeclaredType03]] and
      (__ \ cc015cRoot \ "CustomsOfficeOfExitForTransitDeclared").formatNullable[Seq[CustomsOfficeOfExitForTransitDeclaredType02]] and
      (__ \ cc015cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType14] and
      (__ \ cc015cRoot \ "Representative").formatNullable[RepresentativeType05] and
      (__ \ cc015cRoot \ "Guarantee").formatNullable[Seq[GuaranteeType02]] and
      (__ \ cc015cRoot \ "Consignment").format[ConsignmentType20] and
      (__ \ cc015cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
      Consignment,
      phaseId
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
        Consignment,
        phaseId
          .map(
            x => Map("@PhaseID" -> DataRecord(x.toString))
          )
          .getOrElse(Map.empty)
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
          obj.Consignment,
          obj.PhaseID
        )
    }
  )

}

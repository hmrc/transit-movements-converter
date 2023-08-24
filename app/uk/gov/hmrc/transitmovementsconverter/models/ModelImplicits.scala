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

import generated.CustomsOfficeOfRecoveryAtDepartureType01
import generated._
import play.api.libs.json.JsError
import play.api.libs.json.JsNumber
import play.api.libs.json.JsObject
import play.api.libs.json.JsString
import play.api.libs.json.JsSuccess
import play.api.libs.json.Json
import play.api.libs.json.Json.JsValueWrapper
import play.api.libs.json.Reads
import play.api.libs.json.Writes
import scalaxb.DataTypeFactory
import uk.gov.hmrc.transitmovementsconverter.models.ModelHelperMethods._
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
object ModelImplicits {

  implicit lazy val aesNctsP5FunctionalErrorCodesReads: Reads[AesNctsP5FunctionalErrorCodes] = Reads {
    case JsNumber(n) =>
      Try(JsSuccess(AesNctsP5FunctionalErrorCodes.fromString(n.toString, TopScope))).recover {
        case _: RuntimeException => JsError()
      }.get
    case JsString(s) =>
      Try(JsSuccess(AesNctsP5FunctionalErrorCodes.fromString(s, TopScope))).recover {
        case _: RuntimeException => JsError()
      }.get
    case _ => JsError()
  }

  implicit lazy val aesNctsP5FunctionalErrorCodesWrites: Writes[AesNctsP5FunctionalErrorCodes] = Writes {
    code =>
      JsString(code.toString)
  }

  implicit lazy val countryCodeReads: Reads[CountryCodesCustomsOfficeLists] = Reads {
    case JsString(s) =>
      Try(JsSuccess(CountryCodesCustomsOfficeLists.fromString(s, TopScope))).recover {
        case _: RuntimeException => JsError()
      }.get
    case _ => JsError()
  }

  implicit lazy val countryCodeWrites: Writes[CountryCodesCustomsOfficeLists] = Writes {
    code =>
      JsString(code.toString)
  }

  implicit lazy val flagReads: Reads[Flag] = Reads {
    case JsString(s) =>
      Try(JsSuccess(Flag.fromString(s, TopScope))).recover {
        case _: RuntimeException => JsError()
      }.get
    case JsNumber(n) if n.intValue == 1 => JsSuccess(Number1)
    case JsNumber(n) if n.intValue == 0 => JsSuccess(Number0)
    case _                              => JsError()
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

  implicit lazy val ctlControlTypeFormat = Json.format[CTLControlType]

  implicit lazy val headerType01Format                             = Json.format[HeaderType01]
  implicit lazy val typeOfControlsTypeFormat                       = formatWithTypeAdjusted(Json.format[TypeOfControlsType])
  implicit lazy val requestedDocumentTypeFormat                    = Json.format[RequestedDocumentType]
  implicit lazy val commodityCodeType01Format                      = Json.format[CommodityCodeType01]
  implicit lazy val commodityCodeType02Format                      = Json.format[CommodityCodeType02]
  implicit lazy val commodityCodeType03Format                      = Json.format[CommodityCodeType03]
  implicit lazy val commodityCodeType05Format                      = Json.format[CommodityCodeType05]
  implicit lazy val commodityCodeType06Format                      = Json.format[CommodityCodeType06]
  implicit lazy val dangerousGoodsType01Format                     = Json.format[DangerousGoodsType01]
  implicit lazy val dangerousGoodsType02Format                     = Json.format[DangerousGoodsType02]
  implicit lazy val goodsMeasureType02Format                       = Json.format[GoodsMeasureType02]
  implicit lazy val goodsMeasureType03Format                       = Json.format[GoodsMeasureType03]
  implicit lazy val goodsMeasureType04Format                       = Json.format[GoodsMeasureType04]
  implicit lazy val commodityType02Format                          = Json.format[CommodityType02]
  implicit lazy val commodityType03Format                          = Json.format[CommodityType03]
  implicit lazy val commodityType05Format                          = Json.format[CommodityType05]
  implicit lazy val commodityType06Format                          = Json.format[CommodityType06]
  implicit lazy val commodityType07Format                          = Json.format[CommodityType07]
  implicit lazy val commodityType08Format                          = Json.format[CommodityType08]
  implicit lazy val additionalReferenceType01Format                = formatWithTypeAdjusted(Json.format[AdditionalReferenceType01])
  implicit lazy val additionalReferenceType02Format                = formatWithTypeAdjusted(Json.format[AdditionalReferenceType02])
  implicit lazy val additionalReferenceType03Format                = formatWithTypeAdjusted(Json.format[AdditionalReferenceType03])
  implicit lazy val additionalReferenceType04Format                = formatWithTypeAdjusted(Json.format[AdditionalReferenceType04])
  implicit lazy val additionalReferenceType05Format                = formatWithTypeAdjusted(Json.format[AdditionalReferenceType05])
  implicit lazy val additionalReferenceType06Format                = formatWithTypeAdjusted(Json.format[AdditionalReferenceType06])
  implicit lazy val addressType01Format                            = Json.format[AddressType01]
  implicit lazy val addressType02Format                            = Json.format[AddressType02]
  implicit lazy val addressType03Format                            = Json.format[AddressType03]
  implicit lazy val addressType07Format                            = Json.format[AddressType07]
  implicit lazy val addressType08Format                            = Json.format[AddressType08]
  implicit lazy val addressType09Format                            = Json.format[AddressType09]
  implicit lazy val addressType10Format                            = Json.format[AddressType10]
  implicit lazy val addressType12Format                            = Json.format[AddressType12]
  implicit lazy val addressType14Format                            = Json.format[AddressType14]
  implicit lazy val addressType15Format                            = Json.format[AddressType15]
  implicit lazy val addressType16Format                            = Json.format[AddressType16]
  implicit lazy val addressType17Format                            = Json.format[AddressType17]
  implicit lazy val addressType18Format                            = Json.format[AddressType18]
  implicit lazy val departureTransportMeansType02                  = Json.format[DepartureTransportMeansType02]
  implicit lazy val departureTransportMeansType05                  = Json.format[DepartureTransportMeansType05]
  implicit lazy val sealType02Format                               = Json.format[SealType02]
  implicit lazy val sealType04Format                               = Json.format[SealType04]
  implicit lazy val sealType05Format                               = Json.format[SealType05]
  implicit lazy val packagingType01Format                          = Json.format[PackagingType01]
  implicit lazy val packagingType02Format                          = Json.format[PackagingType02]
  implicit lazy val packagingType03Format                          = Json.format[PackagingType03]
  implicit lazy val packagingType04Format                          = Json.format[PackagingType04]
  implicit lazy val additionalSupplyChainActorTypeFormat           = Json.format[AdditionalSupplyChainActorType]
  implicit lazy val previousDocumentType03Format                   = formatWithTypeAdjusted(Json.format[PreviousDocumentType03])
  implicit lazy val previousDocumentType04Format                   = formatWithTypeAdjusted(Json.format[PreviousDocumentType04])
  implicit lazy val previousDocumentType06Format                   = formatWithTypeAdjusted(Json.format[PreviousDocumentType06])
  implicit lazy val previousDocumentType07Format                   = formatWithTypeAdjusted(Json.format[PreviousDocumentType07])
  implicit lazy val previousDocumentType08Format                   = formatWithTypeAdjusted(Json.format[PreviousDocumentType08])
  implicit lazy val previousDocumentType09Format                   = formatWithTypeAdjusted(Json.format[PreviousDocumentType09])
  implicit lazy val previousDocumentType10Format                   = formatWithTypeAdjusted(Json.format[PreviousDocumentType10])
  implicit lazy val supportingDocumentType02Format                 = formatWithTypeAdjusted(Json.format[SupportingDocumentType02])
  implicit lazy val supportingDocumentType03Format                 = formatWithTypeAdjusted(Json.format[SupportingDocumentType03])
  implicit lazy val supportingDocumentType04Format                 = formatWithTypeAdjusted(Json.format[SupportingDocumentType04])
  implicit lazy val supportingDocumentType05Format                 = formatWithTypeAdjusted(Json.format[SupportingDocumentType05])
  implicit lazy val supportingDocumentType06Format                 = formatWithTypeAdjusted(Json.format[SupportingDocumentType06])
  implicit lazy val transportDocumentType01Format                  = formatWithTypeAdjusted(Json.format[TransportDocumentType01])
  implicit lazy val transportDocumentType02Format                  = formatWithTypeAdjusted(Json.format[TransportDocumentType02])
  implicit lazy val transportDocumentType03Format                  = formatWithTypeAdjusted(Json.format[TransportDocumentType03])
  implicit lazy val transportDocumentType04Format                  = formatWithTypeAdjusted(Json.format[TransportDocumentType04])
  implicit lazy val additionalInformationType02Format              = Json.format[AdditionalInformationType02]
  implicit lazy val additionalInformationType03Format              = Json.format[AdditionalInformationType03]
  implicit lazy val transportChargesTypeFormat                     = Json.format[TransportChargesType]
  implicit lazy val controlResultType02Format                      = Json.format[ControlResultType02]
  implicit lazy val recoveryNotificationTypeFormat                 = Json.format[RecoveryNotificationType]
  implicit lazy val customsOfficeOfRecoveryAtDepartureType01Format = Json.format[CustomsOfficeOfRecoveryAtDepartureType01]
  implicit lazy val invalidGuaranteeReasonType01Format             = Json.format[InvalidGuaranteeReasonType01]

  implicit lazy val customsOfficeType01Format                 = Json.format[CustomsOfficeType01]
  implicit lazy val customsOfficeType02Format                 = Json.format[CustomsOfficeType02]
  implicit lazy val gnssTypeFormat                            = Json.format[GNSSType]
  implicit lazy val economicOperatorType01Format              = Json.format[EconomicOperatorType01]
  implicit lazy val economicOperatorType03Format              = Json.format[EconomicOperatorType03]
  implicit lazy val postcodeAddressType01Format               = Json.format[PostcodeAddressType01]
  implicit lazy val postcodeAddressType02Format               = Json.format[PostcodeAddressType02]
  implicit lazy val contactPersonType01Format                 = Json.format[ContactPersonType01]
  implicit lazy val contactPersonType02Format                 = Json.format[ContactPersonType02]
  implicit lazy val contactPersonType04Format                 = Json.format[ContactPersonType04]
  implicit lazy val contactPersonType05Format                 = Json.format[ContactPersonType05]
  implicit lazy val contactPersonType06Format                 = Json.format[ContactPersonType06]
  implicit lazy val guaranteeReferenceType01                  = Json.format[GuaranteeReferenceType01]
  implicit lazy val guaranteeReferenceType03                  = Json.format[GuaranteeReferenceType03]
  implicit lazy val guaranteeReferenceType08                  = Json.format[GuaranteeReferenceType08]
  implicit lazy val carrierType03Format                       = Json.format[CarrierType03]
  implicit lazy val carrierType04Format                       = Json.format[CarrierType04]
  implicit lazy val consignorType03Format                     = Json.format[ConsignorType03]
  implicit lazy val consignorType04Format                     = Json.format[ConsignorType04]
  implicit lazy val consignorType05Format                     = Json.format[ConsignorType05]
  implicit lazy val consignorType06Format                     = Json.format[ConsignorType06]
  implicit lazy val consignorType07Format                     = Json.format[ConsignorType07]
  implicit lazy val consigneeType03Format                     = Json.format[ConsigneeType03]
  implicit lazy val consigneeType02Format                     = Json.format[ConsigneeType02]
  implicit lazy val consigneeType04Format                     = Json.format[ConsigneeType04]
  implicit lazy val consigneeType05Format                     = Json.format[ConsigneeType05]
  implicit lazy val consignmentItemType02Format               = Json.format[ConsignmentItemType02]
  implicit lazy val consignmentItemType03Format               = Json.format[ConsignmentItemType03]
  implicit lazy val consignmentItemType04Format               = Json.format[ConsignmentItemType04]
  implicit lazy val consignmentItemType05Format               = Json.format[ConsignmentItemType05]
  implicit lazy val consignmentItemType08Format               = Json.format[ConsignmentItemType08]
  implicit lazy val consignmentItemType09Format               = Json.format[ConsignmentItemType09]
  implicit lazy val departureTransportMeansType03Format       = Json.format[DepartureTransportMeansType03]
  implicit lazy val departureTransportMeansType04Format       = Json.format[DepartureTransportMeansType04]
  implicit lazy val houseConsignmentType02Format              = Json.format[HouseConsignmentType02]
  implicit lazy val houseConsignmentType03Format              = Json.format[HouseConsignmentType03]
  implicit lazy val houseConsignmentType04Format              = Json.format[HouseConsignmentType04]
  implicit lazy val houseConsignmentType05Format              = Json.format[HouseConsignmentType05]
  implicit lazy val houseConsignmentType06Format              = Json.format[HouseConsignmentType06]
  implicit lazy val houseConsignmentType10Format              = Json.format[HouseConsignmentType10]
  implicit lazy val transportMeansType01Format                = Json.format[TransportMeansType01]
  implicit lazy val transportMeansType02Format                = Json.format[TransportMeansType02]
  implicit lazy val goodsReferenceType01Format                = Json.format[GoodsReferenceType01]
  implicit lazy val goodsReferenceType02Format                = Json.format[GoodsReferenceType02]
  implicit lazy val transhipmentType01Format                  = Json.format[TranshipmentType01]
  implicit lazy val transhipmentType02Format                  = Json.format[TranshipmentType02]
  implicit lazy val transportEquipmentType01Format            = Json.format[TransportEquipmentType01]
  implicit lazy val transportEquipmentType03Format            = Json.format[TransportEquipmentType03]
  implicit lazy val transportEquipmentType05Format            = Json.format[TransportEquipmentType05]
  implicit lazy val transportEquipmentType06Format            = Json.format[TransportEquipmentType06]
  implicit lazy val transportEquipmentType07Format            = Json.format[TransportEquipmentType07]
  implicit lazy val locationOfGoodsType01Format               = Json.format[LocationOfGoodsType01]
  implicit lazy val locationOfGoodsType02Format               = Json.format[LocationOfGoodsType02]
  implicit lazy val locationOfGoodsType03Format               = Json.format[LocationOfGoodsType03]
  implicit lazy val locationOfGoodsType05Format               = Json.format[LocationOfGoodsType05]
  implicit lazy val countryOfRoutingOfConsignmentType01Format = Json.format[CountryOfRoutingOfConsignmentType01]
  implicit lazy val activeBorderTransportMeansType01Format    = Json.format[ActiveBorderTransportMeansType01]
  implicit lazy val activeBorderTransportMeansType02Format    = Json.format[ActiveBorderTransportMeansType02]
  implicit lazy val activeBorderTransportMeansType03Format    = Json.format[ActiveBorderTransportMeansType03]
  implicit lazy val placeOfLoadingType02Format                = Json.format[PlaceOfLoadingType02]
  implicit lazy val placeOfLoadingType03Format                = Json.format[PlaceOfLoadingType03]
  implicit lazy val placeOfUnloadingType01Format              = Json.format[PlaceOfUnloadingType01]
  implicit lazy val placeOfUnloadingType02Format              = Json.format[PlaceOfUnloadingType02]

  implicit lazy val endorsementType01Format = Json.format[EndorsementType01]
  implicit lazy val endorsementType03Format = Json.format[EndorsementType03]
  implicit lazy val locationType01Format    = Json.format[LocationType01]
  implicit lazy val locationType02Format    = Json.format[LocationType02]
  implicit lazy val incidentType01Format    = Json.format[IncidentType01]
  implicit lazy val incidentType03Format    = Json.format[IncidentType03]
  implicit lazy val incidentType04Format    = Json.format[IncidentType04]

  implicit lazy val consigneeActualType01Format = Json.format[ConsigneeActualType01]
  implicit lazy val enquiryTypeFormat           = Json.format[EnquiryType01]

  implicit lazy val functionalErrorType02Format                       = Json.format[FunctionalErrorType02]
  implicit lazy val functionalErrorType04Format                       = Json.format[FunctionalErrorType04]
  implicit lazy val invalidationType01Format                          = Json.format[InvalidationType01]
  implicit lazy val invalidationType02Format                          = Json.format[InvalidationType02]
  implicit lazy val transitOperationType01Format                      = Json.format[TransitOperationType01]
  implicit lazy val transitOperationType02Format                      = Json.format[TransitOperationType02]
  implicit lazy val transitOperationType03Format                      = Json.format[TransitOperationType03]
  implicit lazy val transitOperationType04Format                      = Json.format[TransitOperationType04]
  implicit lazy val transitOperationType05Format                      = Json.format[TransitOperationType05]
  implicit lazy val transitOperationType06Format                      = Json.format[TransitOperationType06]
  implicit lazy val transitOperationType08Format                      = Json.format[TransitOperationType08]
  implicit lazy val transitOperationType10Format                      = Json.format[TransitOperationType10]
  implicit lazy val transitOperationType11Format                      = Json.format[TransitOperationType11]
  implicit lazy val transitOperationType12Format                      = Json.format[TransitOperationType12]
  implicit lazy val transitOperationType14Format                      = Json.format[TransitOperationType14]
  implicit lazy val transitOperationType15Format                      = Json.format[TransitOperationType15]
  implicit lazy val transitOperationType16Format                      = Json.format[TransitOperationType16]
  implicit lazy val transitOperationType18Format                      = Json.format[TransitOperationType18]
  implicit lazy val transitOperationType19Format                      = Json.format[TransitOperationType19]
  implicit lazy val transitOperationType20Format                      = Json.format[TransitOperationType20]
  implicit lazy val transitOperationType21Format                      = Json.format[TransitOperationType21]
  implicit lazy val transitOperationType22Format                      = Json.format[TransitOperationType22]
  implicit lazy val transitOperationType23Format                      = Json.format[TransitOperationType23]
  implicit lazy val transitOperationType24Format                      = Json.format[TransitOperationType24]
  implicit lazy val transitOperationType26Format                      = Json.format[TransitOperationType26]
  implicit lazy val transitOperationType43Format                      = Json.format[TransitOperationType43]
  implicit lazy val transitOperationType47Format                      = Json.format[TransitOperationType47]
  implicit lazy val transitOperationType48Format                      = Json.format[TransitOperationType48]
  implicit lazy val authorisationType01Format                         = formatWithTypeAdjusted(Json.format[AuthorisationType01])
  implicit lazy val authorisationType02Format                         = formatWithTypeAdjusted(Json.format[AuthorisationType02])
  implicit lazy val authorisationType03Format                         = formatWithTypeAdjusted(Json.format[AuthorisationType03])
  implicit lazy val customsOfficeOfDepartureType03Format              = Json.format[CustomsOfficeOfDepartureType03]
  implicit lazy val customsOfficeOfEnquiryAtDeparture01               = Json.format[CustomsOfficeOfEnquiryAtDepartureType01]
  implicit lazy val customsOfficeOfDestinationDeclaredType01Format    = Json.format[CustomsOfficeOfDestinationDeclaredType01]
  implicit lazy val customsOfficeOfTransitDeclaredType04Format        = Json.format[CustomsOfficeOfTransitDeclaredType04]
  implicit lazy val customsOfficeOfExitForTransitDeclaredType02Format = Json.format[CustomsOfficeOfExitForTransitDeclaredType02]
  implicit lazy val customsOfficeOfDestinationActualType01Format      = Json.format[CustomsOfficeOfDestinationActualType01]
  implicit lazy val customsOfficeOfDestinationActualType03Format      = Json.format[CustomsOfficeOfDestinationActualType03]
  implicit lazy val customsOfficeOfIncidentRegistration02Format       = Json.format[CustomsOfficeOfIncidentRegistrationType02]
  implicit lazy val holderOfTheTransitProcedureType02Format           = Json.format[HolderOfTheTransitProcedureType02]
  implicit lazy val holderOfTheTransitProcedureType05Format           = Json.format[HolderOfTheTransitProcedureType05]
  implicit lazy val holderOfTheTransitProcedureType06Format           = Json.format[HolderOfTheTransitProcedureType06]
  implicit lazy val holderOfTheTransitProcedureType07Format           = Json.format[HolderOfTheTransitProcedureType07]
  implicit lazy val holderOfTheTransitProcedureType08Format           = Json.format[HolderOfTheTransitProcedureType08]
  implicit lazy val holderOfTheTransitProcedureType13Format           = Json.format[HolderOfTheTransitProcedureType13]
  implicit lazy val holderOfTheTransitProcedureType14Format           = Json.format[HolderOfTheTransitProcedureType14]
  implicit lazy val holderOfTheTransitProcedureType15Format           = Json.format[HolderOfTheTransitProcedureType15]
  implicit lazy val holderOfTheTransitProcedureType19Format           = Json.format[HolderOfTheTransitProcedureType19]
  implicit lazy val holderOfTheTransitProcedureType20Format           = Json.format[HolderOfTheTransitProcedureType20]
  implicit lazy val traderAtDestinationType01Format                   = Json.format[TraderAtDestinationType01]
  implicit lazy val traderAtDestinationType02Format                   = Json.format[TraderAtDestinationType02]
  implicit lazy val traderAtDestinationType03Format                   = Json.format[TraderAtDestinationType03]
  implicit lazy val representativeType01Format                        = Json.format[RepresentativeType01]
  implicit lazy val representativeType02Format                        = Json.format[RepresentativeType02]
  implicit lazy val representativeType03Format                        = Json.format[RepresentativeType03]
  implicit lazy val representativeType04Format                        = Json.format[RepresentativeType04]
  implicit lazy val representativeType05Format                        = Json.format[RepresentativeType05]
  implicit lazy val guaranteeType01Format                             = Json.format[GuaranteeType01]
  implicit lazy val guaranteeType02Format                             = Json.format[GuaranteeType02]
  implicit lazy val guaranteeType03Format                             = Json.format[GuaranteeType03]
  implicit lazy val guarantorType06Format                             = Json.format[GuarantorType06]
  implicit lazy val consignmentType01Format                           = Json.format[ConsignmentType01]
  implicit lazy val consignmentType03Format                           = Json.format[ConsignmentType03]
  implicit lazy val consignmentType05Format                           = Json.format[ConsignmentType05]
  implicit lazy val consignmentType06Format                           = Json.format[ConsignmentType06]
  implicit lazy val consignmentType07Format                           = Json.format[ConsignmentType07]
  implicit lazy val consignmentType08Format                           = Json.format[ConsignmentType08]
  implicit lazy val consignmentType22Format                           = Json.format[ConsignmentType22]

  implicit lazy val unloadingRemarkFormat = Json.format[UnloadingRemarkType]

  // Due to various ConsignmentTypes having more than 22 fields, and that Scala 2.12 (and 2.13) only supports Tuples of up to 22 fields,
  // we can't use the standard unapply (as it is not generated).
  //
  // This means we have to do this by constructing a Json object...

  implicit lazy val consignmentType04Reads: Reads[ConsignmentType04] = Reads {
    case x: JsObject =>
      JsSuccess(
        ConsignmentType04(
          (x \ "countryOfDispatch").asOptionString,
          (x \ "countryOfDestination").asOptionString,
          (x \ "containerIndicator").as[Flag],
          (x \ "inlandModeOfTransport").asOptionString,
          (x \ "modeOfTransportAtTheBorder").asOptionString,
          (x \ "grossMass").as[BigDecimal],
          (x \ "referenceNumberUCR").asOptionString,
          (x \ "Carrier").asOption[CarrierType03],
          (x \ "Consignor").asOption[ConsignorType03],
          (x \ "Consignee").asOption[ConsigneeType04],
          (x \ "AdditionalSupplyChainActor").asSeq[AdditionalSupplyChainActorType],
          (x \ "TransportEquipment").asSeq[TransportEquipmentType05],
          (x \ "LocationOfGoods").asOption[LocationOfGoodsType02],
          (x \ "DepartureTransportMeans").asSeq[DepartureTransportMeansType02],
          (x \ "CountryOfRoutingOfConsignment").asSeq[CountryOfRoutingOfConsignmentType01],
          (x \ "ActiveBorderTransportMeans").asSeq[ActiveBorderTransportMeansType01],
          (x \ "PlaceOfLoading").asOption[PlaceOfLoadingType02],
          (x \ "PlaceOfUnloading").asOption[PlaceOfUnloadingType02],
          (x \ "PreviousDocument").asSeq[PreviousDocumentType06],
          (x \ "SupportingDocument").asSeq[SupportingDocumentType06],
          (x \ "TransportDocument").asSeq[TransportDocumentType02],
          (x \ "AdditionalReference").asSeq[AdditionalReferenceType03],
          (x \ "AdditionalInformation").asSeq[AdditionalInformationType02],
          (x \ "TransportCharges").asOption[TransportChargesType],
          (x \ "HouseConsignment").asSeq[HouseConsignmentType03]
        )
      )
    case _ => throw MalformedJsonException("ConsignmentType04 is not an object")
  }

  implicit lazy val consignmentType04Writes: Writes[ConsignmentType04] = Writes {
    consignmentType20 =>
      val seq: Seq[(String, JsValueWrapper)] = Seq.empty[(String, JsValueWrapper)] ++
        consignmentType20.countryOfDispatch.entry("countryOfDispatch") ++
        consignmentType20.countryOfDestination.entry("countryOfDestination") ++
        Seq[(String, JsValueWrapper)]("containerIndicator" -> consignmentType20.containerIndicator) ++
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
//        Seq[(String, JsValueWrapper)]("PlaceOfLoading" -> consignmentType20.PlaceOfLoading) ++
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
          (x \ "AdditionalReference").asSeq[AdditionalReferenceType05],
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

}

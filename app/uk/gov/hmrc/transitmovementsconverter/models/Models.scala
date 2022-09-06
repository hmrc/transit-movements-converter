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
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.OFormat
import play.api.libs.json.__
import scalaxb.DataRecord
import uk.gov.hmrc.transitmovementsconverter.models.ModelExtensionMethods._

import javax.xml.datatype.XMLGregorianCalendar

object Models {

  import uk.gov.hmrc.transitmovementsconverter.models.ModelHelpers._

  // Message Types

  private def commonTypes(root: String) = (__ \ root \ "messageRecipient").format[String] and
    (__ \ root \ "preparationDateAndTime").format[XMLGregorianCalendar] and
    (__ \ root \ "messageIdentification").format[String] and
    (__ \ root \ "messageType").format[MessageTypes] and
    (__ \ root \ "correlationIdentifier").formatNullable[String]

  private def commonTypesWithSender(root: String) =
    (__ \ root \ "messageSender").format[String] and
      (__ \ root \ "messageRecipient").format[String] and
      (__ \ root \ "preparationDateAndTime").format[XMLGregorianCalendar] and
      (__ \ root \ "messageIdentification").format[String] and
      (__ \ root \ "messageType").format[MessageTypes] and
      (__ \ root \ "correlationIdentifier").formatNullable[String]

  // ** CC004C **

  private lazy val cc004cRoot = "n1:CC004C"

  implicit lazy val cc004cFormats: OFormat[CC004CType] = (
    commonTypesWithSender(cc004cRoot) and
      (__ \ cc004cRoot \ "TransitOperation").format[TransitOperationType01] and
      (__ \ cc004cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
      (__ \ cc004cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType20] and
      (__ \ cc004cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
  )(
    (
      messageSender,
      messageRecipient,
      preparationDateAndTime,
      messageIdentification,
      messageType,
      correlationIdentifier,
      TransitOperation,
      CustomsOfficeOfDeparture,
      HolderOfTheTransitProcedure,
      phaseId
    ) =>
      CC004CType(
        MESSAGESequence(
          messageSender,
          MESSAGE_1Sequence(messageRecipient, preparationDateAndTime, messageIdentification, messageType, correlationIdentifier)
        ),
        TransitOperation,
        CustomsOfficeOfDeparture,
        HolderOfTheTransitProcedure,
        phaseId
          .map(
            x => Map("@PhaseID" -> DataRecord(x))
          )
          .getOrElse(Map.empty)
      ),
    {
      obj: CC004CType =>
        val seqType = obj.messageSequence1.messagE_1Sequence2
        (
          obj.messageSequence1.messageSender,
          seqType.messageRecipient,
          seqType.preparationDateAndTime,
          seqType.messageIdentification,
          seqType.messageType,
          seqType.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.PhaseID
        )
    }
  )

  // ** CC009C **

  private lazy val cc009cRoot = "n1:CC009C"

  implicit lazy val cc009cFormats: OFormat[CC009CType] = (
    commonTypesWithSender(cc009cRoot) and
      (__ \ cc009cRoot \ "TransitOperation").format[TransitOperationType03] and
      (__ \ cc009cRoot \ "Invalidation").format[InvalidationType01] and
      (__ \ cc009cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
      (__ \ cc009cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType13] and
      (__ \ cc009cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
  )(
    (
      messageSender,
      messageRecipient,
      preparationDateAndTime,
      messageIdentification,
      messageType,
      correlationIdentifier,
      TransitOperation,
      Invalidation,
      CustomsOfficeOfDeparture,
      HolderOfTheTransitProcedure,
      phaseId
    ) =>
      CC009CType(
        MESSAGESequence(
          messageSender,
          MESSAGE_1Sequence(messageRecipient, preparationDateAndTime, messageIdentification, messageType, correlationIdentifier)
        ),
        TransitOperation,
        Invalidation,
        CustomsOfficeOfDeparture,
        HolderOfTheTransitProcedure,
        phaseId
          .map(
            x => Map("@PhaseID" -> DataRecord(x))
          )
          .getOrElse(Map.empty)
      ),
    {
      obj: CC009CType =>
        val seqType = obj.messageSequence1.messagE_1Sequence2
        (
          obj.messageSequence1.messageSender,
          seqType.messageRecipient,
          seqType.preparationDateAndTime,
          seqType.messageIdentification,
          seqType.messageType,
          seqType.correlationIdentifier,
          obj.TransitOperation,
          obj.Invalidation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.PhaseID
        )
    }
  )

  // ** CC013C **

  private lazy val cc013cRoot = "n1:CC013C"

  implicit lazy val cc013cFormats: OFormat[CC013CType] = (
    commonTypes(cc013cRoot) and
      (__ \ cc013cRoot \ "TransitOperation").format[TransitOperationType04] and
      (__ \ cc013cRoot \ "Authorisation").formatNullable[Seq[AuthorisationType03]] and
      (__ \ cc013cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
      (__ \ cc013cRoot \ "CustomsOfficeOfDestinationDeclared").format[CustomsOfficeOfDestinationDeclaredType01] and
      (__ \ cc013cRoot \ "CustomsOfficeOfTransitDeclared").formatNullable[Seq[CustomsOfficeOfTransitDeclaredType03]] and
      (__ \ cc013cRoot \ "CustomsOfficeOfExitForTransitDeclared").formatNullable[Seq[CustomsOfficeOfExitForTransitDeclaredType02]] and
      (__ \ cc013cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType14] and
      (__ \ cc013cRoot \ "Representative").formatNullable[RepresentativeType05] and
      (__ \ cc013cRoot \ "Guarantee").formatNullable[Seq[GuaranteeType01]] and
      (__ \ cc013cRoot \ "Consignment").format[ConsignmentType20] and
      (__ \ cc013cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
      CC013CType(
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
            x => Map("@PhaseID" -> DataRecord(x))
          )
          .getOrElse(Map.empty)
      ),
    {
      obj: CC013CType =>
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

  // ** CC014C **

  private lazy val cc014cRoot = "n1:CC014C"

  implicit lazy val cc014cFormats: OFormat[CC014CType] =
    (
      commonTypes(cc014cRoot) and
        (__ \ cc014cRoot \ "TransitOperation").format[TransitOperationType05] and
        (__ \ cc014cRoot \ "Invalidation").format[InvalidationType02] and
        (__ \ cc014cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc014cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType02] and
        (__ \ cc014cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
    )(
      (
        messageRecipient,
        preparationDateAndTime,
        messageIdentification,
        messageType,
        correlationIdentifier,
        TransitOperation,
        Invalidation,
        CustomsOfficeOfDeparture,
        HolderOfTheTransitProcedure,
        phaseId
      ) =>
        CC014CType(
          MESSAGE_FROM_TRADERSequence(
            None,
            MESSAGE_1Sequence(messageRecipient, preparationDateAndTime, messageIdentification, messageType, correlationIdentifier)
          ),
          TransitOperation,
          Invalidation,
          CustomsOfficeOfDeparture,
          HolderOfTheTransitProcedure,
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      {
        obj: CC014CType =>
          val seqType = obj.messagE_FROM_TRADERSequence1.messagE_1Sequence2
          (
            seqType.messageRecipient,
            seqType.preparationDateAndTime,
            seqType.messageIdentification,
            seqType.messageType,
            seqType.correlationIdentifier,
            obj.TransitOperation,
            obj.Invalidation,
            obj.CustomsOfficeOfDeparture,
            obj.HolderOfTheTransitProcedure,
            obj.PhaseID
          )
      }
    )

  // ** CC015C **

  private lazy val cc015cRoot = "n1:CC015C"

  implicit lazy val cc015cFormats: OFormat[CC015CType] = (
    commonTypes(cc015cRoot) and
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
            x => Map("@PhaseID" -> DataRecord(x))
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

  // ** CC019C **

  private lazy val cc019cRoot = "n1:CC019C"

  implicit lazy val cc019cFormats: OFormat[CC019CType] =
    (
      commonTypesWithSender(cc019cRoot) and
        (__ \ cc019cRoot \ "TransitOperation").format[TransitOperationType08] and
        (__ \ cc019cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc019cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType20] and
        (__ \ cc019cRoot \ "Guarantor").formatNullable[GuarantorType06] and
        (__ \ cc019cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
    )(
      (
        messageSender,
        messageRecipient,
        preparationDateAndTime,
        messageIdentification,
        messageType,
        correlationIdentifier,
        TransitOperation,
        CustomsOfficeOfDeparture,
        HolderOfTheTransitProcedure,
        Guarantor,
        phaseId
      ) =>
        CC019CType(
          MESSAGESequence(
            messageSender,
            MESSAGE_1Sequence(messageRecipient, preparationDateAndTime, messageIdentification, messageType, correlationIdentifier)
          ),
          TransitOperation,
          CustomsOfficeOfDeparture,
          HolderOfTheTransitProcedure,
          Guarantor,
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      {
        obj: CC019CType =>
          val seqType = obj.messageSequence1.messagE_1Sequence2
          (
            obj.messageSequence1.messageSender,
            seqType.messageRecipient,
            seqType.preparationDateAndTime,
            seqType.messageIdentification,
            seqType.messageType,
            seqType.correlationIdentifier,
            obj.TransitOperation,
            obj.CustomsOfficeOfDeparture,
            obj.HolderOfTheTransitProcedure,
            obj.Guarantor,
            obj.PhaseID
          )
      }
    )

  // ** CC170C **

  private lazy val cc170cRoot = "n1:CC170C"

  implicit lazy val cc170cFormats: OFormat[CC170CType] =
    (
      commonTypes(cc170cRoot) and
        (__ \ cc170cRoot \ "TransitOperation").format[TransitOperationType24] and
        (__ \ cc170cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc170cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType19] and
        (__ \ cc170cRoot \ "Representative").formatNullable[RepresentativeType05] and
        (__ \ cc170cRoot \ "Consignment").format[ConsignmentType08] and
        (__ \ cc170cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
    )(
      (
        messageRecipient,
        preparationDateAndTime,
        messageIdentification,
        messageType,
        correlationIdentifier,
        TransitOperation,
        CustomsOfficeOfDeparture,
        HolderOfTheTransitProcedure,
        Representative,
        Consignment,
        phaseId
      ) =>
        CC170CType(
          MESSAGE_FROM_TRADERSequence(
            None,
            MESSAGE_1Sequence(messageRecipient, preparationDateAndTime, messageIdentification, messageType, correlationIdentifier)
          ),
          TransitOperation,
          CustomsOfficeOfDeparture,
          HolderOfTheTransitProcedure,
          Representative,
          Consignment,
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      {
        obj: CC170CType =>
          val seqType = obj.messagE_FROM_TRADERSequence1.messagE_1Sequence2
          (
            seqType.messageRecipient,
            seqType.preparationDateAndTime,
            seqType.messageIdentification,
            seqType.messageType,
            seqType.correlationIdentifier,
            obj.TransitOperation,
            obj.CustomsOfficeOfDeparture,
            obj.HolderOfTheTransitProcedure,
            obj.Representative,
            obj.Consignment,
            obj.PhaseID
          )
      }
    )

}

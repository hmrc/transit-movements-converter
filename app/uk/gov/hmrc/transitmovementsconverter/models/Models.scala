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

  import uk.gov.hmrc.transitmovementsconverter.models.ModelImplicits._

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

  // ** CC028C **

  private lazy val cc028cRoot = "n1:CC028C"

  implicit lazy val cc028cFormats: OFormat[CC028CType] =
    (
      commonTypesWithSender(cc028cRoot) and
        (__ \ cc028cRoot \ "TransitOperation").format[TransitOperationType11] and
        (__ \ cc028cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc028cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType20] and
        (__ \ cc028cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        CC028CType(
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
        obj: CC028CType =>
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

  // ** CC029C **

  private lazy val cc029cRoot = "n1:CC029C"

  implicit lazy val cc029cFormats: OFormat[CC029CType] =
    (
      commonTypesWithSender(cc029cRoot) and
        (__ \ cc029cRoot \ "TransitOperation").format[TransitOperationType12] and
        (__ \ cc029cRoot \ "Authorisation").formatNullable[Seq[AuthorisationType02]] and
        (__ \ cc029cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc029cRoot \ "CustomsOfficeOfDestinationDeclared").format[CustomsOfficeOfDestinationDeclaredType01] and
        (__ \ cc029cRoot \ "CustomsOfficeOfTransitDeclared").formatNullable[Seq[CustomsOfficeOfTransitDeclaredType03]] and
        (__ \ cc029cRoot \ "CustomsOfficeOfExitForTransitDeclared").formatNullable[Seq[CustomsOfficeOfExitForTransitDeclaredType02]] and
        (__ \ cc029cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType05] and
        (__ \ cc029cRoot \ "Representative").formatNullable[RepresentativeType02] and
        (__ \ cc029cRoot \ "ControlResult").formatNullable[ControlResultType02] and
        (__ \ cc029cRoot \ "Guarantee").formatNullable[Seq[GuaranteeType03]] and
        (__ \ cc029cRoot \ "Consignment").format[ConsignmentType04] and
        (__ \ cc029cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
    )(
      (
        messageSender,
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
        ControlResult,
        Guarantee,
        Consignment,
        phaseId
      ) =>
        CC029CType(
          MESSAGESequence(
            messageSender,
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
          ControlResult,
          Guarantee.getOrElse(Nil),
          Consignment,
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      {
        obj: CC029CType =>
          val seqType = obj.messageSequence1.messagE_1Sequence2
          (
            obj.messageSequence1.messageSender,
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
            obj.ControlResult,
            obj.Guarantee.toOption,
            obj.Consignment,
            obj.PhaseID
          )

      }
    )

  // ** CC035C **

  private lazy val cc035cRoot = "n1:CC035C"

  implicit lazy val cc035cFormats: OFormat[CC035CType] =
    (
      commonTypesWithSender(cc035cRoot) and
        (__ \ cc035cRoot \ "TransitOperation").format[TransitOperationType48] and
        (__ \ cc035cRoot \ "RecoveryNotification").format[RecoveryNotificationType] and
        (__ \ cc035cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc035cRoot \ "CustomsOfficeOfRecoveryAtDeparture").format[CustomsOfficeOfRecoveryAtDepartureType01] and
        (__ \ cc035cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType20] and
        (__ \ cc035cRoot \ "Guarantor").formatNullable[GuarantorType06] and
        (__ \ cc035cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
    )(
      (
        messageSender,
        messageRecipient,
        preparationDateAndTime,
        messageIdentification,
        messageType,
        correlationIdentifier,
        TransitOperation,
        RecoveryNotification,
        CustomsOfficeOfDeparture,
        CustomsOfficeOfRecoveryAtDeparture,
        HolderOfTheTransitProcedure,
        Guarantor,
        phaseId
      ) =>
        CC035CType(
          MESSAGESequence(
            messageSender,
            MESSAGE_1Sequence(messageRecipient, preparationDateAndTime, messageIdentification, messageType, correlationIdentifier)
          ),
          TransitOperation,
          RecoveryNotification,
          CustomsOfficeOfDeparture,
          CustomsOfficeOfRecoveryAtDeparture,
          HolderOfTheTransitProcedure,
          Guarantor,
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      {
        obj: CC035CType =>
          val seqType = obj.messageSequence1.messagE_1Sequence2
          (
            obj.messageSequence1.messageSender,
            seqType.messageRecipient,
            seqType.preparationDateAndTime,
            seqType.messageIdentification,
            seqType.messageType,
            seqType.correlationIdentifier,
            obj.TransitOperation,
            obj.RecoveryNotification,
            obj.CustomsOfficeOfDeparture,
            obj.CustomsOfficeOfRecoveryAtDeparture,
            obj.HolderOfTheTransitProcedure,
            obj.Guarantor,
            obj.PhaseID
          )
      }
    )

  // ** CC045C **

  private lazy val cc045cRoot = "n1:CC045C"

  implicit lazy val cc045cFormats: OFormat[CC045CType] =
    (
      commonTypesWithSender(cc045cRoot) and
        (__ \ cc045cRoot \ "TransitOperation").format[TransitOperationType16] and
        (__ \ cc045cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc045cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType20] and
        (__ \ cc045cRoot \ "Guarantor").formatNullable[GuarantorType06] and
        (__ \ cc045cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        CC045CType(
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
        obj: CC045CType =>
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

  // ** CC051C **

  private lazy val cc051cRoot = "n1:CC051C"

  implicit lazy val cc051cFormats: OFormat[CC051CType] =
    (
      commonTypesWithSender(cc051cRoot) and
        (__ \ cc051cRoot \ "TransitOperation").format[TransitOperationType18] and
        (__ \ cc051cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc051cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType15] and
        (__ \ cc051cRoot \ "Representative").formatNullable[RepresentativeType03] and
        (__ \ cc051cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        Representative,
        phaseId
      ) =>
        CC051CType(
          MESSAGESequence(
            messageSender,
            MESSAGE_1Sequence(messageRecipient, preparationDateAndTime, messageIdentification, messageType, correlationIdentifier)
          ),
          TransitOperation,
          CustomsOfficeOfDeparture,
          HolderOfTheTransitProcedure,
          Representative,
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      {
        obj: CC051CType =>
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
            obj.Representative,
            obj.PhaseID
          )
      }
    )

  // ** CC051C **

  private lazy val cc054cRoot = "n1:CC054C"

  implicit lazy val cc054cFormats: OFormat[CC054CType] =
    (
      commonTypes(cc054cRoot) and
        (__ \ cc054cRoot \ "TransitOperation").format[TransitOperationType19] and
        (__ \ cc054cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc054cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType19] and
        (__ \ cc051cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        phaseId
      ) =>
        CC054CType(
          MESSAGE_FROM_TRADERSequence(
            None,
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
        obj: CC054CType =>
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
            obj.PhaseID
          )
      }
    )

  // ** CC051C **

  private lazy val cc055cRoot = "n1:CC055C"

  implicit lazy val cc055cFormats: OFormat[CC055CType] =
    (
      commonTypesWithSender(cc055cRoot) and
        (__ \ cc055cRoot \ "TransitOperation").format[TransitOperationType48] and
        (__ \ cc055cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc055cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType07] and
        (__ \ cc055cRoot \ "GuaranteeReference").formatNullable[Seq[GuaranteeReferenceType08]] and
        (__ \ cc055cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        GuaranteeReference,
        phaseId
      ) =>
        CC055CType(
          MESSAGESequence(
            messageSender,
            MESSAGE_1Sequence(messageRecipient, preparationDateAndTime, messageIdentification, messageType, correlationIdentifier)
          ),
          TransitOperation,
          CustomsOfficeOfDeparture,
          HolderOfTheTransitProcedure,
          GuaranteeReference.getOrElse(Nil),
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      {
        obj: CC055CType =>
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
            obj.GuaranteeReference.toOption,
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

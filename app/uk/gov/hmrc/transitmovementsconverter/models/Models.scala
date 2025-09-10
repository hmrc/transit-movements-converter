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

import generated.v2_1.*
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.OFormat
import play.api.libs.json.__
import scalaxb.DataRecord
import uk.gov.hmrc.transitmovementsconverter.models.ModelHelperMethods.*

import javax.xml.datatype.XMLGregorianCalendar

object Models {

  import ModelImplicits.*

  // Message Types

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
          messageRecipient,
          preparationDateAndTime,
          messageIdentification,
          messageType,
          correlationIdentifier
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
    obj =>
      (
        obj.messageSequence1.messageSender,
        obj.messageSequence1.messageRecipient,
        obj.messageSequence1.preparationDateAndTime,
        obj.messageSequence1.messageIdentification,
        obj.messageSequence1.messageType,
        obj.messageSequence1.correlationIdentifier,
        obj.TransitOperation,
        obj.CustomsOfficeOfDeparture,
        obj.HolderOfTheTransitProcedure,
        obj.PhaseID
      )
  )

  // ** CC007C **

  private lazy val cc007cRoot = "n1:CC007C"

  implicit lazy val cc007cFormats: OFormat[CC007CType] = (
    commonTypesWithSender(cc007cRoot) and
      (__ \ cc007cRoot \ "TransitOperation").format[TransitOperationType02] and
      (__ \ cc007cRoot \ "Authorisation").format[Seq[AuthorisationType01]] and
      (__ \ cc007cRoot \ "CustomsOfficeOfDestinationActual").format[CustomsOfficeOfDestinationActualType03] and
      (__ \ cc007cRoot \ "TraderAtDestination").format[TraderAtDestinationType01] and
      (__ \ cc007cRoot \ "Consignment").format[ConsignmentType01] and
      (__ \ cc007cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
      CustomsOfficeOfDestinationActual,
      TraderAtDestination,
      Consignment,
      phaseId
    ) =>
      CC007CType(
        MESSAGESequence(
          messageSender,
          messageRecipient,
          preparationDateAndTime,
          messageIdentification,
          messageType,
          correlationIdentifier
        ),
        TransitOperation,
        Authorisation,
        CustomsOfficeOfDestinationActual,
        TraderAtDestination,
        Consignment,
        phaseId
          .map(
            x => Map("@PhaseID" -> DataRecord(x))
          )
          .getOrElse(Map.empty)
      ),
    obj =>
      (
        obj.messageSequence1.messageSender,
        obj.messageSequence1.messageRecipient,
        obj.messageSequence1.preparationDateAndTime,
        obj.messageSequence1.messageIdentification,
        obj.messageSequence1.messageType,
        obj.messageSequence1.correlationIdentifier,
        obj.TransitOperation,
        obj.Authorisation,
        obj.CustomsOfficeOfDestinationActual,
        obj.TraderAtDestination,
        obj.Consignment,
        obj.PhaseID
      )
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
          messageRecipient,
          preparationDateAndTime,
          messageIdentification,
          messageType,
          correlationIdentifier
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
    obj =>
      (
        obj.messageSequence1.messageSender,
        obj.messageSequence1.messageRecipient,
        obj.messageSequence1.preparationDateAndTime,
        obj.messageSequence1.messageIdentification,
        obj.messageSequence1.messageType,
        obj.messageSequence1.correlationIdentifier,
        obj.TransitOperation,
        obj.Invalidation,
        obj.CustomsOfficeOfDeparture,
        obj.HolderOfTheTransitProcedure,
        obj.PhaseID
      )
  )

  // ** CC013C **

  private lazy val cc013cRoot = "n1:CC013C"

  implicit lazy val cc013cFormats: OFormat[CC013CType] = (
    commonTypesWithSender(cc013cRoot) and
      (__ \ cc013cRoot \ "TransitOperation").format[TransitOperationType04] and
      (__ \ cc013cRoot \ "Authorisation").formatNullable[Seq[AuthorisationType03]] and
      (__ \ cc013cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
      (__ \ cc013cRoot \ "CustomsOfficeOfDestinationDeclared").format[CustomsOfficeOfDestinationDeclaredType01] and
      (__ \ cc013cRoot \ "CustomsOfficeOfTransitDeclared").formatNullable[Seq[CustomsOfficeOfTransitDeclaredType04]] and
      (__ \ cc013cRoot \ "CustomsOfficeOfExitForTransitDeclared").formatNullable[Seq[CustomsOfficeOfExitForTransitDeclaredType02]] and
      (__ \ cc013cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType14] and
      (__ \ cc013cRoot \ "Representative").formatNullable[RepresentativeType05] and
      (__ \ cc013cRoot \ "Guarantee").formatNullable[Seq[GuaranteeType01]] and
      (__ \ cc013cRoot \ "Consignment").format[ConsignmentType20] and
      (__ \ cc013cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
      Guarantee,
      Consignment,
      phaseId
    ) =>
      CC013CType(
        MESSAGESequence(
          messageSender,
          messageRecipient,
          preparationDateAndTime,
          messageIdentification,
          messageType,
          correlationIdentifier
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
    obj =>
      (
        obj.messageSequence1.messageSender,
        obj.messageSequence1.messageRecipient,
        obj.messageSequence1.preparationDateAndTime,
        obj.messageSequence1.messageIdentification,
        obj.messageSequence1.messageType,
        obj.messageSequence1.correlationIdentifier,
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
  )

  // ** CC014C **

  private lazy val cc014cRoot = "n1:CC014C"

  implicit lazy val cc014cFormats: OFormat[CC014CType] =
    (
      commonTypesWithSender(cc014cRoot) and
        (__ \ cc014cRoot \ "TransitOperation").format[TransitOperationType05] and
        (__ \ cc014cRoot \ "Invalidation").format[InvalidationType02] and
        (__ \ cc014cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc014cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType02] and
        (__ \ cc014cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        CC014CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
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
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.Invalidation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.PhaseID
        )
    )

  // ** CC015C **

  private lazy val cc015cRoot = "n1:CC015C"

  implicit lazy val cc015cFormats: OFormat[CC015CType] = (
    commonTypesWithSender(cc015cRoot) and
      (__ \ cc015cRoot \ "TransitOperation").format[TransitOperationType06] and
      (__ \ cc015cRoot \ "Authorisation").formatNullable[Seq[AuthorisationType03]] and
      (__ \ cc015cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
      (__ \ cc015cRoot \ "CustomsOfficeOfDestinationDeclared").format[CustomsOfficeOfDestinationDeclaredType01] and
      (__ \ cc015cRoot \ "CustomsOfficeOfTransitDeclared").formatNullable[Seq[CustomsOfficeOfTransitDeclaredType04]] and
      (__ \ cc015cRoot \ "CustomsOfficeOfExitForTransitDeclared").formatNullable[Seq[CustomsOfficeOfExitForTransitDeclaredType02]] and
      (__ \ cc015cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType14] and
      (__ \ cc015cRoot \ "Representative").formatNullable[RepresentativeType05] and
      (__ \ cc015cRoot \ "Guarantee").formatNullable[Seq[GuaranteeType02]] and
      (__ \ cc015cRoot \ "Consignment").format[ConsignmentType20] and
      (__ \ cc015cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
      Guarantee,
      Consignment,
      phaseId
    ) =>
      CC015CType(
        MESSAGESequence(
          messageSender,
          messageRecipient,
          preparationDateAndTime,
          messageIdentification,
          messageType,
          correlationIdentifier
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
    obj =>
      (
        obj.messageSequence1.messageSender,
        obj.messageSequence1.messageRecipient,
        obj.messageSequence1.preparationDateAndTime,
        obj.messageSequence1.messageIdentification,
        obj.messageSequence1.messageType,
        obj.messageSequence1.correlationIdentifier,
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
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
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
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.Guarantor,
          obj.PhaseID
        )
    )

  // ** CC025C **

  private lazy val cc025cRoot = "n1:CC025C"

  implicit lazy val cc025cFormats: OFormat[CC025CType] =
    (
      commonTypesWithSender(cc025cRoot) and
        (__ \ cc025cRoot \ "TransitOperation").format[TransitOperationType10] and
        (__ \ cc025cRoot \ "CustomsOfficeOfDestinationActual").format[CustomsOfficeOfDestinationActualType03] and
        (__ \ cc025cRoot \ "TraderAtDestination").format[TraderAtDestinationType03] and
        (__ \ cc025cRoot \ "Consignment").formatNullable[CUSTOM_ConsignmentType03] and
        (__ \ cc025cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
    )(
      (
        messageSender,
        messageRecipient,
        preparationDateAndTime,
        messageIdentification,
        messageType,
        correlationIdentifier,
        TransitOperation,
        CustomsOfficeOfDestinationActual,
        TraderAtDestination,
        Consignment,
        phaseId
      ) =>
        CC025CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
          ),
          TransitOperation,
          CustomsOfficeOfDestinationActual,
          TraderAtDestination,
          Consignment,
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDestinationActual,
          obj.TraderAtDestination,
          obj.Consignment,
          obj.PhaseID
        )
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
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
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
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.PhaseID
        )
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
        (__ \ cc029cRoot \ "CustomsOfficeOfTransitDeclared").formatNullable[Seq[CustomsOfficeOfTransitDeclaredType04]] and
        (__ \ cc029cRoot \ "CustomsOfficeOfExitForTransitDeclared").formatNullable[Seq[CustomsOfficeOfExitForTransitDeclaredType02]] and
        (__ \ cc029cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType05] and
        (__ \ cc029cRoot \ "Representative").formatNullable[RepresentativeType02] and
        (__ \ cc029cRoot \ "ControlResult").formatNullable[ControlResultType02] and
        (__ \ cc029cRoot \ "Guarantee").formatNullable[Seq[GuaranteeType03]] and
        (__ \ cc029cRoot \ "Consignment").format[CUSTOM_ConsignmentType04] and
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
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
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
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
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
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
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
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.RecoveryNotification,
          obj.CustomsOfficeOfDeparture,
          obj.CustomsOfficeOfRecoveryAtDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.Guarantor,
          obj.PhaseID
        )
    )

  // ** CC043C **
  private lazy val cc043cRoot = "n1:CC043C"

  implicit lazy val cc043cFormats: OFormat[CC043CType] =
    (
      commonTypesWithSender(cc043cRoot) and
        (__ \ cc043cRoot \ "TransitOperation").format[TransitOperationType14] and
        (__ \ cc043cRoot \ "CustomsOfficeOfDestinationActual").format[CustomsOfficeOfDestinationActualType03] and
        (__ \ cc043cRoot \ "HolderOfTheTransitProcedure").formatNullable[HolderOfTheTransitProcedureType06] and
        (__ \ cc043cRoot \ "TraderAtDestination").format[TraderAtDestinationType03] and
        (__ \ cc043cRoot \ "CTLControl").formatNullable[CTLControlType] and
        (__ \ cc043cRoot \ "Consignment").formatNullable[CUSTOM_ConsignmentType05] and
        (__ \ cc043cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
    )(
      (
        messageSender,
        messageRecipient,
        preparationDateAndTime,
        messageIdentification,
        messageType,
        correlationIdentifier,
        TransitOperation,
        CustomsOfficeOfDestinationActual,
        HolderOfTheTransitProcedure,
        TraderAtDestination,
        CTLControl,
        Consignment,
        phaseId
      ) =>
        CC043CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
          ),
          TransitOperation,
          CustomsOfficeOfDestinationActual,
          HolderOfTheTransitProcedure,
          TraderAtDestination,
          CTLControl,
          Consignment,
          phaseId
            .map(
              phase => Map("@PhaseID" -> DataRecord(phase))
            )
            .getOrElse(Map.empty)
        ),
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDestinationActual,
          obj.HolderOfTheTransitProcedure,
          obj.TraderAtDestination,
          obj.CTLControl,
          obj.Consignment,
          obj.PhaseID
        )
    )

  // ** CC044C **
  private lazy val cc044cRoot = "n1:CC044C"

  implicit lazy val cc044cFormats: OFormat[CC044CType] =
    (
      commonTypesWithSender(cc044cRoot) and
        (__ \ cc044cRoot \ "TransitOperation").format[TransitOperationType15] and
        (__ \ cc044cRoot \ "CustomsOfficeOfDestinationActual").format[CustomsOfficeOfDestinationActualType03] and
        (__ \ cc044cRoot \ "TraderAtDestination").format[TraderAtDestinationType02] and
        (__ \ cc044cRoot \ "UnloadingRemark").format[UnloadingRemarkType] and
        (__ \ cc044cRoot \ "Consignment").formatNullable[ConsignmentType06] and
        (__ \ cc044cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
    )(
      (
        messageSender,
        messageRecipient,
        preparationDateAndTime,
        messageIdentification,
        messageType,
        correlationIdentifier,
        TransitOperation,
        CustomsOfficeOfDestinationActual,
        TraderAtDestination,
        UnloadingRemark,
        Consignment,
        phaseId
      ) =>
        CC044CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
          ),
          TransitOperation,
          CustomsOfficeOfDestinationActual,
          TraderAtDestination,
          UnloadingRemark,
          Consignment,
          phaseId
            .map(
              phase => Map("@PhaseID" -> DataRecord(phase))
            )
            .getOrElse(Map.empty)
        ),
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDestinationActual,
          obj.TraderAtDestination,
          obj.UnloadingRemark,
          obj.Consignment,
          obj.PhaseID
        )
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
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
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
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.Guarantor,
          obj.PhaseID
        )
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
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
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
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.Representative,
          obj.PhaseID
        )
    )

  // ** CC055C **

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
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
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
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.GuaranteeReference.toOption,
          obj.PhaseID
        )
    )

  // ** CC056C **

  private lazy val cc056cRoot = "n1:CC056C"

  implicit lazy val cc056cFormats: OFormat[CC056CType] =
    (
      commonTypesWithSender(cc056cRoot) and
        (__ \ cc056cRoot \ "TransitOperation").format[TransitOperationType20] and
        (__ \ cc056cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc056cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType08] and
        (__ \ cc056cRoot \ "Representative").formatNullable[RepresentativeType01] and
        (__ \ cc056cRoot \ "FunctionalError").formatNullable[Seq[FunctionalErrorType04]] and
        (__ \ cc056cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        FunctionalError,
        phaseId
      ) =>
        CC056CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
          ),
          TransitOperation,
          CustomsOfficeOfDeparture,
          HolderOfTheTransitProcedure,
          Representative,
          FunctionalError.getOrElse(Nil),
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.Representative,
          obj.FunctionalError.toOption,
          obj.PhaseID
        )
    )

  // ** CC057C **

  private lazy val cc057cRoot = "n1:CC057C"

  implicit lazy val cc057cFormats: OFormat[CC057CType] =
    (
      commonTypesWithSender(cc057cRoot) and
        (__ \ cc057cRoot \ "TransitOperation").format[TransitOperationType21] and
        (__ \ cc057cRoot \ "CustomsOfficeOfDestinationActual").format[CustomsOfficeOfDestinationActualType03] and
        (__ \ cc057cRoot \ "TraderAtDestination").format[TraderAtDestinationType03] and
        (__ \ cc057cRoot \ "FunctionalError").formatNullable[Seq[FunctionalErrorType04]] and
        (__ \ cc057cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
    )(
      (
        messageSender,
        messageRecipient,
        preparationDateAndTime,
        messageIdentification,
        messageType,
        correlationIdentifier,
        TransitOperation,
        CustomsOfficeOfDestinationActual,
        TraderAtDestination,
        FunctionalError,
        phaseId
      ) =>
        CC057CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
          ),
          TransitOperation,
          CustomsOfficeOfDestinationActual,
          TraderAtDestination,
          FunctionalError.getOrElse(Nil),
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDestinationActual,
          obj.TraderAtDestination,
          obj.FunctionalError.toOption,
          obj.PhaseID
        )
    )

  // ** CC060C **

  private lazy val cc060cRoot = "n1:CC060C"

  implicit lazy val cc060cFormats: OFormat[CC060CType] =
    (
      commonTypesWithSender(cc060cRoot) and
        (__ \ cc060cRoot \ "TransitOperation").format[TransitOperationType22] and
        (__ \ cc060cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc060cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType13] and
        (__ \ cc060cRoot \ "Representative").formatNullable[RepresentativeType04] and
        (__ \ cc060cRoot \ "TypeOfControls").formatNullable[Seq[TypeOfControlsType]] and
        (__ \ cc060cRoot \ "RequestedDocument").formatNullable[Seq[RequestedDocumentType]] and
        (__ \ cc060cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        TypeOfControls,
        RequestedDocument,
        phaseId
      ) =>
        CC060CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
          ),
          TransitOperation,
          CustomsOfficeOfDeparture,
          HolderOfTheTransitProcedure,
          Representative,
          TypeOfControls.getOrElse(Nil),
          RequestedDocument.getOrElse(Nil),
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.Representative,
          obj.TypeOfControls.toOption,
          obj.RequestedDocument.toOption,
          obj.PhaseID
        )
    )

  // ** CC140C **

  private lazy val cc140cRoot = "n1:CC140C"

  implicit lazy val cc140cFormats: OFormat[CC140CType] =
    (
      commonTypesWithSender(cc140cRoot) and
        (__ \ cc140cRoot \ "TransitOperation").format[TransitOperationType23] and
        (__ \ cc140cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc140cRoot \ "CustomsOfficeOfEnquiryAtDeparture").format[CustomsOfficeOfEnquiryAtDepartureType01] and
        (__ \ cc140cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType20] and
        (__ \ cc140cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        CustomsOfficeOfEnquiryAtDeparture,
        HolderOfTheTransitProcedure,
        phaseId
      ) =>
        CC140CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
          ),
          TransitOperation,
          CustomsOfficeOfDeparture,
          CustomsOfficeOfEnquiryAtDeparture,
          HolderOfTheTransitProcedure,
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.CustomsOfficeOfEnquiryAtDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.PhaseID
        )
    )

  // ** CC140C **

  private lazy val cc141cRoot = "n1:CC141C"

  implicit lazy val cc141cFormats: OFormat[CC141CType] =
    (
      commonTypesWithSender(cc141cRoot) and
        (__ \ cc141cRoot \ "TransitOperation").format[TransitOperationType43] and
        (__ \ cc141cRoot \ "CustomsOfficeOfDestinationActual").formatNullable[CustomsOfficeOfDestinationActualType01] and
        (__ \ cc141cRoot \ "CustomsOfficeOfEnquiryAtDeparture").format[CustomsOfficeOfEnquiryAtDepartureType01] and
        (__ \ cc141cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType19] and
        (__ \ cc141cRoot \ "Enquiry").formatNullable[EnquiryType01] and
        (__ \ cc141cRoot \ "Consignment").formatNullable[ConsignmentType07] and
        (__ \ cc141cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
    )(
      (
        messageSender,
        messageRecipient,
        preparationDateAndTime,
        messageIdentification,
        messageType,
        correlationIdentifier,
        TransitOperation,
        CustomsOfficeOfDestinationActual,
        CustomsOfficeOfEnquiryAtDeparture,
        HolderOfTheTransitProcedure,
        Enquiry,
        Consignment,
        phaseId
      ) =>
        CC141CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
          ),
          TransitOperation,
          CustomsOfficeOfDestinationActual,
          CustomsOfficeOfEnquiryAtDeparture,
          HolderOfTheTransitProcedure,
          Enquiry,
          Consignment,
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDestinationActual,
          obj.CustomsOfficeOfEnquiryAtDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.Enquiry,
          obj.Consignment,
          obj.PhaseID
        )
    )

  // ** CC170C **

  private lazy val cc170cRoot = "n1:CC170C"

  implicit lazy val cc170cFormats: OFormat[CC170CType] =
    (
      commonTypesWithSender(cc170cRoot) and
        (__ \ cc170cRoot \ "TransitOperation").format[TransitOperationType24] and
        (__ \ cc170cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc170cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType19] and
        (__ \ cc170cRoot \ "Representative").formatNullable[RepresentativeType05] and
        (__ \ cc170cRoot \ "Consignment").format[ConsignmentType08] and
        (__ \ cc170cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        Consignment,
        phaseId
      ) =>
        CC170CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
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
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.Representative,
          obj.Consignment,
          obj.PhaseID
        )
    )

  // ** CC182C **

  private lazy val cc182cRoot = "n1:CC182C"

  implicit lazy val cc182cFormats: OFormat[CC182CType] =
    (
      commonTypesWithSender(cc182cRoot) and
        (__ \ cc182cRoot \ "TransitOperation").format[TransitOperationType47] and
        (__ \ cc182cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc182cRoot \ "CustomsOfficeOfIncidentRegistration").format[CustomsOfficeOfIncidentRegistrationType02] and
        (__ \ cc182cRoot \ "Consignment").format[ConsignmentType22] and
        (__ \ cc182cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        CustomsOfficeOfIncidentRegistration,
        Consignment,
        phaseId
      ) =>
        CC182CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
          ),
          TransitOperation,
          CustomsOfficeOfDeparture,
          CustomsOfficeOfIncidentRegistration,
          Consignment,
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.CustomsOfficeOfIncidentRegistration,
          obj.Consignment,
          obj.PhaseID
        )
    )

  // ** CC906C **

  private lazy val cc906cRoot = "n1:CC906C"

  implicit lazy val cc906cFormats: OFormat[CC906CType] =
    (
      commonTypesWithSender(cc906cRoot) and
        (__ \ cc906cRoot \ "Header").format[HeaderType01] and
        (__ \ cc906cRoot \ "FunctionalError").formatNullable[Seq[FunctionalErrorType02]] and
        (__ \ cc906cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
    )(
      (
        messageSender,
        messageRecipient,
        preparationDateAndTime,
        messageIdentification,
        messageType,
        correlationIdentifier,
        Header,
        FunctionalError,
        phaseId
      ) =>
        CC906CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
          ),
          Header,
          FunctionalError.getOrElse(Nil),
          phaseId
            .map(
              x => Map("@PhaseID" -> DataRecord(x))
            )
            .getOrElse(Map.empty)
        ),
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.Header,
          obj.FunctionalError.toOption,
          obj.PhaseID
        )
    )

  // ** CC928C **

  private lazy val cc928cRoot = "n1:CC928C"

  implicit lazy val cc928cFormats: OFormat[CC928CType] =
    (
      commonTypesWithSender(cc928cRoot) and
        (__ \ cc928cRoot \ "TransitOperation").format[TransitOperationType26] and
        (__ \ cc928cRoot \ "CustomsOfficeOfDeparture").format[CustomsOfficeOfDepartureType03] and
        (__ \ cc928cRoot \ "HolderOfTheTransitProcedure").format[HolderOfTheTransitProcedureType20] and
        (__ \ cc928cRoot \ "@PhaseID").formatNullable[PhaseIDtype]
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
        CC928CType(
          MESSAGESequence(
            messageSender,
            messageRecipient,
            preparationDateAndTime,
            messageIdentification,
            messageType,
            correlationIdentifier
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
      obj =>
        (
          obj.messageSequence1.messageSender,
          obj.messageSequence1.messageRecipient,
          obj.messageSequence1.preparationDateAndTime,
          obj.messageSequence1.messageIdentification,
          obj.messageSequence1.messageType,
          obj.messageSequence1.correlationIdentifier,
          obj.TransitOperation,
          obj.CustomsOfficeOfDeparture,
          obj.HolderOfTheTransitProcedure,
          obj.PhaseID
        )
    )

}

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

package uk.gov.hmrc.transitmovementsconverter.v2_1.models.testobjects

import play.api.libs.json.*

import scala.xml.NodeSeq

object CC057CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1: NodeSeq = <ncts:CC057C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC057C</messageType>
    <!--Optional:-->
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <MRN>string</MRN>
      <businessRejectionType>token</businessRejectionType>
      <rejectionDateAndTime>2014-06-09T16:15:04+01:00</rejectionDateAndTime>
      <rejectionCode>token</rejectionCode>
      <!--Optional:-->
      <rejectionReason>string</rejectionReason>
    </TransitOperation>
    <CustomsOfficeOfDestinationActual>
      <referenceNumber>stringst</referenceNumber>
    </CustomsOfficeOfDestinationActual>
    <TraderAtDestination>
      <identificationNumber>string</identificationNumber>
    </TraderAtDestination>
    <!--0 to 9999 repetitions:-->
    <FunctionalError>
      <errorPointer>string</errorPointer>
      <errorCode>13</errorCode>
      <errorReason>string</errorReason>
      <!--Optional:-->
      <originalAttributeValue>string</originalAttributeValue>
    </FunctionalError>
  </ncts:CC057C>

  lazy val json1: JsValue = Json.parse(
    """
      |{
      |   "n1:CC057C":{
      |   "TraderAtDestination":{
      |     "identificationNumber":"string"
      |     },
      |     "TransitOperation":{
      |       "MRN":"string",
      |       "businessRejectionType":"token",
      |       "rejectionDateAndTime":"2014-06-09T16:15:04+01:00",
      |       "rejectionCode":"token",
      |       "rejectionReason":"string"
      |     },
      |     "preparationDateAndTime":"2007-10-26T07:36:28",
      |     "messageType":"CC057C",
      |     "@PhaseID":"NCTS5.0",
      |     "FunctionalError":[
      |       {
      |         "errorPointer":"string",
      |         "errorCode":"13",
      |         "errorReason":"string",
      |         "originalAttributeValue":"string"
      |         }
      |      ],
      |      "correlationIdentifier":"token",
      |      "messageSender":"token",
      |      "messageRecipient":"token",
      |      "messageIdentification":"token",
      |      "CustomsOfficeOfDestinationActual":{
      |       "referenceNumber":"stringst"
      |       }
      |     }
      |   }
      |""".stripMargin
  )
}

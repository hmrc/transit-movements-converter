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

object CC140CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1: NodeSeq = <ncts:CC140C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC140C</messageType>
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <MRN>string</MRN>
      <requestOnNonArrivedMovementDate>2014-06-09+01:00</requestOnNonArrivedMovementDate>
      <limitForResponseDate>2008-11-15</limitForResponseDate>
    </TransitOperation>
    <CustomsOfficeOfDeparture>
      <referenceNumber>stringst</referenceNumber>
    </CustomsOfficeOfDeparture>
    <CustomsOfficeOfEnquiryAtDeparture>
      <referenceNumber>stringst</referenceNumber>
    </CustomsOfficeOfEnquiryAtDeparture>
    <HolderOfTheTransitProcedure>
      <identificationNumber>string</identificationNumber>
      <TIRHolderIdentificationNumber>string</TIRHolderIdentificationNumber>
      <name>string</name>
      <Address>
        <streetAndNumber>string</streetAndNumber>
        <postcode>string</postcode>
        <city>string</city>
        <country>st</country>
      </Address>
    </HolderOfTheTransitProcedure>
  </ncts:CC140C>

  lazy val json1: JsValue = Json.parse(
    """
      |{
      |  "n1:CC140C":
      |    {
      |      "preparationDateAndTime": "2007-10-26T07:36:28",
      |      "TransitOperation":
      |    {
      |      "MRN": "string",
      |      "requestOnNonArrivedMovementDate": "2014-06-09+01:00",
      |      "limitForResponseDate": "2008-11-15"
      |    },
      |      "CustomsOfficeOfDeparture":
      |    {
      |      "referenceNumber": "stringst"
      |    },
      |      "messageType": "CC140C",
      |      "CustomsOfficeOfEnquiryAtDeparture":
      |    {
      |      "referenceNumber": "stringst"
      |    },
      |      "@PhaseID": "NCTS5.0",
      |      "correlationIdentifier": "token",
      |      "messageSender": "token",
      |      "messageRecipient": "token",
      |      "HolderOfTheTransitProcedure":
      |    {
      |      "identificationNumber": "string",
      |      "TIRHolderIdentificationNumber": "string",
      |      "name": "string",
      |      "Address":
      |    {
      |      "streetAndNumber": "string",
      |      "postcode": "string",
      |      "city": "string",
      |      "country": "st"
      |    }
      |    },
      |      "messageIdentification": "token"
      |    }
      |}
      |""".stripMargin
  )
}

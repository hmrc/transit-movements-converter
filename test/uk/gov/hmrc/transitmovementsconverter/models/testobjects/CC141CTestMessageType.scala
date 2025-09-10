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

package uk.gov.hmrc.transitmovementsconverter.models.testobjects

import play.api.libs.json.*

import scala.xml.NodeSeq

object CC141CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1: NodeSeq = <ncts:CC141C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC141C</messageType>
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <MRN>string</MRN>
    </TransitOperation>
    <CustomsOfficeOfDestinationActual>
      <referenceNumber>stringst</referenceNumber>
    </CustomsOfficeOfDestinationActual>
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
    <Enquiry>
      <TC11DeliveryDate>2014-06-09+01:00</TC11DeliveryDate>
      <text>string</text>
    </Enquiry>
    <Consignment>
      <ConsigneeActual>
        <identificationNumber>string</identificationNumber>
        <name>string</name>
        <Address>
          <streetAndNumber>string</streetAndNumber>
          <postcode>string</postcode>
          <city>string</city>
          <country>st</country>
        </Address>
      </ConsigneeActual>
    </Consignment>
  </ncts:CC141C>

  lazy val json1: JsValue = Json.parse(
    """
      |{
      |    "n1:CC141C":
      |    {
      |        "preparationDateAndTime": "2007-10-26T07:36:28",
      |        "TransitOperation":
      |        {
      |            "MRN": "string"
      |        },
      |        "messageSender": "token",
      |        "Consignment":
      |        {
      |            "ConsigneeActual":
      |            {
      |                "identificationNumber": "string",
      |                "name": "string",
      |                "Address":
      |                {
      |                    "streetAndNumber": "string",
      |                    "postcode": "string",
      |                    "city": "string",
      |                    "country": "st"
      |                }
      |            }
      |        },
      |        "Enquiry":
      |        {
      |            "TC11DeliveryDate": "2014-06-09+01:00",
      |            "text": "string"
      |        },
      |        "messageType": "CC141C",
      |        "CustomsOfficeOfEnquiryAtDeparture":
      |        {
      |            "referenceNumber": "stringst"
      |        },
      |        "@PhaseID": "NCTS5.0",
      |        "correlationIdentifier": "token",
      |        "messageRecipient": "token",
      |        "HolderOfTheTransitProcedure":
      |        {
      |            "identificationNumber": "string",
      |            "TIRHolderIdentificationNumber": "string",
      |            "name": "string",
      |            "Address":
      |            {
      |                "streetAndNumber": "string",
      |                "postcode": "string",
      |                "city": "string",
      |                "country": "st"
      |            }
      |        },
      |        "messageIdentification": "token",
      |        "CustomsOfficeOfDestinationActual":
      |        {
      |            "referenceNumber": "stringst"
      |        }
      |    }
      |}
      |""".stripMargin
  )
}

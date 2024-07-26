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

import play.api.libs.json.JsValue
import play.api.libs.json.Json

import scala.xml.NodeSeq

object CC055CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1: NodeSeq = <ncts:CC055C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC055C</messageType>
    <!--Optional:-->
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <MRN>string</MRN>
      <declarationAcceptanceDate>2014-06-09+01:00</declarationAcceptanceDate>
    </TransitOperation>
    <CustomsOfficeOfDeparture>
      <referenceNumber>stringst</referenceNumber>
    </CustomsOfficeOfDeparture>
    <HolderOfTheTransitProcedure>
      <!--Optional:-->
      <identificationNumber>string</identificationNumber>
      <!--Optional:-->
      <name>string</name>
      <!--Optional:-->
      <Address>
        <streetAndNumber>string</streetAndNumber>
        <!--Optional:-->
        <postcode>string</postcode>
        <city>string</city>
        <country>st</country>
      </Address>
    </HolderOfTheTransitProcedure>
    <!--1 to 99 repetitions:-->
    <GuaranteeReference>
      <sequenceNumber>token</sequenceNumber>
      <GRN>string</GRN>
      <InvalidGuaranteeReason>
        <sequenceNumber>token</sequenceNumber>
        <code>token</code>
        <!--Optional:-->
        <text>string</text>
      </InvalidGuaranteeReason>
    </GuaranteeReference>
  </ncts:CC055C>

  lazy val json1: JsValue = Json.parse(
    """
      |{
      |    "n1:CC055C": {
      |        "preparationDateAndTime": "2007-10-26T07:36:28",
      |        "TransitOperation": {
      |            "MRN": "string",
      |            "declarationAcceptanceDate": "2014-06-09+01:00"
      |        },
      |        "CustomsOfficeOfDeparture": {
      |            "referenceNumber": "stringst"
      |        },
      |        "messageType": "CC055C",
      |        "@PhaseID": "NCTS5.0",
      |        "correlationIdentifier": "token",
      |        "messageSender": "token",
      |        "messageRecipient": "token",
      |        "HolderOfTheTransitProcedure": {
      |            "identificationNumber": "string",
      |            "name": "string",
      |            "Address": {
      |                "streetAndNumber": "string",
      |                "postcode": "string",
      |                "city": "string",
      |                "country": "st"
      |            }
      |        },
      |        "GuaranteeReference": [
      |            {
      |                "sequenceNumber": "token",
      |                "GRN": "string",
      |                "InvalidGuaranteeReason": [ {
      |                    "sequenceNumber": "token",
      |                    "code": "token",
      |                    "text": "string"
      |                }
      |                ]
      |            }
      |        ],
      |        "messageIdentification": "token"
      |    }
      |}
      |""".stripMargin
  )
}

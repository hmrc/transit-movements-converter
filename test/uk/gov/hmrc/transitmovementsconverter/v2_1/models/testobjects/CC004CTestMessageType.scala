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

object CC004CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1 = <ncts:CC004C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC004C</messageType>
    <!--Optional:-->
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <!--Optional:-->
      <LRN>string</LRN>
      <!--Optional:-->
      <MRN>string</MRN>
      <amendmentSubmissionDateAndTime>2014-06-09T16:15:04+01:00</amendmentSubmissionDateAndTime>
      <amendmentAcceptanceDateAndTime>2008-11-15T16:52:58</amendmentAcceptanceDateAndTime>
    </TransitOperation>
    <CustomsOfficeOfDeparture>
      <referenceNumber>stringst</referenceNumber>
    </CustomsOfficeOfDeparture>
    <HolderOfTheTransitProcedure>
      <!--Optional:-->
      <identificationNumber>string</identificationNumber>
      <!--Optional:-->
      <TIRHolderIdentificationNumber>string</TIRHolderIdentificationNumber>
      <!--Optional:-->
      <name>string</name>
      <!--Optional:-->
      <Address>
        <streetAndNumber>string</streetAndNumber>
        <!--Optional:-->
        <postcode>string</postcode>
        <city>string</city>
        <country>GB</country>
      </Address>
    </HolderOfTheTransitProcedure>
  </ncts:CC004C>

  lazy val json1 = Json.parse(
    """
      |{
      |    "n1:CC004C": {
      |        "preparationDateAndTime": "2007-10-26T07:36:28",
      |        "TransitOperation": {
      |            "LRN": "string",
      |            "MRN": "string",
      |            "amendmentSubmissionDateAndTime": "2014-06-09T16:15:04+01:00",
      |            "amendmentAcceptanceDateAndTime": "2008-11-15T16:52:58"
      |        },
      |        "CustomsOfficeOfDeparture": {
      |            "referenceNumber": "stringst"
      |        },
      |        "messageType": "CC004C",
      |        "@PhaseID": "NCTS5.0",
      |        "correlationIdentifier": "token",
      |        "messageSender": "token",
      |        "messageRecipient": "token",
      |        "HolderOfTheTransitProcedure": {
      |            "identificationNumber": "string",
      |            "TIRHolderIdentificationNumber": "string",
      |            "name": "string",
      |            "Address": {
      |                "streetAndNumber": "string",
      |                "postcode": "string",
      |                "city": "string",
      |                "country": "GB"
      |            }
      |        },
      |        "messageIdentification": "token"
      |    }
      |}
      |""".stripMargin
  )

}

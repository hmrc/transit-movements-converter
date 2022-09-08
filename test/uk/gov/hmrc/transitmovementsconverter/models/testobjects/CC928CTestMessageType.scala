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

package uk.gov.hmrc.transitmovementsconverter.models.testobjects

import play.api.libs.json.Json

object CC928CTestMessageType extends TestMessageType {

  lazy val xml1 = <ncts:CC928C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC928C</messageType>
    <!--Optional:-->
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <LRN>string</LRN>
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
        <country>st</country>
      </Address>
    </HolderOfTheTransitProcedure>
  </ncts:CC928C>

  lazy val json1 = Json.parse(
    """
        |{
        |    "n1:CC928C": {
        |        "preparationDateAndTime": "2007-10-26T07:36:28",
        |        "TransitOperation": {
        |            "LRN": "string"
        |        },
        |        "CustomsOfficeOfDeparture": {
        |            "referenceNumber": "stringst"
        |        },
        |        "messageType": "CC928C",
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
        |                "country": "st"
        |            }
        |        },
        |        "messageIdentification": "token"
        |    }
        |}
        |""".stripMargin
  )

}

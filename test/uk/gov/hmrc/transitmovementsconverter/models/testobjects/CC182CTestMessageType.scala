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

object CC182CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1 = <ncts:CC182C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC182C</messageType>
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <MRN>string</MRN>
      <incidentNotificationDateAndTime>2014-06-09T16:15:04+01:00</incidentNotificationDateAndTime>
    </TransitOperation>
    <CustomsOfficeOfDeparture>
      <referenceNumber>stringst</referenceNumber>
    </CustomsOfficeOfDeparture>
    <CustomsOfficeOfIncidentRegistration>
      <referenceNumber>stringst</referenceNumber>
    </CustomsOfficeOfIncidentRegistration>
    <Consignment>
      <Incident>
        <sequenceNumber>100</sequenceNumber>
        <code>token</code>
        <text>string</text>
        <Endorsement>
          <date>2008-11-15</date>
          <authority>string</authority>
          <place>string</place>
          <country>st</country>
        </Endorsement>
        <Location>
          <qualifierOfIdentification>token</qualifierOfIdentification>
          <UNLocode>token</UNLocode>
          <country>st</country>
          <GNSS>
            <latitude>string</latitude>
            <longitude>string</longitude>
          </GNSS>
          <Address>
            <streetAndNumber>string</streetAndNumber>
            <postcode>string</postcode>
            <city>string</city>
          </Address>
        </Location>
        <TransportEquipment>
          <sequenceNumber>100</sequenceNumber>
          <containerIdentificationNumber>string</containerIdentificationNumber>
          <numberOfSeals>100</numberOfSeals>
          <Seal>
            <sequenceNumber>100</sequenceNumber>           <identifier>string</identifier>
          </Seal>
          <GoodsReference>
            <sequenceNumber>100</sequenceNumber>
           <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
          </GoodsReference>
        </TransportEquipment>
        <Transhipment>
          <containerIndicator>0</containerIndicator>
          <TransportMeans>
            <typeOfIdentification>token</typeOfIdentification>
            <identificationNumber>string</identificationNumber>
            <nationality>st</nationality>
          </TransportMeans>
        </Transhipment>
      </Incident>
    </Consignment>
  </ncts:CC182C>

  lazy val json1 = Json.parse(
    """
      |{
      |  "n1:CC182C": {
      |    "messageSender": "token",
      |    "messageRecipient": "token",
      |    "preparationDateAndTime": "2007-10-26T07:36:28",
      |    "messageIdentification": "token",
      |    "messageType": "CC182C",
      |    "correlationIdentifier": "token",
      |    "TransitOperation": {
      |      "MRN": "string",
      |      "incidentNotificationDateAndTime": "2014-06-09T16:15:04+01:00"
      |    },
      |    "CustomsOfficeOfDeparture": {
      |      "referenceNumber": "stringst"
      |    },
      |    "CustomsOfficeOfIncidentRegistration": {
      |      "referenceNumber": "stringst"
      |    },
      |    "Consignment": {
      |      "Incident": [
      |        {
      |          "sequenceNumber": 100,
      |          "code": "token",
      |          "text": "string",
      |          "Endorsement": {
      |            "date": "2008-11-15",
      |            "authority": "string",
      |            "place": "string",
      |            "country": "st"
      |          },
      |          "Location": {
      |            "qualifierOfIdentification": "token",
      |            "UNLocode": "token",
      |            "country": "st",
      |            "GNSS": {
      |              "latitude": "string",
      |              "longitude": "string"
      |            },
      |            "Address": {
      |              "streetAndNumber": "string",
      |              "postcode": "string",
      |              "city": "string"
      |            }
      |          },
      |          "TransportEquipment": [
      |            {
      |              "sequenceNumber": 100,
      |              "containerIdentificationNumber": "string",
      |              "numberOfSeals": 100,
      |              "Seal": [
      |                {
      |                  "sequenceNumber": 100,
      |                  "identifier": "string"
      |                }
      |              ],
      |              "GoodsReference": [
      |                {
      |                  "sequenceNumber": 100,
      |                  "declarationGoodsItemNumber": 100
      |                }
      |              ]
      |            }
      |          ],
      |          "Transhipment": {
      |            "containerIndicator": "0",
      |            "TransportMeans": {
      |              "typeOfIdentification": "token",
      |              "identificationNumber": "string",
      |              "nationality": "st"
      |            }
      |          }
      |        }
      |      ]
      |    },
      |    "@PhaseID": "NCTS5.0"
      |  }
      |}
        |""".stripMargin
  )

}

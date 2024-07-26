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

object CC170CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1 = <ncts:CC170C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>token</messageSender>
      <messageRecipient>token</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>token</messageIdentification>
      <messageType>CC170C</messageType>
      <!--Optional:-->
      <correlationIdentifier>token</correlationIdentifier>
      <TransitOperation>
        <LRN>string</LRN>
        <!--Optional:-->
        <limitDate>2014-06-09+01:00</limitDate>
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
      <!--Optional:-->
      <Representative>
        <identificationNumber>string</identificationNumber>
        <status>token</status>
        <!--Optional:-->
        <ContactPerson>
          <name>string</name>
          <phoneNumber>token</phoneNumber>
          <!--Optional:-->
          <eMailAddress>string</eMailAddress>
        </ContactPerson>
      </Representative>
      <Consignment>
        <!--Optional:-->
        <containerIndicator>1</containerIndicator>
        <!--Optional:-->
        <inlandModeOfTransport>token</inlandModeOfTransport>
        <!--Optional:-->
        <modeOfTransportAtTheBorder>token</modeOfTransportAtTheBorder>
        <!--0 to 9999 repetitions:-->
        <TransportEquipment>
          <sequenceNumber>token</sequenceNumber>
          <!--Optional:-->
          <containerIdentificationNumber>string</containerIdentificationNumber>
          <numberOfSeals>100</numberOfSeals>
          <!--0 to 99 repetitions:-->
          <Seal>
            <sequenceNumber>token</sequenceNumber>
            <identifier>string</identifier>
          </Seal>
          <!--0 to 9999 repetitions:-->
          <GoodsReference>
            <sequenceNumber>token</sequenceNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
          </GoodsReference>
        </TransportEquipment>
        <LocationOfGoods>
          <typeOfLocation>token</typeOfLocation>
          <qualifierOfIdentification>token</qualifierOfIdentification>
          <!--Optional:-->
          <authorisationNumber>string</authorisationNumber>
          <!--Optional:-->
          <additionalIdentifier>stri</additionalIdentifier>
          <!--Optional:-->
          <UNLocode>token</UNLocode>
          <!--Optional:-->
          <CustomsOffice>
            <referenceNumber>stringst</referenceNumber>
          </CustomsOffice>
          <!--Optional:-->
          <GNSS>
            <latitude>string</latitude>
            <longitude>string</longitude>
          </GNSS>
          <!--Optional:-->
          <EconomicOperator>
            <identificationNumber>string</identificationNumber>
          </EconomicOperator>
          <!--Optional:-->
          <Address>
            <streetAndNumber>string</streetAndNumber>
            <!--Optional:-->
            <postcode>string</postcode>
            <city>string</city>
            <country>st</country>
          </Address>
          <!--Optional:-->
          <PostcodeAddress>
            <!--Optional:-->
            <houseNumber>string</houseNumber>
            <postcode>string</postcode>
            <country>st</country>
          </PostcodeAddress>
          <!--Optional:-->
          <ContactPerson>
            <name>string</name>
            <phoneNumber>token</phoneNumber>
            <!--Optional:-->
            <eMailAddress>string</eMailAddress>
          </ContactPerson>
        </LocationOfGoods>
        <!--0 to 999 repetitions:-->
        <DepartureTransportMeans>
          <sequenceNumber>token</sequenceNumber>
          <typeOfIdentification>token</typeOfIdentification>
          <identificationNumber>string</identificationNumber>
          <nationality>st</nationality>
        </DepartureTransportMeans>
        <!--0 to 9 repetitions:-->
        <ActiveBorderTransportMeans>
          <sequenceNumber>token</sequenceNumber>
          <customsOfficeAtBorderReferenceNumber>token</customsOfficeAtBorderReferenceNumber>
          <typeOfIdentification>token</typeOfIdentification>
          <identificationNumber>string</identificationNumber>
          <nationality>st</nationality>
          <!--Optional:-->
          <conveyanceReferenceNumber>string</conveyanceReferenceNumber>
        </ActiveBorderTransportMeans>
        <!--Optional:-->
        <PlaceOfLoading>
          <!--Optional:-->
          <UNLocode>token</UNLocode>
          <!--Optional:-->
          <country>st</country>
          <!--Optional:-->
          <location>string</location>
        </PlaceOfLoading>
        <!--1 to 99 repetitions:-->
        <HouseConsignment>
          <sequenceNumber>token</sequenceNumber>
          <!--0 to 999 repetitions:-->
          <DepartureTransportMeans>
            <sequenceNumber>token</sequenceNumber>
            <typeOfIdentification>token</typeOfIdentification>
            <identificationNumber>string</identificationNumber>
            <nationality>st</nationality>
          </DepartureTransportMeans>
        </HouseConsignment>
      </Consignment>
    </ncts:CC170C>

  lazy val json1 = Json.parse(
    """
        |{
        |    "n1:CC170C": {
        |        "preparationDateAndTime": "2007-10-26T07:36:28",
        |        "TransitOperation": {
        |            "LRN": "string",
        |            "limitDate": "2014-06-09+01:00"
        |        },
        |        "CustomsOfficeOfDeparture": {
        |            "referenceNumber": "stringst"
        |        },
        |        "messageType": "CC170C",
        |        "@PhaseID": "NCTS5.0",
        |        "correlationIdentifier": "token",
        |        "messageRecipient": "token",
        |         "messageSender": "token",
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
        |        "Consignment": {
        |            "containerIndicator": "1",
        |            "inlandModeOfTransport": "token",
        |            "modeOfTransportAtTheBorder": "token",
        |            "TransportEquipment": [
        |                {
        |                    "sequenceNumber": "token",
        |                    "containerIdentificationNumber": "string",
        |                    "numberOfSeals": 100,
        |                    "Seal": [
        |                        {
        |                            "sequenceNumber": "token",
        |                            "identifier": "string"
        |                        }
        |                    ],
        |                    "GoodsReference": [
        |                        {
        |                            "sequenceNumber": "token",
        |                            "declarationGoodsItemNumber": 100
        |                        }
        |                    ]
        |                }
        |            ],
        |            "LocationOfGoods": {
        |                "typeOfLocation": "token",
        |                "qualifierOfIdentification": "token",
        |                "authorisationNumber": "string",
        |                "additionalIdentifier": "stri",
        |                "UNLocode": "token",
        |                "CustomsOffice": {
        |                    "referenceNumber": "stringst"
        |                },
        |                "GNSS": {
        |                    "latitude": "string",
        |                    "longitude": "string"
        |                },
        |                "EconomicOperator": {
        |                    "identificationNumber": "string"
        |                },
        |                "Address": {
        |                    "streetAndNumber": "string",
        |                    "postcode": "string",
        |                    "city": "string",
        |                    "country": "st"
        |                },
        |                "PostcodeAddress": {
        |                    "houseNumber": "string",
        |                    "postcode": "string",
        |                    "country": "st"
        |                },
        |                "ContactPerson": {
        |                    "name": "string",
        |                    "phoneNumber": "token",
        |                    "eMailAddress": "string"
        |                }
        |            },
        |            "DepartureTransportMeans": [
        |                {
        |                    "sequenceNumber": "token",
        |                    "typeOfIdentification": "token",
        |                    "identificationNumber": "string",
        |                    "nationality": "st"
        |                }
        |            ],
        |            "ActiveBorderTransportMeans": [
        |                {
        |                    "sequenceNumber": "token",
        |                    "customsOfficeAtBorderReferenceNumber": "token",
        |                    "typeOfIdentification": "token",
        |                    "identificationNumber": "string",
        |                    "nationality": "st",
        |                    "conveyanceReferenceNumber": "string"
        |                }
        |            ],
        |            "PlaceOfLoading": {
        |                "UNLocode": "token",
        |                "country": "st",
        |                "location": "string"
        |            },
        |            "HouseConsignment": [
        |                {
        |                    "sequenceNumber": "token",
        |                    "DepartureTransportMeans": [
        |                        {
        |                            "sequenceNumber": "token",
        |                            "typeOfIdentification": "token",
        |                            "identificationNumber": "string",
        |                            "nationality": "st"
        |                        }
        |                    ]
        |                }
        |            ]
        |        },
        |        "Representative": {
        |            "identificationNumber": "string",
        |            "status": "token",
        |            "ContactPerson": {
        |                "name": "string",
        |                "phoneNumber": "token",
        |                "eMailAddress": "string"
        |            }
        |        },
        |        "messageIdentification": "token"
        |    }
        |}
        |""".stripMargin
  )

}

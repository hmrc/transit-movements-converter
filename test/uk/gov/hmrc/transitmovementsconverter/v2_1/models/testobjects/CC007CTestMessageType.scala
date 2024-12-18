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

object CC007CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1 = <ncts:CC007C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC007C</messageType>
    <!--Optional:-->
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <MRN>string</MRN>
      <arrivalNotificationDateAndTime>2014-06-09T16:15:04+01:00</arrivalNotificationDateAndTime>
      <simplifiedProcedure>1</simplifiedProcedure>
      <incidentFlag>1</incidentFlag>
    </TransitOperation>
    <!--0 to 9 repetitions:-->
    <Authorisation>
      <sequenceNumber>100</sequenceNumber>
      <type>token</type>
      <referenceNumber>string</referenceNumber>
    </Authorisation>
    <CustomsOfficeOfDestinationActual>
      <referenceNumber>stringst</referenceNumber>
    </CustomsOfficeOfDestinationActual>
    <TraderAtDestination>
      <identificationNumber>string</identificationNumber>
      <!--Optional:-->
      <communicationLanguageAtDestination>token</communicationLanguageAtDestination>
    </TraderAtDestination>
    <Consignment>
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
      <!--0 to 9 repetitions:-->
      <Incident>
        <sequenceNumber>100</sequenceNumber>
        <code>token</code>
        <text>string</text>
        <!--Optional:-->
        <Endorsement>
          <date>2013-05-22+01:00</date>
          <authority>string</authority>
          <place>string</place>
          <country>st</country>
        </Endorsement>
        <Location>
          <qualifierOfIdentification>token</qualifierOfIdentification>
          <!--Optional:-->
          <UNLocode>token</UNLocode>
          <country>st</country>
          <!--Optional:-->
          <GNSS>
            <latitude>string</latitude>
            <longitude>string</longitude>
          </GNSS>
          <!--Optional:-->
          <Address>
            <streetAndNumber>string</streetAndNumber>
            <!--Optional:-->
            <postcode>string</postcode>
            <city>string</city>
          </Address>
        </Location>
        <!--0 to 9999 repetitions:-->
        <TransportEquipment>
          <sequenceNumber>100</sequenceNumber>          <!--Optional:-->
          <containerIdentificationNumber>string</containerIdentificationNumber>
          <!--Optional:-->
          <numberOfSeals>100</numberOfSeals>
          <!--0 to 99 repetitions:-->
          <Seal>
            <sequenceNumber>100</sequenceNumber>
          <identifier>string</identifier>
          </Seal>
          <!--0 to 9999 repetitions:-->
          <GoodsReference>
            <sequenceNumber>100</sequenceNumber>           <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
          </GoodsReference>
        </TransportEquipment>
        <!--Optional:-->
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
  </ncts:CC007C>

  lazy val json1 = Json.parse(
    """
     {
      |  "n1:CC007C": {
      |    "messageSender": "token",
      |    "messageRecipient": "token",
      |    "preparationDateAndTime": "2007-10-26T07:36:28",
      |    "messageIdentification": "token",
      |    "messageType": "CC007C",
      |    "correlationIdentifier": "token",
      |    "TransitOperation": {
      |      "MRN": "string",
      |      "arrivalNotificationDateAndTime": "2014-06-09T16:15:04+01:00",
      |      "simplifiedProcedure": "1",
      |      "incidentFlag": "1"
      |    },
      |    "Authorisation": [
      |      {
      |        "sequenceNumber": 100,
      |        "referenceNumber": "string",
      |        "type": "token"
      |      }
      |    ],
      |    "CustomsOfficeOfDestinationActual": {
      |      "referenceNumber": "stringst"
      |    },
      |    "TraderAtDestination": {
      |      "identificationNumber": "string",
      |      "communicationLanguageAtDestination": "token"
      |    },
      |    "Consignment": {
      |      "LocationOfGoods": {
      |        "typeOfLocation": "token",
      |        "qualifierOfIdentification": "token",
      |        "authorisationNumber": "string",
      |        "additionalIdentifier": "stri",
      |        "UNLocode": "token",
      |        "CustomsOffice": {
      |          "referenceNumber": "stringst"
      |        },
      |        "GNSS": {
      |          "latitude": "string",
      |          "longitude": "string"
      |        },
      |        "EconomicOperator": {
      |          "identificationNumber": "string"
      |        },
      |        "Address": {
      |          "streetAndNumber": "string",
      |          "postcode": "string",
      |          "city": "string",
      |          "country": "st"
      |        },
      |        "PostcodeAddress": {
      |          "houseNumber": "string",
      |          "postcode": "string",
      |          "country": "st"
      |        },
      |        "ContactPerson": {
      |          "name": "string",
      |          "phoneNumber": "token",
      |          "eMailAddress": "string"
      |        }
      |      },
      |      "Incident": [
      |        {
      |          "sequenceNumber": 100,
      |          "code": "token",
      |          "text": "string",
      |          "Endorsement": {
      |            "date": "2013-05-22+01:00",
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

  lazy val xmlPhase51 = <ncts:CC007C PhaseID="NCTS5.1" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC007C</messageType>
    <!--Optional:-->
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <MRN>string</MRN>
      <arrivalNotificationDateAndTime>2014-06-09T16:15:04+01:00</arrivalNotificationDateAndTime>
      <simplifiedProcedure>1</simplifiedProcedure>
      <incidentFlag>1</incidentFlag>
    </TransitOperation>
    <!--0 to 9 repetitions:-->
    <Authorisation>
      <sequenceNumber>100</sequenceNumber>
      <type>token</type>
      <referenceNumber>string</referenceNumber>
    </Authorisation>
    <CustomsOfficeOfDestinationActual>
      <referenceNumber>stringst</referenceNumber>
    </CustomsOfficeOfDestinationActual>
    <TraderAtDestination>
      <identificationNumber>string</identificationNumber>
      <!--Optional:-->
      <communicationLanguageAtDestination>token</communicationLanguageAtDestination>
    </TraderAtDestination>
    <Consignment>
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
      <!--0 to 9 repetitions:-->
      <Incident>
        <sequenceNumber>100</sequenceNumber>
        <code>token</code>
        <text>string</text>
        <!--Optional:-->
        <Endorsement>
          <date>2013-05-22+01:00</date>
          <authority>string</authority>
          <place>string</place>
          <country>st</country>
        </Endorsement>
        <Location>
          <qualifierOfIdentification>token</qualifierOfIdentification>
          <!--Optional:-->
          <UNLocode>token</UNLocode>
          <country>st</country>
          <!--Optional:-->
          <GNSS>
            <latitude>string</latitude>
            <longitude>string</longitude>
          </GNSS>
          <!--Optional:-->
          <Address>
            <streetAndNumber>string</streetAndNumber>
            <!--Optional:-->
            <postcode>string</postcode>
            <city>string</city>
          </Address>
        </Location>
        <!--0 to 9999 repetitions:-->
        <TransportEquipment>
          <sequenceNumber>100</sequenceNumber>
          <!--Optional:-->
          <containerIdentificationNumber>string</containerIdentificationNumber>
          <!--Optional:-->
          <numberOfSeals>100</numberOfSeals>
          <!--0 to 99 repetitions:-->
          <Seal>
            <sequenceNumber>100</sequenceNumber>
            <identifier>string</identifier>
          </Seal>
          <!--0 to 9999 repetitions:-->
          <GoodsReference>
            <sequenceNumber>100</sequenceNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
          </GoodsReference>
        </TransportEquipment>
        <!--Optional:-->
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
  </ncts:CC007C>

  lazy val jsonPhase51 = Json.parse(
    """
      |{
      |    "n1:CC007C": {
      |        "preparationDateAndTime": "2007-10-26T07:36:28",
      |        "TransitOperation": {
      |            "MRN": "string",
      |            "arrivalNotificationDateAndTime": "2014-06-09T16:15:04+01:00",
      |            "simplifiedProcedure": "1",
      |            "incidentFlag": "1"
      |        },
      |        "TraderAtDestination": {
      |            "identificationNumber": "string",
      |            "communicationLanguageAtDestination": "token"
      |        },
      |        "messageType": "CC007C",
      |        "@PhaseID": "NCTS5.1",
      |        "correlationIdentifier": "token",
      |        "Authorisation": [
      |            {
      |                "sequenceNumber": "100",
      |                "referenceNumber": "string",
      |                "type": "token"
      |            }
      |        ],
      |        "messageSender": "token",
      |        "messageRecipient": "token",
      |        "Consignment": {
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
      |            "Incident": [
      |                {
      |                    "sequenceNumber": "100",
      |                    "code": "token",
      |                    "text": "string",
      |                    "Endorsement": {
      |                        "date": "2013-05-22+01:00",
      |                        "authority": "string",
      |                        "place": "string",
      |                        "country": "st"
      |                    },
      |                    "Location": {
      |                        "qualifierOfIdentification": "token",
      |                        "UNLocode": "token",
      |                        "country": "st",
      |                        "GNSS": {
      |                            "latitude": "string",
      |                            "longitude": "string"
      |                        },
      |                        "Address": {
      |                            "streetAndNumber": "string",
      |                            "postcode": "string",
      |                            "city": "string"
      |                        }
      |                    },
      |                    "TransportEquipment": [
      |                        {
      |                            "sequenceNumber": "100",
      |                            "containerIdentificationNumber": "string",
      |                            "numberOfSeals": 100,
      |                            "Seal": [
      |                                {
      |                                    "sequenceNumber": "100",
      |                                    "identifier": "string"
      |                                }
      |                            ],
      |                            "GoodsReference": [
      |                                {
      |                                    "sequenceNumber": "100",
      |                                    "declarationGoodsItemNumber": 100
      |                                }
      |                            ]
      |                        }
      |                    ],
      |                    "Transhipment": {
      |                        "containerIndicator": "0",
      |                        "TransportMeans": {
      |                            "typeOfIdentification": "token",
      |                            "identificationNumber": "string",
      |                            "nationality": "st"
      |                        }
      |                    }
      |                }
      |            ]
      |        },
      |        "messageIdentification": "token",
      |        "CustomsOfficeOfDestinationActual": {
      |            "referenceNumber": "stringst"
      |        }
      |    }
      |}
      |""".stripMargin
  )

}

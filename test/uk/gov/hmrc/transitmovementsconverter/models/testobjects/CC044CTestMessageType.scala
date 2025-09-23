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

object CC044CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1: NodeSeq =
    <ncts:CC044C xmlns:ncts="http://ncts.dgtaxud.ec" PhaseID="NCTS5.0">
      <messageSender>token</messageSender>
      <messageRecipient>token</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>token</messageIdentification>
      <messageType>CC044C</messageType>
      <correlationIdentifier>token</correlationIdentifier>  
      <TransitOperation>
        <MRN>qvRcL</MRN>
      </TransitOperation>
      <CustomsOfficeOfDestinationActual>
        <referenceNumber>ZQZ20443</referenceNumber>
      </CustomsOfficeOfDestinationActual>
      <TraderAtDestination>
        <identificationNumber>G35dSwQ</identificationNumber>
      </TraderAtDestination>
      <UnloadingRemark>
        <conform>0</conform>
        <unloadingCompletion>1</unloadingCompletion>
        <unloadingDate>2018-11-01</unloadingDate>
      </UnloadingRemark>
      <!--Optional:-->
      <Consignment>
        <!--Optional:-->
        <grossMass>1000.000000000001</grossMass>
        <!--0 to 9999 repetitions:-->
        <TransportEquipment>
          <sequenceNumber>100</sequenceNumber>
          <!--0 to 99 repetitions:-->
          <Seal>
            <sequenceNumber>100</sequenceNumber>
            <identifier>id1</identifier>
          </Seal>
          <!--0 to 9999 repetitions:-->
          <GoodsReference>
            <sequenceNumber>100</sequenceNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
          </GoodsReference>
        </TransportEquipment>
        <!--0 to 999 repetitions:-->
        <DepartureTransportMeans>
          <sequenceNumber>100</sequenceNumber>
        </DepartureTransportMeans>
        <!--0 to 99 repetitions:-->
        <SupportingDocument>
          <sequenceNumber>100</sequenceNumber>
        </SupportingDocument>
        <!--0 to 99 repetitions:-->
        <TransportDocument>
          <sequenceNumber>100</sequenceNumber>
        </TransportDocument>
        <!--0 to 99 repetitions:-->
        <AdditionalReference>
          <sequenceNumber>100</sequenceNumber>
          <!--Optional:-->
        </AdditionalReference>
        <!--0 to 99 repetitions:-->
        <HouseConsignment>
          <sequenceNumber>100</sequenceNumber>
          <!--Optional:-->
          <grossMass>3000.000000000002</grossMass>
          <!--0 to 999 repetitions:-->
          <DepartureTransportMeans>
            <sequenceNumber>100</sequenceNumber>
          </DepartureTransportMeans>
          <!--0 to 99 repetitions:-->
          <SupportingDocument>
            <sequenceNumber>100</sequenceNumber>
          </SupportingDocument>
          <!--0 to 99 repetitions:-->
          <TransportDocument>
            <sequenceNumber>100</sequenceNumber>
          </TransportDocument>
          <!--0 to 99 repetitions:-->
          <AdditionalReference>
            <sequenceNumber>100</sequenceNumber>
          </AdditionalReference>
          <!--0 to 999 repetitions:-->
          <ConsignmentItem>
            <goodsItemNumber>100</goodsItemNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
            <!--0 to 99 repetitions:-->
            <Packaging>
              <sequenceNumber>100</sequenceNumber>
            </Packaging>
            <!--0 to 99 repetitions:-->
            <SupportingDocument>
              <sequenceNumber>100</sequenceNumber>
            </SupportingDocument>
            <!--0 to 99 repetitions:-->
            <TransportDocument>
              <sequenceNumber>100</sequenceNumber>
              <type>A</type>
              <referenceNumber>A1</referenceNumber>
            </TransportDocument>
            <!--0 to 99 repetitions:-->
            <AdditionalReference>
              <sequenceNumber>100</sequenceNumber>
            </AdditionalReference>
          </ConsignmentItem>
        </HouseConsignment>
      </Consignment>
    </ncts:CC044C>

  lazy val json1: JsValue = Json.parse(
    """
      |{
      |  "n1:CC044C": {
      |    "messageSender": "token",
      |    "messageRecipient": "token",
      |    "preparationDateAndTime": "2007-10-26T07:36:28",
      |    "messageIdentification": "token",
      |    "messageType": "CC044C",
      |    "correlationIdentifier": "token",
      |    "TransitOperation": {
      |      "MRN": "qvRcL"
      |    },
      |    "CustomsOfficeOfDestinationActual": {
      |      "referenceNumber": "ZQZ20443"
      |    },
      |    "TraderAtDestination": {
      |      "identificationNumber": "G35dSwQ"
      |    },
      |    "UnloadingRemark": {
      |      "conform": "0",
      |      "unloadingCompletion": "1",
      |      "unloadingDate": "2018-11-01"
      |    },
      |    "Consignment": {
      |      "grossMass": 1000.000000000001,
      |      "TransportEquipment": [
      |        {
      |          "sequenceNumber": 100,
      |          "Seal": [
      |            {
      |              "sequenceNumber": 100,
      |              "identifier": "id1"
      |            }
      |          ],
      |          "GoodsReference": [
      |            {
      |              "sequenceNumber": 100,
      |              "declarationGoodsItemNumber": 100
      |            }
      |          ]
      |        }
      |      ],
      |      "DepartureTransportMeans": [
      |        {
      |          "sequenceNumber": 100
      |        }
      |      ],
      |      "SupportingDocument": [
      |        {
      |          "sequenceNumber": 100
      |        }
      |      ],
      |      "TransportDocument": [
      |        {
      |          "sequenceNumber": 100
      |        }
      |      ],
      |      "AdditionalReference": [
      |        {
      |          "sequenceNumber": 100
      |        }
      |      ],
      |      "HouseConsignment": [
      |        {
      |          "sequenceNumber": 100,
      |          "grossMass": 3000.000000000002,
      |          "DepartureTransportMeans": [
      |            {
      |              "sequenceNumber": 100
      |            }
      |          ],
      |          "SupportingDocument": [
      |            {
      |              "sequenceNumber": 100
      |            }
      |          ],
      |          "TransportDocument": [
      |            {
      |              "sequenceNumber": 100
      |            }
      |          ],
      |          "AdditionalReference": [
      |            {
      |              "sequenceNumber": 100
      |            }
      |          ],
      |          "ConsignmentItem": [
      |            {
      |              "goodsItemNumber": 100,
      |              "declarationGoodsItemNumber": 100,
      |              "Packaging": [
      |                {
      |                  "sequenceNumber": 100
      |                }
      |              ],
      |              "SupportingDocument": [
      |                {
      |                  "sequenceNumber": 100
      |                }
      |              ],
      |              "TransportDocument": [
      |                {
      |                  "sequenceNumber": 100,
      |                  "referenceNumber": "A1",
      |                  "type": "A"
      |                }
      |              ],
      |              "AdditionalReference": [
      |                {
      |                  "sequenceNumber": 100
      |                }
      |              ]
      |            }
      |          ]
      |        }
      |      ]
      |    },
      |    "@PhaseID": "NCTS5.0"
      |  }
      |}
    |""".stripMargin
  )
}

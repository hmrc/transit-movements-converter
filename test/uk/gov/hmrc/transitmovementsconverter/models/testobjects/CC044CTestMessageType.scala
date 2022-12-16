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

import play.api.libs.json._

import scala.xml.NodeSeq

object CC044CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1: NodeSeq =
    <ncts:CC044C xmlns:ncts="http://ncts.dgtaxud.ec" PhaseID="NCTS5.0">
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
          <sequenceNumber>1122</sequenceNumber>
          <!--0 to 99 repetitions:-->
          <Seal>
            <sequenceNumber>sq1</sequenceNumber>
            <identifier>id1</identifier>
          </Seal>
          <!--0 to 9999 repetitions:-->
          <GoodsReference>
            <sequenceNumber>gr1</sequenceNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
          </GoodsReference>
        </TransportEquipment>
        <!--0 to 999 repetitions:-->
        <DepartureTransportMeans>
          <sequenceNumber>dtm1</sequenceNumber>
        </DepartureTransportMeans>
        <!--0 to 99 repetitions:-->
        <SupportingDocument>
          <sequenceNumber>123</sequenceNumber>
        </SupportingDocument>
        <!--0 to 99 repetitions:-->
        <TransportDocument>
          <sequenceNumber>4567</sequenceNumber>
        </TransportDocument>
        <!--0 to 99 repetitions:-->
        <AdditionalReference>
          <sequenceNumber>ar2</sequenceNumber>
          <!--Optional:-->
        </AdditionalReference>
        <!--0 to 99 repetitions:-->
        <HouseConsignment>
          <sequenceNumber>hc1</sequenceNumber>
          <!--Optional:-->
          <grossMass>3000.000000000002</grossMass>
          <!--0 to 999 repetitions:-->
          <DepartureTransportMeans>
            <sequenceNumber>dtm1</sequenceNumber>
          </DepartureTransportMeans>
          <!--0 to 99 repetitions:-->
          <SupportingDocument>
            <sequenceNumber>sd1</sequenceNumber>
          </SupportingDocument>
          <!--0 to 99 repetitions:-->
          <TransportDocument>
            <sequenceNumber>td1</sequenceNumber>
          </TransportDocument>
          <!--0 to 99 repetitions:-->
          <AdditionalReference>
            <sequenceNumber>AD2</sequenceNumber>
          </AdditionalReference>
          <!--0 to 999 repetitions:-->
          <ConsignmentItem>
            <goodsItemNumber>token</goodsItemNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
            <!--0 to 99 repetitions:-->
            <Packaging>
              <sequenceNumber>pkg123</sequenceNumber>
            </Packaging>
            <!--0 to 99 repetitions:-->
            <SupportingDocument>
              <sequenceNumber>SD1</sequenceNumber>
            </SupportingDocument>
            <!--0 to 99 repetitions:-->
            <TransportDocument>
              <sequenceNumber>TD1</sequenceNumber>
              <type>A</type>
              <referenceNumber>A1</referenceNumber>
            </TransportDocument>
            <!--0 to 99 repetitions:-->
            <AdditionalReference>
              <sequenceNumber>ar22</sequenceNumber>
            </AdditionalReference>
          </ConsignmentItem>
        </HouseConsignment>
      </Consignment>
    </ncts:CC044C>

  lazy val json1: JsValue = Json.parse(
    """
    |{
    |  "n1:CC044C":
    |    {
    |      "preparationDateAndTime": "2007-10-26T07:36:28",
    |      "TransitOperation":
    |    {
    |      "MRN": "qvRcL"
    |    },
    |      "TraderAtDestination":
    |    {
    |      "identificationNumber": "G35dSwQ"
    |    },
    |      "UnloadingRemark":
    |    {
    |      "conform": "0",
    |      "unloadingCompletion": "1",
    |      "unloadingDate": "2018-11-01"
    |    },
    |      "messageType": "CC044C",
    |      "@PhaseID": "NCTS5.0",
    |      "correlationIdentifier": "token",
    |      "messageRecipient": "token",
    |      "Consignment":
    |    {
    |      "grossMass": 1000.000000000001,
    |      "TransportEquipment":
    |      [
    |    {
    |      "sequenceNumber": "1122",
    |      "Seal":
    |      [
    |    {
    |      "sequenceNumber": "sq1",
    |      "identifier": "id1"
    |    }
    |      ],
    |      "GoodsReference":
    |      [
    |    {
    |      "sequenceNumber": "gr1",
    |      "declarationGoodsItemNumber": 100
    |    }
    |      ]
    |    }
    |      ],
    |      "DepartureTransportMeans":
    |      [
    |    {
    |      "sequenceNumber": "dtm1"
    |    }
    |      ],
    |      "SupportingDocument":
    |      [
    |    {
    |      "sequenceNumber": "123"
    |    }
    |      ],
    |      "TransportDocument":
    |      [
    |    {
    |      "sequenceNumber": "4567"
    |    }
    |      ],
    |      "AdditionalReference":
    |      [
    |    {
    |      "sequenceNumber": "ar2"
    |    }
    |      ],
    |      "HouseConsignment":
    |      [
    |    {
    |      "sequenceNumber": "hc1",
    |      "grossMass": 3000.000000000002,
    |      "DepartureTransportMeans":
    |      [
    |    {
    |      "sequenceNumber": "dtm1"
    |    }
    |      ],
    |      "SupportingDocument":
    |      [
    |    {
    |      "sequenceNumber": "sd1"
    |    }
    |      ],
    |      "TransportDocument":
    |      [
    |    {
    |      "sequenceNumber": "td1"
    |    }
    |      ],
    |      "AdditionalReference":
    |      [
    |    {
    |      "sequenceNumber": "AD2"
    |    }
    |      ],
    |      "ConsignmentItem":
    |      [
    |    {
    |      "goodsItemNumber": "token",
    |      "declarationGoodsItemNumber": 100,
    |      "Packaging":
    |      [
    |    {
    |      "sequenceNumber": "pkg123"
    |    }
    |      ],
    |      "SupportingDocument":
    |      [
    |    {
    |      "sequenceNumber": "SD1"
    |    }
    |      ],
    |      "TransportDocument":
    |      [
    |    {
    |      "sequenceNumber": "TD1",
    |      "referenceNumber": "A1",
    |      "type": "A"
    |    }
    |      ],
    |      "AdditionalReference":
    |      [
    |    {
    |      "sequenceNumber": "ar22"
    |    }
    |      ]
    |    }
    |      ]
    |    }
    |      ]
    |    },
    |      "messageIdentification": "token",
    |      "CustomsOfficeOfDestinationActual":
    |    {
    |      "referenceNumber": "ZQZ20443"
    |    }
    |    }
    |}
    |""".stripMargin
  )
}

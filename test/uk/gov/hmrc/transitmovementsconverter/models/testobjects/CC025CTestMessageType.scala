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

object CC025CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1 = <ncts:CC025C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC025C</messageType>
    <!--Optional:-->
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <MRN>string</MRN>
      <releaseDate>2014-06-09+01:00</releaseDate>
      <releaseIndicator>token</releaseIndicator>
    </TransitOperation>
    <CustomsOfficeOfDestinationActual>
      <referenceNumber>stringst</referenceNumber>
    </CustomsOfficeOfDestinationActual>
    <TraderAtDestination>
      <identificationNumber>string</identificationNumber>
    </TraderAtDestination>
    <!--Optional:-->
    <Consignment>
      <!--1 to 99 repetitions:-->
      <HouseConsignment>
        <sequenceNumber>100</sequenceNumber>
        <releaseType>token</releaseType>
        <!--0 to 999 repetitions:-->
        <ConsignmentItem>
          <goodsItemNumber>100</goodsItemNumber>
          <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
          <releaseType>token</releaseType>
          <Commodity>
            <descriptionOfGoods>string</descriptionOfGoods>
            <!--Optional:-->
            <cusCode>token</cusCode>
            <!--Optional:-->
            <CommodityCode>
              <harmonizedSystemSubHeadingCode>token</harmonizedSystemSubHeadingCode>
              <!--Optional:-->
              <combinedNomenclatureCode>st</combinedNomenclatureCode>
            </CommodityCode>
            <!--0 to 99 repetitions:-->
            <DangerousGoods>
              <sequenceNumber>100</sequenceNumber>
              <UNNumber>token</UNNumber>
            </DangerousGoods>
          </Commodity>
          <!--1 to 99 repetitions:-->
          <Packaging>
            <sequenceNumber>100</sequenceNumber>            <typeOfPackages>token</typeOfPackages>
            <!--Optional:-->
            <numberOfPackages>100</numberOfPackages>
            <!--Optional:-->
            <shippingMarks>string</shippingMarks>
          </Packaging>
        </ConsignmentItem>
      </HouseConsignment>
    </Consignment>
  </ncts:CC025C>

  lazy val json1 = Json.parse(
    """
      |{
      |   "n1:CC025C":
      |    {
      |      "preparationDateAndTime": "2007-10-26T07:36:28",
      |      "TransitOperation":
      |    {
      |      "MRN": "string",
      |      "releaseDate": "2014-06-09+01:00",
      |      "releaseIndicator": "token"
      |    },
      |      "TraderAtDestination":
      |    {
      |      "identificationNumber": "string"
      |    },
      |      "messageType": "CC025C",
      |      "@PhaseID": "NCTS5.0",
      |      "correlationIdentifier": "token",
      |      "messageSender": "token",
      |      "messageRecipient": "token",
      |      "Consignment":
      |    {
      |      "HouseConsignment":
      |      [
      |    {
      |      "sequenceNumber": 100,
      |      "releaseType": "token",
      |      "ConsignmentItem":
      |      [
      |    {
      |      "goodsItemNumber": 100,
      |      "declarationGoodsItemNumber": 100,
      |      "releaseType": "token",
      |      "Commodity":
      |    {
      |      "descriptionOfGoods": "string",
      |      "cusCode": "token",
      |      "CommodityCode":
      |    {
      |      "harmonizedSystemSubHeadingCode": "token",
      |      "combinedNomenclatureCode": "st"
      |    },
      |      "DangerousGoods":
      |      [
      |    {
      |      "sequenceNumber": 100,
      |      "UNNumber": "token"
      |    }
      |      ]
      |    },
      |      "Packaging":
      |      [
      |    {
      |      "sequenceNumber": 100,
      |      "typeOfPackages": "token",
      |      "numberOfPackages": 100,
      |      "shippingMarks": "string"
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
      |      "referenceNumber": "stringst"
      |    }
      |    }
      |}   
      |""".stripMargin
  )

}

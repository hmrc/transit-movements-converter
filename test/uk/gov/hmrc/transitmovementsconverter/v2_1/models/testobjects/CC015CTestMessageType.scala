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

object CC015CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1 = <ncts:CC015C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
    <preparationDateAndTime>2022-01-22T07:43:36</preparationDateAndTime>
    <messageIdentification>6Onxa3En</messageIdentification>
    <messageType>CC015C</messageType>
    <TransitOperation>
      <LRN>qvRcL</LRN>
      <declarationType>Pbg</declarationType>
      <additionalDeclarationType>O</additionalDeclarationType>
      <security>8</security>
      <reducedDatasetIndicator>1</reducedDatasetIndicator>
      <bindingItinerary>0</bindingItinerary>
    </TransitOperation>
    <CustomsOfficeOfDeparture>
      <referenceNumber>ZQZ20442</referenceNumber>
    </CustomsOfficeOfDeparture>
    <CustomsOfficeOfDestinationDeclared>
      <referenceNumber>ZQZ20442</referenceNumber>
    </CustomsOfficeOfDestinationDeclared>
    <HolderOfTheTransitProcedure>
      <identificationNumber>SFzsisksA</identificationNumber>
    </HolderOfTheTransitProcedure>
    <Guarantee>
      <sequenceNumber>48711</sequenceNumber>
      <guaranteeType>1</guaranteeType>
      <otherGuaranteeReference>1qJMA6MbhnnrOJJjHBHX</otherGuaranteeReference>
    </Guarantee>
    <Consignment>
      <grossMass>6430669292.48125</grossMass>
      <HouseConsignment>
        <sequenceNumber>48711</sequenceNumber>
        <grossMass>6430669292.48125</grossMass>
        <ConsignmentItem>
          <goodsItemNumber>18914</goodsItemNumber>
          <declarationGoodsItemNumber>1458</declarationGoodsItemNumber>
          <Commodity>
            <descriptionOfGoods>ZMyM5HTSTnLqT5FT9aHXwScqXKC1VitlWeO5gs91cVXBXOB8xBdXG5aGhG9VFjjDGiraIETFfbQWeA7VUokO7ngDOrKZ23ccKKMA6C3GpXciUTt9nS2pzCFFFeg4BXdkIe</descriptionOfGoods>
          </Commodity>
          <Packaging>
            <sequenceNumber>48711</sequenceNumber>
            <typeOfPackages>Oi</typeOfPackages>
          </Packaging>
        </ConsignmentItem>
      </HouseConsignment>
    </Consignment>
  </ncts:CC015C>

  lazy val json1 =
    Json.obj(
      "n1:CC015C" ->
        Json.obj(
          "messageSender"          -> "token",
          "messageRecipient"       -> "FdOcminxBxSLGm1rRUn0q96S1",
          "preparationDateAndTime" -> "2022-01-22T07:43:36",
          "messageIdentification"  -> "6Onxa3En",
          "messageType"            -> "CC015C",
          "TransitOperation" -> Json.obj(
            "LRN"                       -> "qvRcL",
            "declarationType"           -> "Pbg",
            "additionalDeclarationType" -> "O",
            "security"                  -> "8",
            "reducedDatasetIndicator"   -> "1",
            "bindingItinerary"          -> "0"
          ),
          "CustomsOfficeOfDeparture" -> Json.obj(
            "referenceNumber" -> "ZQZ20442"
          ),
          "CustomsOfficeOfDestinationDeclared" -> Json.obj(
            "referenceNumber" -> "ZQZ20442"
          ),
          "HolderOfTheTransitProcedure" -> Json.obj(
            "identificationNumber" -> "SFzsisksA"
          ),
          "Guarantee" -> Json.arr(
            Json.obj(
              "sequenceNumber"          -> 48711,
              "guaranteeType"           -> "1",
              "otherGuaranteeReference" -> "1qJMA6MbhnnrOJJjHBHX",
              "GuaranteeReference"      -> Json.arr()
            )
          ),
          "Consignment" -> Json.obj(
            "grossMass" -> 6430669292.48125,
            "HouseConsignment" -> Json.arr(
              Json.obj(
                "sequenceNumber"             -> 48711,
                "grossMass"                  -> 6430669292.48125,
                "AdditionalSupplyChainActor" -> Json.arr(),
                "DepartureTransportMeans"    -> Json.arr(),
                "PreviousDocument"           -> Json.arr(),
                "SupportingDocument"         -> Json.arr(),
                "TransportDocument"          -> Json.arr(),
                "AdditionalReference"        -> Json.arr(),
                "AdditionalInformation"      -> Json.arr(),
                "ConsignmentItem" -> Json.arr(
                  Json.obj(
                    "goodsItemNumber"            -> 18914,
                    "declarationGoodsItemNumber" -> 1458,
                    "AdditionalSupplyChainActor" -> Json.arr(),
                    "Commodity" -> Json.obj(
                      "descriptionOfGoods" -> "ZMyM5HTSTnLqT5FT9aHXwScqXKC1VitlWeO5gs91cVXBXOB8xBdXG5aGhG9VFjjDGiraIETFfbQWeA7VUokO7ngDOrKZ23ccKKMA6C3GpXciUTt9nS2pzCFFFeg4BXdkIe",
                      "DangerousGoods"     -> Json.arr()
                    ),
                    "Packaging" -> Json.arr(
                      Json.obj(
                        "sequenceNumber" -> 48711,
                        "typeOfPackages" -> "Oi"
                      )
                    ),
                    "PreviousDocument"      -> Json.arr(),
                    "SupportingDocument"    -> Json.arr(),
                    "TransportDocument"     -> Json.arr(),
                    "AdditionalReference"   -> Json.arr(),
                    "AdditionalInformation" -> Json.arr()
                  )
                )
              )
            )
          ),
          "@PhaseID" -> "NCTS5.0"
        )
    )

}

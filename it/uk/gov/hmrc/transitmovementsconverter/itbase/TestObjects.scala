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

package uk.gov.hmrc.transitmovementsconverter.itbase

import play.api.libs.json.Json

object TestObjects {

  object CC015C {

    lazy val invalidXml1 = <ncts:CC015C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>FdOcminxBxSLGm1rRUn0q96S1</messageSender>
    </ncts:CC015C>

    lazy val xml1 = <ncts:CC015C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
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

    lazy val invalidJson1 =
      Json.obj(
        "n1:CC015C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )

    lazy val json1 =
      Json.obj(
        "n1:CC015C" ->
          Json.obj(
            "@PhaseID"               -> "NCTS5.0",
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
                "sequenceNumber"          -> "48711",
                "guaranteeType"           -> "1",
                "otherGuaranteeReference" -> "1qJMA6MbhnnrOJJjHBHX",
                "GuaranteeReference"      -> Json.arr()
              )
            ),
            "Consignment" -> Json.obj(
              "grossMass" -> 6430669292.48125,
              "HouseConsignment" -> Json.arr(
                Json.obj(
                  "sequenceNumber"             -> "48711",
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
                      "goodsItemNumber"            -> "18914",
                      "declarationGoodsItemNumber" -> 1458,
                      "AdditionalSupplyChainActor" -> Json.arr(),
                      "Commodity" -> Json.obj(
                        "descriptionOfGoods" -> "ZMyM5HTSTnLqT5FT9aHXwScqXKC1VitlWeO5gs91cVXBXOB8xBdXG5aGhG9VFjjDGiraIETFfbQWeA7VUokO7ngDOrKZ23ccKKMA6C3GpXciUTt9nS2pzCFFFeg4BXdkIe",
                        "DangerousGoods"     -> Json.arr()
                      ),
                      "Packaging" -> Json.arr(
                        Json.obj(
                          "sequenceNumber" -> "48711",
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
            )
          )
      )

  }

  object CC019C {

    lazy val xmlValid = <ncts:CC019C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
        <messageSender>GdOcminxBxSLGm1rRUn0q96S2</messageSender>
        <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
        <preparationDateAndTime>2022-01-22T07:43:36</preparationDateAndTime>
        <messageIdentification>6Onxa3En</messageIdentification>
        <messageType>CC019C</messageType>
        <TransitOperation>
          <MRN>qvRcL</MRN>
          <discrepanciesNotificationDate>2014-06-09</discrepanciesNotificationDate>
        </TransitOperation>
        <CustomsOfficeOfDeparture>
          <referenceNumber>ZQZ20443</referenceNumber>
        </CustomsOfficeOfDeparture>
        <HolderOfTheTransitProcedure>
          <identificationNumber>SFzsisksA</identificationNumber>
        </HolderOfTheTransitProcedure>
        <Guarantor>
          <identificationNumber>GdzWtySAdjL</identificationNumber>
        </Guarantor>
    </ncts:CC019C>

    lazy val invalidXml = <ncts:CC019C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
    </ncts:CC019C>

    lazy val jsonValid =
      Json.obj(
        "n1:CC019C" ->
          Json.obj(
            "preparationDateAndTime" -> "2022-01-22T07:43:36",
            "TransitOperation" -> Json.obj(
              "MRN"                           -> "qvRcL",
              "discrepanciesNotificationDate" -> "2014-06-09"
            ),
            "CustomsOfficeOfDeparture" -> Json.obj(
              "referenceNumber" -> "ZQZ20443"
            ),
            "messageType"      -> "CC019C",
            "@PhaseID"         -> "NCTS5.0",
            "messageSender"    -> "GdOcminxBxSLGm1rRUn0q96S2",
            "messageRecipient" -> "FdOcminxBxSLGm1rRUn0q96S1",
            "Guarantor" -> Json.obj(
              "identificationNumber" -> "GdzWtySAdjL"
            ),
            "HolderOfTheTransitProcedure" -> Json.obj(
              "identificationNumber" -> "SFzsisksA"
            ),
            "messageIdentification" -> "6Onxa3En"
          )
      )

    lazy val invalidJson =
      Json.obj(
        "n1:CC019C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )
  }

  object CC044C {

    lazy val xmlValid = <ncts:CC044C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>6Onxa3En</messageIdentification>
      <messageType>CC044C</messageType>
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
      <Consignment>
        <grossMass>1000.000000000001</grossMass>
        <TransportEquipment>
          <sequenceNumber>1122</sequenceNumber>
          <Seal>
            <sequenceNumber>sq1</sequenceNumber>
            <identifier>id1</identifier>
          </Seal>
          <GoodsReference>
            <sequenceNumber>gr1</sequenceNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
          </GoodsReference>
        </TransportEquipment>
        <DepartureTransportMeans>
          <sequenceNumber>dtm1</sequenceNumber>
        </DepartureTransportMeans>
        <SupportingDocument>
          <sequenceNumber>123</sequenceNumber>
        </SupportingDocument>
        <TransportDocument>
          <sequenceNumber>4567</sequenceNumber>
        </TransportDocument>
        <AdditionalReference>
          <sequenceNumber>ar2</sequenceNumber>
        </AdditionalReference>
         <HouseConsignment>
          <sequenceNumber>hc1</sequenceNumber>
          <grossMass>3000.000000000002</grossMass>
          <DepartureTransportMeans>
            <sequenceNumber>dtm1</sequenceNumber>
          </DepartureTransportMeans>
          <SupportingDocument>
            <sequenceNumber>sd1</sequenceNumber>
          </SupportingDocument>
          <TransportDocument>
            <sequenceNumber>td1</sequenceNumber>
          </TransportDocument>
          <AdditionalReference>
            <sequenceNumber>AD2</sequenceNumber>
          </AdditionalReference>
          <ConsignmentItem>
            <goodsItemNumber>token</goodsItemNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>pkg123</sequenceNumber>
            </Packaging>
            <SupportingDocument>
              <sequenceNumber>SD1</sequenceNumber>
            </SupportingDocument>
            <TransportDocument>
              <sequenceNumber>TD1</sequenceNumber>
              <type>A</type>
              <referenceNumber>A1</referenceNumber>
            </TransportDocument>
            <AdditionalReference>
              <sequenceNumber>ar22</sequenceNumber>
            </AdditionalReference>
          </ConsignmentItem>
        </HouseConsignment>
      </Consignment>
    </ncts:CC044C>

    lazy val invalidXml = <ncts:CC044C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
    </ncts:CC044C>

    lazy val jsonValid = Json.obj(
      "n1:CC044C" ->
        Json.obj(
          "preparationDateAndTime" -> "2007-10-26T07:36:28",
          "TransitOperation" -> Json.obj(
            "MRN" -> "qvRcL"
          ),
          "TraderAtDestination" -> Json.obj(
            "identificationNumber" -> "G35dSwQ"
          ),
          "UnloadingRemark" -> Json.obj(
            "conform"             -> "0",
            "unloadingCompletion" -> "1",
            "unloadingDate"       -> "2018-11-01"
          ),
          "messageType"      -> "CC044C",
          "@PhaseID"         -> "NCTS5.0",
          "messageRecipient" -> "FdOcminxBxSLGm1rRUn0q96S1",
          "Consignment" -> Json.obj(
            "grossMass" -> 1000.000000000001,
            "TransportEquipment" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> "1122",
                "Seal" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> "sq1",
                    "identifier"     -> "id1"
                  )
                ),
                "GoodsReference" -> Json.arr(
                  Json.obj(
                    "sequenceNumber"             -> "gr1",
                    "declarationGoodsItemNumber" -> 100
                  )
                )
              )
            ),
            "DepartureTransportMeans" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> "dtm1"
              )
            ),
            "SupportingDocument" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> "123"
              )
            ),
            "TransportDocument" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> "4567"
              )
            ),
            "AdditionalReference" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> "ar2"
              )
            ),
            "HouseConsignment" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> "hc1",
                "grossMass"      -> 3000.000000000002,
                "DepartureTransportMeans" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> "dtm1"
                  )
                ),
                "SupportingDocument" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> "sd1"
                  )
                ),
                "TransportDocument" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> "td1"
                  )
                ),
                "AdditionalReference" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> "AD2"
                  )
                ),
                "ConsignmentItem" -> Json.arr(
                  Json.obj(
                    "goodsItemNumber"            -> "token",
                    "declarationGoodsItemNumber" -> 100,
                    "Packaging" -> Json.arr(
                      Json.obj(
                        "sequenceNumber" -> "pkg123"
                      )
                    ),
                    "SupportingDocument" -> Json.arr(
                      Json.obj(
                        "sequenceNumber" -> "SD1"
                      )
                    ),
                    "TransportDocument" -> Json.arr(
                      Json.obj(
                        "sequenceNumber"  -> "TD1",
                        "referenceNumber" -> "A1",
                        "type"            -> "A"
                      )
                    ),
                    "AdditionalReference" -> Json.arr(
                      Json.obj(
                        "sequenceNumber" -> "ar22"
                      )
                    )
                  )
                )
              )
            )
          ),
          "messageIdentification" -> "6Onxa3En",
          "CustomsOfficeOfDestinationActual" -> Json.obj(
            "referenceNumber" -> "ZQZ20443"
          )
        )
    )

    lazy val invalidJson =
      Json.obj(
        "n1:CC044C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )

  }
}

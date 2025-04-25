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

package uk.gov.hmrc.transitmovementsconverter.itbase

import play.api.libs.json.Json

object TestObjects {

  object CC015C {

    lazy val invalidXml1 = <ncts:CC015C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>FdOcminxBxSLGm1rRUn0q96S1</messageSender>
    </ncts:CC015C>

    lazy val xml1 = <ncts:CC015C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>Ge1cminxBxSLGm1rRUn0q96R2</messageSender>
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
            "messageSender"          -> "Ge1cminxBxSLGm1rRUn0q96R2",
            "messageRecipient"       -> "FdOcminxBxSLGm1rRUn0q96S1",
            "preparationDateAndTime" -> "2022-01-22T07:43:36",
            "messageIdentification"  -> "6Onxa3En",
            "messageType"            -> "CC015C",
            "TransitOperation" ->
              Json.obj(
                "LRN"                       -> "qvRcL",
                "reducedDatasetIndicator"   -> "1",
                "bindingItinerary"          -> "0",
                "additionalDeclarationType" -> "O",
                "declarationType"           -> "Pbg",
                "security"                  -> "8"
              ),
            "CustomsOfficeOfDeparture" ->
              Json.obj(
                "referenceNumber" -> "ZQZ20442"
              ),
            "CustomsOfficeOfDestinationDeclared" ->
              Json.obj(
                "referenceNumber" -> "ZQZ20442"
              ),
            "HolderOfTheTransitProcedure" ->
              Json.obj(
                "identificationNumber" -> "SFzsisksA"
              ),
            "Guarantee" ->
              Json.arr(
                Json.obj(
                  "sequenceNumber" -> 48711,
                  "guaranteeType"  -> "1",
                  "GuaranteeReference" ->
                    Json.arr(),
                  "otherGuaranteeReference" -> "1qJMA6MbhnnrOJJjHBHX"
                )
              ),
            "Consignment" ->
              Json.obj(
                "grossMass" -> 6430669292.48125,
                "HouseConsignment" ->
                  Json.arr(
                    Json.obj(
                      "TransportDocument" ->
                        Json.arr(),
                      "ConsignmentItem" ->
                        Json.arr(
                          Json.obj(
                            "goodsItemNumber" -> 18914,
                            "TransportDocument" ->
                              Json.arr(),
                            "AdditionalInformation" ->
                              Json.arr(),
                            "Commodity" ->
                              Json.obj(
                                "descriptionOfGoods" -> "ZMyM5HTSTnLqT5FT9aHXwScqXKC1VitlWeO5gs91cVXBXOB8xBdXG5aGhG9VFjjDGiraIETFfbQWeA7VUokO7ngDOrKZ23ccKKMA6C3GpXciUTt9nS2pzCFFFeg4BXdkIe",
                                "DangerousGoods" ->
                                  Json.arr()
                              ),
                            "declarationGoodsItemNumber" -> 1458,
                            "SupportingDocument" ->
                              Json.arr(),
                            "Packaging" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber" -> 48711,
                                  "typeOfPackages" -> "Oi"
                                )
                              ),
                            "PreviousDocument" ->
                              Json.arr(),
                            "AdditionalReference" ->
                              Json.arr(),
                            "AdditionalSupplyChainActor" ->
                              Json.arr()
                          )
                        ),
                      "AdditionalInformation" ->
                        Json.arr(),
                      "grossMass" -> 6430669292.48125,
                      "PreviousDocument" ->
                        Json.arr(),
                      "sequenceNumber" -> 48711,
                      "DepartureTransportMeans" ->
                        Json.arr(),
                      "SupportingDocument" ->
                        Json.arr(),
                      "AdditionalReference" ->
                        Json.arr(),
                      "AdditionalSupplyChainActor" ->
                        Json.arr()
                    )
                  )
              ),
            "@PhaseID" -> "NCTS5.0"
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

  object CC025C {

    lazy val xmlValid = <ncts:CC025C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>GdOcminxBxSLGm1rRUn0q96S2</messageSender>
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
      <preparationDateAndTime>2022-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>6Onxa3En</messageIdentification>
      <messageType>CC025C</messageType>
      <correlationIdentifier>co-id-1</correlationIdentifier>
      <TransitOperation>
        <MRN>qvRcL</MRN>
        <releaseDate>2022-06-09+01:00</releaseDate>
        <releaseIndicator>1</releaseIndicator>
      </TransitOperation>
      <CustomsOfficeOfDestinationActual>
        <referenceNumber>2</referenceNumber>
      </CustomsOfficeOfDestinationActual>
      <TraderAtDestination>
        <identificationNumber>3</identificationNumber>
      </TraderAtDestination>
      <Consignment>
        <HouseConsignment>
          <sequenceNumber>3</sequenceNumber>
          <releaseType>1</releaseType>
          <ConsignmentItem>
            <goodsItemNumber>10</goodsItemNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
            <releaseType>2</releaseType>
            <Commodity>
              <descriptionOfGoods>jeans</descriptionOfGoods>
              <cusCode>5</cusCode>
              <CommodityCode>
                <harmonizedSystemSubHeadingCode>12</harmonizedSystemSubHeadingCode>
                <combinedNomenclatureCode>13</combinedNomenclatureCode>
              </CommodityCode>
              <DangerousGoods>
                <sequenceNumber>4</sequenceNumber>
                <UNNumber>6</UNNumber>
              </DangerousGoods>
            </Commodity>
            <Packaging>
              <sequenceNumber>4</sequenceNumber>
              <typeOfPackages>2</typeOfPackages>
              <numberOfPackages>100</numberOfPackages>
              <shippingMarks>blue</shippingMarks>
            </Packaging>
          </ConsignmentItem>
        </HouseConsignment>
      </Consignment>
    </ncts:CC025C>

    lazy val xmlInvalid = <ncts:CC025C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>GdOcminxBxSLGm1rRUn0q96S2</messageSender>
    </ncts:CC025C>

    lazy val jsonValid =
      Json.obj(
        "n1:CC025C" ->
          Json.obj(
            "messageSender"          -> "GdOcminxBxSLGm1rRUn0q96S2",
            "messageRecipient"       -> "FdOcminxBxSLGm1rRUn0q96S1",
            "preparationDateAndTime" -> "2022-10-26T07:36:28",
            "messageIdentification"  -> "6Onxa3En",
            "messageType"            -> "CC025C",
            "correlationIdentifier"  -> "co-id-1",
            "TransitOperation" ->
              Json.obj(
                "MRN"              -> "qvRcL",
                "releaseDate"      -> "2022-06-09+01:00",
                "releaseIndicator" -> "1"
              ),
            "CustomsOfficeOfDestinationActual" ->
              Json.obj(
                "referenceNumber" -> "2"
              ),
            "TraderAtDestination" ->
              Json.obj(
                "identificationNumber" -> "3"
              ),
            "Consignment" ->
              Json.obj(
                "HouseConsignment" ->
                  Json.arr(
                    Json.obj(
                      "sequenceNumber" -> 3,
                      "releaseType"    -> "1",
                      "ConsignmentItem" ->
                        Json.arr(
                          Json.obj(
                            "declarationGoodsItemNumber" -> 100,
                            "Packaging" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber"   -> 4,
                                  "typeOfPackages"   -> "2",
                                  "numberOfPackages" -> 100,
                                  "shippingMarks"    -> "blue"
                                )
                              ),
                            "Commodity" ->
                              Json.obj(
                                "descriptionOfGoods" -> "jeans",
                                "DangerousGoods" ->
                                  Json.arr(
                                    Json.obj(
                                      "sequenceNumber" -> 4,
                                      "UNNumber"       -> "6"
                                    )
                                  ),
                                "cusCode" -> "5",
                                "CommodityCode" ->
                                  Json.obj(
                                    "harmonizedSystemSubHeadingCode" -> "12",
                                    "combinedNomenclatureCode"       -> "13"
                                  )
                              ),
                            "releaseType"     -> "2",
                            "goodsItemNumber" -> 10
                          )
                        )
                    )
                  )
              ),
            "@PhaseID" -> "NCTS5.0"
          )
      )

    lazy val jsonInvalid =
      Json.obj(
        "n1:CC025C" ->
          Json.obj(
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )
  }

  object CC035C {

    lazy val xmlValid = <ncts:CC035C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
        <messageSender>GdOcminxBxSLGm1rRUn0q96S2</messageSender>
        <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
        <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
        <messageIdentification>6Onxa3En</messageIdentification>
        <messageType>CC035C</messageType>
        <correlationIdentifier>cn-id-1</correlationIdentifier>
        <TransitOperation>
          <MRN>mrn-1</MRN>
          <declarationAcceptanceDate>2014-06-09+01:00</declarationAcceptanceDate>
        </TransitOperation>
        <RecoveryNotification>
          <recoveryNotificationDate>2008-11-15</recoveryNotificationDate>
          <recoveryNotificationText>some notification</recoveryNotificationText>
          <amountClaimed>1000.01</amountClaimed>
          <currency>EURO</currency>
        </RecoveryNotification>
        <CustomsOfficeOfDeparture>
          <referenceNumber>DOVER-1</referenceNumber>
        </CustomsOfficeOfDeparture>
        <CustomsOfficeOfRecoveryAtDeparture>
          <referenceNumber>DOVER-2</referenceNumber>
        </CustomsOfficeOfRecoveryAtDeparture>
        <HolderOfTheTransitProcedure>
          <identificationNumber>30001</identificationNumber>
          <TIRHolderIdentificationNumber>30002</TIRHolderIdentificationNumber>
          <name>Smith</name>
          <Address>
            <streetAndNumber>10 The High Street</streetAndNumber>
            <postcode>N1 3PZ</postcode>
            <city>Paris</city>
            <country>FR</country>
          </Address>
        </HolderOfTheTransitProcedure>
        <Guarantor>
          <identificationNumber>23456</identificationNumber>
          <name>Jane Doe</name>
          <Address>
            <streetAndNumber>Main Street 2</streetAndNumber>
            <postcode>N98 1ZZ</postcode>
            <city>Newcastle</city>
            <country>LV</country>
          </Address>
        </Guarantor>
      </ncts:CC035C>

    lazy val xmlInvalid = <ncts:CC035C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>GdOcminxBxSLGm1rRUn0q96S2</messageSender>
    </ncts:CC035C>

    lazy val jsonValid = Json.obj(
      "n1:CC035C" ->
        Json.obj(
          "CustomsOfficeOfRecoveryAtDeparture" -> Json.obj(
            "referenceNumber" -> "DOVER-2"
          ),
          "preparationDateAndTime" -> "2007-10-26T07:36:28",
          "TransitOperation" -> Json.obj(
            "MRN"                       -> "mrn-1",
            "declarationAcceptanceDate" -> "2014-06-09+01:00"
          ),
          "CustomsOfficeOfDeparture" -> Json.obj(
            "referenceNumber" -> "DOVER-1"
          ),
          "RecoveryNotification" -> Json.obj(
            "recoveryNotificationDate" -> "2008-11-15",
            "recoveryNotificationText" -> "some notification",
            "amountClaimed"            -> 1000.01,
            "currency"                 -> "EURO"
          ),
          "messageSender"         -> "GdOcminxBxSLGm1rRUn0q96S2",
          "messageType"           -> "CC035C",
          "@PhaseID"              -> "NCTS5.0",
          "correlationIdentifier" -> "cn-id-1",
          "messageRecipient"      -> "FdOcminxBxSLGm1rRUn0q96S1",
          "Guarantor" -> Json.obj(
            "identificationNumber" -> "23456",
            "name"                 -> "Jane Doe",
            "Address" -> Json.obj(
              "streetAndNumber" -> "Main Street 2",
              "postcode"        -> "N98 1ZZ",
              "city"            -> "Newcastle",
              "country"         -> "LV"
            )
          ),
          "HolderOfTheTransitProcedure" -> Json.obj(
            "identificationNumber"          -> "30001",
            "TIRHolderIdentificationNumber" -> "30002",
            "name"                          -> "Smith",
            "Address" -> Json.obj(
              "streetAndNumber" -> "10 The High Street",
              "postcode"        -> "N1 3PZ",
              "city"            -> "Paris",
              "country"         -> "FR"
            )
          ),
          "messageIdentification" -> "6Onxa3En"
        )
    )

    lazy val jsonInvalid =
      Json.obj(
        "n1:CC035C" ->
          Json.obj(
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )
  }

  object CC043C {
    lazy val xmlValid = <ncts:CC043C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>41SFANJ3qS79HFh5LYfs3TF2LDilwYVYqqN</messageSender>
      <messageRecipient>PdVWmZvOTZz6mahN-0000000000000000</messageRecipient>
      <preparationDateAndTime>2025-03-31T12:58:25</preparationDateAndTime>
      <messageIdentification>ZBWwOTeoCqYMaAeAY8Y31eUfLaOK8erhR</messageIdentification>
      <messageType>CC043C</messageType>
      <correlationIdentifier>8AxBt4L3rnp</correlationIdentifier>
      <TransitOperation>
        <MRN>26WBSP5YO22NFZBDK1</MRN>
        <declarationType>jWRVr</declarationType>
        <declarationAcceptanceDate>2025-03-31</declarationAcceptanceDate>
        <security>6</security>
        <reducedDatasetIndicator>0</reducedDatasetIndicator>
      </TransitOperation>
      <CustomsOfficeOfDestinationActual>
        <referenceNumber>UZXOOMXT</referenceNumber>
      </CustomsOfficeOfDestinationActual>
      <HolderOfTheTransitProcedure>
        <identificationNumber>3rGXFbPxH9mbK</identificationNumber>
        <TIRHolderIdentificationNumber>RvYVetCTsKj</TIRHolderIdentificationNumber>
        <name>Ye3p5Ts8Mvu9fse44bB6RjAmbtrlbpyaUm0Pqx4OTPA0uQCCnlTQU1</name>
        <Address>
          <streetAndNumber>YGgcPH6gtajCp8hP2zGbedRLSIdx40MXJb</streetAndNumber>
          <postcode>myxILuGb</postcode>
          <city>0xbfZn58cLcypafN8nfzobtfsmva</city>
          <country>GS</country>
        </Address>
      </HolderOfTheTransitProcedure>
      <TraderAtDestination>
        <identificationNumber>i</identificationNumber>
      </TraderAtDestination>
      <CTLControl>
        <continueUnloading>9</continueUnloading>
      </CTLControl>
      <Consignment>
        <countryOfDestination>CH</countryOfDestination>
        <containerIndicator>0</containerIndicator>
        <inlandModeOfTransport>1</inlandModeOfTransport>
        <grossMass>12345.6789</grossMass>
        <Consignor>
          <identificationNumber>FrEQFlb5GseYtOG</identificationNumber>
          <name>jdk5SptgzVd5Ukjs9OkYfyGXC8SO2CW7ZS2OBj7</name>
          <Address>
            <streetAndNumber>sXHdiozRcU5qNbEe3N0YgAKEAH0eSq27PfbaKnLO1kzl0y7PG</streetAndNumber>
            <postcode>sau0OjFjDp6Zi3VC</postcode>
            <city>uk3Hrbe</city>
            <country>TB</country>
          </Address>
        </Consignor>
        <Consignee>
          <identificationNumber>va4Ymtc0JctX11</identificationNumber>
          <name>L3NvJ4LiJ1JI9KilKtt1YbsbfS9CNLFKlfjSBPtDE8rb0iw1AOPbkoQIyivK</name>
          <Address>
            <streetAndNumber>s51SBbgjWJQGiTKcsBg4Dq</streetAndNumber>
            <postcode>wu0pKlhLNMYsgMYJ</postcode>
            <city>vb7Kt5oUf1zoVyev0q</city>
            <country>XJ</country>
          </Address>
        </Consignee>
        <TransportEquipment>
          <sequenceNumber>14848</sequenceNumber>
          <containerIdentificationNumber>XRFDbfYon0</containerIdentificationNumber>
          <numberOfSeals>97</numberOfSeals>
          <Seal>
            <sequenceNumber>10869</sequenceNumber>
            <identifier>FoUbPaFDwTLgc1WpPJv</identifier>
          </Seal>
          <GoodsReference>
            <sequenceNumber>62475</sequenceNumber>
            <declarationGoodsItemNumber>996</declarationGoodsItemNumber>
          </GoodsReference>
        </TransportEquipment>
        <DepartureTransportMeans>
          <sequenceNumber>81355</sequenceNumber>
          <typeOfIdentification>53</typeOfIdentification>
          <identificationNumber>H6Q1SvmZGf</identificationNumber>
          <nationality>RO</nationality>
        </DepartureTransportMeans>
        <PreviousDocument>
          <sequenceNumber>76282</sequenceNumber>
          <type>VRPa</type>
          <referenceNumber>xblR5IfoHqt6uT</referenceNumber>
          <complementOfInformation>X0t8PC3flnwVP6CfbhKXeIp9s66Aogi</complementOfInformation>
        </PreviousDocument>
        <SupportingDocument>
          <sequenceNumber>15518</sequenceNumber>
          <type>elZY</type>
          <referenceNumber>Kf2v3XqxJjnH</referenceNumber>
          <complementOfInformation>aRQd8GtSACcR</complementOfInformation>
        </SupportingDocument>
        <TransportDocument>
          <sequenceNumber>43686</sequenceNumber>
          <type>NGMP</type>
          <referenceNumber>2bFu9hRWNlMPxYQlr1OLxwVH7AJD0igvvraOW6ZbwdsEWavIZfNlyHuvr6KplUUB</referenceNumber>
        </TransportDocument>
        <AdditionalReference>
          <sequenceNumber>69777</sequenceNumber>
          <type>ybi8</type>
          <referenceNumber>4uJC8P5iw2wVdHAydOWlxRAs2Ce</referenceNumber>
        </AdditionalReference>
        <AdditionalInformation>
          <sequenceNumber>38727</sequenceNumber>
          <code>BTGGj</code>
          <text>hPTiuI6R1oIgfk3XfhhDIJbyztM09ubmvufufUog5c6Hj0cFOpBHZz1LHid8te43pX3Z1sfzuBgmYsKCt2uPtXVWo0xKriIiNSILgTpUJKfPG5mFX2Sda9tGZkgAoPooFxH7eMuqlx0iEREZv5LlkW49ymOTYKbkgCYktPrg9KJUsLmsc1TJrhUWSsUvN6PPKLvx6lz74bmrJM9xb6F8xNUwgEPAINQr6n16Ta1D7e0hTn4zdDTe19DCpY12o8FlJm07jUzxNWp2BlFw8UrN</text>
        </AdditionalInformation>
        <Incident>
          <sequenceNumber>23448</sequenceNumber>
          <code>7</code>
          <text>UjJOJSeukFAnPWHtNd3CChMdVWm6Tb6F8ePfsoOVi85hQoPJDbpDhZuSizGPrWR6IOWWGiQV3csftGYIXXtLW8WBvgo9L3LWrJNdUPr9NkovULEKTL5PZj6x4s03an71bXOJLGyZiaQm9tylXRm4rJH8uDJx97ygc4KU3hsvTQSD396MB5Awri7onR8i5SeRFgd41BqJCccJ7uZFBsiisV31</text>
          <Endorsement>
            <date>2025-03-31</date>
            <authority>NjKuLDHq2kGg1TKcg5BG6ocHDR</authority>
            <place>UHHrSTsfMZSIz445kDF3YrWCEZWPZ</place>
            <country>DB</country>
          </Endorsement>
          <Location>
            <qualifierOfIdentification>J</qualifierOfIdentification>
            <UNLocode>RHjdkq4Y</UNLocode>
            <country>WC</country>
            <GNSS>
              <latitude>90.000000</latitude>
              <longitude>180.000000</longitude>
            </GNSS>
            <Address>
              <streetAndNumber>xUVdmeE19Am</streetAndNumber>
              <postcode>Qwdp3VofGaFdI6P</postcode>
              <city>PaWRWjvEVkOt4GwOzJw1NF82HWAC</city>
            </Address>
          </Location>
          <TransportEquipment>
            <sequenceNumber>62677</sequenceNumber>
            <containerIdentificationNumber>VuiK6U0s</containerIdentificationNumber>
            <numberOfSeals>802</numberOfSeals>
            <Seal>
              <sequenceNumber>71715</sequenceNumber>
              <identifier>awHHiNhL9o1G5</identifier>
            </Seal>
            <GoodsReference>
              <sequenceNumber>73377</sequenceNumber>
              <declarationGoodsItemNumber>622</declarationGoodsItemNumber>
            </GoodsReference>
          </TransportEquipment>
          <Transhipment>
            <containerIndicator>1</containerIndicator>
            <TransportMeans>
              <typeOfIdentification>74</typeOfIdentification>
              <identificationNumber>gg6KV7cORk0JJond7WWQ8U6M0</identificationNumber>
              <nationality>OB</nationality>
            </TransportMeans>
          </Transhipment>
        </Incident>
        <HouseConsignment>
          <sequenceNumber>70825</sequenceNumber>
          <countryOfDestination>TR</countryOfDestination>
          <grossMass>9876.54321</grossMass>
          <securityIndicatorFromExportDeclaration>3</securityIndicatorFromExportDeclaration>
          <Consignor>
            <identificationNumber>BQ1HdiNjr3VeK0I</identificationNumber>
            <name>vdxSm0mBVXiLxzfUK7JRzMv</name>
            <Address>
              <streetAndNumber>C0q19QeqQIM9oyGUdBjfwbZ38u4HM</streetAndNumber>
              <postcode>TD5UIGQdfW5g</postcode>
              <city>DlroLDbDQ</city>
              <country>TU</country>
            </Address>
          </Consignor>
          <Consignee>
            <identificationNumber>eeIiS1OPSTY</identificationNumber>
            <name>0Bx1td</name>
            <Address>
              <streetAndNumber>0rqqSPEgPnNjmM0GPEfAeOGbcBB4ar</streetAndNumber>
              <postcode>RAx</postcode>
              <city>EbiykdllnfVPt8KAxTWsarjZ65rB8xsR1</city>
              <country>XH</country>
            </Address>
          </Consignee>
          <DepartureTransportMeans>
            <sequenceNumber>92653</sequenceNumber>
            <typeOfIdentification>47</typeOfIdentification>
            <identificationNumber>wJ</identificationNumber>
            <nationality>SV</nationality>
          </DepartureTransportMeans>
          <PreviousDocument>
            <sequenceNumber>95738</sequenceNumber>
            <type>ae07</type>
            <referenceNumber>ZqgFpWpraJuG5RZS0eTYjBFzY7aoMJKEiU3kPFi5kkH8Dy6i9QcGnC0FZfpA2Cs</referenceNumber>
            <complementOfInformation>LmzTb7e7ZLvT4E</complementOfInformation>
          </PreviousDocument>
          <SupportingDocument>
            <sequenceNumber>54357</sequenceNumber>
            <type>bpHP</type>
            <referenceNumber>Qyk6Zetg3U5eNF12mYCNLfPJQHd276iH</referenceNumber>
            <complementOfInformation>mCAv6Unq</complementOfInformation>
          </SupportingDocument>
          <TransportDocument>
            <sequenceNumber>46315</sequenceNumber>
            <type>tUEs</type>
            <referenceNumber>tH3DcnUokCCbkXFhjhEciWrK40TfFOcxmZmoCKP1rScTzpW8x2n4xET4uJxBftxZJdGtxs</referenceNumber>
          </TransportDocument>
          <AdditionalReference>
            <sequenceNumber>9317</sequenceNumber>
            <type>qLM6</type>
            <referenceNumber>4CQ9zcSER4cjI8DmuMw3ZDboZe0</referenceNumber>
          </AdditionalReference>
          <AdditionalInformation>
            <sequenceNumber>59865</sequenceNumber>
            <code>QdGfl</code>
            <text>feMHmi</text>
          </AdditionalInformation>
          <ConsignmentItem>
            <goodsItemNumber>51439</goodsItemNumber>
            <declarationGoodsItemNumber>882</declarationGoodsItemNumber>
            <declarationType>9W</declarationType>
            <countryOfDestination>AF</countryOfDestination>
            <Consignee>
              <identificationNumber>2tUaCMtLIBw</identificationNumber>
              <name>5D24gcuPHu0yx56hyj0nJGiZKmWb3XWu</name>
              <Address>
                <streetAndNumber>csxYjvlUuojccc3</streetAndNumber>
                <postcode>656NMGoiJRgYfo</postcode>
                <city>YcPkeYMCeP4VRBiz</city>
                <country>EI</country>
              </Address>
            </Consignee>
            <Commodity>
              <descriptionOfGoods>3vikv8d9BvFgGeRyxeNFnrVjaazwIcC4CL9n3Peo2HPHKdjoFsbXVT3ml0lwBjYNzjmmNJDBpbVGFZDK2F00</descriptionOfGoods>
              <cusCode>DytxgskCm</cusCode>
              <CommodityCode>
                <harmonizedSystemSubHeadingCode>A3UCz1</harmonizedSystemSubHeadingCode>
                <combinedNomenclatureCode>So</combinedNomenclatureCode>
              </CommodityCode>
              <DangerousGoods>
                <sequenceNumber>29421</sequenceNumber>
                <UNNumber>3643</UNNumber>
              </DangerousGoods>
            </Commodity>
            <Packaging>
              <sequenceNumber>24315</sequenceNumber>
              <typeOfPackages>EZ</typeOfPackages>
              <numberOfPackages>2261115</numberOfPackages>
              <shippingMarks>00252979421587833898928020</shippingMarks>
            </Packaging>
            <PreviousDocument>
              <sequenceNumber>90825</sequenceNumber>
              <type>pRAG</type>
              <referenceNumber>7LKDeT1Uh02RSwcdNzxLoZJlMh7lPknONqWscStDn8I</referenceNumber>
              <goodsItemNumber>87713</goodsItemNumber>
              <complementOfInformation>9KbnIgdxojRtKHdSrCJkf8mqq26UKLV</complementOfInformation>
            </PreviousDocument>
            <SupportingDocument>
              <sequenceNumber>38936</sequenceNumber>
              <type>nMai</type>
              <referenceNumber>MTBfBBpWSGWsKegEDBGCNZEqMe</referenceNumber>
              <complementOfInformation>DMyrz9E0WPPVTbZoBG</complementOfInformation>
            </SupportingDocument>
            <TransportDocument>
              <sequenceNumber>63956</sequenceNumber>
              <type>Wu72</type>
              <referenceNumber>i4KNl4Q3UKa43SjihEbHDVmIOTVnlKZ8GW9SECQRASLL0K02sCluABiU</referenceNumber>
            </TransportDocument>
            <AdditionalReference>
              <sequenceNumber>46776</sequenceNumber>
              <type>qmNV</type>
              <referenceNumber>F3rluFp8VbTz7OBMXJ5U1</referenceNumber>
            </AdditionalReference>
            <AdditionalInformation>
              <sequenceNumber>61599</sequenceNumber>
              <code>3OGR7</code>
              <text>DsreFzZhfPHxwK2hxbwo9qrAMy0NFqMplf32OxxAGLmF0wlvkrc</text>
            </AdditionalInformation>
          </ConsignmentItem>
        </HouseConsignment>
      </Consignment>
    </ncts:CC043C>

    lazy val xmlInvalid = <ncts:CC043C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
    </ncts:CC043C>

    lazy val jsonValid =
      Json.obj(
        "n1:CC043C" ->
          Json.obj(
            "messageSender"          -> "41SFANJ3qS79HFh5LYfs3TF2LDilwYVYqqN",
            "messageRecipient"       -> "PdVWmZvOTZz6mahN-0000000000000000",
            "preparationDateAndTime" -> "2025-03-31T12:58:25",
            "messageIdentification"  -> "ZBWwOTeoCqYMaAeAY8Y31eUfLaOK8erhR",
            "messageType"            -> "CC043C",
            "correlationIdentifier"  -> "8AxBt4L3rnp",
            "TransitOperation" ->
              Json.obj(
                "reducedDatasetIndicator"   -> "0",
                "declarationAcceptanceDate" -> "2025-03-31",
                "MRN"                       -> "26WBSP5YO22NFZBDK1",
                "declarationType"           -> "jWRVr",
                "security"                  -> "6"
              ),
            "CustomsOfficeOfDestinationActual" ->
              Json.obj(
                "referenceNumber" -> "UZXOOMXT"
              ),
            "HolderOfTheTransitProcedure" ->
              Json.obj(
                "name" -> "Ye3p5Ts8Mvu9fse44bB6RjAmbtrlbpyaUm0Pqx4OTPA0uQCCnlTQU1",
                "Address" ->
                  Json.obj(
                    "streetAndNumber" -> "YGgcPH6gtajCp8hP2zGbedRLSIdx40MXJb",
                    "city"            -> "0xbfZn58cLcypafN8nfzobtfsmva",
                    "country"         -> "GS",
                    "postcode"        -> "myxILuGb"
                  ),
                "identificationNumber"          -> "3rGXFbPxH9mbK",
                "TIRHolderIdentificationNumber" -> "RvYVetCTsKj"
              ),
            "TraderAtDestination" ->
              Json.obj(
                "identificationNumber" -> "i"
              ),
            "CTLControl" ->
              Json.obj(
                "continueUnloading" -> 9
              ),
            "Consignment" ->
              Json.obj(
                "Incident" ->
                  Json.arr(
                    Json.obj(
                      "Endorsement" ->
                        Json.obj(
                          "date"      -> "2025-03-31",
                          "authority" -> "NjKuLDHq2kGg1TKcg5BG6ocHDR",
                          "place"     -> "UHHrSTsfMZSIz445kDF3YrWCEZWPZ",
                          "country"   -> "DB"
                        ),
                      "Location" ->
                        Json.obj(
                          "Address" ->
                            Json.obj(
                              "streetAndNumber" -> "xUVdmeE19Am",
                              "city"            -> "PaWRWjvEVkOt4GwOzJw1NF82HWAC",
                              "postcode"        -> "Qwdp3VofGaFdI6P"
                            ),
                          "country"                   -> "WC",
                          "UNLocode"                  -> "RHjdkq4Y",
                          "qualifierOfIdentification" -> "J",
                          "GNSS" ->
                            Json.obj(
                              "latitude"  -> "90.000000",
                              "longitude" -> "180.000000"
                            )
                        ),
                      "Transhipment" ->
                        Json.obj(
                          "containerIndicator" -> "1",
                          "TransportMeans" ->
                            Json.obj(
                              "typeOfIdentification" -> "74",
                              "identificationNumber" -> "gg6KV7cORk0JJond7WWQ8U6M0",
                              "nationality"          -> "OB"
                            )
                        ),
                      "text" -> "UjJOJSeukFAnPWHtNd3CChMdVWm6Tb6F8ePfsoOVi85hQoPJDbpDhZuSizGPrWR6IOWWGiQV3csftGYIXXtLW8WBvgo9L3LWrJNdUPr9NkovULEKTL5PZj6x4s03an71bXOJLGyZiaQm9tylXRm4rJH8uDJx97ygc4KU3hsvTQSD396MB5Awri7onR8i5SeRFgd41BqJCccJ7uZFBsiisV31",
                      "TransportEquipment" ->
                        Json.arr(
                          Json.obj(
                            "GoodsReference" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber"             -> 73377,
                                  "declarationGoodsItemNumber" -> 622
                                )
                              ),
                            "containerIdentificationNumber" -> "VuiK6U0s",
                            "numberOfSeals"                 -> 802,
                            "sequenceNumber"                -> 62677,
                            "Seal" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber" -> 71715,
                                  "identifier"     -> "awHHiNhL9o1G5"
                                )
                              )
                          )
                        ),
                      "code"           -> "7",
                      "sequenceNumber" -> 23448
                    )
                  ),
                "TransportDocument" ->
                  Json.arr(
                    Json.obj(
                      "sequenceNumber"  -> 43686,
                      "referenceNumber" -> "2bFu9hRWNlMPxYQlr1OLxwVH7AJD0igvvraOW6ZbwdsEWavIZfNlyHuvr6KplUUB",
                      "type"            -> "NGMP"
                    )
                  ),
                "Consignee" ->
                  Json.obj(
                    "identificationNumber" -> "va4Ymtc0JctX11",
                    "name"                 -> "L3NvJ4LiJ1JI9KilKtt1YbsbfS9CNLFKlfjSBPtDE8rb0iw1AOPbkoQIyivK",
                    "Address" ->
                      Json.obj(
                        "streetAndNumber" -> "s51SBbgjWJQGiTKcsBg4Dq",
                        "city"            -> "vb7Kt5oUf1zoVyev0q",
                        "country"         -> "XJ",
                        "postcode"        -> "wu0pKlhLNMYsgMYJ"
                      )
                  ),
                "AdditionalInformation" ->
                  Json.arr(
                    Json.obj(
                      "sequenceNumber" -> 38727,
                      "code"           -> "BTGGj",
                      "text" -> "hPTiuI6R1oIgfk3XfhhDIJbyztM09ubmvufufUog5c6Hj0cFOpBHZz1LHid8te43pX3Z1sfzuBgmYsKCt2uPtXVWo0xKriIiNSILgTpUJKfPG5mFX2Sda9tGZkgAoPooFxH7eMuqlx0iEREZv5LlkW49ymOTYKbkgCYktPrg9KJUsLmsc1TJrhUWSsUvN6PPKLvx6lz74bmrJM9xb6F8xNUwgEPAINQr6n16Ta1D7e0hTn4zdDTe19DCpY12o8FlJm07jUzxNWp2BlFw8UrN"
                    )
                  ),
                "grossMass" -> 12345.6789,
                "PreviousDocument" ->
                  Json.arr(
                    Json.obj(
                      "sequenceNumber"          -> 76282,
                      "referenceNumber"         -> "xblR5IfoHqt6uT",
                      "complementOfInformation" -> "X0t8PC3flnwVP6CfbhKXeIp9s66Aogi",
                      "type"                    -> "VRPa"
                    )
                  ),
                "HouseConsignment" ->
                  Json.arr(
                    Json.obj(
                      "Consignor" ->
                        Json.obj(
                          "identificationNumber" -> "BQ1HdiNjr3VeK0I",
                          "name"                 -> "vdxSm0mBVXiLxzfUK7JRzMv",
                          "Address" ->
                            Json.obj(
                              "streetAndNumber" -> "C0q19QeqQIM9oyGUdBjfwbZ38u4HM",
                              "city"            -> "DlroLDbDQ",
                              "country"         -> "TU",
                              "postcode"        -> "TD5UIGQdfW5g"
                            )
                        ),
                      "TransportDocument" ->
                        Json.arr(
                          Json.obj(
                            "sequenceNumber"  -> 46315,
                            "referenceNumber" -> "tH3DcnUokCCbkXFhjhEciWrK40TfFOcxmZmoCKP1rScTzpW8x2n4xET4uJxBftxZJdGtxs",
                            "type"            -> "tUEs"
                          )
                        ),
                      "Consignee" ->
                        Json.obj(
                          "identificationNumber" -> "eeIiS1OPSTY",
                          "name"                 -> "0Bx1td",
                          "Address" ->
                            Json.obj(
                              "streetAndNumber" -> "0rqqSPEgPnNjmM0GPEfAeOGbcBB4ar",
                              "city"            -> "EbiykdllnfVPt8KAxTWsarjZ65rB8xsR1",
                              "country"         -> "XH",
                              "postcode"        -> "RAx"
                            )
                        ),
                      "ConsignmentItem" ->
                        Json.arr(
                          Json.obj(
                            "goodsItemNumber" -> 51439,
                            "Consignee" ->
                              Json.obj(
                                "identificationNumber" -> "2tUaCMtLIBw",
                                "name"                 -> "5D24gcuPHu0yx56hyj0nJGiZKmWb3XWu",
                                "Address" ->
                                  Json.obj(
                                    "streetAndNumber" -> "csxYjvlUuojccc3",
                                    "city"            -> "YcPkeYMCeP4VRBiz",
                                    "country"         -> "EI",
                                    "postcode"        -> "656NMGoiJRgYfo"
                                  )
                              ),
                            "AdditionalInformation" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber" -> 61599,
                                  "code"           -> "3OGR7",
                                  "text"           -> "DsreFzZhfPHxwK2hxbwo9qrAMy0NFqMplf32OxxAGLmF0wlvkrc"
                                )
                              ),
                            "Commodity" ->
                              Json.obj(
                                "descriptionOfGoods" -> "3vikv8d9BvFgGeRyxeNFnrVjaazwIcC4CL9n3Peo2HPHKdjoFsbXVT3ml0lwBjYNzjmmNJDBpbVGFZDK2F00",
                                "DangerousGoods" ->
                                  Json.arr(
                                    Json.obj(
                                      "sequenceNumber" -> 29421,
                                      "UNNumber"       -> "3643"
                                    )
                                  ),
                                "cusCode" -> "DytxgskCm",
                                "CommodityCode" ->
                                  Json.obj(
                                    "harmonizedSystemSubHeadingCode" -> "A3UCz1",
                                    "combinedNomenclatureCode"       -> "So"
                                  )
                              ),
                            "TransportDocument" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber"  -> 63956,
                                  "referenceNumber" -> "i4KNl4Q3UKa43SjihEbHDVmIOTVnlKZ8GW9SECQRASLL0K02sCluABiU",
                                  "type"            -> "Wu72"
                                )
                              ),
                            "declarationType"            -> "9W",
                            "declarationGoodsItemNumber" -> 882,
                            "SupportingDocument" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber"          -> 38936,
                                  "referenceNumber"         -> "MTBfBBpWSGWsKegEDBGCNZEqMe",
                                  "complementOfInformation" -> "DMyrz9E0WPPVTbZoBG",
                                  "type"                    -> "nMai"
                                )
                              ),
                            "Packaging" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber"   -> 24315,
                                  "typeOfPackages"   -> "EZ",
                                  "numberOfPackages" -> 2261115,
                                  "shippingMarks"    -> "00252979421587833898928020"
                                )
                              ),
                            "PreviousDocument" ->
                              Json.arr(
                                Json.obj(
                                  "goodsItemNumber"         -> 87713,
                                  "type"                    -> "pRAG",
                                  "sequenceNumber"          -> 90825,
                                  "complementOfInformation" -> "9KbnIgdxojRtKHdSrCJkf8mqq26UKLV",
                                  "referenceNumber"         -> "7LKDeT1Uh02RSwcdNzxLoZJlMh7lPknONqWscStDn8I"
                                )
                              ),
                            "countryOfDestination" -> "AF",
                            "AdditionalReference" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber"  -> 46776,
                                  "referenceNumber" -> "F3rluFp8VbTz7OBMXJ5U1",
                                  "type"            -> "qmNV"
                                )
                              )
                          )
                        ),
                      "AdditionalInformation" ->
                        Json.arr(
                          Json.obj(
                            "sequenceNumber" -> 59865,
                            "code"           -> "QdGfl",
                            "text"           -> "feMHmi"
                          )
                        ),
                      "securityIndicatorFromExportDeclaration" -> "3",
                      "grossMass"                              -> 9876.54321,
                      "PreviousDocument" ->
                        Json.arr(
                          Json.obj(
                            "sequenceNumber"          -> 95738,
                            "referenceNumber"         -> "ZqgFpWpraJuG5RZS0eTYjBFzY7aoMJKEiU3kPFi5kkH8Dy6i9QcGnC0FZfpA2Cs",
                            "complementOfInformation" -> "LmzTb7e7ZLvT4E",
                            "type"                    -> "ae07"
                          )
                        ),
                      "sequenceNumber" -> 70825,
                      "DepartureTransportMeans" ->
                        Json.arr(
                          Json.obj(
                            "sequenceNumber"       -> 92653,
                            "typeOfIdentification" -> "47",
                            "identificationNumber" -> "wJ",
                            "nationality"          -> "SV"
                          )
                        ),
                      "SupportingDocument" ->
                        Json.arr(
                          Json.obj(
                            "sequenceNumber"          -> 54357,
                            "referenceNumber"         -> "Qyk6Zetg3U5eNF12mYCNLfPJQHd276iH",
                            "complementOfInformation" -> "mCAv6Unq",
                            "type"                    -> "bpHP"
                          )
                        ),
                      "countryOfDestination" -> "TR",
                      "AdditionalReference" ->
                        Json.arr(
                          Json.obj(
                            "sequenceNumber"  -> 9317,
                            "referenceNumber" -> "4CQ9zcSER4cjI8DmuMw3ZDboZe0",
                            "type"            -> "qLM6"
                          )
                        )
                    )
                  ),
                "Consignor" ->
                  Json.obj(
                    "identificationNumber" -> "FrEQFlb5GseYtOG",
                    "name"                 -> "jdk5SptgzVd5Ukjs9OkYfyGXC8SO2CW7ZS2OBj7",
                    "Address" ->
                      Json.obj(
                        "streetAndNumber" -> "sXHdiozRcU5qNbEe3N0YgAKEAH0eSq27PfbaKnLO1kzl0y7PG",
                        "city"            -> "uk3Hrbe",
                        "country"         -> "TB",
                        "postcode"        -> "sau0OjFjDp6Zi3VC"
                      )
                  ),
                "inlandModeOfTransport" -> "1",
                "TransportEquipment" ->
                  Json.arr(
                    Json.obj(
                      "GoodsReference" ->
                        Json.arr(
                          Json.obj(
                            "sequenceNumber"             -> 62475,
                            "declarationGoodsItemNumber" -> 996
                          )
                        ),
                      "containerIdentificationNumber" -> "XRFDbfYon0",
                      "numberOfSeals"                 -> 97,
                      "sequenceNumber"                -> 14848,
                      "Seal" ->
                        Json.arr(
                          Json.obj(
                            "sequenceNumber" -> 10869,
                            "identifier"     -> "FoUbPaFDwTLgc1WpPJv"
                          )
                        )
                    )
                  ),
                "containerIndicator" -> "0",
                "DepartureTransportMeans" ->
                  Json.arr(
                    Json.obj(
                      "sequenceNumber"       -> 81355,
                      "typeOfIdentification" -> "53",
                      "identificationNumber" -> "H6Q1SvmZGf",
                      "nationality"          -> "RO"
                    )
                  ),
                "SupportingDocument" ->
                  Json.arr(
                    Json.obj(
                      "sequenceNumber"          -> 15518,
                      "referenceNumber"         -> "Kf2v3XqxJjnH",
                      "complementOfInformation" -> "aRQd8GtSACcR",
                      "type"                    -> "elZY"
                    )
                  ),
                "countryOfDestination" -> "CH",
                "AdditionalReference" ->
                  Json.arr(
                    Json.obj(
                      "sequenceNumber"  -> 69777,
                      "referenceNumber" -> "4uJC8P5iw2wVdHAydOWlxRAs2Ce",
                      "type"            -> "ybi8"
                    )
                  )
              ),
            "@PhaseID" -> "NCTS5.0"
          )
      )

    lazy val jsonInvalid =
      Json.obj(
        "n1:CC043C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )
  }

  object CC044C {

    lazy val xmlValid =
      <ncts:CC044C PhaseID="NCTS5.1" xmlns:ncts="http://ncts.dgtaxud.ec">
        <messageSender>9999912345</messageSender>
        <messageRecipient>NTA.GB</messageRecipient>
        <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
        <messageIdentification>1</messageIdentification>
        <messageType>CC044C</messageType>
        <TransitOperation>
          <MRN>24XI000081LAQJBTJ2</MRN>
        </TransitOperation>
        <CustomsOfficeOfDestinationActual>
          <referenceNumber>GB000246</referenceNumber>
        </CustomsOfficeOfDestinationActual>
        <TraderAtDestination>
          <identificationNumber>GB953574106000</identificationNumber>
        </TraderAtDestination>
        <UnloadingRemark>
          <conform>0</conform>
          <unloadingCompletion>1</unloadingCompletion>
          <unloadingDate>2018-11-01</unloadingDate>
          <stateOfSeals>0</stateOfSeals>
        </UnloadingRemark>
        <Consignment>
          <grossMass>3000</grossMass>
          <TransportEquipment>
            <sequenceNumber>1</sequenceNumber>
            <Seal>
              <sequenceNumber>2</sequenceNumber>
              <identifier>5678</identifier>
            </Seal>
          </TransportEquipment>
          <HouseConsignment>
            <sequenceNumber>1</sequenceNumber>
            <grossMass>500</grossMass>
            <ConsignmentItem>
              <goodsItemNumber>1</goodsItemNumber>
              <declarationGoodsItemNumber>1</declarationGoodsItemNumber>
            </ConsignmentItem>
            <ConsignmentItem>
              <goodsItemNumber>2</goodsItemNumber>
              <declarationGoodsItemNumber>2</declarationGoodsItemNumber>
              <Commodity>
                <GoodsMeasure>
                  <grossMass>500</grossMass>
                  <netMass>300</netMass>
                </GoodsMeasure>
              </Commodity>
            </ConsignmentItem>
          </HouseConsignment>
          <HouseConsignment>
            <sequenceNumber>2</sequenceNumber>
            <grossMass>2500</grossMass>
            <ConsignmentItem>
              <goodsItemNumber>1</goodsItemNumber>
              <declarationGoodsItemNumber>3</declarationGoodsItemNumber>
              <Commodity>
                <descriptionOfGoods>Steel sheets</descriptionOfGoods>
                <CommodityCode>
                  <harmonizedSystemSubHeadingCode>730110</harmonizedSystemSubHeadingCode>
                </CommodityCode>
                <GoodsMeasure>
                  <grossMass>800</grossMass>
                  <netMass>750</netMass>
                </GoodsMeasure>
              </Commodity>
              <Packaging>
                <sequenceNumber>1</sequenceNumber>
                <typeOfPackages>NE</typeOfPackages>
                <numberOfPackages>6</numberOfPackages>
              </Packaging>
            </ConsignmentItem>
            <ConsignmentItem>
              <goodsItemNumber>2</goodsItemNumber>
              <declarationGoodsItemNumber>4</declarationGoodsItemNumber>
              <Commodity>
                <GoodsMeasure>
                  <grossMass>1200</grossMass>
                  <netMass>1000</netMass>
                </GoodsMeasure>
              </Commodity>
            </ConsignmentItem>
            <ConsignmentItem>
              <goodsItemNumber>3</goodsItemNumber>
              <declarationGoodsItemNumber>5</declarationGoodsItemNumber>
              <Commodity>
                <descriptionOfGoods>Cardboard Sheets</descriptionOfGoods>
                <CommodityCode>
                  <harmonizedSystemSubHeadingCode>480700</harmonizedSystemSubHeadingCode>
                </CommodityCode>
                <GoodsMeasure>
                  <grossMass>500</grossMass>
                  <netMass>300</netMass>
                </GoodsMeasure>
              </Commodity>
              <Packaging>
                <sequenceNumber>1</sequenceNumber>
                <typeOfPackages>BX</typeOfPackages>
                <numberOfPackages>10</numberOfPackages>
                <shippingMarks>Shipping marks or references</shippingMarks>
              </Packaging>
            </ConsignmentItem>
          </HouseConsignment>
        </Consignment>
      </ncts:CC044C>

    lazy val xmlInvalid = <ncts:CC044C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
    </ncts:CC044C>

    lazy val jsonValid =
      Json.obj(
        "n1:CC044C" ->
          Json.obj(
            "messageSender"          -> "9999912345",
            "messageRecipient"       -> "NTA.GB",
            "preparationDateAndTime" -> "2007-10-26T07:36:28",
            "messageIdentification"  -> "1",
            "messageType"            -> "CC044C",
            "TransitOperation" ->
              Json.obj(
                "MRN" -> "24XI000081LAQJBTJ2"
              ),
            "CustomsOfficeOfDestinationActual" ->
              Json.obj(
                "referenceNumber" -> "GB000246"
              ),
            "TraderAtDestination" ->
              Json.obj(
                "identificationNumber" -> "GB953574106000"
              ),
            "UnloadingRemark" ->
              Json.obj(
                "conform"             -> "0",
                "unloadingCompletion" -> "1",
                "unloadingDate"       -> "2018-11-01",
                "stateOfSeals"        -> "0"
              ),
            "Consignment" ->
              Json.obj(
                "TransportDocument" ->
                  Json.arr(),
                "TransportEquipment" ->
                  Json.arr(
                    Json.obj(
                      "sequenceNumber" -> 1,
                      "Seal" ->
                        Json.arr(
                          Json.obj(
                            "sequenceNumber" -> 2,
                            "identifier"     -> "5678"
                          )
                        ),
                      "GoodsReference" ->
                        Json.arr()
                    )
                  ),
                "grossMass" -> 3000,
                "HouseConsignment" ->
                  Json.arr(
                    Json.obj(
                      "TransportDocument" ->
                        Json.arr(),
                      "ConsignmentItem" ->
                        Json.arr(
                          Json.obj(
                            "goodsItemNumber" -> 1,
                            "TransportDocument" ->
                              Json.arr(),
                            "Packaging" ->
                              Json.arr(),
                            "AdditionalReference" ->
                              Json.arr(),
                            "declarationGoodsItemNumber" -> 1,
                            "SupportingDocument" ->
                              Json.arr()
                          ),
                          Json.obj(
                            "goodsItemNumber" -> 2,
                            "TransportDocument" ->
                              Json.arr(),
                            "Packaging" ->
                              Json.arr(),
                            "Commodity" ->
                              Json.obj(
                                "GoodsMeasure" ->
                                  Json.obj(
                                    "grossMass" -> 500,
                                    "netMass"   -> 300
                                  )
                              ),
                            "AdditionalReference" ->
                              Json.arr(),
                            "declarationGoodsItemNumber" -> 2,
                            "SupportingDocument" ->
                              Json.arr()
                          )
                        ),
                      "grossMass"      -> 500,
                      "sequenceNumber" -> 1,
                      "AdditionalReference" ->
                        Json.arr(),
                      "DepartureTransportMeans" ->
                        Json.arr(),
                      "SupportingDocument" ->
                        Json.arr()
                    ),
                    Json.obj(
                      "TransportDocument" ->
                        Json.arr(),
                      "ConsignmentItem" ->
                        Json.arr(
                          Json.obj(
                            "goodsItemNumber" -> 1,
                            "TransportDocument" ->
                              Json.arr(),
                            "Packaging" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber"   -> 1,
                                  "typeOfPackages"   -> "NE",
                                  "numberOfPackages" -> 6
                                )
                              ),
                            "Commodity" ->
                              Json.obj(
                                "descriptionOfGoods" -> "Steel sheets",
                                "CommodityCode" ->
                                  Json.obj(
                                    "harmonizedSystemSubHeadingCode" -> "730110"
                                  ),
                                "GoodsMeasure" ->
                                  Json.obj(
                                    "grossMass" -> 800,
                                    "netMass"   -> 750
                                  )
                              ),
                            "AdditionalReference" ->
                              Json.arr(),
                            "declarationGoodsItemNumber" -> 3,
                            "SupportingDocument" ->
                              Json.arr()
                          ),
                          Json.obj(
                            "goodsItemNumber" -> 2,
                            "TransportDocument" ->
                              Json.arr(),
                            "Packaging" ->
                              Json.arr(),
                            "Commodity" ->
                              Json.obj(
                                "GoodsMeasure" ->
                                  Json.obj(
                                    "grossMass" -> 1200,
                                    "netMass"   -> 1000
                                  )
                              ),
                            "AdditionalReference" ->
                              Json.arr(),
                            "declarationGoodsItemNumber" -> 4,
                            "SupportingDocument" ->
                              Json.arr()
                          ),
                          Json.obj(
                            "goodsItemNumber" -> 3,
                            "TransportDocument" ->
                              Json.arr(),
                            "Packaging" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber"   -> 1,
                                  "typeOfPackages"   -> "BX",
                                  "numberOfPackages" -> 10,
                                  "shippingMarks"    -> "Shipping marks or references"
                                )
                              ),
                            "Commodity" ->
                              Json.obj(
                                "descriptionOfGoods" -> "Cardboard Sheets",
                                "CommodityCode" ->
                                  Json.obj(
                                    "harmonizedSystemSubHeadingCode" -> "480700"
                                  ),
                                "GoodsMeasure" ->
                                  Json.obj(
                                    "grossMass" -> 500,
                                    "netMass"   -> 300
                                  )
                              ),
                            "AdditionalReference" ->
                              Json.arr(),
                            "declarationGoodsItemNumber" -> 5,
                            "SupportingDocument" ->
                              Json.arr()
                          )
                        ),
                      "grossMass"      -> 2500,
                      "sequenceNumber" -> 2,
                      "AdditionalReference" ->
                        Json.arr(),
                      "DepartureTransportMeans" ->
                        Json.arr(),
                      "SupportingDocument" ->
                        Json.arr()
                    )
                  ),
                "AdditionalReference" ->
                  Json.arr(),
                "DepartureTransportMeans" ->
                  Json.arr(),
                "SupportingDocument" ->
                  Json.arr()
              ),
            "@PhaseID" -> "NCTS5.1"
          )
      )

    lazy val jsonInvalid =
      Json.obj(
        "n1:CC044C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )
  }

  object CC045C {

    lazy val xmlValid = <ncts:CC045C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>FdOcminxBxSLGm1rRUn0q96S1</messageSender>
      <messageRecipient>XzcminxBxSLGm1rRUn0q96S2</messageRecipient>
      <preparationDateAndTime>2022-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>6Onxa3En</messageIdentification>
      <messageType>CC045C</messageType>
      <correlationIdentifier>corr-1</correlationIdentifier>
      <TransitOperation>
        <MRN>mrn1</MRN>
        <writeOffDate>2022-06-09+01:00</writeOffDate>
      </TransitOperation>
      <CustomsOfficeOfDeparture>
        <referenceNumber>ZQZ20443</referenceNumber>
      </CustomsOfficeOfDeparture>
      <HolderOfTheTransitProcedure>
        <identificationNumber>Fzsisks</identificationNumber>
        <TIRHolderIdentificationNumber>tir-id-1</TIRHolderIdentificationNumber>
        <name>Jean Doe</name>
        <Address>
          <streetAndNumber>1 main street</streetAndNumber>
          <postcode>ab12 3cd</postcode>
          <city>Newcastle</city>
          <country>GB</country>
        </Address>
      </HolderOfTheTransitProcedure>
      <Guarantor>
        <identificationNumber>id2</identificationNumber>
        <name>john doe</name>
        <Address>
          <streetAndNumber>1 high street</streetAndNumber>
          <postcode>N1 99Z</postcode>
          <city>Newcastle</city>
          <country>GB</country>
        </Address>
      </Guarantor>
    </ncts:CC045C>

    lazy val xmlInvalid = <ncts:CC045C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
    </ncts:CC045C>

    lazy val jsonValid = Json.obj(
      "n1:CC045C" ->
        Json.obj(
          "preparationDateAndTime" -> "2022-10-26T07:36:28",
          "TransitOperation" -> Json.obj(
            "MRN"          -> "mrn1",
            "writeOffDate" -> "2022-06-09+01:00"
          ),
          "CustomsOfficeOfDeparture" -> Json.obj(
            "referenceNumber" -> "ZQZ20443"
          ),
          "messageType"           -> "CC045C",
          "@PhaseID"              -> "NCTS5.0",
          "correlationIdentifier" -> "corr-1",
          "messageSender"         -> "FdOcminxBxSLGm1rRUn0q96S1",
          "messageRecipient"      -> "XzcminxBxSLGm1rRUn0q96S2",
          "Guarantor" -> Json.obj(
            "identificationNumber" -> "id2",
            "name"                 -> "john doe",
            "Address" -> Json.obj(
              "streetAndNumber" -> "1 high street",
              "postcode"        -> "N1 99Z",
              "city"            -> "Newcastle",
              "country"         -> "GB"
            )
          ),
          "HolderOfTheTransitProcedure" -> Json.obj(
            "identificationNumber"          -> "Fzsisks",
            "TIRHolderIdentificationNumber" -> "tir-id-1",
            "name"                          -> "Jean Doe",
            "Address" -> Json.obj(
              "streetAndNumber" -> "1 main street",
              "postcode"        -> "ab12 3cd",
              "city"            -> "Newcastle",
              "country"         -> "GB"
            )
          ),
          "messageIdentification" -> "6Onxa3En"
        )
    )

    lazy val jsonInvalid =
      Json.obj(
        "n1:CC045C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )
  }

  object CC057C {

    lazy val xmlValid = <ncts:CC057C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>token</messageSender>
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
      <preparationDateAndTime>2022-12-25T07:36:28</preparationDateAndTime>
      <messageIdentification>6Onxa3En</messageIdentification>
      <messageType>CC057C</messageType>
      <correlationIdentifier>co-id-1</correlationIdentifier>
      <TransitOperation>
        <MRN>qvRc</MRN>
        <businessRejectionType>sdfghj</businessRejectionType>
        <rejectionDateAndTime>2014-12-25T16:15:04+01:00</rejectionDateAndTime>
        <rejectionCode>2</rejectionCode>
        <rejectionReason>N5t6u7</rejectionReason>
      </TransitOperation>
      <CustomsOfficeOfDestinationActual>
        <referenceNumber>2</referenceNumber>
      </CustomsOfficeOfDestinationActual>
      <TraderAtDestination>
        <identificationNumber>1</identificationNumber>
      </TraderAtDestination>
      <FunctionalError>
        <errorPointer>2</errorPointer>
        <errorCode>13</errorCode>
        <errorReason>waf 12</errorReason>
        <originalAttributeValue>2</originalAttributeValue>
      </FunctionalError>
    </ncts:CC057C>

    lazy val xmlInvalid = <ncts:CC057C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
    </ncts:CC057C>

    lazy val jsonValid = Json.obj(
      "n1:CC057C" ->
        Json.obj(
          "preparationDateAndTime" -> "2022-12-25T07:36:28",
          "TransitOperation" -> Json.obj(
            "MRN"                   -> "qvRc",
            "businessRejectionType" -> "sdfghj",
            "rejectionDateAndTime"  -> "2014-12-25T16:15:04+01:00",
            "rejectionCode"         -> "2",
            "rejectionReason"       -> "N5t6u7"
          ),
          "TraderAtDestination" -> Json.obj(
            "identificationNumber" -> "1"
          ),
          "messageType"           -> "CC057C",
          "@PhaseID"              -> "NCTS5.0",
          "correlationIdentifier" -> "co-id-1",
          "FunctionalError" -> Json.arr(
            Json.obj(
              "errorPointer"           -> "2",
              "errorCode"              -> "13",
              "errorReason"            -> "waf 12",
              "originalAttributeValue" -> "2"
            )
          ),
          "messageSender"         -> "token",
          "messageRecipient"      -> "FdOcminxBxSLGm1rRUn0q96S1",
          "messageIdentification" -> "6Onxa3En",
          "CustomsOfficeOfDestinationActual" -> Json.obj(
            "referenceNumber" -> "2"
          )
        )
    )

    lazy val jsonInvalid =
      Json.obj(
        "n1:CC057C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )
  }

  object CC140C {

    lazy val xmlValid = <ncts:CC140C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>FdOcminxBxSLGm1rRUn0q96S1</messageSender>
      <messageRecipient>XzcminxBxSLGm1rRUn0q96S2</messageRecipient>
      <preparationDateAndTime>2022-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>6Onxa3En</messageIdentification>
      <messageType>CC140C</messageType>
      <correlationIdentifier>corr-1</correlationIdentifier>
      <TransitOperation>
        <MRN>mrn1</MRN>
        <requestOnNonArrivedMovementDate>2022-06-09+01:00</requestOnNonArrivedMovementDate>
        <limitForResponseDate>2022-11-15</limitForResponseDate>
      </TransitOperation>
      <CustomsOfficeOfDeparture>
        <referenceNumber>1</referenceNumber>
      </CustomsOfficeOfDeparture>
      <CustomsOfficeOfEnquiryAtDeparture>
        <referenceNumber>2</referenceNumber>
      </CustomsOfficeOfEnquiryAtDeparture>
      <HolderOfTheTransitProcedure>
        <identificationNumber>Fzsisks</identificationNumber>
        <TIRHolderIdentificationNumber>tir-id-1</TIRHolderIdentificationNumber>
        <name>Jean Doe</name>
        <Address>
          <streetAndNumber>1 main street</streetAndNumber>
          <postcode>ab12 3cd</postcode>
          <city>Newcastle</city>
          <country>GB</country>
        </Address>
      </HolderOfTheTransitProcedure>
    </ncts:CC140C>

    lazy val xmlInvalid = <ncts:CC140C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
    </ncts:CC140C>

    lazy val jsonValid = Json.obj(
      "n1:CC140C" ->
        Json.obj(
          "preparationDateAndTime" -> "2022-10-26T07:36:28",
          "TransitOperation" -> Json.obj(
            "MRN"                             -> "mrn1",
            "requestOnNonArrivedMovementDate" -> "2022-06-09+01:00",
            "limitForResponseDate"            -> "2022-11-15"
          ),
          "CustomsOfficeOfDeparture" -> Json.obj(
            "referenceNumber" -> "1"
          ),
          "messageType" -> "CC140C",
          "CustomsOfficeOfEnquiryAtDeparture" -> Json.obj(
            "referenceNumber" -> "2"
          ),
          "@PhaseID"              -> "NCTS5.0",
          "correlationIdentifier" -> "corr-1",
          "messageSender"         -> "FdOcminxBxSLGm1rRUn0q96S1",
          "messageRecipient"      -> "XzcminxBxSLGm1rRUn0q96S2",
          "HolderOfTheTransitProcedure" -> Json.obj(
            "identificationNumber"          -> "Fzsisks",
            "TIRHolderIdentificationNumber" -> "tir-id-1",
            "name"                          -> "Jean Doe",
            "Address" -> Json.obj(
              "streetAndNumber" -> "1 main street",
              "postcode"        -> "ab12 3cd",
              "city"            -> "Newcastle",
              "country"         -> "GB"
            )
          ),
          "messageIdentification" -> "6Onxa3En"
        )
    )

    lazy val jsonInvalid =
      Json.obj(
        "n1:CC140C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )

  }

  object CC141C {

    lazy val xmlValid = <ncts:CC141C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>FdOcminxBxSLGm1rRUn0q96S1</messageSender>
      <messageRecipient>XzcminxBxSLGm1rRUn0q96S2</messageRecipient>
      <preparationDateAndTime>2022-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>6Onxa3En</messageIdentification>
      <messageType>CC141C</messageType>
      <correlationIdentifier>corr-1</correlationIdentifier>
      <TransitOperation>
        <MRN>mrn1</MRN>
      </TransitOperation>
      <CustomsOfficeOfDestinationActual>
        <referenceNumber>1</referenceNumber>
      </CustomsOfficeOfDestinationActual>
      <CustomsOfficeOfEnquiryAtDeparture>
        <referenceNumber>2</referenceNumber>
      </CustomsOfficeOfEnquiryAtDeparture>
      <HolderOfTheTransitProcedure>
        <identificationNumber>Fzsisks</identificationNumber>
        <TIRHolderIdentificationNumber>tir-id-1</TIRHolderIdentificationNumber>
        <name>Jean Doe</name>
        <Address>
          <streetAndNumber>1 main street</streetAndNumber>
          <postcode>ab12 3cd</postcode>
          <city>Newcastle</city>
          <country>GB</country>
        </Address>
      </HolderOfTheTransitProcedure>
    </ncts:CC141C>

    lazy val xmlInvalid = <ncts:CC141C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
    </ncts:CC141C>

    lazy val jsonValid = Json.obj(
      "n1:CC141C" ->
        Json.obj(
          "preparationDateAndTime" -> "2022-10-26T07:36:28",
          "TransitOperation" -> Json.obj(
            "MRN" -> "mrn1"
          ),
          "messageType" -> "CC141C",
          "CustomsOfficeOfEnquiryAtDeparture" -> Json.obj(
            "referenceNumber" -> "2"
          ),
          "@PhaseID"              -> "NCTS5.0",
          "correlationIdentifier" -> "corr-1",
          "messageSender"         -> "FdOcminxBxSLGm1rRUn0q96S1",
          "messageRecipient"      -> "XzcminxBxSLGm1rRUn0q96S2",
          "HolderOfTheTransitProcedure" -> Json.obj(
            "identificationNumber"          -> "Fzsisks",
            "TIRHolderIdentificationNumber" -> "tir-id-1",
            "name"                          -> "Jean Doe",
            "Address" -> Json.obj(
              "streetAndNumber" -> "1 main street",
              "postcode"        -> "ab12 3cd",
              "city"            -> "Newcastle",
              "country"         -> "GB"
            )
          ),
          "messageIdentification" -> "6Onxa3En",
          "CustomsOfficeOfDestinationActual" -> Json.obj(
            "referenceNumber" -> "1"
          )
        )
    )

    lazy val jsonInvalid =
      Json.obj(
        "n1:CC141C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )

  }

  object CC182C {

    lazy val xmlValid = <ncts:CC182C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>FdOcminxBxSLGm1rRUn0q96S1</messageSender>
      <messageRecipient>XzcminxBxSLGm1rRUn0q96S2</messageRecipient>
      <preparationDateAndTime>2022-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>6Onxa3En</messageIdentification>
      <messageType>CC182C</messageType>
      <correlationIdentifier>corr-1</correlationIdentifier>
      <TransitOperation>
        <MRN>mrn1</MRN>
        <incidentNotificationDateAndTime>2022-06-09T16:15:04+01:00</incidentNotificationDateAndTime>
      </TransitOperation>
      <CustomsOfficeOfDeparture>
        <referenceNumber>1</referenceNumber>
      </CustomsOfficeOfDeparture>
      <CustomsOfficeOfIncidentRegistration>
        <referenceNumber>2</referenceNumber>
      </CustomsOfficeOfIncidentRegistration>
      <Consignment>
        <Incident>
          <sequenceNumber>3</sequenceNumber>
          <code>1</code>
          <text>an incident</text>
          <Endorsement>
            <date>2022-11-15</date>
            <authority>ports</authority>
            <place>Dover</place>
            <country>GB</country>
          </Endorsement>
          <Location>
            <qualifierOfIdentification>token</qualifierOfIdentification>
            <UNLocode>34</UNLocode>
            <country>FR</country>
            <GNSS>
              <latitude>90.1</latitude>
              <longitude>90.2</longitude>
            </GNSS>
            <Address>
              <streetAndNumber>2 high street</streetAndNumber>
              <postcode>ab12 34c</postcode>
              <city>city2</city>
            </Address>
          </Location>
          <TransportEquipment>
            <sequenceNumber>5</sequenceNumber>
            <containerIdentificationNumber>1</containerIdentificationNumber>
            <numberOfSeals>100</numberOfSeals>
            <Seal>
              <sequenceNumber>34</sequenceNumber>
              <identifier>sl7</identifier>
            </Seal>
            <GoodsReference>
              <sequenceNumber>55</sequenceNumber>
              <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
            </GoodsReference>
          </TransportEquipment>
          <Transhipment>
            <containerIndicator>0</containerIndicator>
            <TransportMeans>
              <typeOfIdentification>4</typeOfIdentification>
              <identificationNumber>22</identificationNumber>
              <nationality>FR</nationality>
            </TransportMeans>
          </Transhipment>
        </Incident>
      </Consignment>
    </ncts:CC182C>

    lazy val xmlInvalid = <ncts:CC182C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>FdOcminxBxSLGm1rRUn0q96S1</messageRecipient>
    </ncts:CC182C>

    lazy val jsonValid =
      Json.obj(
        "n1:CC182C" ->
          Json.obj(
            "messageSender"          -> "FdOcminxBxSLGm1rRUn0q96S1",
            "messageRecipient"       -> "XzcminxBxSLGm1rRUn0q96S2",
            "preparationDateAndTime" -> "2022-10-26T07:36:28",
            "messageIdentification"  -> "6Onxa3En",
            "messageType"            -> "CC182C",
            "correlationIdentifier"  -> "corr-1",
            "TransitOperation" ->
              Json.obj(
                "MRN"                             -> "mrn1",
                "incidentNotificationDateAndTime" -> "2022-06-09T16:15:04+01:00"
              ),
            "CustomsOfficeOfDeparture" ->
              Json.obj(
                "referenceNumber" -> "1"
              ),
            "CustomsOfficeOfIncidentRegistration" ->
              Json.obj(
                "referenceNumber" -> "2"
              ),
            "Consignment" ->
              Json.obj(
                "Incident" ->
                  Json.arr(
                    Json.obj(
                      "Endorsement" ->
                        Json.obj(
                          "date"      -> "2022-11-15",
                          "authority" -> "ports",
                          "place"     -> "Dover",
                          "country"   -> "GB"
                        ),
                      "Location" ->
                        Json.obj(
                          "Address" ->
                            Json.obj(
                              "streetAndNumber" -> "2 high street",
                              "city"            -> "city2",
                              "postcode"        -> "ab12 34c"
                            ),
                          "country"                   -> "FR",
                          "UNLocode"                  -> "34",
                          "qualifierOfIdentification" -> "token",
                          "GNSS" ->
                            Json.obj(
                              "latitude"  -> "90.1",
                              "longitude" -> "90.2"
                            )
                        ),
                      "Transhipment" ->
                        Json.obj(
                          "containerIndicator" -> "0",
                          "TransportMeans" ->
                            Json.obj(
                              "typeOfIdentification" -> "4",
                              "identificationNumber" -> "22",
                              "nationality"          -> "FR"
                            )
                        ),
                      "text" -> "an incident",
                      "TransportEquipment" ->
                        Json.arr(
                          Json.obj(
                            "GoodsReference" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber"             -> 55,
                                  "declarationGoodsItemNumber" -> 100
                                )
                              ),
                            "containerIdentificationNumber" -> "1",
                            "numberOfSeals"                 -> 100,
                            "sequenceNumber"                -> 5,
                            "Seal" ->
                              Json.arr(
                                Json.obj(
                                  "sequenceNumber" -> 34,
                                  "identifier"     -> "sl7"
                                )
                              )
                          )
                        ),
                      "code"           -> "1",
                      "sequenceNumber" -> 3
                    )
                  )
              ),
            "@PhaseID" -> "NCTS5.0"
          )
      )

    lazy val jsonInvalid =
      Json.obj(
        "n1:CC182C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )

  }

}

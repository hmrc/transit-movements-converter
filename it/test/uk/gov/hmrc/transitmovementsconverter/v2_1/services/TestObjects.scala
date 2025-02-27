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

package uk.gov.hmrc.transitmovementsconverter.v2_1.services

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
                        "DangerousGoods" -> Json.arr()
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
            "TransitOperation" -> Json.obj(
              "MRN"              -> "qvRcL",
              "releaseDate"      -> "2022-06-09+01:00",
              "releaseIndicator" -> "1"
            ),
            "CustomsOfficeOfDestinationActual" -> Json.obj(
              "referenceNumber" -> "2"
            ),
            "TraderAtDestination" -> Json.obj(
              "identificationNumber" -> "3"
            ),
            "Consignment" -> Json.obj(
              "HouseConsignment" -> Json.arr(
                Json.obj(
                  "sequenceNumber" -> 3,
                  "releaseType"    -> "1",
                  "ConsignmentItem" -> Json.arr(
                    Json.obj(
                      "goodsItemNumber"            -> 10,
                      "declarationGoodsItemNumber" -> 100,
                      "releaseType"                -> "2",
                      "Commodity" -> Json.obj(
                        "descriptionOfGoods" -> "jeans",
                        "cusCode"            -> "5",
                        "CommodityCode" -> Json.obj(
                          "harmonizedSystemSubHeadingCode" -> "12",
                          "combinedNomenclatureCode"       -> "13"
                        ),
                        "DangerousGoods" -> Json.arr(
                          Json.obj(
                            "sequenceNumber" -> 4,
                            "UNNumber"       -> "6"
                          )
                        )
                      ),
                      "Packaging" -> Json.arr(
                        Json.obj(
                          "sequenceNumber"   -> 4,
                          "typeOfPackages"   -> "2",
                          "numberOfPackages" -> 100,
                          "shippingMarks"    -> "blue"
                        )
                      )
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
      <messageSender>FdOcminxBxSLGm1rRUn0q96S1</messageSender>
      <messageRecipient>XzcminxBxSLGm1rRUn0q96S2</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>6Onxa3En</messageIdentification>
      <messageType>CC043C</messageType>
      <correlationIdentifier>corr-1</correlationIdentifier>
      <TransitOperation>
        <MRN>mrn1</MRN>
        <declarationType>3</declarationType>
        <declarationAcceptanceDate>2014-06-09+01:00</declarationAcceptanceDate>
        <security>4</security>
        <reducedDatasetIndicator>1</reducedDatasetIndicator>
      </TransitOperation>
      <CustomsOfficeOfDestinationActual>
        <referenceNumber>ZQZ20443</referenceNumber>
      </CustomsOfficeOfDestinationActual>
      <HolderOfTheTransitProcedure>
        <identificationNumber>Fzsisks</identificationNumber>
        <TIRHolderIdentificationNumber>trp-id-1</TIRHolderIdentificationNumber>
        <name>Jean Doe</name>
        <Address>
          <streetAndNumber>1 avenue marceau</streetAndNumber>
          <postcode>10006</postcode>
          <city>Paris</city>
          <country>FR</country>
        </Address>
      </HolderOfTheTransitProcedure>
      <TraderAtDestination>
        <identificationNumber>tad-1</identificationNumber>
      </TraderAtDestination>
      <CTLControl>
        <continueUnloading>9</continueUnloading>
      </CTLControl>
      <Consignment>
        <countryOfDestination>FR</countryOfDestination>
        <containerIndicator>1</containerIndicator>
        <inlandModeOfTransport>2</inlandModeOfTransport>
        <grossMass>1000.99</grossMass>
        <Consignor>
          <identificationNumber>id2</identificationNumber>
          <name>john doe</name>
          <Address>
            <streetAndNumber>1 high street</streetAndNumber>
            <postcode>N1 99Z</postcode>
            <city>Newcastle</city>
            <country>GB</country>
          </Address>
        </Consignor>
        <Consignee>
          <identificationNumber>csgnee-1</identificationNumber>
          <name>Jane Doe</name>
          <Address>
            <streetAndNumber>1 Champs Elysees</streetAndNumber>
            <postcode>75008</postcode>
            <city>Paris</city>
            <country>FR</country>
          </Address>
        </Consignee>
        <TransportEquipment>
          <sequenceNumber>100</sequenceNumber>
          <containerIdentificationNumber>cin-1</containerIdentificationNumber>
          <numberOfSeals>103</numberOfSeals>
          <Seal>
            <sequenceNumber>1001</sequenceNumber>
            <identifier>1002</identifier>
          </Seal>
          <GoodsReference>
            <sequenceNumber>100</sequenceNumber>
            <declarationGoodsItemNumber>108</declarationGoodsItemNumber>
          </GoodsReference>
        </TransportEquipment>
        <DepartureTransportMeans>
          <sequenceNumber>100</sequenceNumber>
          <typeOfIdentification>4</typeOfIdentification>
          <identificationNumber>28</identificationNumber>
          <nationality>DE</nationality>
        </DepartureTransportMeans>
        <PreviousDocument>
          <sequenceNumber>100</sequenceNumber>
          <type>9811</type>
          <referenceNumber>info1</referenceNumber>
          <complementOfInformation>8</complementOfInformation>
        </PreviousDocument>
        <SupportingDocument>
          <sequenceNumber>100</sequenceNumber>
          <type>4567</type>
          <referenceNumber>1234</referenceNumber>
          <complementOfInformation>2</complementOfInformation>
        </SupportingDocument>
        <TransportDocument>
          <sequenceNumber>100</sequenceNumber>
          <type>4</type>
          <referenceNumber>refn-1</referenceNumber>
        </TransportDocument>
        <AdditionalReference>
          <sequenceNumber>100</sequenceNumber>
          <type>4</type>
          <referenceNumber>addref-1</referenceNumber>
        </AdditionalReference>
        <AdditionalInformation>
          <sequenceNumber>100</sequenceNumber>
          <code>6</code>
          <text>additional ref text</text>
        </AdditionalInformation>
        <Incident>
          <sequenceNumber>100</sequenceNumber>
          <code>5</code>
          <text>some text 1</text>
          <Endorsement>
            <date>2013-05-22+01:00</date>
            <authority>de</authority>
            <place>Cologne</place>
            <country>DE</country>
          </Endorsement>
          <Location>
            <qualifierOfIdentification>loc1</qualifierOfIdentification>
            <UNLocode>34</UNLocode>
            <country>DE</country>
            <GNSS>
              <latitude>91.0</latitude>
              <longitude>92.0</longitude>
            </GNSS>
            <Address>
              <streetAndNumber>2 high street</streetAndNumber>
              <postcode>ab12 34c</postcode>
              <city>city2</city>
            </Address>
          </Location>
          <TransportEquipment>
            <sequenceNumber>100</sequenceNumber>
            <containerIdentificationNumber>tn1</containerIdentificationNumber>
            <numberOfSeals>34</numberOfSeals>
            <Seal>
              <sequenceNumber>100</sequenceNumber>
              <identifier>sl7</identifier>
            </Seal>
            <GoodsReference>
              <sequenceNumber>100</sequenceNumber>
              <declarationGoodsItemNumber>78</declarationGoodsItemNumber>
            </GoodsReference>
          </TransportEquipment>
          <Transhipment>
            <containerIndicator>1</containerIndicator>
            <TransportMeans>
              <typeOfIdentification>5</typeOfIdentification>
              <identificationNumber>44</identificationNumber>
              <nationality>FR</nationality>
            </TransportMeans>
          </Transhipment>
        </Incident>
        <HouseConsignment>
          <sequenceNumber>100</sequenceNumber>
          <grossMass>1234.567</grossMass>
          <securityIndicatorFromExportDeclaration>si1</securityIndicatorFromExportDeclaration>
          <Consignor>
            <identificationNumber>csgr1</identificationNumber>
            <name>michael doe</name>
            <Address>
              <streetAndNumber>3 main street</streetAndNumber>
              <postcode>bc2 45d</postcode>
              <city>city4</city>
              <country>FR</country>
            </Address>
          </Consignor>
          <Consignee>
            <identificationNumber>csgee1</identificationNumber>
            <name>John Smith</name>
            <Address>
              <streetAndNumber>5 main street</streetAndNumber>
              <postcode>cd4 56e</postcode>
              <city>city5</city>
              <country>DE</country>
            </Address>
          </Consignee>
          <DepartureTransportMeans>
            <sequenceNumber>100</sequenceNumber>
            <typeOfIdentification>2</typeOfIdentification>
            <identificationNumber>23</identificationNumber>
            <nationality>IT</nationality>
          </DepartureTransportMeans>
          <PreviousDocument>
            <sequenceNumber>100</sequenceNumber>
            <type>3</type>
            <referenceNumber>4</referenceNumber>
            <complementOfInformation>1</complementOfInformation>
          </PreviousDocument>
          <SupportingDocument>
            <sequenceNumber>100</sequenceNumber>
            <type>7</type>
            <referenceNumber>ref4</referenceNumber>
            <complementOfInformation>6</complementOfInformation>
          </SupportingDocument>
          <TransportDocument>
            <sequenceNumber>100</sequenceNumber>
            <type>8</type>
            <referenceNumber>9</referenceNumber>
          </TransportDocument>
          <AdditionalReference>
            <sequenceNumber>100</sequenceNumber>
            <type>5</type>
            <referenceNumber>4</referenceNumber>
          </AdditionalReference>
          <AdditionalInformation>
            <sequenceNumber>100</sequenceNumber>
            <code>7</code>
            <text>8</text>
          </AdditionalInformation>
          <ConsignmentItem>
            <goodsItemNumber>6</goodsItemNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
            <declarationType>2</declarationType>
            <countryOfDestination>DE</countryOfDestination>
            <Consignee>
              <identificationNumber>5</identificationNumber>
              <name>Smith</name>
              <Address>
                <streetAndNumber>5 main street</streetAndNumber>
                <postcode>ab12 3cd</postcode>
                <city>Newcastle</city>
                <country>GB</country>
              </Address>
            </Consignee>
            <Commodity>
              <descriptionOfGoods>shirts</descriptionOfGoods>
              <cusCode>1</cusCode>
              <CommodityCode>
                <harmonizedSystemSubHeadingCode>3</harmonizedSystemSubHeadingCode>
                <combinedNomenclatureCode>45</combinedNomenclatureCode>
              </CommodityCode>
              <DangerousGoods>
                <sequenceNumber>100</sequenceNumber>
                <UNNumber>1</UNNumber>
              </DangerousGoods>
            </Commodity>
            <Packaging>
              <sequenceNumber>100</sequenceNumber>
              <typeOfPackages>3</typeOfPackages>
              <numberOfPackages>99</numberOfPackages>
              <shippingMarks>xyz</shippingMarks>
            </Packaging>
            <PreviousDocument>
              <sequenceNumber>100</sequenceNumber>
              <type>3</type>
              <referenceNumber>4</referenceNumber>
              <goodsItemNumber>5</goodsItemNumber>
              <complementOfInformation>1</complementOfInformation>
            </PreviousDocument>
            <SupportingDocument>
              <sequenceNumber>100</sequenceNumber>
              <type>7</type>
              <referenceNumber>8</referenceNumber>
              <complementOfInformation>0</complementOfInformation>
            </SupportingDocument>
            <TransportDocument>
              <sequenceNumber>100</sequenceNumber>
              <type>2</type>
              <referenceNumber>4</referenceNumber>
            </TransportDocument>
            <AdditionalReference>
              <sequenceNumber>100</sequenceNumber>
              <type>3</type>
              <referenceNumber>1</referenceNumber>
            </AdditionalReference>
            <AdditionalInformation>
              <sequenceNumber>100</sequenceNumber>
              <code>6</code>
              <text>additional info text</text>
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
        "n1:CC043C" -> Json.obj(
          "messageSender"          -> "FdOcminxBxSLGm1rRUn0q96S1",
          "messageRecipient"       -> "XzcminxBxSLGm1rRUn0q96S2",
          "preparationDateAndTime" -> "2007-10-26T07:36:28",
          "messageIdentification"  -> "6Onxa3En",
          "messageType"            -> "CC043C",
          "correlationIdentifier"  -> "corr-1",
          "TransitOperation" -> Json.obj(
            "MRN"                       -> "mrn1",
            "declarationType"           -> "3",
            "declarationAcceptanceDate" -> "2014-06-09+01:00",
            "security"                  -> "4",
            "reducedDatasetIndicator"   -> "1"
          ),
          "CustomsOfficeOfDestinationActual" -> Json.obj(
            "referenceNumber" -> "ZQZ20443"
          ),
          "HolderOfTheTransitProcedure" -> Json.obj(
            "identificationNumber"          -> "Fzsisks",
            "TIRHolderIdentificationNumber" -> "trp-id-1",
            "name"                          -> "Jean Doe",
            "Address" -> Json.obj(
              "streetAndNumber" -> "1 avenue marceau",
              "postcode"        -> "10006",
              "city"            -> "Paris",
              "country"         -> "FR"
            )
          ),
          "TraderAtDestination" -> Json.obj(
            "identificationNumber" -> "tad-1"
          ),
          "CTLControl" -> Json.obj(
            "continueUnloading" -> 9
          ),
          "Consignment" -> Json.obj(
            "countryOfDestination"  -> "FR",
            "containerIndicator"    -> "1",
            "inlandModeOfTransport" -> "2",
            "grossMass"             -> 1000.99,
            "Consignor" -> Json.obj(
              "identificationNumber" -> "id2",
              "name"                 -> "john doe",
              "Address" -> Json.obj(
                "streetAndNumber" -> "1 high street",
                "postcode"        -> "N1 99Z",
                "city"            -> "Newcastle",
                "country"         -> "GB"
              )
            ),
            "Consignee" -> Json.obj(
              "identificationNumber" -> "csgnee-1",
              "name"                 -> "Jane Doe",
              "Address" -> Json.obj(
                "streetAndNumber" -> "1 Champs Elysees",
                "postcode"        -> "75008",
                "city"            -> "Paris",
                "country"         -> "FR"
              )
            ),
            "TransportEquipment" -> Json.arr(
              Json.obj(
                "sequenceNumber"                -> 100,
                "containerIdentificationNumber" -> "cin-1",
                "numberOfSeals"                 -> 103,
                "Seal" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> 1001,
                    "identifier"     -> "1002"
                  )
                ),
                "GoodsReference" -> Json.arr(
                  Json.obj(
                    "sequenceNumber"             -> 100,
                    "declarationGoodsItemNumber" -> 108
                  )
                )
              )
            ),
            "DepartureTransportMeans" -> Json.arr(
              Json.obj(
                "sequenceNumber"       -> 100,
                "typeOfIdentification" -> "4",
                "identificationNumber" -> "28",
                "nationality"          -> "DE"
              )
            ),
            "PreviousDocument" -> Json.arr(
              Json.obj(
                "sequenceNumber"          -> 100,
                "referenceNumber"         -> "info1",
                "complementOfInformation" -> "8",
                "type"                    -> "9811"
              )
            ),
            "SupportingDocument" -> Json.arr(
              Json.obj(
                "sequenceNumber"          -> 100,
                "referenceNumber"         -> "1234",
                "complementOfInformation" -> "2",
                "type"                    -> "4567"
              )
            ),
            "TransportDocument" -> Json.arr(
              Json.obj(
                "sequenceNumber"  -> 100,
                "referenceNumber" -> "refn-1",
                "type"            -> "4"
              )
            ),
            "AdditionalReference" -> Json.arr(
              Json.obj(
                "sequenceNumber"  -> 100,
                "referenceNumber" -> "addref-1",
                "type"            -> "4"
              )
            ),
            "AdditionalInformation" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> 100,
                "code"           -> "6",
                "text"           -> "additional ref text"
              )
            ),
            "Incident" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> 100,
                "code"           -> "5",
                "text"           -> "some text 1",
                "Endorsement" -> Json.obj(
                  "date"      -> "2013-05-22+01:00",
                  "authority" -> "de",
                  "place"     -> "Cologne",
                  "country"   -> "DE"
                ),
                "Location" -> Json.obj(
                  "qualifierOfIdentification" -> "loc1",
                  "UNLocode"                  -> "34",
                  "country"                   -> "DE",
                  "GNSS" -> Json.obj(
                    "latitude"  -> "91.0",
                    "longitude" -> "92.0"
                  ),
                  "Address" -> Json.obj(
                    "streetAndNumber" -> "2 high street",
                    "postcode"        -> "ab12 34c",
                    "city"            -> "city2"
                  )
                ),
                "TransportEquipment" -> Json.arr(
                  Json.obj(
                    "sequenceNumber"                -> 100,
                    "containerIdentificationNumber" -> "tn1",
                    "numberOfSeals"                 -> 34,
                    "Seal" -> Json.arr(
                      Json.obj(
                        "sequenceNumber" -> 100,
                        "identifier"     -> "sl7"
                      )
                    ),
                    "GoodsReference" -> Json.arr(
                      Json.obj(
                        "sequenceNumber"             -> 100,
                        "declarationGoodsItemNumber" -> 78
                      )
                    )
                  )
                ),
                "Transhipment" -> Json.obj(
                  "containerIndicator" -> "1",
                  "TransportMeans" -> Json.obj(
                    "typeOfIdentification" -> "5",
                    "identificationNumber" -> "44",
                    "nationality"          -> "FR"
                  )
                )
              )
            ),
            "HouseConsignment" -> Json.arr(
              Json.obj(
                "sequenceNumber"                         -> 100,
                "grossMass"                              -> 1234.567,
                "securityIndicatorFromExportDeclaration" -> "si1",
                "Consignor" -> Json.obj(
                  "identificationNumber" -> "csgr1",
                  "name"                 -> "michael doe",
                  "Address" -> Json.obj(
                    "streetAndNumber" -> "3 main street",
                    "postcode"        -> "bc2 45d",
                    "city"            -> "city4",
                    "country"         -> "FR"
                  )
                ),
                "Consignee" -> Json.obj(
                  "identificationNumber" -> "csgee1",
                  "name"                 -> "John Smith",
                  "Address" -> Json.obj(
                    "streetAndNumber" -> "5 main street",
                    "postcode"        -> "cd4 56e",
                    "city"            -> "city5",
                    "country"         -> "DE"
                  )
                ),
                "DepartureTransportMeans" -> Json.arr(
                  Json.obj(
                    "sequenceNumber"       -> 100,
                    "typeOfIdentification" -> "2",
                    "identificationNumber" -> "23",
                    "nationality"          -> "IT"
                  )
                ),
                "PreviousDocument" -> Json.arr(
                  Json.obj(
                    "sequenceNumber"          -> 100,
                    "referenceNumber"         -> "4",
                    "complementOfInformation" -> "1",
                    "type"                    -> "3"
                  )
                ),
                "SupportingDocument" -> Json.arr(
                  Json.obj(
                    "sequenceNumber"          -> 100,
                    "referenceNumber"         -> "ref4",
                    "complementOfInformation" -> "6",
                    "type"                    -> "7"
                  )
                ),
                "TransportDocument" -> Json.arr(
                  Json.obj(
                    "sequenceNumber"  -> 100,
                    "referenceNumber" -> "9",
                    "type"            -> "8"
                  )
                ),
                "AdditionalReference" -> Json.arr(
                  Json.obj(
                    "sequenceNumber"  -> 100,
                    "referenceNumber" -> "4",
                    "type"            -> "5"
                  )
                ),
                "AdditionalInformation" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> 100,
                    "code"           -> "7",
                    "text"           -> "8"
                  )
                ),
                "ConsignmentItem" -> Json.arr(
                  Json.obj(
                    "goodsItemNumber"            -> 6,
                    "declarationGoodsItemNumber" -> 100,
                    "declarationType"            -> "2",
                    "countryOfDestination"       -> "DE",
                    "Consignee" -> Json.obj(
                      "identificationNumber" -> "5",
                      "name"                 -> "Smith",
                      "Address" -> Json.obj(
                        "streetAndNumber" -> "5 main street",
                        "postcode"        -> "ab12 3cd",
                        "city"            -> "Newcastle",
                        "country"         -> "GB"
                      )
                    ),
                    "Commodity" -> Json.obj(
                      "descriptionOfGoods" -> "shirts",
                      "cusCode"            -> "1",
                      "CommodityCode" -> Json.obj(
                        "harmonizedSystemSubHeadingCode" -> "3",
                        "combinedNomenclatureCode"       -> "45"
                      ),
                      "DangerousGoods" -> Json.arr(
                        Json.obj(
                          "sequenceNumber" -> 100,
                          "UNNumber"       -> "1"
                        )
                      )
                    ),
                    "Packaging" -> Json.arr(
                      Json.obj(
                        "sequenceNumber"   -> 100,
                        "typeOfPackages"   -> "3",
                        "numberOfPackages" -> 99,
                        "shippingMarks"    -> "xyz"
                      )
                    ),
                    "PreviousDocument" -> Json.arr(
                      Json.obj(
                        "sequenceNumber"          -> 100,
                        "referenceNumber"         -> "4",
                        "goodsItemNumber"         -> 5,
                        "complementOfInformation" -> "1",
                        "type"                    -> "3"
                      )
                    ),
                    "SupportingDocument" -> Json.arr(
                      Json.obj(
                        "sequenceNumber"          -> 100,
                        "referenceNumber"         -> "8",
                        "complementOfInformation" -> "0",
                        "type"                    -> "7"
                      )
                    ),
                    "TransportDocument" -> Json.arr(
                      Json.obj(
                        "sequenceNumber"  -> 100,
                        "referenceNumber" -> "4",
                        "type"            -> "2"
                      )
                    ),
                    "AdditionalReference" -> Json.arr(
                      Json.obj(
                        "sequenceNumber"  -> 100,
                        "referenceNumber" -> "1",
                        "type"            -> "3"
                      )
                    ),
                    "AdditionalInformation" -> Json.arr(
                      Json.obj(
                        "sequenceNumber" -> 100,
                        "code"           -> "6",
                        "text"           -> "additional info text"
                      )
                    )
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
        "n1:CC043C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )
  }

  object CC044C {

    lazy val xmlValid = <ncts:CC044C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>Ge1cminxBxSLGm1rRUn0q96R3</messageSender>
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
            <sequenceNumber>100</sequenceNumber>
            <identifier>id1</identifier>
          </Seal>
          <GoodsReference>
            <sequenceNumber>100</sequenceNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
          </GoodsReference>
        </TransportEquipment>
        <DepartureTransportMeans>
          <sequenceNumber>100</sequenceNumber>        </DepartureTransportMeans>
        <SupportingDocument>
          <sequenceNumber>100</sequenceNumber>
       </SupportingDocument>
        <TransportDocument>
          <sequenceNumber>100</sequenceNumber>       </TransportDocument>
        <AdditionalReference>
          <sequenceNumber>100</sequenceNumber>
        </AdditionalReference>
        <HouseConsignment>
          <sequenceNumber>100</sequenceNumber>         <grossMass>3000.000000000002</grossMass>
          <DepartureTransportMeans>
            <sequenceNumber>100</sequenceNumber>
          </DepartureTransportMeans>
          <SupportingDocument>
            <sequenceNumber>100</sequenceNumber>         </SupportingDocument>
          <TransportDocument>
            <sequenceNumber>100</sequenceNumber>
         </TransportDocument>
          <AdditionalReference>
            <sequenceNumber>100</sequenceNumber>          </AdditionalReference>
          <ConsignmentItem>
            <goodsItemNumber>100</goodsItemNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>100</sequenceNumber>
            </Packaging>
            <SupportingDocument>
              <sequenceNumber>100</sequenceNumber>          </SupportingDocument>
            <TransportDocument>
              <sequenceNumber>100</sequenceNumber>
              <type>A</type>
              <referenceNumber>A1</referenceNumber>
            </TransportDocument>
            <AdditionalReference>
              <sequenceNumber>100</sequenceNumber>            </AdditionalReference>
          </ConsignmentItem>
        </HouseConsignment>
      </Consignment>
    </ncts:CC044C>

    lazy val xmlInvalid = <ncts:CC044C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
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
          "messageSender"    -> "Ge1cminxBxSLGm1rRUn0q96R3",
          "messageRecipient" -> "FdOcminxBxSLGm1rRUn0q96S1",
          "Consignment" -> Json.obj(
            "grossMass" -> 1000.000000000001,
            "TransportEquipment" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> 1122,
                "Seal" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> 100,
                    "identifier"     -> "id1"
                  )
                ),
                "GoodsReference" -> Json.arr(
                  Json.obj(
                    "sequenceNumber"             -> 100,
                    "declarationGoodsItemNumber" -> 100
                  )
                )
              )
            ),
            "DepartureTransportMeans" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> 100
              )
            ),
            "SupportingDocument" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> 100
              )
            ),
            "TransportDocument" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> 100
              )
            ),
            "AdditionalReference" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> 100
              )
            ),
            "HouseConsignment" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> 100,
                "grossMass"      -> 3000.000000000002,
                "DepartureTransportMeans" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> 100
                  )
                ),
                "SupportingDocument" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> 100
                  )
                ),
                "TransportDocument" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> 100
                  )
                ),
                "AdditionalReference" -> Json.arr(
                  Json.obj(
                    "sequenceNumber" -> 100
                  )
                ),
                "ConsignmentItem" -> Json.arr(
                  Json.obj(
                    "goodsItemNumber"            -> 100,
                    "declarationGoodsItemNumber" -> 100,
                    "Packaging" -> Json.arr(
                      Json.obj(
                        "sequenceNumber" -> 100
                      )
                    ),
                    "SupportingDocument" -> Json.arr(
                      Json.obj(
                        "sequenceNumber" -> 100
                      )
                    ),
                    "TransportDocument" -> Json.arr(
                      Json.obj(
                        "sequenceNumber"  -> 100,
                        "referenceNumber" -> "A1",
                        "type"            -> "A"
                      )
                    ),
                    "AdditionalReference" -> Json.arr(
                      Json.obj(
                        "sequenceNumber" -> 100
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

    lazy val jsonValid = Json.obj(
      "n1:CC182C" ->
        Json.obj(
          "messageSender"          -> "FdOcminxBxSLGm1rRUn0q96S1",
          "messageRecipient"       -> "XzcminxBxSLGm1rRUn0q96S2",
          "preparationDateAndTime" -> "2022-10-26T07:36:28",
          "messageIdentification"  -> "6Onxa3En",
          "messageType"            -> "CC182C",
          "correlationIdentifier"  -> "corr-1",
          "TransitOperation" -> Json.obj(
            "MRN"                             -> "mrn1",
            "incidentNotificationDateAndTime" -> "2022-06-09T16:15:04+01:00"
          ),
          "CustomsOfficeOfDeparture" -> Json.obj(
            "referenceNumber" -> "1"
          ),
          "CustomsOfficeOfIncidentRegistration" -> Json.obj(
            "referenceNumber" -> "2"
          ),
          "Consignment" -> Json.obj(
            "Incident" -> Json.arr(
              Json.obj(
                "sequenceNumber" -> 3,
                "code"           -> "1",
                "text"           -> "an incident",
                "Endorsement" -> Json.obj(
                  "date"      -> "2022-11-15",
                  "authority" -> "ports",
                  "place"     -> "Dover",
                  "country"   -> "GB"
                ),
                "Location" -> Json.obj(
                  "qualifierOfIdentification" -> "token",
                  "UNLocode"                  -> "34",
                  "country"                   -> "FR",
                  "GNSS" -> Json.obj(
                    "latitude"  -> "90.1",
                    "longitude" -> "90.2"
                  ),
                  "Address" -> Json.obj(
                    "streetAndNumber" -> "2 high street",
                    "postcode"        -> "ab12 34c",
                    "city"            -> "city2"
                  )
                ),
                "TransportEquipment" -> Json.arr(
                  Json.obj(
                    "sequenceNumber"                -> 5,
                    "containerIdentificationNumber" -> "1",
                    "numberOfSeals"                 -> 100,
                    "Seal" -> Json.arr(
                      Json.obj(
                        "sequenceNumber" -> 34,
                        "identifier"     -> "sl7"
                      )
                    ),
                    "GoodsReference" -> Json.arr(
                      Json.obj(
                        "sequenceNumber"             -> 55,
                        "declarationGoodsItemNumber" -> 100
                      )
                    )
                  )
                ),
                "Transhipment" -> Json.obj(
                  "containerIndicator" -> "0",
                  "TransportMeans" -> Json.obj(
                    "typeOfIdentification" -> "4",
                    "identificationNumber" -> "22",
                    "nationality"          -> "FR"
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
        "n1:CC182C" ->
          Json.obj(
            "@PhaseID"      -> "NCTS5.0",
            "messageSender" -> "FdOcminxBxSLGm1rRUn0q96S1"
          )
      )

  }

}

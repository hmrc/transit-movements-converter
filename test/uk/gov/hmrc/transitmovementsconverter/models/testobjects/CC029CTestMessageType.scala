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

import play.api.libs.json.Json

object CC029CTestMessageType extends TestMessageType {

  lazy val xml1 =
    <ncts:CC029C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>token</messageSender>
      <messageRecipient>token</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>token</messageIdentification>
      <messageType>CC029C</messageType>
      <!--Optional:-->
      <correlationIdentifier>token</correlationIdentifier>
      <TransitOperation>
        <LRN>string</LRN>
        <MRN>string</MRN>
        <declarationType>token</declarationType>
        <additionalDeclarationType>token</additionalDeclarationType>
        <!--Optional:-->
        <TIRCarnetNumber>string</TIRCarnetNumber>
        <declarationAcceptanceDate>2014-06-09+01:00</declarationAcceptanceDate>
        <releaseDate>2008-11-15</releaseDate>
        <security>token</security>
        <reducedDatasetIndicator>0</reducedDatasetIndicator>
        <!--Optional:-->
        <specificCircumstanceIndicator>token</specificCircumstanceIndicator>
        <!--Optional:-->
        <communicationLanguageAtDeparture>st</communicationLanguageAtDeparture>
        <bindingItinerary>1</bindingItinerary>
      </TransitOperation>
      <!--0 to 9 repetitions:-->
      <Authorisation>
        <sequenceNumber>token</sequenceNumber>
        <type>token</type>
        <referenceNumber>string</referenceNumber>
      </Authorisation>
      <CustomsOfficeOfDeparture>
        <referenceNumber>stringst</referenceNumber>
      </CustomsOfficeOfDeparture>
      <CustomsOfficeOfDestinationDeclared>
        <referenceNumber>stringst</referenceNumber>
      </CustomsOfficeOfDestinationDeclared>
      <!--0 to 9 repetitions:-->
      <CustomsOfficeOfTransitDeclared>
        <sequenceNumber>token</sequenceNumber>
        <referenceNumber>stringst</referenceNumber>
        <!--Optional:-->
        <arrivalDateAndTimeEstimated>2002-11-05T08:01:03+00:00</arrivalDateAndTimeEstimated>
      </CustomsOfficeOfTransitDeclared>
      <!--0 to 9 repetitions:-->
      <CustomsOfficeOfExitForTransitDeclared>
        <sequenceNumber>token</sequenceNumber>
        <referenceNumber>stringst</referenceNumber>
      </CustomsOfficeOfExitForTransitDeclared>
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
        <!--Optional:-->
        <ContactPerson>
          <name>string</name>
          <phoneNumber>string</phoneNumber>
          <!--Optional:-->
          <eMailAddress>string</eMailAddress>
        </ContactPerson>
      </HolderOfTheTransitProcedure>
      <!--Optional:-->
      <Representative>
        <identificationNumber>string</identificationNumber>
        <status>token</status>
        <!--Optional:-->
        <ContactPerson>
          <name>string</name>
          <phoneNumber>string</phoneNumber>
          <!--Optional:-->
          <eMailAddress>string</eMailAddress>
        </ContactPerson>
      </Representative>
      <!--Optional:-->
      <ControlResult>
        <code>token</code>
        <date>2002-06-24+01:00</date>
        <controlledBy>string</controlledBy>
        <!--Optional:-->
        <text>string</text>
      </ControlResult>
      <!--1 to 9 repetitions:-->
      <Guarantee>
        <sequenceNumber>token</sequenceNumber>
        <guaranteeType>s</guaranteeType>
        <!--Optional:-->
        <otherGuaranteeReference>string</otherGuaranteeReference>
        <!--0 to 99 repetitions:-->
        <GuaranteeReference>
          <sequenceNumber>token</sequenceNumber>
          <!--Optional:-->
          <GRN>string</GRN>
          <!--Optional:-->
          <accessCode>stri</accessCode>
          <amountToBeCovered>1000.000000000000</amountToBeCovered>
          <currency>token</currency>
        </GuaranteeReference>
      </Guarantee>
      <Consignment>
        <!--Optional:-->
        <countryOfDispatch>st</countryOfDispatch>
        <!--Optional:-->
        <countryOfDestination>token</countryOfDestination>
        <containerIndicator>0</containerIndicator>
        <!--Optional:-->
        <inlandModeOfTransport>token</inlandModeOfTransport>
        <!--Optional:-->
        <modeOfTransportAtTheBorder>token</modeOfTransportAtTheBorder>
        <grossMass>1000.000000000000</grossMass>
        <!--Optional:-->
        <referenceNumberUCR>string</referenceNumberUCR>
        <!--Optional:-->
        <Carrier>
          <identificationNumber>string</identificationNumber>
          <!--Optional:-->
          <ContactPerson>
            <name>string</name>
            <phoneNumber>string</phoneNumber>
            <!--Optional:-->
            <eMailAddress>string</eMailAddress>
          </ContactPerson>
        </Carrier>
        <!--Optional:-->
        <Consignor>
          <!--Optional:-->
          <identificationNumber>string</identificationNumber>
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
          <!--Optional:-->
          <ContactPerson>
            <name>string</name>
            <phoneNumber>string</phoneNumber>
            <!--Optional:-->
            <eMailAddress>string</eMailAddress>
          </ContactPerson>
        </Consignor>
        <!--Optional:-->
        <Consignee>
          <!--Optional:-->
          <identificationNumber>string</identificationNumber>
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
        </Consignee>
        <!--0 to 99 repetitions:-->
        <AdditionalSupplyChainActor>
          <sequenceNumber>token</sequenceNumber>
          <role>token</role>
          <identificationNumber>string</identificationNumber>
        </AdditionalSupplyChainActor>
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
        <!--Optional:-->
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
            <phoneNumber>string</phoneNumber>
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
        <!--0 to 99 repetitions:-->
        <CountryOfRoutingOfConsignment>
          <sequenceNumber>token</sequenceNumber>
          <country>st</country>
        </CountryOfRoutingOfConsignment>
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
        <PlaceOfLoading>
          <!--Optional:-->
          <UNLocode>token</UNLocode>
          <!--Optional:-->
          <country>st</country>
          <!--Optional:-->
          <location>string</location>
        </PlaceOfLoading>
        <!--Optional:-->
        <PlaceOfUnloading>
          <!--Optional:-->
          <UNLocode>token</UNLocode>
          <!--Optional:-->
          <country>st</country>
          <!--Optional:-->
          <location>string</location>
        </PlaceOfUnloading>
        <!--0 to 9999 repetitions:-->
        <PreviousDocument>
          <sequenceNumber>token</sequenceNumber>
          <type>token</type>
          <referenceNumber>string</referenceNumber>
          <!--Optional:-->
          <complementOfInformation>string</complementOfInformation>
        </PreviousDocument>
        <!--0 to 99 repetitions:-->
        <SupportingDocument>
          <sequenceNumber>token</sequenceNumber>
          <type>token</type>
          <referenceNumber>string</referenceNumber>
          <!--Optional:-->
          <documentLineItemNumber>100</documentLineItemNumber>
          <!--Optional:-->
          <complementOfInformation>string</complementOfInformation>
        </SupportingDocument>
        <!--0 to 99 repetitions:-->
        <TransportDocument>
          <sequenceNumber>token</sequenceNumber>
          <type>token</type>
          <referenceNumber>string</referenceNumber>
        </TransportDocument>
        <!--0 to 99 repetitions:-->
        <AdditionalReference>
          <sequenceNumber>token</sequenceNumber>
          <type>token</type>
          <!--Optional:-->
          <referenceNumber>string</referenceNumber>
        </AdditionalReference>
        <!--0 to 99 repetitions:-->
        <AdditionalInformation>
          <sequenceNumber>token</sequenceNumber>
          <code>token</code>
          <!--Optional:-->
          <text>string</text>
        </AdditionalInformation>
        <!--Optional:-->
        <TransportCharges>
          <methodOfPayment>s</methodOfPayment>
        </TransportCharges>
        <!--1 to 99 repetitions:-->
        <HouseConsignment>
          <sequenceNumber>token</sequenceNumber>
          <!--Optional:-->
          <countryOfDispatch>st</countryOfDispatch>
          <grossMass>1000.000000000000</grossMass>
          <!--Optional:-->
          <referenceNumberUCR>string</referenceNumberUCR>
          <!--Optional:-->
          <securityIndicatorFromExportDeclaration>token</securityIndicatorFromExportDeclaration>
          <!--Optional:-->
          <Consignor>
            <!--Optional:-->
            <identificationNumber>string</identificationNumber>
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
            <!--Optional:-->
            <ContactPerson>
              <name>string</name>
              <phoneNumber>string</phoneNumber>
              <!--Optional:-->
              <eMailAddress>string</eMailAddress>
            </ContactPerson>
          </Consignor>
          <!--Optional:-->
          <Consignee>
            <!--Optional:-->
            <identificationNumber>string</identificationNumber>
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
          </Consignee>
          <!--0 to 99 repetitions:-->
          <AdditionalSupplyChainActor>
            <sequenceNumber>token</sequenceNumber>
            <role>token</role>
            <identificationNumber>string</identificationNumber>
          </AdditionalSupplyChainActor>
          <!--0 to 999 repetitions:-->
          <DepartureTransportMeans>
            <sequenceNumber>token</sequenceNumber>
            <typeOfIdentification>token</typeOfIdentification>
            <identificationNumber>string</identificationNumber>
            <nationality>st</nationality>
          </DepartureTransportMeans>
          <!--0 to 99 repetitions:-->
          <PreviousDocument>
            <sequenceNumber>token</sequenceNumber>
            <type>token</type>
            <referenceNumber>string</referenceNumber>
            <!--Optional:-->
            <complementOfInformation>string</complementOfInformation>
          </PreviousDocument>
          <!--0 to 99 repetitions:-->
          <SupportingDocument>
            <sequenceNumber>token</sequenceNumber>
            <type>token</type>
            <referenceNumber>string</referenceNumber>
            <!--Optional:-->
            <documentLineItemNumber>100</documentLineItemNumber>
            <!--Optional:-->
            <complementOfInformation>string</complementOfInformation>
          </SupportingDocument>
          <!--0 to 99 repetitions:-->
          <TransportDocument>
            <sequenceNumber>token</sequenceNumber>
            <type>token</type>
            <referenceNumber>string</referenceNumber>
          </TransportDocument>
          <!--0 to 99 repetitions:-->
          <AdditionalReference>
            <sequenceNumber>token</sequenceNumber>
            <type>token</type>
            <!--Optional:-->
            <referenceNumber>string</referenceNumber>
          </AdditionalReference>
          <!--0 to 99 repetitions:-->
          <AdditionalInformation>
            <sequenceNumber>token</sequenceNumber>
            <code>token</code>
            <!--Optional:-->
            <text>string</text>
          </AdditionalInformation>
          <!--Optional:-->
          <TransportCharges>
            <methodOfPayment>s</methodOfPayment>
          </TransportCharges>
          <!--1 to 999 repetitions:-->
          <ConsignmentItem>
            <goodsItemNumber>token</goodsItemNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
            <!--Optional:-->
            <declarationType>token</declarationType>
            <!--Optional:-->
            <countryOfDispatch>st</countryOfDispatch>
            <!--Optional:-->
            <countryOfDestination>token</countryOfDestination>
            <!--Optional:-->
            <referenceNumberUCR>string</referenceNumberUCR>
            <!--Optional:-->
            <Consignee>
              <!--Optional:-->
              <identificationNumber>string</identificationNumber>
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
            </Consignee>
            <!--0 to 99 repetitions:-->
            <AdditionalSupplyChainActor>
              <sequenceNumber>token</sequenceNumber>
              <role>token</role>
              <identificationNumber>string</identificationNumber>
            </AdditionalSupplyChainActor>
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
                <sequenceNumber>token</sequenceNumber>
                <UNNumber>token</UNNumber>
              </DangerousGoods>
              <GoodsMeasure>
                <grossMass>1000.000000000000</grossMass>
                <!--Optional:-->
                <netMass>1000.000000000000</netMass>
              </GoodsMeasure>
            </Commodity>
            <!--1 to 99 repetitions:-->
            <Packaging>
              <sequenceNumber>token</sequenceNumber>
              <typeOfPackages>token</typeOfPackages>
              <!--Optional:-->
              <numberOfPackages>100</numberOfPackages>
              <!--Optional:-->
              <shippingMarks>string</shippingMarks>
            </Packaging>
            <!--0 to 99 repetitions:-->
            <PreviousDocument>
              <sequenceNumber>token</sequenceNumber>
              <type>token</type>
              <referenceNumber>string</referenceNumber>
              <!--Optional:-->
              <goodsItemNumber>100</goodsItemNumber>
              <!--Optional:-->
              <typeOfPackages>token</typeOfPackages>
              <!--Optional:-->
              <numberOfPackages>100</numberOfPackages>
              <!--Optional:-->
              <measurementUnitAndQualifier>token</measurementUnitAndQualifier>
              <!--Optional:-->
              <quantity>1000.000000000000</quantity>
              <!--Optional:-->
              <complementOfInformation>string</complementOfInformation>
            </PreviousDocument>
            <!--0 to 99 repetitions:-->
            <SupportingDocument>
              <sequenceNumber>token</sequenceNumber>
              <type>token</type>
              <referenceNumber>string</referenceNumber>
              <!--Optional:-->
              <documentLineItemNumber>100</documentLineItemNumber>
              <!--Optional:-->
              <complementOfInformation>string</complementOfInformation>
            </SupportingDocument>
            <!--0 to 99 repetitions:-->
            <TransportDocument>
              <sequenceNumber>token</sequenceNumber>
              <type>token</type>
              <referenceNumber>string</referenceNumber>
            </TransportDocument>
            <!--0 to 99 repetitions:-->
            <AdditionalReference>
              <sequenceNumber>token</sequenceNumber>
              <type>token</type>
              <!--Optional:-->
              <referenceNumber>string</referenceNumber>
            </AdditionalReference>
            <!--0 to 99 repetitions:-->
            <AdditionalInformation>
              <sequenceNumber>token</sequenceNumber>
              <code>token</code>
              <!--Optional:-->
              <text>string</text>
            </AdditionalInformation>
            <!--Optional:-->
            <TransportCharges>
              <methodOfPayment>s</methodOfPayment>
            </TransportCharges>
          </ConsignmentItem>
        </HouseConsignment>
      </Consignment>
    </ncts:CC029C>

  lazy val json1 = Json.parse(
    """
      |{
      |    "n1:CC029C": {
      |        "preparationDateAndTime": "2007-10-26T07:36:28",
      |        "TransitOperation": {
      |            "LRN": "string",
      |            "MRN": "string",
      |            "declarationType": "token",
      |            "additionalDeclarationType": "token",
      |            "TIRCarnetNumber": "string",
      |            "declarationAcceptanceDate": "2014-06-09+01:00",
      |            "releaseDate": "2008-11-15",
      |            "security": "token",
      |            "reducedDatasetIndicator": "0",
      |            "specificCircumstanceIndicator": "token",
      |            "communicationLanguageAtDeparture": "st",
      |            "bindingItinerary": "1"
      |        },
      |        "CustomsOfficeOfDeparture": {
      |            "referenceNumber": "stringst"
      |        },
      |        "messageSender": "token",
      |        "Consignment": {
      |            "countryOfDispatch": "st",
      |            "countryOfDestination": "token",
      |            "containerIndicator": "0",
      |            "inlandModeOfTransport": "token",
      |            "modeOfTransportAtTheBorder": "token",
      |            "grossMass": 1000,
      |            "referenceNumberUCR": "string",
      |            "Carrier": {
      |                "identificationNumber": "string",
      |                "ContactPerson": {
      |                    "name": "string",
      |                    "phoneNumber": "string",
      |                    "eMailAddress": "string"
      |                }
      |            },
      |            "Consignor": {
      |                "identificationNumber": "string",
      |                "name": "string",
      |                "Address": {
      |                    "streetAndNumber": "string",
      |                    "postcode": "string",
      |                    "city": "string",
      |                    "country": "st"
      |                },
      |                "ContactPerson": {
      |                    "name": "string",
      |                    "phoneNumber": "string",
      |                    "eMailAddress": "string"
      |                }
      |            },
      |            "Consignee": {
      |                "identificationNumber": "string",
      |                "name": "string",
      |                "Address": {
      |                    "streetAndNumber": "string",
      |                    "postcode": "string",
      |                    "city": "string",
      |                    "country": "st"
      |                }
      |            },
      |            "AdditionalSupplyChainActor": [
      |                {
      |                    "sequenceNumber": "token",
      |                    "role": "token",
      |                    "identificationNumber": "string"
      |                }
      |            ],
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
      |                    "phoneNumber": "string",
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
      |            "CountryOfRoutingOfConsignment": [
      |                {
      |                    "sequenceNumber": "token",
      |                    "country": "st"
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
      |            "PlaceOfUnloading": {
      |                "UNLocode": "token",
      |                "country": "st",
      |                "location": "string"
      |            },
      |            "PreviousDocument": [
      |                {
      |                    "sequenceNumber": "token",
      |                    "typeValue": "token",
      |                    "referenceNumber": "string",
      |                    "complementOfInformation": "string"
      |                }
      |            ],
      |            "SupportingDocument": [
      |                {
      |                    "sequenceNumber": "token",
      |                    "typeValue": "token",
      |                    "referenceNumber": "string",
      |                    "documentLineItemNumber": 100,
      |                    "complementOfInformation": "string"
      |                }
      |            ],
      |            "TransportDocument": [
      |                {
      |                    "sequenceNumber": "token",
      |                    "typeValue": "token",
      |                    "referenceNumber": "string"
      |                }
      |            ],
      |            "AdditionalReference": [
      |                {
      |                    "sequenceNumber": "token",
      |                    "typeValue": "token",
      |                    "referenceNumber": "string"
      |                }
      |            ],
      |            "AdditionalInformation": [
      |                {
      |                    "sequenceNumber": "token",
      |                    "code": "token",
      |                    "text": "string"
      |                }
      |            ],
      |            "TransportCharges": {
      |                "methodOfPayment": "s"
      |            },
      |            "HouseConsignment": [
      |                {
      |                    "sequenceNumber": "token",
      |                    "countryOfDispatch": "st",
      |                    "grossMass": 1000,
      |                    "referenceNumberUCR": "string",
      |                    "securityIndicatorFromExportDeclaration": "token",
      |                    "Consignor": {
      |                        "identificationNumber": "string",
      |                        "name": "string",
      |                        "Address": {
      |                            "streetAndNumber": "string",
      |                            "postcode": "string",
      |                            "city": "string",
      |                            "country": "st"
      |                        },
      |                        "ContactPerson": {
      |                            "name": "string",
      |                            "phoneNumber": "string",
      |                            "eMailAddress": "string"
      |                        }
      |                    },
      |                    "Consignee": {
      |                        "identificationNumber": "string",
      |                        "name": "string",
      |                        "Address": {
      |                            "streetAndNumber": "string",
      |                            "postcode": "string",
      |                            "city": "string",
      |                            "country": "st"
      |                        }
      |                    },
      |                    "AdditionalSupplyChainActor": [
      |                        {
      |                            "sequenceNumber": "token",
      |                            "role": "token",
      |                            "identificationNumber": "string"
      |                        }
      |                    ],
      |                    "DepartureTransportMeans": [
      |                        {
      |                            "sequenceNumber": "token",
      |                            "typeOfIdentification": "token",
      |                            "identificationNumber": "string",
      |                            "nationality": "st"
      |                        }
      |                    ],
      |                    "PreviousDocument": [
      |                        {
      |                            "sequenceNumber": "token",
      |                            "typeValue": "token",
      |                            "referenceNumber": "string",
      |                            "complementOfInformation": "string"
      |                        }
      |                    ],
      |                    "SupportingDocument": [
      |                        {
      |                            "sequenceNumber": "token",
      |                            "typeValue": "token",
      |                            "referenceNumber": "string",
      |                            "documentLineItemNumber": 100,
      |                            "complementOfInformation": "string"
      |                        }
      |                    ],
      |                    "TransportDocument": [
      |                        {
      |                            "sequenceNumber": "token",
      |                            "typeValue": "token",
      |                            "referenceNumber": "string"
      |                        }
      |                    ],
      |                    "AdditionalReference": [
      |                        {
      |                            "sequenceNumber": "token",
      |                            "typeValue": "token",
      |                            "referenceNumber": "string"
      |                        }
      |                    ],
      |                    "AdditionalInformation": [
      |                        {
      |                            "sequenceNumber": "token",
      |                            "code": "token",
      |                            "text": "string"
      |                        }
      |                    ],
      |                    "TransportCharges": {
      |                        "methodOfPayment": "s"
      |                    },
      |                    "ConsignmentItem": [
      |                        {
      |                            "goodsItemNumber": "token",
      |                            "declarationGoodsItemNumber": 100,
      |                            "declarationType": "token",
      |                            "countryOfDispatch": "st",
      |                            "countryOfDestination": "token",
      |                            "referenceNumberUCR": "string",
      |                            "Consignee": {
      |                                "identificationNumber": "string",
      |                                "name": "string",
      |                                "Address": {
      |                                    "streetAndNumber": "string",
      |                                    "postcode": "string",
      |                                    "city": "string",
      |                                    "country": "st"
      |                                }
      |                            },
      |                            "AdditionalSupplyChainActor": [
      |                                {
      |                                    "sequenceNumber": "token",
      |                                    "role": "token",
      |                                    "identificationNumber": "string"
      |                                }
      |                            ],
      |                            "Commodity": {
      |                                "descriptionOfGoods": "string",
      |                                "cusCode": "token",
      |                                "CommodityCode": {
      |                                    "harmonizedSystemSubHeadingCode": "token",
      |                                    "combinedNomenclatureCode": "st"
      |                                },
      |                                "DangerousGoods": [
      |                                    {
      |                                        "sequenceNumber": "token",
      |                                        "UNNumber": "token"
      |                                    }
      |                                ],
      |                                "GoodsMeasure": {
      |                                    "grossMass": 1000,
      |                                    "netMass": 1000
      |                                }
      |                            },
      |                            "Packaging": [
      |                                {
      |                                    "sequenceNumber": "token",
      |                                    "typeOfPackages": "token",
      |                                    "numberOfPackages": 100,
      |                                    "shippingMarks": "string"
      |                                }
      |                            ],
      |                            "PreviousDocument": [
      |                                {
      |                                    "sequenceNumber": "token",
      |                                    "typeValue": "token",
      |                                    "referenceNumber": "string",
      |                                    "goodsItemNumber": 100,
      |                                    "typeOfPackages": "token",
      |                                    "numberOfPackages": 100,
      |                                    "measurementUnitAndQualifier": "token",
      |                                    "quantity": 1000,
      |                                    "complementOfInformation": "string"
      |                                }
      |                            ],
      |                            "SupportingDocument": [
      |                                {
      |                                    "sequenceNumber": "token",
      |                                    "typeValue": "token",
      |                                    "referenceNumber": "string",
      |                                    "documentLineItemNumber": 100,
      |                                    "complementOfInformation": "string"
      |                                }
      |                            ],
      |                            "TransportDocument": [
      |                                {
      |                                    "sequenceNumber": "token",
      |                                    "typeValue": "token",
      |                                    "referenceNumber": "string"
      |                                }
      |                            ],
      |                            "AdditionalReference": [
      |                                {
      |                                    "sequenceNumber": "token",
      |                                    "typeValue": "token",
      |                                    "referenceNumber": "string"
      |                                }
      |                            ],
      |                            "AdditionalInformation": [
      |                                {
      |                                    "sequenceNumber": "token",
      |                                    "code": "token",
      |                                    "text": "string"
      |                                }
      |                            ],
      |                            "TransportCharges": {
      |                                "methodOfPayment": "s"
      |                            }
      |                        }
      |                    ]
      |                }
      |            ]
      |        },
      |        "CustomsOfficeOfExitForTransitDeclared": [
      |            {
      |                "sequenceNumber": "token",
      |                "referenceNumber": "stringst"
      |            }
      |        ],
      |        "CustomsOfficeOfTransitDeclared": [
      |            {
      |                "sequenceNumber": "token",
      |                "referenceNumber": "stringst",
      |                "arrivalDateAndTimeEstimated": "2002-11-05T08:01:03Z"
      |            }
      |        ],
      |        "CustomsOfficeOfDestinationDeclared": {
      |            "referenceNumber": "stringst"
      |        },
      |        "messageType": "CC029C",
      |        "@PhaseID": "NCTS5.0",
      |        "correlationIdentifier": "token",
      |        "Authorisation": [
      |            {
      |                "sequenceNumber": "token",
      |                "typeValue": "token",
      |                "referenceNumber": "string"
      |            }
      |        ],
      |        "ControlResult": {
      |            "code": "token",
      |            "date": "2002-06-24+01:00",
      |            "controlledBy": "string",
      |            "text": "string"
      |        },
      |        "messageRecipient": "token",
      |        "HolderOfTheTransitProcedure": {
      |            "identificationNumber": "string",
      |            "TIRHolderIdentificationNumber": "string",
      |            "name": "string",
      |            "Address": {
      |                "streetAndNumber": "string",
      |                "postcode": "string",
      |                "city": "string",
      |                "country": "st"
      |            },
      |            "ContactPerson": {
      |                "name": "string",
      |                "phoneNumber": "string",
      |                "eMailAddress": "string"
      |            }
      |        },
      |        "Representative": {
      |            "identificationNumber": "string",
      |            "status": "token",
      |            "ContactPerson": {
      |                "name": "string",
      |                "phoneNumber": "string",
      |                "eMailAddress": "string"
      |            }
      |        },
      |        "messageIdentification": "token",
      |        "Guarantee": [
      |            {
      |                "sequenceNumber": "token",
      |                "guaranteeType": "s",
      |                "otherGuaranteeReference": "string",
      |                "GuaranteeReference": [
      |                    {
      |                        "sequenceNumber": "token",
      |                        "GRN": "string",
      |                        "accessCode": "stri",
      |                        "amountToBeCovered": 1000,
      |                        "currency": "token"
      |                    }
      |                ]
      |            }
      |        ]
      |    }
      |}
      |""".stripMargin
  )
}

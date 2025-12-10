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

package uk.gov.hmrc.transitmovementsconverter.v3_0.models.testobjects

import play.api.libs.json.JsValue
import play.api.libs.json.Json

import scala.xml.NodeSeq

object CC029CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

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
        <sequenceNumber>100</sequenceNumber>
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
        <sequenceNumber>100</sequenceNumber>
        <referenceNumber>stringst</referenceNumber>
        <!--Optional:-->
        <arrivalDateAndTimeEstimated>2002-11-05T08:01:03+00:00</arrivalDateAndTimeEstimated>
      </CustomsOfficeOfTransitDeclared>
      <!--0 to 9 repetitions:-->
      <CustomsOfficeOfExitForTransitDeclared>
        <sequenceNumber>100</sequenceNumber>        <referenceNumber>stringst</referenceNumber>
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
        <sequenceNumber>100</sequenceNumber>
       <guaranteeType>s</guaranteeType>
        <!--Optional:-->
        <otherGuaranteeReference>string</otherGuaranteeReference>
        <!--0 to 99 repetitions:-->
        <GuaranteeReference>
          <sequenceNumber>100</sequenceNumber>         <!--Optional:-->
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
          <sequenceNumber>100</sequenceNumber>
         <role>token</role>
          <identificationNumber>string</identificationNumber>
        </AdditionalSupplyChainActor>
        <!--0 to 9999 repetitions:-->
        <TransportEquipment>
          <sequenceNumber>100</sequenceNumber>        <!--Optional:-->
          <containerIdentificationNumber>string</containerIdentificationNumber>
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
          <sequenceNumber>100</sequenceNumber>
         <typeOfIdentification>token</typeOfIdentification>
          <identificationNumber>string</identificationNumber>
          <nationality>st</nationality>
        </DepartureTransportMeans>
        <!--0 to 99 repetitions:-->
        <CountryOfRoutingOfConsignment>
          <sequenceNumber>100</sequenceNumber>         <country>st</country>
        </CountryOfRoutingOfConsignment>
        <!--0 to 9 repetitions:-->
        <ActiveBorderTransportMeans>
          <sequenceNumber>100</sequenceNumber>
          <customsOfficeAtBorderReferenceNumber>token</customsOfficeAtBorderReferenceNumber>
          <typeOfIdentification>token</typeOfIdentification>
          <identificationNumber>string</identificationNumber>
          <nationality>st</nationality>
          <!--Optional:-->
          <conveyanceReferenceNumber>string</conveyanceReferenceNumber>
        </ActiveBorderTransportMeans>
        <PlaceOfLoading>

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
          <sequenceNumber>100</sequenceNumber>       <type>token</type>
          <referenceNumber>string</referenceNumber>
          <!--Optional:-->
          <complementOfInformation>string</complementOfInformation>
        </PreviousDocument>
        <!--0 to 99 repetitions:-->
        <SupportingDocument>
          <sequenceNumber>100</sequenceNumber>
          <type>token</type>
          <referenceNumber>string</referenceNumber>
          <!--Optional:-->
          <documentLineItemNumber>100</documentLineItemNumber>
          <!--Optional:-->
          <complementOfInformation>string</complementOfInformation>
        </SupportingDocument>
        <!--0 to 99 repetitions:-->
        <TransportDocument>
          <sequenceNumber>100</sequenceNumber>          <type>token</type>
          <referenceNumber>string</referenceNumber>
        </TransportDocument>
        <!--0 to 99 repetitions:-->
        <AdditionalReference>
          <sequenceNumber>100</sequenceNumber>
        <type>token</type>
          <!--Optional:-->
          <referenceNumber>string</referenceNumber>
        </AdditionalReference>
        <!--0 to 99 repetitions:-->
        <AdditionalInformation>
          <sequenceNumber>100</sequenceNumber>         <code>token</code>
          <!--Optional:-->
          <text>string</text>
        </AdditionalInformation>
        <!--Optional:-->
        <TransportCharges>
          <methodOfPayment>s</methodOfPayment>
        </TransportCharges>
        <!--1 to 99 repetitions:-->
        <HouseConsignment>
          <sequenceNumber>100</sequenceNumber>
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
            <sequenceNumber>100</sequenceNumber>          <role>token</role>
            <identificationNumber>string</identificationNumber>
          </AdditionalSupplyChainActor>
          <!--0 to 999 repetitions:-->
          <DepartureTransportMeans>
            <sequenceNumber>100</sequenceNumber>
            <typeOfIdentification>token</typeOfIdentification>
            <identificationNumber>string</identificationNumber>
            <nationality>st</nationality>
          </DepartureTransportMeans>
          <!--0 to 99 repetitions:-->
          <PreviousDocument>
            <sequenceNumber>100</sequenceNumber>          <type>token</type>
            <referenceNumber>string</referenceNumber>
            <!--Optional:-->
            <complementOfInformation>string</complementOfInformation>
          </PreviousDocument>
          <!--0 to 99 repetitions:-->
          <SupportingDocument>
            <sequenceNumber>100</sequenceNumber>
           <type>token</type>
            <referenceNumber>string</referenceNumber>
            <!--Optional:-->
            <documentLineItemNumber>100</documentLineItemNumber>
            <!--Optional:-->
            <complementOfInformation>string</complementOfInformation>
          </SupportingDocument>
          <!--0 to 99 repetitions:-->
          <TransportDocument>
            <sequenceNumber>100</sequenceNumber>            <type>token</type>
            <referenceNumber>string</referenceNumber>
          </TransportDocument>
          <!--0 to 99 repetitions:-->
          <AdditionalReference>
            <sequenceNumber>100</sequenceNumber>
            <type>token</type>
            <!--Optional:-->
            <referenceNumber>string</referenceNumber>
          </AdditionalReference>
          <!--0 to 99 repetitions:-->
          <AdditionalInformation>
            <sequenceNumber>100</sequenceNumber>         <code>token</code>
            <!--Optional:-->
            <text>string</text>
          </AdditionalInformation>
          <!--Optional:-->
          <TransportCharges>
            <methodOfPayment>s</methodOfPayment>
          </TransportCharges>
          <!--1 to 999 repetitions:-->
          <ConsignmentItem>
            <goodsItemNumber>100</goodsItemNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
            <!--Optional:-->
            <declarationType>token</declarationType>
            <!--Optional:-->
            <countryOfDispatch>st</countryOfDispatch>
            <!--Optional:-->
            <countryOfDestination>token</countryOfDestination>\
            <!--Optional:-->
            <referenceNumberUCR>string</referenceNumberUCR>
            <!--0 to 99 repetitions:-->
            <AdditionalSupplyChainActor>
              <sequenceNumber>100</sequenceNumber>
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
                <sequenceNumber>100</sequenceNumber>                <UNNumber>token</UNNumber>
              </DangerousGoods>
              <GoodsMeasure>
                <grossMass>10.0</grossMass>
              </GoodsMeasure>
            </Commodity>
            <!--1 to 99 repetitions:-->
            <Packaging>
              <sequenceNumber>100</sequenceNumber>
             <typeOfPackages>token</typeOfPackages>
              <!--Optional:-->
              <numberOfPackages>100</numberOfPackages>
              <!--Optional:-->
              <shippingMarks>string</shippingMarks>
            </Packaging>
            <!--0 to 99 repetitions:-->
            <PreviousDocument>
              <sequenceNumber>100</sequenceNumber>             <type>token</type>
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
              <sequenceNumber>100</sequenceNumber>
              <type>token</type>
              <referenceNumber>string</referenceNumber>
              <!--Optional:-->
              <documentLineItemNumber>100</documentLineItemNumber>
              <!--Optional:-->
              <complementOfInformation>string</complementOfInformation>
            </SupportingDocument>
            <!--0 to 99 repetitions:-->
            <AdditionalReference>
              <sequenceNumber>100</sequenceNumber>
              <type>token</type>
              <!--Optional:-->
              <referenceNumber>string</referenceNumber>
            </AdditionalReference>
            <!--0 to 99 repetitions:-->
            <AdditionalInformation>
              <sequenceNumber>100</sequenceNumber>             <code>token</code>
              <!--Optional:-->
              <text>string</text>
            </AdditionalInformation>
          </ConsignmentItem>
        </HouseConsignment>
      </Consignment>
    </ncts:CC029C>

  lazy val json1 = Json.parse(
    """{
      |    "n1:CC029C":
      |    {
      |        "messageSender": "token",
      |        "messageRecipient": "token",
      |        "preparationDateAndTime": "2007-10-26T07:36:28",
      |        "messageIdentification": "token",
      |        "messageType": "CC029C",
      |        "correlationIdentifier": "token",
      |        "TransitOperation":
      |        {
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
      |        "Authorisation":
      |        [
      |            {
      |                "sequenceNumber": 100,
      |                "referenceNumber": "string",
      |                "type": "token"
      |            }
      |        ],
      |        "CustomsOfficeOfDeparture":
      |        {
      |            "referenceNumber": "stringst"
      |        },
      |        "CustomsOfficeOfDestinationDeclared":
      |        {
      |            "referenceNumber": "stringst"
      |        },
      |        "CustomsOfficeOfTransitDeclared":
      |        [
      |            {
      |                "sequenceNumber": 100,
      |                "referenceNumber": "stringst",
      |                "arrivalDateAndTimeEstimated": "2002-11-05T08:01:03Z"
      |            }
      |        ],
      |        "CustomsOfficeOfExitForTransitDeclared":
      |        [
      |            {
      |                "sequenceNumber": 100,
      |                "referenceNumber": "stringst"
      |            }
      |        ],
      |        "HolderOfTheTransitProcedure":
      |        {
      |            "identificationNumber": "string",
      |            "TIRHolderIdentificationNumber": "string",
      |            "name": "string",
      |            "Address":
      |            {
      |                "streetAndNumber": "string",
      |                "postcode": "string",
      |                "city": "string",
      |                "country": "st"
      |            },
      |            "ContactPerson":
      |            {
      |                "name": "string",
      |                "phoneNumber": "string",
      |                "eMailAddress": "string"
      |            }
      |        },
      |        "Representative":
      |        {
      |            "identificationNumber": "string",
      |            "status": "token",
      |            "ContactPerson":
      |            {
      |                "name": "string",
      |                "phoneNumber": "string",
      |                "eMailAddress": "string"
      |            }
      |        },
      |        "ControlResult":
      |        {
      |            "code": "token",
      |            "date": "2002-06-24+01:00",
      |            "controlledBy": "string",
      |            "text": "string"
      |        },
      |        "Guarantee":
      |        [
      |            {
      |                "sequenceNumber": 100,
      |                "guaranteeType": "s",
      |                "otherGuaranteeReference": "string",
      |                "GuaranteeReference":
      |                [
      |                    {
      |                        "sequenceNumber": 100,
      |                        "GRN": "string",
      |                        "accessCode": "stri",
      |                        "amountToBeCovered": 1000,
      |                        "currency": "token"
      |                    }
      |                ]
      |            }
      |        ],
      |        "Consignment":
      |        {
      |            "countryOfDispatch": "st",
      |            "countryOfDestination": "token",
      |            "containerIndicator": "0",
      |            "inlandModeOfTransport": "token",
      |            "modeOfTransportAtTheBorder": "token",
      |            "grossMass": 1000,
      |            "referenceNumberUCR": "string",
      |            "Carrier":
      |            {
      |                "identificationNumber": "string",
      |                "ContactPerson":
      |                {
      |                    "name": "string",
      |                    "phoneNumber": "string",
      |                    "eMailAddress": "string"
      |                }
      |            },
      |            "Consignor":
      |            {
      |                "identificationNumber": "string",
      |                "name": "string",
      |                "Address":
      |                {
      |                    "streetAndNumber": "string",
      |                    "postcode": "string",
      |                    "city": "string",
      |                    "country": "st"
      |                },
      |                "ContactPerson":
      |                {
      |                    "name": "string",
      |                    "phoneNumber": "string",
      |                    "eMailAddress": "string"
      |                }
      |            },
      |            "Consignee":
      |            {
      |                "identificationNumber": "string",
      |                "name": "string",
      |                "Address":
      |                {
      |                    "streetAndNumber": "string",
      |                    "postcode": "string",
      |                    "city": "string",
      |                    "country": "st"
      |                }
      |            },
      |            "AdditionalSupplyChainActor":
      |            [
      |                {
      |                    "sequenceNumber": 100,
      |                    "role": "token",
      |                    "identificationNumber": "string"
      |                }
      |            ],
      |            "TransportEquipment":
      |            [
      |                {
      |                    "sequenceNumber": 100,
      |                    "containerIdentificationNumber": "string",
      |                    "numberOfSeals": 100,
      |                    "Seal":
      |                    [
      |                        {
      |                            "sequenceNumber": 100,
      |                            "identifier": "string"
      |                        }
      |                    ],
      |                    "GoodsReference":
      |                    [
      |                        {
      |                            "sequenceNumber": 100,
      |                            "declarationGoodsItemNumber": 100
      |                        }
      |                    ]
      |                }
      |            ],
      |            "LocationOfGoods":
      |            {
      |                "typeOfLocation": "token",
      |                "qualifierOfIdentification": "token",
      |                "authorisationNumber": "string",
      |                "additionalIdentifier": "stri",
      |                "UNLocode": "token",
      |                "CustomsOffice":
      |                {
      |                    "referenceNumber": "stringst"
      |                },
      |                "GNSS":
      |                {
      |                    "latitude": "string",
      |                    "longitude": "string"
      |                },
      |                "EconomicOperator":
      |                {
      |                    "identificationNumber": "string"
      |                },
      |                "Address":
      |                {
      |                    "streetAndNumber": "string",
      |                    "postcode": "string",
      |                    "city": "string",
      |                    "country": "st"
      |                },
      |                "PostcodeAddress":
      |                {
      |                    "houseNumber": "string",
      |                    "postcode": "string",
      |                    "country": "st"
      |                },
      |                "ContactPerson":
      |                {
      |                    "name": "string",
      |                    "phoneNumber": "string",
      |                    "eMailAddress": "string"
      |                }
      |            },
      |            "DepartureTransportMeans":
      |            [
      |                {
      |                    "sequenceNumber": 100,
      |                    "typeOfIdentification": "token",
      |                    "identificationNumber": "string",
      |                    "nationality": "st"
      |                }
      |            ],
      |            "CountryOfRoutingOfConsignment":
      |            [
      |                {
      |                    "sequenceNumber": 100,
      |                    "country": "st"
      |                }
      |            ],
      |            "ActiveBorderTransportMeans":
      |            [
      |                {
      |                    "sequenceNumber": 100,
      |                    "customsOfficeAtBorderReferenceNumber": "token",
      |                    "typeOfIdentification": "token",
      |                    "identificationNumber": "string",
      |                    "nationality": "st",
      |                    "conveyanceReferenceNumber": "string"
      |                }
      |            ],
      |            "PlaceOfLoading":
      |            {},
      |            "PlaceOfUnloading":
      |            {
      |                "UNLocode": "token",
      |                "country": "st",
      |                "location": "string"
      |            },
      |            "PreviousDocument":
      |            [
      |                {
      |                    "sequenceNumber": 100,
      |                    "referenceNumber": "string",
      |                    "complementOfInformation": "string",
      |                    "type": "token"
      |                }
      |            ],
      |            "SupportingDocument":
      |            [
      |                {
      |                    "sequenceNumber": 100,
      |                    "typeValue": "token",
      |                    "referenceNumber": "string",
      |                    "documentLineItemNumber": 100,
      |                    "complementOfInformation": "string"
      |                }
      |            ],
      |            "TransportDocument":
      |            [
      |                {
      |                    "sequenceNumber": 100,
      |                    "referenceNumber": "string",
      |                    "type": "token"
      |                }
      |            ],
      |            "AdditionalReference":
      |            [
      |                {
      |                    "sequenceNumber": 100,
      |                    "referenceNumber": "string",
      |                    "type": "token"
      |                }
      |            ],
      |            "AdditionalInformation":
      |            [
      |                {
      |                    "sequenceNumber": 100,
      |                    "code": "token",
      |                    "text": "string"
      |                }
      |            ],
      |            "TransportCharges":
      |            {
      |                "methodOfPayment": "s"
      |            },
      |            "HouseConsignment":
      |            [
      |                {
      |                    "sequenceNumber": 100,
      |                    "countryOfDispatch": "st",
      |                    "grossMass": 1000,
      |                    "referenceNumberUCR": "string",
      |                    "securityIndicatorFromExportDeclaration": "token",
      |                    "Consignor":
      |                    {
      |                        "identificationNumber": "string",
      |                        "name": "string",
      |                        "Address":
      |                        {
      |                            "streetAndNumber": "string",
      |                            "postcode": "string",
      |                            "city": "string",
      |                            "country": "st"
      |                        },
      |                        "ContactPerson":
      |                        {
      |                            "name": "string",
      |                            "phoneNumber": "string",
      |                            "eMailAddress": "string"
      |                        }
      |                    },
      |                    "Consignee":
      |                    {
      |                        "identificationNumber": "string",
      |                        "name": "string",
      |                        "Address":
      |                        {
      |                            "streetAndNumber": "string",
      |                            "postcode": "string",
      |                            "city": "string",
      |                            "country": "st"
      |                        }
      |                    },
      |                    "AdditionalSupplyChainActor":
      |                    [
      |                        {
      |                            "sequenceNumber": 100,
      |                            "role": "token",
      |                            "identificationNumber": "string"
      |                        }
      |                    ],
      |                    "DepartureTransportMeans":
      |                    [
      |                        {
      |                            "sequenceNumber": 100,
      |                            "typeOfIdentification": "token",
      |                            "identificationNumber": "string",
      |                            "nationality": "st"
      |                        }
      |                    ],
      |                    "PreviousDocument":
      |                    [
      |                        {
      |                            "sequenceNumber": 100,
      |                            "referenceNumber": "string",
      |                            "complementOfInformation": "string",
      |                            "type": "token"
      |                        }
      |                    ],
      |                    "SupportingDocument":
      |                    [
      |                        {
      |                            "sequenceNumber": 100,
      |                            "typeValue": "token",
      |                            "referenceNumber": "string",
      |                            "documentLineItemNumber": 100,
      |                            "complementOfInformation": "string"
      |                        }
      |                    ],
      |                    "TransportDocument":
      |                    [
      |                        {
      |                            "sequenceNumber": 100,
      |                            "referenceNumber": "string",
      |                            "type": "token"
      |                        }
      |                    ],
      |                    "AdditionalReference":
      |                    [
      |                        {
      |                            "sequenceNumber": 100,
      |                            "referenceNumber": "string",
      |                            "type": "token"
      |                        }
      |                    ],
      |                    "AdditionalInformation":
      |                    [
      |                        {
      |                            "sequenceNumber": 100,
      |                            "code": "token",
      |                            "text": "string"
      |                        }
      |                    ],
      |                    "TransportCharges":
      |                    {
      |                        "methodOfPayment": "s"
      |                    },
      |                    "ConsignmentItem":
      |                    [
      |                        {
      |                            "goodsItemNumber": 100,
      |                            "declarationGoodsItemNumber": 100,
      |                            "declarationType": "token",
      |                            "countryOfDispatch": "st",
      |                            "countryOfDestination": "token",
      |                            "referenceNumberUCR": "string",
      |                            "AdditionalSupplyChainActor":
      |                            [
      |                                {
      |                                    "sequenceNumber": 100,
      |                                    "role": "token",
      |                                    "identificationNumber": "string"
      |                                }
      |                            ],
      |                            "Commodity":
      |                            {
      |                                "descriptionOfGoods": "string",
      |                                "cusCode": "token",
      |                                "CommodityCode":
      |                                {
      |                                    "harmonizedSystemSubHeadingCode": "token",
      |                                    "combinedNomenclatureCode": "st"
      |                                },
      |                                "DangerousGoods":
      |                                [
      |                                    {
      |                                        "sequenceNumber": 100,
      |                                        "UNNumber": "token"
      |                                    }
      |                                ],
      |                                "GoodsMeasure":
      |                                {
      |                                    "grossMass": 10
      |                                }
      |                            },
      |                            "Packaging":
      |                            [
      |                                {
      |                                    "sequenceNumber": 100,
      |                                    "typeOfPackages": "token",
      |                                    "numberOfPackages": 100,
      |                                    "shippingMarks": "string"
      |                                }
      |                            ],
      |                            "PreviousDocument":
      |                            [
      |                                {
      |                                    "sequenceNumber": 100,
      |                                    "referenceNumber": "string",
      |                                    "goodsItemNumber": 100,
      |                                    "typeOfPackages": "token",
      |                                    "numberOfPackages": 100,
      |                                    "measurementUnitAndQualifier": "token",
      |                                    "quantity": 1000,
      |                                    "complementOfInformation": "string",
      |                                    "type": "token"
      |                                }
      |                            ],
      |                            "SupportingDocument":
      |                            [
      |                                {
      |                                    "sequenceNumber": 100,
      |                                    "typeValue": "token",
      |                                    "referenceNumber": "string",
      |                                    "documentLineItemNumber": 100,
      |                                    "complementOfInformation": "string"
      |                                }
      |                            ],
      |                            "AdditionalReference":
      |                            [
      |                                {
      |                                    "sequenceNumber": 100,
      |                                    "referenceNumber": "string",
      |                                    "type": "token"
      |                                }
      |                            ],
      |                            "AdditionalInformation":
      |                            [
      |                                {
      |                                    "sequenceNumber": 100,
      |                                    "code": "token",
      |                                    "text": "string"
      |                                }
      |                            ]
      |                        }
      |                    ]
      |                }
      |            ]
      |        },
      |        "@PhaseID": "NCTS5.0"
      |    }
      |}""".stripMargin
  )
}

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

package uk.gov.hmrc.transitmovementsconverter.models

import play.api.libs.json.JsValue
import play.api.libs.json.Json

import scala.xml.NodeSeq

object TestObjects {

  object CC004C extends TestType {

    lazy val xml1 = <ncts:CC004C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>token</messageSender>
      <messageRecipient>token</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>token</messageIdentification>
      <messageType>CC004C</messageType>
      <!--Optional:-->
      <correlationIdentifier>token</correlationIdentifier>
      <TransitOperation>
        <!--Optional:-->
        <LRN>string</LRN>
        <!--Optional:-->
        <MRN>string</MRN>
        <amendmentSubmissionDateAndTime>2014-06-09T16:15:04+01:00</amendmentSubmissionDateAndTime>
        <amendmentAcceptanceDateAndTime>2008-11-15T16:52:58</amendmentAcceptanceDateAndTime>
      </TransitOperation>
      <CustomsOfficeOfDeparture>
        <referenceNumber>stringst</referenceNumber>
      </CustomsOfficeOfDeparture>
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
          <country>GB</country>
        </Address>
      </HolderOfTheTransitProcedure>
    </ncts:CC004C>

    lazy val json1 = Json.parse(
      """
        |{
        |    "n1:CC004C": {
        |        "preparationDateAndTime": "2007-10-26T07:36:28",
        |        "TransitOperation": {
        |            "LRN": "string",
        |            "MRN": "string",
        |            "amendmentSubmissionDateAndTime": "2014-06-09T16:15:04+01:00",
        |            "amendmentAcceptanceDateAndTime": "2008-11-15T16:52:58"
        |        },
        |        "CustomsOfficeOfDeparture": {
        |            "referenceNumber": "stringst"
        |        },
        |        "messageType": "CC004C",
        |        "@PhaseID": "NCTS5.0",
        |        "correlationIdentifier": "token",
        |        "messageSender": "token",
        |        "messageRecipient": "token",
        |        "HolderOfTheTransitProcedure": {
        |            "identificationNumber": "string",
        |            "TIRHolderIdentificationNumber": "string",
        |            "name": "string",
        |            "Address": {
        |                "streetAndNumber": "string",
        |                "postcode": "string",
        |                "city": "string",
        |                "country": "GB"
        |            }
        |        },
        |        "messageIdentification": "token"
        |    }
        |}
        |""".stripMargin
    )

  }

  object CC009C extends TestType {

    lazy val xml1 = <ncts:CC009C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>token</messageSender>
      <messageRecipient>token</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>token</messageIdentification>
      <messageType>CD975C</messageType>
      <!--Optional:-->
      <correlationIdentifier>token</correlationIdentifier>
      <TransitOperation>
        <!--Optional:-->
        <LRN>string</LRN>
        <!--Optional:-->
        <MRN>string</MRN>
      </TransitOperation>
      <Invalidation>
        <!--Optional:-->
        <requestDateAndTime>2014-06-09T16:15:04+01:00</requestDateAndTime>
        <!--Optional:-->
        <decisionDateAndTime>2008-11-15T16:52:58</decisionDateAndTime>
        <!--Optional:-->
        <decision>0</decision>
        <initiatedByCustoms>1</initiatedByCustoms>
        <!--Optional:-->
        <justification>string</justification>
      </Invalidation>
      <CustomsOfficeOfDeparture>
        <referenceNumber>stringst</referenceNumber>
      </CustomsOfficeOfDeparture>
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
          <phoneNumber>token</phoneNumber>
          <!--Optional:-->
          <eMailAddress>string</eMailAddress>
        </ContactPerson>
      </HolderOfTheTransitProcedure>
    </ncts:CC009C>

    lazy val json1 = Json.parse(
      """
        |{
        |    "n1:CC009C": {
        |        "preparationDateAndTime": "2007-10-26T07:36:28",
        |        "TransitOperation": {
        |            "LRN": "string",
        |            "MRN": "string"
        |        },
        |        "CustomsOfficeOfDeparture": {
        |            "referenceNumber": "stringst"
        |        },
        |        "messageType": "CD975C",
        |        "@PhaseID": "NCTS5.0",
        |        "correlationIdentifier": "token",
        |        "messageSender": "token",
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
        |                "phoneNumber": "token",
        |                "eMailAddress": "string"
        |            }
        |        },
        |        "Invalidation": {
        |            "requestDateAndTime": "2014-06-09T16:15:04+01:00",
        |            "decisionDateAndTime": "2008-11-15T16:52:58",
        |            "decision": "0",
        |            "initiatedByCustoms": "1",
        |            "justification": "string"
        |        },
        |        "messageIdentification": "token"
        |    }
        |}
        |""".stripMargin
    )

  }

  object CC013C extends TestType {

    lazy val xml1 = <ncts:CC013C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>token</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>token</messageIdentification>
      <messageType>CD975C</messageType>
      <!--Optional:-->
      <correlationIdentifier>token</correlationIdentifier>
      <TransitOperation>
        <!--Optional:-->
        <LRN>string</LRN>
        <!--Optional:-->
        <MRN>string</MRN>
        <declarationType>token</declarationType>
        <additionalDeclarationType>token</additionalDeclarationType>
        <!--Optional:-->
        <TIRCarnetNumber>string</TIRCarnetNumber>
        <!--Optional:-->
        <presentationOfTheGoodsDateAndTime>2014-06-09T16:15:04+01:00</presentationOfTheGoodsDateAndTime>
        <security>token</security>
        <reducedDatasetIndicator>1</reducedDatasetIndicator>
        <!--Optional:-->
        <specificCircumstanceIndicator>token</specificCircumstanceIndicator>
        <!--Optional:-->
        <communicationLanguageAtDeparture>st</communicationLanguageAtDeparture>
        <bindingItinerary>1</bindingItinerary>
        <amendmentTypeFlag>0</amendmentTypeFlag>
        <!--Optional:-->
        <limitDate>2017-05-15</limitDate>
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
        <arrivalDateAndTimeEstimated>2013-12-21T11:32:42+00:00</arrivalDateAndTimeEstimated>
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
          <phoneNumber>token</phoneNumber>
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
          <phoneNumber>token</phoneNumber>
          <!--Optional:-->
          <eMailAddress>string</eMailAddress>
        </ContactPerson>
      </Representative>
      <!--1 to 9 repetitions:-->
      <Guarantee>
        <sequenceNumber>token</sequenceNumber>
        <!--Optional:-->
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
          <!--Optional:-->
          <amountToBeCovered>1000.000000000000</amountToBeCovered>
          <!--Optional:-->
          <currency>token</currency>
        </GuaranteeReference>
      </Guarantee>
      <Consignment>
        <!--Optional:-->
        <countryOfDispatch>st</countryOfDispatch>
        <!--Optional:-->
        <countryOfDestination>token</countryOfDestination>
        <!--Optional:-->
        <containerIndicator>1</containerIndicator>
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
            <phoneNumber>token</phoneNumber>
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
            <phoneNumber>token</phoneNumber>
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
            <phoneNumber>token</phoneNumber>
            <!--Optional:-->
            <eMailAddress>string</eMailAddress>
          </ContactPerson>
        </LocationOfGoods>
        <!--0 to 999 repetitions:-->
        <DepartureTransportMeans>
          <sequenceNumber>token</sequenceNumber>
          <!--Optional:-->
          <typeOfIdentification>token</typeOfIdentification>
          <!--Optional:-->
          <identificationNumber>string</identificationNumber>
          <!--Optional:-->
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
          <!--Optional:-->
          <customsOfficeAtBorderReferenceNumber>token</customsOfficeAtBorderReferenceNumber>
          <!--Optional:-->
          <typeOfIdentification>token</typeOfIdentification>
          <!--Optional:-->
          <identificationNumber>string</identificationNumber>
          <!--Optional:-->
          <nationality>st</nationality>
          <!--Optional:-->
          <conveyanceReferenceNumber>string</conveyanceReferenceNumber>
        </ActiveBorderTransportMeans>
        <!--Optional:-->
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
              <phoneNumber>token</phoneNumber>
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
              <!--Optional:-->
              <GoodsMeasure>
                <!--Optional:-->
                <grossMass>1000.000000000000</grossMass>
                <!--Optional:-->
                <netMass>1000.000000000000</netMass>
                <!--Optional:-->
                <supplementaryUnits>1000.000000000000</supplementaryUnits>
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
    </ncts:CC013C>

    lazy val json1 = Json.parse(
      """
          |{
          |    "n1:CC013C": {
          |        "preparationDateAndTime": "2007-10-26T07:36:28",
          |        "TransitOperation": {
          |            "LRN": "string",
          |            "MRN": "string",
          |            "declarationType": "token",
          |            "additionalDeclarationType": "token",
          |            "TIRCarnetNumber": "string",
          |            "presentationOfTheGoodsDateAndTime": "2014-06-09T16:15:04+01:00",
          |            "security": "token",
          |            "reducedDatasetIndicator": "1",
          |            "specificCircumstanceIndicator": "token",
          |            "communicationLanguageAtDeparture": "st",
          |            "bindingItinerary": "1",
          |            "amendmentTypeFlag": "0",
          |            "limitDate": "2017-05-15"
          |        },
          |        "CustomsOfficeOfDeparture": {
          |            "referenceNumber": "stringst"
          |        },
          |        "Consignment": {
          |            "countryOfDispatch": "st",
          |            "countryOfDestination": "token",
          |            "containerIndicator": "1",
          |            "inlandModeOfTransport": "token",
          |            "modeOfTransportAtTheBorder": "token",
          |            "grossMass": 1000,
          |            "referenceNumberUCR": "string",
          |            "Carrier": {
          |                "identificationNumber": "string",
          |                "ContactPerson": {
          |                    "name": "string",
          |                    "phoneNumber": "token",
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
          |                    "phoneNumber": "token",
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
          |                    "phoneNumber": "token",
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
          |                            "phoneNumber": "token",
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
          |                                    "netMass": 1000,
          |                                    "supplementaryUnits": 1000
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
          |                "arrivalDateAndTimeEstimated": "2013-12-21T11:32:42Z"
          |            }
          |        ],
          |        "CustomsOfficeOfDestinationDeclared": {
          |            "referenceNumber": "stringst"
          |        },
          |        "messageType": "CD975C",
          |        "@PhaseID": "NCTS5.0",
          |        "correlationIdentifier": "token",
          |        "Authorisation": [
          |            {
          |                "sequenceNumber": "token",
          |                "typeValue": "token",
          |                "referenceNumber": "string"
          |            }
          |        ],
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
          |                "phoneNumber": "token",
          |                "eMailAddress": "string"
          |            }
          |        },
          |        "Representative": {
          |            "identificationNumber": "string",
          |            "status": "token",
          |            "ContactPerson": {
          |                "name": "string",
          |                "phoneNumber": "token",
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

  object CC014C extends TestType {

    lazy val xml1 = <ncts:CC014C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>token</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>token</messageIdentification>
      <messageType>CD975C</messageType>
      <!--Optional:-->
      <correlationIdentifier>token</correlationIdentifier>
      <TransitOperation>
        <!--Optional:-->
        <LRN>string</LRN>
        <!--Optional:-->
        <MRN>string</MRN>
      </TransitOperation>
      <Invalidation>
        <!--Optional:-->
        <requestDateAndTime>2014-06-09T16:15:04+01:00</requestDateAndTime>
        <!--Optional:-->
        <decisionDateAndTime>2008-11-15T16:52:58</decisionDateAndTime>
        <!--Optional:-->
        <decision>0</decision>
        <initiatedByCustoms>1</initiatedByCustoms>
        <!--Optional:-->
        <justification>string</justification>
      </Invalidation>
      <CustomsOfficeOfDeparture>
        <referenceNumber>stringst</referenceNumber>
      </CustomsOfficeOfDeparture>
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
          <phoneNumber>token</phoneNumber>
          <!--Optional:-->
          <eMailAddress>string</eMailAddress>
        </ContactPerson>
      </HolderOfTheTransitProcedure>
    </ncts:CC014C>

    lazy val json1 = Json.parse(
      """
        |{
        |    "n1:CC014C": {
        |        "preparationDateAndTime": "2007-10-26T07:36:28",
        |        "TransitOperation": {
        |            "LRN": "string",
        |            "MRN": "string"
        |        },
        |        "CustomsOfficeOfDeparture": {
        |            "referenceNumber": "stringst"
        |        },
        |        "messageType": "CD975C",
        |        "@PhaseID": "NCTS5.0",
        |        "correlationIdentifier": "token",
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
        |                "phoneNumber": "token",
        |                "eMailAddress": "string"
        |            }
        |        },
        |        "Invalidation": {
        |            "requestDateAndTime": "2014-06-09T16:15:04+01:00",
        |            "decisionDateAndTime": "2008-11-15T16:52:58",
        |            "decision": "0",
        |            "initiatedByCustoms": "1",
        |            "justification": "string"
        |        },
        |        "messageIdentification": "token"
        |    }
        |}
        |""".stripMargin
    )

  }

  object CC015C extends TestType {

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

  object CC019C extends TestType {

    lazy val xml1 = <ncts:CC019C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>token</messageSender>
      <messageRecipient>token</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>token</messageIdentification>
      <messageType>CD975C</messageType>
      <!--Optional:-->
      <correlationIdentifier>token</correlationIdentifier>
      <TransitOperation>
        <MRN>string</MRN>
        <discrepanciesNotificationDate>2014-06-09+01:00</discrepanciesNotificationDate>
        <!--Optional:-->
        <discrepanciesNotificationText>string</discrepanciesNotificationText>
      </TransitOperation>
      <CustomsOfficeOfDeparture>
        <referenceNumber>stringst</referenceNumber>
      </CustomsOfficeOfDeparture>
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
      </HolderOfTheTransitProcedure>
      <!--Optional:-->
      <Guarantor>
        <identificationNumber>string</identificationNumber>
        <!--Optional:-->
        <name>string</name>
        <!--Optional:-->
        <Address>
          <streetAndNumber>string</streetAndNumber>
          <!--Optional:-->
          <postcode>string</postcode>
          <city>string</city>
          <country>BE</country>
        </Address>
      </Guarantor>
    </ncts:CC019C>

    lazy val json1 = Json.parse(
      """
        |{
        |    "n1:CC019C": {
        |        "preparationDateAndTime": "2007-10-26T07:36:28",
        |        "TransitOperation": {
        |            "MRN": "string",
        |            "discrepanciesNotificationDate": "2014-06-09+01:00",
        |            "discrepanciesNotificationText": "string"
        |        },
        |        "CustomsOfficeOfDeparture": {
        |            "referenceNumber": "stringst"
        |        },
        |        "messageType": "CD975C",
        |        "@PhaseID": "NCTS5.0",
        |        "correlationIdentifier": "token",
        |        "messageSender": "token",
        |        "messageRecipient": "token",
        |        "Guarantor": {
        |            "identificationNumber": "string",
        |            "name": "string",
        |            "Address": {
        |                "streetAndNumber": "string",
        |                "postcode": "string",
        |                "city": "string",
        |                "country": "BE"
        |            }
        |        },
        |        "HolderOfTheTransitProcedure": {
        |            "identificationNumber": "string",
        |            "TIRHolderIdentificationNumber": "string",
        |            "name": "string",
        |            "Address": {
        |                "streetAndNumber": "string",
        |                "postcode": "string",
        |                "city": "string",
        |                "country": "st"
        |            }
        |        },
        |        "messageIdentification": "token"
        |    }
        |}
        |""".stripMargin
    )

  }

  object CC170C extends TestType {

    lazy val xml1 = <ncts:CC170C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageRecipient>token</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>token</messageIdentification>
      <messageType>CD975C</messageType>
      <!--Optional:-->
      <correlationIdentifier>token</correlationIdentifier>
      <TransitOperation>
        <LRN>string</LRN>
        <!--Optional:-->
        <limitDate>2014-06-09+01:00</limitDate>
      </TransitOperation>
      <CustomsOfficeOfDeparture>
        <referenceNumber>stringst</referenceNumber>
      </CustomsOfficeOfDeparture>
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
      </HolderOfTheTransitProcedure>
      <!--Optional:-->
      <Representative>
        <identificationNumber>string</identificationNumber>
        <status>token</status>
        <!--Optional:-->
        <ContactPerson>
          <name>string</name>
          <phoneNumber>token</phoneNumber>
          <!--Optional:-->
          <eMailAddress>string</eMailAddress>
        </ContactPerson>
      </Representative>
      <Consignment>
        <!--Optional:-->
        <containerIndicator>1</containerIndicator>
        <!--Optional:-->
        <inlandModeOfTransport>token</inlandModeOfTransport>
        <!--Optional:-->
        <modeOfTransportAtTheBorder>token</modeOfTransportAtTheBorder>
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
        <!--0 to 999 repetitions:-->
        <DepartureTransportMeans>
          <sequenceNumber>token</sequenceNumber>
          <typeOfIdentification>token</typeOfIdentification>
          <identificationNumber>string</identificationNumber>
          <nationality>st</nationality>
        </DepartureTransportMeans>
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
        <!--Optional:-->
        <PlaceOfLoading>
          <!--Optional:-->
          <UNLocode>token</UNLocode>
          <!--Optional:-->
          <country>st</country>
          <!--Optional:-->
          <location>string</location>
        </PlaceOfLoading>
        <!--1 to 99 repetitions:-->
        <HouseConsignment>
          <sequenceNumber>token</sequenceNumber>
          <!--0 to 999 repetitions:-->
          <DepartureTransportMeans>
            <sequenceNumber>token</sequenceNumber>
            <typeOfIdentification>token</typeOfIdentification>
            <identificationNumber>string</identificationNumber>
            <nationality>st</nationality>
          </DepartureTransportMeans>
        </HouseConsignment>
      </Consignment>
    </ncts:CC170C>

    lazy val json1 = Json.parse(
      """
        |{
        |    "n1:CC170C": {
        |        "preparationDateAndTime": "2007-10-26T07:36:28",
        |        "TransitOperation": {
        |            "LRN": "string",
        |            "limitDate": "2014-06-09+01:00"
        |        },
        |        "CustomsOfficeOfDeparture": {
        |            "referenceNumber": "stringst"
        |        },
        |        "messageType": "CD975C",
        |        "@PhaseID": "NCTS5.0",
        |        "correlationIdentifier": "token",
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
        |            }
        |        },
        |        "Consignment": {
        |            "containerIndicator": "1",
        |            "inlandModeOfTransport": "token",
        |            "modeOfTransportAtTheBorder": "token",
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
        |                    "phoneNumber": "token",
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
        |            "HouseConsignment": [
        |                {
        |                    "sequenceNumber": "token",
        |                    "DepartureTransportMeans": [
        |                        {
        |                            "sequenceNumber": "token",
        |                            "typeOfIdentification": "token",
        |                            "identificationNumber": "string",
        |                            "nationality": "st"
        |                        }
        |                    ]
        |                }
        |            ]
        |        },
        |        "Representative": {
        |            "identificationNumber": "string",
        |            "status": "token",
        |            "ContactPerson": {
        |                "name": "string",
        |                "phoneNumber": "token",
        |                "eMailAddress": "string"
        |            }
        |        },
        |        "messageIdentification": "token"
        |    }
        |}
        |""".stripMargin
    )

  }

  trait TestType {
    def xml1: NodeSeq
    def json1: JsValue
  }

}

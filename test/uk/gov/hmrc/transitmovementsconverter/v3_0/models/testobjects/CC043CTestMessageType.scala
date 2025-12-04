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

import play.api.libs.json.*

import scala.xml.NodeSeq

object CC043CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1: NodeSeq =
    <ncts:CC043C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
      <messageSender>token</messageSender>
      <messageRecipient>token</messageRecipient>
      <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
      <messageIdentification>token</messageIdentification>
      <messageType>CD975C</messageType>
      <!--Optional:-->
      <correlationIdentifier>token</correlationIdentifier>
      <TransitOperation>
        <MRN>string</MRN>
        <!--Optional:-->
        <declarationType>token</declarationType>
        <!--Optional:-->
        <declarationAcceptanceDate>2014-06-09+01:00</declarationAcceptanceDate>
        <security>token</security>
        <reducedDatasetIndicator>1</reducedDatasetIndicator>
      </TransitOperation>
      <CustomsOfficeOfDestinationActual>
        <referenceNumber>stringst</referenceNumber>
      </CustomsOfficeOfDestinationActual>
      <!--Optional:-->
      <HolderOfTheTransitProcedure>
        <!--Optional:-->
        <identificationNumber>string</identificationNumber>
        <!--Optional:-->
        <TIRHolderIdentificationNumber>string</TIRHolderIdentificationNumber>
        <name>string</name>
        <Address>
          <streetAndNumber>string</streetAndNumber>
          <!--Optional:-->
          <postcode>string</postcode>
          <city>string</city>
          <country>st</country>
        </Address>
      </HolderOfTheTransitProcedure>
      <TraderAtDestination>
        <identificationNumber>string</identificationNumber>
      </TraderAtDestination>
      <!--Optional:-->
      <CTLControl>
        <continueUnloading>9</continueUnloading>
      </CTLControl>
      <!--Optional:-->
      <Consignment>
        <!--Optional:-->
        <countryOfDestination>token</countryOfDestination>
        <containerIndicator>1</containerIndicator>
        <!--Optional:-->
        <inlandModeOfTransport>token</inlandModeOfTransport>
        <!--Optional:-->
        <grossMass>1000.000000000000</grossMass>
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
        <!--0 to 9999 repetitions:-->
        <TransportEquipment>
          <sequenceNumber>100</sequenceNumber>
          <!--Optional:-->
          <containerIdentificationNumber>string</containerIdentificationNumber>
          <numberOfSeals>100</numberOfSeals>
          <!--0 to 99 repetitions:-->
          <Seal>
            <sequenceNumber>100</sequenceNumber>
            <identifier>string</identifier>
          </Seal>
          <!--0 to 9999 repetitions:-->
          <GoodsReference>
            <sequenceNumber>100</sequenceNumber>            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
          </GoodsReference>
        </TransportEquipment>
        <!--0 to 999 repetitions:-->
        <DepartureTransportMeans>
          <sequenceNumber>100</sequenceNumber>
         <typeOfIdentification>token</typeOfIdentification>
          <identificationNumber>string</identificationNumber>
          <nationality>st</nationality>
        </DepartureTransportMeans>
        <!--0 to 9999 repetitions:-->
        <PreviousDocument>
          <sequenceNumber>100</sequenceNumber>         <type>token</type>
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
          <complementOfInformation>string</complementOfInformation>
        </SupportingDocument>
        <!--0 to 99 repetitions:-->
        <TransportDocument>
          <sequenceNumber>100</sequenceNumber>        <type>token</type>
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
        <!--0 to 9 repetitions:-->
        <Incident>
          <sequenceNumber>100</sequenceNumber>
         <code>token</code>
          <text>string</text>
          <!--Optional:-->
          <Endorsement>
            <date>2013-05-22+01:00</date>
            <authority>string</authority>
            <place>string</place>
            <country>st</country>
          </Endorsement>
          <Location>
            <qualifierOfIdentification>token</qualifierOfIdentification>
            <!--Optional:-->
            <UNLocode>token</UNLocode>
            <country>st</country>
            <!--Optional:-->
            <GNSS>
              <latitude>string</latitude>
              <longitude>string</longitude>
            </GNSS>
            <!--Optional:-->
            <Address>
              <streetAndNumber>string</streetAndNumber>
              <!--Optional:-->
              <postcode>string</postcode>
              <city>string</city>
            </Address>
          </Location>
          <!--0 to 9999 repetitions:-->
          <TransportEquipment>
            <sequenceNumber>100</sequenceNumber>           <!--Optional:-->
            <containerIdentificationNumber>string</containerIdentificationNumber>
            <!--Optional:-->
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
          <Transhipment>
            <containerIndicator>0</containerIndicator>
            <TransportMeans>
              <typeOfIdentification>token</typeOfIdentification>
              <identificationNumber>string</identificationNumber>
              <nationality>st</nationality>
            </TransportMeans>
          </Transhipment>
        </Incident>
        <!--1 to 99 repetitions:-->
        <HouseConsignment>
          <sequenceNumber>100</sequenceNumber>
          <grossMass>1000.000000000000</grossMass>
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
          <!--0 to 999 repetitions:-->
          <DepartureTransportMeans>
            <sequenceNumber>100</sequenceNumber>            <typeOfIdentification>token</typeOfIdentification>
            <identificationNumber>string</identificationNumber>
            <nationality>st</nationality>
          </DepartureTransportMeans>
          <!--0 to 99 repetitions:-->
          <PreviousDocument>
            <sequenceNumber>100</sequenceNumber>
          <type>token</type>
            <referenceNumber>string</referenceNumber>
            <!--Optional:-->
            <complementOfInformation>string</complementOfInformation>
          </PreviousDocument>
          <!--0 to 99 repetitions:-->
          <SupportingDocument>
            <sequenceNumber>100</sequenceNumber>           <type>token</type>
            <referenceNumber>string</referenceNumber>
            <!--Optional:-->
            <complementOfInformation>string</complementOfInformation>
          </SupportingDocument>
          <!--0 to 99 repetitions:-->
          <TransportDocument>
            <sequenceNumber>100</sequenceNumber>
            <type>token</type>
            <referenceNumber>string</referenceNumber>
          </TransportDocument>
          <!--0 to 99 repetitions:-->
          <AdditionalReference>
            <sequenceNumber>100</sequenceNumber>          <type>token</type>
            <!--Optional:-->
            <referenceNumber>string</referenceNumber>
          </AdditionalReference>
          <!--0 to 99 repetitions:-->
          <AdditionalInformation>
            <sequenceNumber>100</sequenceNumber>
            <code>token</code>
            <!--Optional:-->
            <text>string</text>
          </AdditionalInformation>
          <!--1 to 999 repetitions:-->
          <ConsignmentItem>
            <goodsItemNumber>100</goodsItemNumber>
            <declarationGoodsItemNumber>100</declarationGoodsItemNumber>
            <!--Optional:-->
            <declarationType>token</declarationType>
            <!--Optional:-->
            <countryOfDestination>token</countryOfDestination>
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
                <sequenceNumber>100</sequenceNumber>              <UNNumber>token</UNNumber>
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
              <sequenceNumber>100</sequenceNumber>              <type>token</type>
              <referenceNumber>string</referenceNumber>
              <!--Optional:-->
              <goodsItemNumber>100</goodsItemNumber>
              <!--Optional:-->
              <complementOfInformation>string</complementOfInformation>
            </PreviousDocument>
            <!--0 to 99 repetitions:-->
            <SupportingDocument>
              <sequenceNumber>100</sequenceNumber>
              <type>token</type>
              <referenceNumber>string</referenceNumber>
              <!--Optional:-->
              <complementOfInformation>string</complementOfInformation>
            </SupportingDocument>
            <!--0 to 99 repetitions:-->
            <TransportDocument>
              <sequenceNumber>100</sequenceNumber>           <type>token</type>
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
              <sequenceNumber>100</sequenceNumber>              <code>token</code>
              <!--Optional:-->
              <text>string</text>
            </AdditionalInformation>
          </ConsignmentItem>
        </HouseConsignment>
      </Consignment>
    </ncts:CC043C>

  lazy val json1: JsValue = Json.parse(
    """{"n1:CC043C":{"messageSender":"token","messageRecipient":"token","preparationDateAndTime":"2007-10-26T07:36:28","messageIdentification":"token","messageType":"CD975C","correlationIdentifier":"token","TransitOperation":{"MRN":"string","declarationType":"token","declarationAcceptanceDate":"2014-06-09+01:00","security":"token","reducedDatasetIndicator":"1"},"CustomsOfficeOfDestinationActual":{"referenceNumber":"stringst"},"HolderOfTheTransitProcedure":{"identificationNumber":"string","TIRHolderIdentificationNumber":"string","name":"string","Address":{"streetAndNumber":"string","postcode":"string","city":"string","country":"st"}},"TraderAtDestination":{"identificationNumber":"string"},"CTLControl":{"continueUnloading":9},"Consignment":{"countryOfDestination":"token","containerIndicator":"1","inlandModeOfTransport":"token","grossMass":1000,"Consignor":{"identificationNumber":"string","name":"string","Address":{"streetAndNumber":"string","postcode":"string","city":"string","country":"st"}},"Consignee":{"identificationNumber":"string","name":"string","Address":{"streetAndNumber":"string","postcode":"string","city":"string","country":"st"}},"TransportEquipment":[{"sequenceNumber":100,"containerIdentificationNumber":"string","numberOfSeals":100,"Seal":[{"sequenceNumber":100,"identifier":"string"}],"GoodsReference":[{"sequenceNumber":100,"declarationGoodsItemNumber":100}]}],"DepartureTransportMeans":[{"sequenceNumber":100,"typeOfIdentification":"token","identificationNumber":"string","nationality":"st"}],"CountryOfRoutingOfConsignment":[],"PreviousDocument":[{"sequenceNumber":100,"typeValue":"token","referenceNumber":"string","complementOfInformation":"string"}],"SupportingDocument":[{"sequenceNumber":100,"referenceNumber":"string","complementOfInformation":"string","type":"token"}],"TransportDocument":[{"sequenceNumber":100,"referenceNumber":"string","type":"token"}],"AdditionalReference":[{"sequenceNumber":100,"referenceNumber":"string","type":"token"}],"AdditionalInformation":[{"sequenceNumber":100,"code":"token","text":"string"}],"Incident":[{"sequenceNumber":100,"code":"token","text":"string","Endorsement":{"date":"2013-05-22+01:00","authority":"string","place":"string","country":"st"},"Location":{"qualifierOfIdentification":"token","UNLocode":"token","country":"st","GNSS":{"latitude":"string","longitude":"string"},"Address":{"streetAndNumber":"string","postcode":"string","city":"string"}},"TransportEquipment":[{"sequenceNumber":100,"containerIdentificationNumber":"string","numberOfSeals":100,"Seal":[{"sequenceNumber":100,"identifier":"string"}],"GoodsReference":[{"sequenceNumber":100,"declarationGoodsItemNumber":100}]}],"Transhipment":{"containerIndicator":"0","TransportMeans":{"typeOfIdentification":"token","identificationNumber":"string","nationality":"st"}}}],"HouseConsignment":[{"sequenceNumber":100,"grossMass":1000,"securityIndicatorFromExportDeclaration":"token","Consignor":{"identificationNumber":"string","name":"string","Address":{"streetAndNumber":"string","postcode":"string","city":"string","country":"st"}},"Consignee":{"identificationNumber":"string","name":"string","Address":{"streetAndNumber":"string","postcode":"string","city":"string","country":"st"}},"DepartureTransportMeans":[{"sequenceNumber":100,"typeOfIdentification":"token","identificationNumber":"string","nationality":"st"}],"PreviousDocument":[{"sequenceNumber":100,"referenceNumber":"string","complementOfInformation":"string","type":"token"}],"SupportingDocument":[{"sequenceNumber":100,"referenceNumber":"string","complementOfInformation":"string","type":"token"}],"TransportDocument":[{"sequenceNumber":100,"referenceNumber":"string","type":"token"}],"AdditionalReference":[{"sequenceNumber":100,"referenceNumber":"string","type":"token"}],"AdditionalInformation":[{"sequenceNumber":100,"code":"token","text":"string"}],"ConsignmentItem":[{"goodsItemNumber":100,"declarationGoodsItemNumber":100,"declarationType":"token","countryOfDestination":"token","Consignee":{"identificationNumber":"string","name":"string","Address":{"streetAndNumber":"string","postcode":"string","city":"string","country":"st"}},"Commodity":{"descriptionOfGoods":"string","cusCode":"token","CommodityCode":{"harmonizedSystemSubHeadingCode":"token","combinedNomenclatureCode":"st"},"DangerousGoods":[{"sequenceNumber":100,"UNNumber":"token"}],"GoodsMeasure":{"grossMass":10}},"Packaging":[{"sequenceNumber":100,"typeOfPackages":"token","numberOfPackages":100,"shippingMarks":"string"}],"PreviousDocument":[{"sequenceNumber":100,"referenceNumber":"string","goodsItemNumber":100,"complementOfInformation":"string","type":"token"}],"SupportingDocument":[{"sequenceNumber":100,"referenceNumber":"string","complementOfInformation":"string","type":"token"}],"TransportDocument":[{"sequenceNumber":100,"referenceNumber":"string","type":"token"}],"AdditionalReference":[{"sequenceNumber":100,"referenceNumber":"string","type":"token"}],"AdditionalInformation":[{"sequenceNumber":100,"code":"token","text":"string"}]}]}]},"@PhaseID":"NCTS5.0"}}"""
  )
}

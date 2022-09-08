package uk.gov.hmrc.transitmovementsconverter.models.testobjects

import play.api.libs.json.JsValue
import play.api.libs.json.Json

import scala.xml.NodeSeq

object CC060CTestMessageType extends TestMessageType {
  lazy val xml1: NodeSeq = <ncts:CC060C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC060C</messageType>
    <!--Optional:-->
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <!--Optional:-->
      <LRN>string</LRN>
      <!--Optional:-->
      <MRN>string</MRN>
      <controlNotificationDateAndTime>2014-06-09T16:15:04+01:00</controlNotificationDateAndTime>
      <notificationType>token</notificationType>
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
    <!--0 to 99 repetitions:-->
    <TypeOfControls>
      <sequenceNumber>token</sequenceNumber>
      <type>str</type>
      <!--Optional:-->
      <text>string</text>
    </TypeOfControls>
    <!--0 to 99 repetitions:-->
    <RequestedDocument>
      <sequenceNumber>token</sequenceNumber>
      <documentType>token</documentType>
      <!--Optional:-->
      <description>string</description>
    </RequestedDocument>
  </ncts:CC060C>

  lazy val json1: JsValue = Json.parse(
    """
      |{
      |    "n1:CC060C": {
      |        "preparationDateAndTime": "2007-10-26T07:36:28",
      |        "TransitOperation": {
      |            "LRN": "string",
      |            "MRN": "string",
      |            "controlNotificationDateAndTime": "2014-06-09T16:15:04+01:00",
      |            "notificationType": "token"
      |        },
      |        "CustomsOfficeOfDeparture": {
      |            "referenceNumber": "stringst"
      |        },
      |        "TypeOfControls": [
      |            {
      |                "sequenceNumber": "token",
      |                "typeValue": "str",
      |                "text": "string"
      |            }
      |        ],
      |        "messageSender": "token",
      |        "RequestedDocument": [
      |            {
      |                "sequenceNumber": "token",
      |                "documentType": "token",
      |                "description": "string"
      |            }
      |        ],
      |        "messageType": "CC060C",
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

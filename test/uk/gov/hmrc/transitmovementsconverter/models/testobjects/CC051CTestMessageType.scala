package uk.gov.hmrc.transitmovementsconverter.models.testobjects

import play.api.libs.json.JsValue
import play.api.libs.json.Json

import scala.xml.NodeSeq

object CC051CTestMessageType extends TestMessageType {
  lazy val xml1: NodeSeq = <ncts:CC051C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC051C</messageType>
    <!--Optional:-->
    <correlationIdentifier>token</correlationIdentifier>
    <TransitOperation>
      <MRN>string</MRN>
      <declarationSubmissionDateAndTime>2014-06-09T16:15:04+01:00</declarationSubmissionDateAndTime>
      <noReleaseMotivationCode>token</noReleaseMotivationCode>
      <noReleaseMotivationText>string</noReleaseMotivationText>
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
      <!--Optional:-->
      <ContactPerson>
        <name>string</name>
        <phoneNumber>string</phoneNumber>
        <!--Optional:-->
        <eMailAddress>string</eMailAddress>
      </ContactPerson>
    </Representative>
  </ncts:CC051C>

  lazy val json1: JsValue = Json.parse(
    """
      |{
      |    "n1:CC051C": {
      |        "preparationDateAndTime": "2007-10-26T07:36:28",
      |        "TransitOperation": {
      |            "MRN": "string",
      |            "declarationSubmissionDateAndTime": "2014-06-09T16:15:04+01:00",
      |            "noReleaseMotivationCode": "token",
      |            "noReleaseMotivationText": "string"
      |        },
      |        "CustomsOfficeOfDeparture": {
      |            "referenceNumber": "stringst"
      |        },
      |        "messageType": "CC051C",
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
      |            }
      |        },
      |        "Representative": {
      |            "identificationNumber": "string",
      |            "ContactPerson": {
      |                "name": "string",
      |                "phoneNumber": "string",
      |                "eMailAddress": "string"
      |            }
      |        },
      |        "messageIdentification": "token"
      |    }
      |}
      |""".stripMargin
  )
}

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

import play.api.libs.json.JsValue
import play.api.libs.json.Json

import scala.xml.NodeSeq

object CC906CTestMessageType extends TestMessageType {

  override lazy val testAssociations: IndexedSeq[(NodeSeq, JsValue)] = IndexedSeq(xml1 -> json1)

  lazy val xml1 = <ncts:CC906C PhaseID="NCTS5.0" xmlns:ncts="http://ncts.dgtaxud.ec">
    <messageSender>token</messageSender>
    <messageRecipient>token</messageRecipient>
    <preparationDateAndTime>2007-10-26T07:36:28</preparationDateAndTime>
    <messageIdentification>token</messageIdentification>
    <messageType>CC906C</messageType>
    <!--Optional:-->
    <correlationIdentifier>token</correlationIdentifier>
    <Header>
      <!--Optional:-->
      <LRN>string</LRN>
      <!--Optional:-->
      <MRN>string</MRN>
    </Header>
    <!--1 to 9999 repetitions:-->
    <FunctionalError>
      <errorPointer>string</errorPointer>
      <errorCode>14</errorCode>
      <errorReason>string</errorReason>
      <!--Optional:-->
      <originalAttributeValue>string</originalAttributeValue>
    </FunctionalError>
  </ncts:CC906C>

  lazy val json1 = Json.parse(
    """
      |{
      |    "n1:CC906C": {
      |        "preparationDateAndTime": "2007-10-26T07:36:28",
      |        "messageType": "CC906C",
      |        "Header": {
      |            "LRN": "string",
      |            "MRN": "string"
      |        },
      |        "@PhaseID": "NCTS5.0",
      |        "correlationIdentifier": "token",
      |        "FunctionalError": [
      |            {
      |                "errorPointer": "string",
      |                "errorCode": "14",
      |                "errorReason": "string",
      |                "originalAttributeValue": "string"
      |            }
      |        ],
      |        "messageSender": "token",
      |        "messageRecipient": "token",
      |        "messageIdentification": "token"
      |    }
      |}
      |""".stripMargin
  )

}

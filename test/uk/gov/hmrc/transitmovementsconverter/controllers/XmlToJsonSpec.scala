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

package uk.gov.hmrc.transitmovementsconverter.controllers

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import uk.gov.hmrc.transitmovementsconverter.services.XmlToJsonServiceImpl

import scala.concurrent.ExecutionContext.Implicits.global
import scala.xml.NodeSeq

class XmlToJsonServiceSpec extends AnyFreeSpec with ScalaFutures with Matchers with TestActorSystem with StreamTestHelpers {

  val validXml: NodeSeq =
    <CC015C>
      <messageSender>Js8RQZM3NmgnNgNMM1T0NB0Cek</messageSender>
      <Consignment>
        <grossMass>3006961631.123445</grossMass>
        <HouseConsignment>
          <sequenceNumber>51159</sequenceNumber>
          <grossMass>3006961631.286343</grossMass>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
        </HouseConsignment>
        <HouseConsignment>
          <sequenceNumber>51159</sequenceNumber>
          <grossMass>3006961631.286343</grossMass>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
        </HouseConsignment>
        <HouseConsignment>
          <sequenceNumber>51159</sequenceNumber>
          <grossMass>3006961631.286343</grossMass>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
        </HouseConsignment>
        <HouseConsignment>
          <sequenceNumber>51159</sequenceNumber>
          <grossMass>3006961631.286343</grossMass>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
        </HouseConsignment>
        <HouseConsignment>
          <sequenceNumber>51159</sequenceNumber>
          <grossMass>3006961631.286343</grossMass>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
          <ConsignmentItem>
            <goodsItemNumber>63423</goodsItemNumber>
            <declarationGoodsItemNumber>31271</declarationGoodsItemNumber>
            <Packaging>
              <sequenceNumber>51159</sequenceNumber>
              <typeOfPackages>Z3</typeOfPackages>
            </Packaging>
          </ConsignmentItem>
        </HouseConsignment>
      </Consignment>
    </CC015C>

  val service = new XmlToJsonServiceImpl

  "When handed an XML stream" - {

    "if it is valid, convert xml to json" in {
      val source = createStream(validXml)

      val result = service.convert(source)

      val xmlStr = "{\n  \"CC015C\": {\n    \"messageSender\": \"Js8RQZM3NmgnNgNMM1T0NB0Cek\",\n    \"Consignment\": {\n      \"grossMass\": 3006961631.123445,\n      \"HouseConsignment\": [\n        {\n          \"sequenceNumber\": 51159,\n          \"grossMass\": 3006961631.286343,\n          \"ConsignmentItem\": [\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            }\n          ]\n        },\n        {\n          \"sequenceNumber\": 51159,\n          \"grossMass\": 3006961631.286343,\n          \"ConsignmentItem\": [\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            }\n          ]\n        },\n        {\n          \"sequenceNumber\": 51159,\n          \"grossMass\": 3006961631.286343,\n          \"ConsignmentItem\": [\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            }\n          ]\n        },\n        {\n          \"sequenceNumber\": 51159,\n          \"grossMass\": 3006961631.286343,\n          \"ConsignmentItem\": [\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            }\n          ]\n        },\n        {\n          \"sequenceNumber\": 51159,\n          \"grossMass\": 3006961631.286343,\n          \"ConsignmentItem\": [\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            },\n            {\n              \"goodsItemNumber\": 63423,\n              \"declarationGoodsItemNumber\": 31271,\n              \"Packaging\": {\n                \"sequenceNumber\": 51159,\n                \"typeOfPackages\": \"Z3\"\n              }\n            }\n          ]\n        }\n      ]\n    }\n  }\n}"

      whenReady(result) {
        _ mustBe xmlStr.replace(" ", "").replace("\n", "")

      }
    }


  }


}

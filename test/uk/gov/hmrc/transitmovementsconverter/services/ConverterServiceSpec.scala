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

package uk.gov.hmrc.transitmovementsconverter.services

import akka.stream.scaladsl.Flow
import akka.stream.scaladsl.Source
import akka.util.ByteString
import generated.XMLProtocol
import org.scalatest.concurrent.PatienceConfiguration.Timeout
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import play.api.libs.json.JsObject
import play.api.libs.json.Json
import play.api.libs.json.OWrites
import play.api.libs.json.Reads
import scalaxb.ElemName
import scalaxb.XMLFormat
import uk.gov.hmrc.transitmovementsconverter.base.StreamTestHelpers
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.models.ConversionFormat
import uk.gov.hmrc.transitmovementsconverter.models.errors.ConversionError
import uk.gov.hmrc.transitmovementsconverter.models.errors.ConversionError.XMLParsingError

import java.io.IOException
import java.nio.charset.StandardCharsets
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.xml.NamespaceBinding
import scala.xml.NodeSeq

class ConverterServiceSpec extends AnyFreeSpec with ScalaFutures with Matchers with TestActorSystem with StreamTestHelpers {

  val timeout = Timeout(2.seconds)

  val validXml: NodeSeq =
    <test>
      <test1>test2</test1>
    </test>

  val invalidXml: NodeSeq =
    <test>
      <test2>test4</test2>
    </test>

  case class TestClass(test1: String)

  val testConverter = new ConversionFormat[TestClass] {

    override def xmlFormat: XMLFormat[TestClass] = new XMLFormat[TestClass] with XMLProtocol {

      override def writes(obj: TestClass, namespace: Option[String], elementLabel: Option[String], scope: NamespaceBinding, typeAttribute: Boolean): NodeSeq =
        <test>
          <test1>obj.test1</test1>
        </test>

      override def reads(seq: NodeSeq, stack: List[ElemName]): Either[String, TestClass] =
        (seq \ "test1") match {
          case NodeSeq.Empty      => Left("Failed")
          case x if x.length != 1 => Left("Failed (has children)")
          case x                  => Right(TestClass(x.text))
        }
    }

    override def jsonReads: Reads[TestClass] = Json.reads[TestClass]

    override def jsonWrites: OWrites[TestClass] = Json.writes[TestClass]
  }

  val service = new ConverterServiceImpl

  "When handed an XML stream" - {

    "if valid, should return appropriate JSON" in {
      val result = service.xmlToJson(testConverter, Source.single(ByteString(validXml.mkString, StandardCharsets.UTF_8)))
      whenReady(result.value, timeout) {
        case Right(x: JsObject) => x mustBe Json.obj("test1" -> "test2")
        case y                  => fail(s"$y is not the expected output")
      }
    }

    "if invalid, should return the appropriate error" in {
      val result = service.xmlToJson(testConverter, Source.single(ByteString(invalidXml.mkString, StandardCharsets.UTF_8)))
      whenReady(result.value, timeout) {
        case Left(XMLParsingError("Failed")) =>
        case y                               => fail(s"$y is not the expected output")
      }
    }

    "if an exception is thrown, should return the appropriate error" in {
      val exception = new IllegalArgumentException
      val result = service.xmlToJson(
        testConverter,
        Source
          .single(ByteString(invalidXml.mkString, StandardCharsets.UTF_8))
          .via(
            Flow.fromFunction(
              _ => throw exception
            )
          )
      )
      whenReady(result.value, timeout) {
        case Left(ConversionError.UnexpectedError(Some(x: IOException))) => x.getCause mustBe exception
        case y                                                           => fail(s"$y is not the expected output")
      }
    }

  }

}

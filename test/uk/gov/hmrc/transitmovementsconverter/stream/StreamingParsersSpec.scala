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

package uk.gov.hmrc.transitmovementsconverter.stream

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import play.api.http.HeaderNames
import play.api.http.Status.OK
import play.api.mvc.Action
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents
import play.api.test.FakeHeaders
import play.api.test.FakeRequest
import play.api.test.Helpers.contentAsString
import play.api.test.Helpers.defaultAwaitTimeout
import play.api.test.Helpers.status
import play.api.test.Helpers.stubControllerComponents
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.v2_1.stream.StreamingParsers

import java.nio.charset.StandardCharsets
import scala.annotation.tailrec
import scala.concurrent.Future

class StreamingParsersSpec extends AnyFreeSpec with Matchers with TestActorSystem {

  lazy val headers = FakeHeaders(Seq(HeaderNames.CONTENT_TYPE -> "text/plain", HeaderNames.ACCEPT -> "application/vnd.hmrc.2.0+json"))

  class Harness(implicit val materializer: Materializer) extends BaseController with StreamingParsers {

    def testFromMemory: Action[Source[ByteString, ?]] = Action.async(streamFromMemory) {
      request => result.apply(request).run(request.body)(materializer)
    }

    def result: Action[String] = Action.async(parse.text) {
      request =>
        Future.successful(Ok(request.body))
    }

    override protected def controllerComponents: ControllerComponents = stubControllerComponents()
  }

  @tailrec
  private def generateByteString(kb: Int, accumulator: ByteString = ByteString.empty): ByteString =
    if (kb <= 0) accumulator
    else {
      lazy val valueAsByte: Byte = (kb % 10).toString.getBytes(StandardCharsets.UTF_8)(0) // one byte each
      generateByteString(kb - 1, ByteString.fromArray(Array.fill(1024)(valueAsByte)) ++ accumulator)
    }

  private def generateSource(byteString: ByteString): Source[ByteString, NotUsed] =
    Source(byteString.grouped(1024).toList)

  "Streaming" - {
    "from Memory" - {
      (1 to 5).foreach {
        value =>
          s"~$value kb string is created" in {
            val byteString = generateByteString(value)
            val request    = FakeRequest("POST", "/", headers, generateSource(byteString))
            val sut        = new Harness
            val result     = sut.testFromMemory()(request)
            status(result) mustBe OK
            contentAsString(result) mustBe byteString.decodeString(StandardCharsets.UTF_8)
          }
      }
    }
  }

}

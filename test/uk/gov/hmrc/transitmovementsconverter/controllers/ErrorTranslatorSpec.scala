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

import cats.syntax.all._
import org.scalacheck.Gen
import org.scalatest.OptionValues
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import uk.gov.hmrc.transitmovementsconverter.models.errors.PresentationError
import uk.gov.hmrc.transitmovementsconverter.models.errors.XmlToJsonError

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ErrorTranslatorSpec extends AnyFreeSpec with Matchers with OptionValues with ScalaFutures with MockitoSugar with ScalaCheckDrivenPropertyChecks {

  object Harness extends ErrorTranslator

  import Harness._

  "ErrorConverter#asPresentation" - {
    "for a success returns the same right" in {
      val input = Right[XmlToJsonError, Unit](()).toEitherT[Future]
      whenReady(input.asPresentation.value) {
        _ mustBe Right(())
      }
    }

    "for an error returns a left with the appropriate presentation error" in {
      val input = Left[XmlToJsonError, Unit](XmlToJsonError.XMLParsingError("IE015")).toEitherT[Future]
      whenReady(input.asPresentation.value) {
        _ mustBe Left(PresentationError.badRequestError("IE015"))
      }
    }
  }

  "XmlToJson Error" - {

    "an Unexpected Error with no exception returns an internal service error with no exception" in {
      val input  = XmlToJsonError.UnexpectedError(None)
      val output = PresentationError.internalServiceError()

      xmlToJsonErrorConverter.convert(input) mustBe output
    }

    "an Unexpected Error with an exception returns an internal service error with an exception" in {
      val exception = new IllegalStateException()
      val input     = XmlToJsonError.UnexpectedError(Some(exception))
      val output    = PresentationError.internalServiceError(cause = Some(exception))

      xmlToJsonErrorConverter.convert(input) mustBe output
    }

    "an XMLParsingError returns a bad request error" in forAll(Gen.identifier) {
      message =>
        val input  = XmlToJsonError.XMLParsingError(message)
        val output = PresentationError.badRequestError(message)

        xmlToJsonErrorConverter.convert(input) mustBe output
    }
  }

}

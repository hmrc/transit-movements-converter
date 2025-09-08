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

package uk.gov.hmrc.transitmovementsconverter.v3_0.controllers

import cats.data.EitherT
import cats.implicits.catsStdInstancesForFuture
import uk.gov.hmrc.transitmovementsconverter.v3_0.models.errors.PresentationError
import uk.gov.hmrc.transitmovementsconverter.v3_0.models.errors.ConversionError

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

trait ErrorTranslator {

  implicit class ErrorConverter[E, A](value: EitherT[Future, E, A]) {

    def asPresentation(implicit c: Converter[E], ec: ExecutionContext): EitherT[Future, PresentationError, A] =
      value.leftMap(c.convert)
  }

  trait Converter[E] {
    def convert(input: E): PresentationError
  }

  implicit val conversionErrorConverter: Converter[ConversionError] = {
    case err: ConversionError.UnexpectedError => PresentationError.internalServiceError(cause = err.thr)
    case ConversionError.XMLParsingError(x)   => PresentationError.badRequestError(x)
    case ConversionError.JsonParsingError(x)  => PresentationError.badRequestError(x.getMessage)
  }

}

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

import akka.stream.Materializer
import akka.stream.scaladsl.Source
import akka.stream.scaladsl.StreamConverters
import akka.util.ByteString
import cats.data.EitherT
import cats.syntax.all._
import com.google.inject.ImplementedBy
import com.google.inject.Inject
import org.xml.sax.InputSource
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import uk.gov.hmrc.transitmovementsconverter.models.ConversionFormat
import uk.gov.hmrc.transitmovementsconverter.models.errors.ConversionError
import uk.gov.hmrc.transitmovementsconverter.services.ConverterServiceImpl.conversionTimeout

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.util.control.NonFatal
import scala.xml.XML

@ImplementedBy(classOf[ConverterServiceImpl])
trait ConverterService {
  def xmlToJson[T](conversionFormat: ConversionFormat[T], source: Source[ByteString, _]): EitherT[Future, ConversionError, JsValue]
}

object ConverterServiceImpl {
  val conversionTimeout = 20.seconds
}

class ConverterServiceImpl @Inject() (implicit materializer: Materializer, ec: ExecutionContext) extends ConverterService {

  override def xmlToJson[T](conversionFormat: ConversionFormat[T], source: Source[ByteString, _]): EitherT[Future, ConversionError, JsValue] =
    EitherT {
      Future {
        // Note that we use 20 seconds here as this is the standard timeout on the service, if we're taking
        // longer than 20 seconds the request will have failed anyway.
        //
        // It's important to note that 20 seconds is the upper limit, and not a target
        scalaxb
          .fromXMLEither(XML.load(new InputSource(source.runWith(StreamConverters.asInputStream(conversionTimeout)))))(conversionFormat.xmlFormat)
          .leftMap(ConversionError.XMLParsingError)
          .map(
            x => Json.toJsObject(x)(conversionFormat.jsonWrites)
          )
      }.recoverWith {
        case NonFatal(e) => Future.successful(Left(ConversionError.UnexpectedError(thr = Some(e))))
      }
    }

}

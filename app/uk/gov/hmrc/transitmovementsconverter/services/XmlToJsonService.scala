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
import uk.gov.hmrc.transitmovementsconverter.models.MessageType
import uk.gov.hmrc.transitmovementsconverter.models.errors.XmlToJsonError

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.util.control.NonFatal
import scala.xml.XML

@ImplementedBy(classOf[XmlToJsonServiceImpl])
trait XmlToJsonService {
  def convert[T](conversionFormat: ConversionFormat[T], source: Source[ByteString, _]): EitherT[Future, XmlToJsonError, JsValue]
}

class XmlToJsonServiceImpl @Inject() (implicit materializer: Materializer, ec: ExecutionContext) extends XmlToJsonService {

  override def convert[T](conversionFormat: ConversionFormat[T], source: Source[ByteString, _]): EitherT[Future, XmlToJsonError, JsValue] =
    EitherT {
      Future {
        scalaxb
          .fromXMLEither(XML.load(new InputSource(source.runWith(StreamConverters.asInputStream(20.seconds)))))(conversionFormat.xmlFormat)
          .leftMap(XmlToJsonError.XMLParsingError)
          .map(
            x => Json.toJsObject(x)(conversionFormat.jsonWrites)
          )
      }.recoverWith {
        case NonFatal(e) => Future.successful(Left(XmlToJsonError.UnexpectedError(thr = Some(e))))
      }
    }

}

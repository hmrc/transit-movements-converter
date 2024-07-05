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

package uk.gov.hmrc.transitmovementsconverter.services

import cats.data.EitherT
import cats.syntax.all._
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.google.inject.ImplementedBy
import com.google.inject.Inject
import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.stream.scaladsl.StreamConverters
import org.apache.pekko.util.ByteString
import org.xml.sax.InputSource
import play.api.Logging
import play.api.libs.json.JsResultException
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import uk.gov.hmrc.transitmovementsconverter.models.ConversionFormat
import uk.gov.hmrc.transitmovementsconverter.models.errors.ConversionError
import uk.gov.hmrc.transitmovementsconverter.services.ConverterServiceImpl.conversionTimeout
import uk.gov.hmrc.transitmovementsconverter.services.ConverterServiceImpl.namespace

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.util.Try
import scala.util.control.NonFatal
import scala.xml.NamespaceBinding
import scala.xml.NodeSeq
import scala.xml.TopScope
import scala.xml.XML

@ImplementedBy(classOf[ConverterServiceImpl])
trait ConverterService {
  def xmlToJson[T](conversionFormat: ConversionFormat[T], source: Source[ByteString, _]): EitherT[Future, ConversionError, JsValue]
  def jsonToXml[T](conversionFormat: ConversionFormat[T], source: Source[ByteString, _]): EitherT[Future, ConversionError, NodeSeq]
}

object ConverterServiceImpl {
  val conversionTimeout = 20.seconds

  val namespace = NamespaceBinding(
    "ncts",
    "http://ncts.dgtaxud.ec",
    TopScope
  )
}

class ConverterServiceImpl @Inject() (implicit materializer: Materializer, ec: ExecutionContext) extends ConverterService with Logging {

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

        case NonFatal(e) =>
          logger.error(s"Xml to Json conversion got failed: $e", e)
          Future.successful(Left(ConversionError.UnexpectedError(thr = Some(e))))
      }
    }

  override def jsonToXml[T](conversionFormat: ConversionFormat[T], source: Source[ByteString, _]): EitherT[Future, ConversionError, NodeSeq] =
    EitherT {
      Future {
        // Note that we use 20 seconds here as this is the standard timeout on the service, if we're taking
        // longer than 20 seconds the request will have failed anyway.
        //
        // It's important to note that 20 seconds is the upper limit, and not a target
        val stream = source.runWith(StreamConverters.asInputStream(conversionTimeout))
        Try(Json.parse(stream).as(conversionFormat.jsonReads)).toEither
          .leftMap {
            case x: JsonParseException   => ConversionError.JsonParsingError(x)
            case x: JsonMappingException => ConversionError.JsonParsingError(x)
            case x: JsResultException    => ConversionError.JsonParsingError(x)
            case NonFatal(thr)           => ConversionError.UnexpectedError(thr = Some(thr))
          }
          .map {
            scalaxb
              .toXML(
                _,
                Some(namespace.uri),
                conversionFormat.xmlRoot,
                namespace
              )(conversionFormat.xmlFormat)
          }
      }.recoverWith {
        case NonFatal(e) =>
          logger.error(s"Json to Xml conversion got failed: $e", e)
          Future.successful(Left(ConversionError.UnexpectedError(thr = Some(e))))
      }
    }

}

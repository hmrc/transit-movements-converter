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

package uk.gov.hmrc.transitmovementsconverter.controllers.service

import akka.stream.Materializer
import akka.stream.scaladsl.{Sink, Source, StreamConverters}
import akka.util.ByteString
import com.google.inject.ImplementedBy
import com.google.inject.Inject
import akka.stream.alpakka.xml.scaladsl.XmlParsing
import com.lucidchart.open.xtract.{DefaultXmlReaders, ParseFailure, ParseResult, ParseSuccess, XmlReader}
import org.xml.sax.InputSource
import play.api.libs.json.{JsString, JsValue, Json}
import uk.gov.hmrc.transitmovementsconverter.controllers.models.{CC015C, Consignment, ConsignmentItem, HouseConsignment, Packaging}

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.xml.XML

@ImplementedBy(classOf[XmlToJsonServiceImpl])
trait XmlToJsonService {
  def convert(source: Source[ByteString, _]): Future[Seq[JsValue]]
}

class XmlToJsonServiceImpl @Inject() (implicit materializer: Materializer, ec: ExecutionContext) extends XmlToJsonService {

  override def convert(source: Source[ByteString, _]): Future[Seq[JsValue]] = {

    val xmlInput = source.runWith(StreamConverters.asInputStream(20.seconds))

    val inputSource = new InputSource(xmlInput)

    Source(XML.load(inputSource))
      .map(elem => XmlReader.of[CC015C].read(elem))
      .statefulMapConcat(() =>
        parseEvent =>
          parseEvent match {
          case a: ParseSuccess[CC015C] => Seq(Json.toJson(a.get))
          case error: ParseFailure => Seq(Json.toJson(error.errors.map(e => e.toString)))
      }
      )
      .runWith(Sink.seq)
  }

}

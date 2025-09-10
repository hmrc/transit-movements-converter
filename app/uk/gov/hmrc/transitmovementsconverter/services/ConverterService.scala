/*
 * Copyright 2025 HM Revenue & Customs
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
import com.google.inject.ImplementedBy
import org.apache.pekko.stream.scaladsl.Source
import org.apache.pekko.util.ByteString
import play.api.libs.json.JsValue
import uk.gov.hmrc.transitmovementsconverter.models.ConversionFormat
import uk.gov.hmrc.transitmovementsconverter.models.errors.ConversionError
import uk.gov.hmrc.transitmovementsconverter.v2_1.services.ConverterServiceImpl

import scala.concurrent.Future
import scala.xml.NamespaceBinding
import scala.xml.NodeSeq
import scala.xml.TopScope

trait ConverterService {
  def xmlToJson[T](conversionFormat: ConversionFormat[T], source: Source[ByteString, ?]): EitherT[Future, ConversionError, JsValue]
  def jsonToXml[T](conversionFormat: ConversionFormat[T], source: Source[ByteString, ?]): EitherT[Future, ConversionError, NodeSeq]
}

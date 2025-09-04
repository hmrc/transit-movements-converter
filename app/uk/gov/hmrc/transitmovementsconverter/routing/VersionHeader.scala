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

package uk.gov.hmrc.transitmovementsconverter.routing

import cats.implicits.catsSyntaxEitherId

sealed trait VersionHeader {
  val value: String
}

object VersionHeader {
  def apply(value: String): Either[VersionHeaderError, VersionHeader] = value match {
    case VERSION_HEADER_2_1.value => VERSION_HEADER_2_1.asRight
    case VERSION_HEADER_3_0.value => VERSION_HEADER_3_0.asRight
    case invalidAcceptHeader      => VersionHeaderError.notAcceptableError(s"Invalid accept Header: $invalidAcceptHeader").asLeft
  }
}

case object VERSION_HEADER_2_1 extends VersionHeader {
  override val value: String = "APIVersion:2.1"
}

case object VERSION_HEADER_3_0 extends VersionHeader {
  override val value: String = "APIVersion:3.0"
}

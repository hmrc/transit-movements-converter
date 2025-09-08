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

sealed trait APIVersionHeader {
  val value: String
}

object APIVersionHeader {
  def apply(value: String): Either[ApiVersionHeaderError, APIVersionHeader] = value match {
    case API_VERSION_2_1.value => API_VERSION_2_1.asRight
    case API_VERSION_3_0.value => API_VERSION_3_0.asRight
    case invalidAcceptHeader   => ApiVersionHeaderError.notAcceptableError(s"Invalid APIVersion Header: $invalidAcceptHeader").asLeft
  }
}

case object API_VERSION_2_1 extends APIVersionHeader {
  override val value: String = "2.1"
}

case object API_VERSION_3_0 extends APIVersionHeader {
  override val value: String = "3.0"
}

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

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem
import uk.gov.hmrc.transitmovementsconverter.routing.APIVersionHeader
import uk.gov.hmrc.transitmovementsconverter.v2_1.services.ConverterServiceImpl as Version2_1ConverterService
import uk.gov.hmrc.transitmovementsconverter.v3_0.services.ConverterServiceImpl as Version3_0ConverterService

class ConverterServiceFactorySpec
    extends AnyFreeSpec
    with Matchers
    with ScalaFutures
    with ScalaCheckDrivenPropertyChecks
    with MockitoSugar
    with TestActorSystem {

  val converterV2_1: Version2_1ConverterService = mock[Version2_1ConverterService]
  val converterV3_0: Version3_0ConverterService = mock[Version3_0ConverterService]

  val factory = new ConverterServiceFactory(
    converterV2_1,
    converterV3_0
  )

  "ConverterServiceFactory.getConverterService" - {
    "return Version2_1ConverterService when for APIVersion 2.1" in {
      val apiVersion = APIVersionHeader.API_VERSION_2_1

      val result = factory.getConverterService(apiVersion)

      result mustBe converterV2_1
    }

    "return Version3_0ConverterService when for APIVersion 2.1" in {

      val apiVersion = APIVersionHeader.API_VERSION_3_0

      val result = factory.getConverterService(apiVersion)

      result mustBe converterV3_0

    }
  }

}

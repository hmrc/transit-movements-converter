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

package uk.gov.hmrc.transitmovementsconverter.controllers.actions

import javax.inject._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import play.api.mvc._

@Singleton
class NoAuthAction @Inject() (
  val parser: BodyParsers.Default
)(implicit val executionContext: ExecutionContext)
    extends ActionBuilder[AuthenticatedRequest, AnyContent] {

  override def invokeBlock[A](
    request: Request[A],
    block: AuthenticatedRequest[A] => Future[Result]
  ): Future[Result] =
    block(AuthenticatedRequest(request))
}

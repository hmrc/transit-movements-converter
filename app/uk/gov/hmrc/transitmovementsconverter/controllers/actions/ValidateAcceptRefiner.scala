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

import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.Sink
import org.apache.pekko.stream.scaladsl.Source
import play.api.libs.json.Json
import play.api.mvc.*
import play.api.mvc.Results.Status
import uk.gov.hmrc.transitmovementsconverter.models.APIVersionHeader
import uk.gov.hmrc.transitmovementsconverter.models.errors.PresentationError

import javax.inject.Inject
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

final case class ValidatedVersionedRequest[T](
  versionHeader: APIVersionHeader,
  request: Request[T]
) extends WrappedRequest[T](request)

final class ValidateAcceptRefiner @Inject() (cc: ControllerComponents)(implicit val ec: ExecutionContext, mat: Materializer)
    extends ActionRefiner[Request, ValidatedVersionedRequest]
    with ActionBuilder[ValidatedVersionedRequest, AnyContent] {

  private def validateAcceptHeader(request: Request[?]): Either[PresentationError, APIVersionHeader] =
    for {
      acceptHeaderValue <-
        request.headers
          .get("APIVersion")
          .toRight(PresentationError.notAcceptableError("An Accept header is missing."))
      version <-
        APIVersionHeader
          .fromString(acceptHeaderValue)
          .toRight(PresentationError.unsupportedMediaTypeError(s"The Accept header $acceptHeaderValue is not supported."))
    } yield version

  def refine[T](request: Request[T]): Future[Either[Result, ValidatedVersionedRequest[T]]] =
    validateAcceptHeader(request) match {
      case Left(err) =>
        clearSource(request)
        Future.successful(Left(Status(err.code.statusCode)(Json.toJson(err))))
      case Right(versionHeader) =>
        Future.successful(Right(ValidatedVersionedRequest(versionHeader, request)))
    }

  private def clearSource(request: Request[?]): Unit =
    request.body match {
      case source: Source[_, _] => val _ = source.runWith(Sink.ignore)
      case _                    => ()
    }

  override protected def executionContext: ExecutionContext = ec

  override def parser: BodyParser[AnyContent] = cc.parsers.defaultBodyParser
}

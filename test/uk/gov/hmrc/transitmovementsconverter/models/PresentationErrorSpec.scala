package uk.gov.hmrc.transitmovementsconverter.models

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.mockito.MockitoSugar
import play.api.libs.json.Json
import uk.gov.hmrc.http.UpstreamErrorResponse
import uk.gov.hmrc.transitmovementsconverter.models.errors.PresentationError
import uk.gov.hmrc.transitmovementsconverter.models.errors.UpstreamServiceError

class PresentationErrorSpec extends AnyFreeSpec with Matchers with MockitoSugar {

  "Test Json is as expected" - {
    def testStandard(function: String => PresentationError, message: String, code: String) = {
      val sut    = function(message)
      val result = Json.toJson(sut)

      result mustBe Json.obj("message" -> message, "code" -> code)
    }

    "for UnsupportedMediaType" in testStandard(PresentationError.unsupportedMediaTypeError, "unsupported media type", "UNSUPPORTED_MEDIA_TYPE")

    "for BadRequest" in testStandard(PresentationError.badRequestError, "bad request", "BAD_REQUEST")

    Seq(Some(new IllegalStateException("message")), None).foreach {
      exception =>
        val textFragment = exception
          .map(
            _ => "contains"
          )
          .getOrElse("does not contain")
        s"for an unexpected error that $textFragment a Throwable" in {
          // Given this exception
          val exception = new IllegalStateException("message")

          // when we create a error for this
          val sut = PresentationError.internalServiceError(cause = Some(exception))

          // and when we turn it to Json
          val json = Json.toJson(sut)

          // then we should get an expected output
          json mustBe Json.obj("code" -> "INTERNAL_SERVER_ERROR", "message" -> "Internal server error")
        }
    }

    "for an upstream error" in {
      // Given this upstream error
      val upstreamErrorResponse = UpstreamErrorResponse("error", 500)

      // when we create a error for this
      val sut = UpstreamServiceError.causedBy(upstreamErrorResponse)

      // and when we turn it to Json
      val json = Json.toJson(sut)

      // then we should get an expected output
      json mustBe Json.obj("code" -> "INTERNAL_SERVER_ERROR", "message" -> "Internal server error")
    }
  }

}
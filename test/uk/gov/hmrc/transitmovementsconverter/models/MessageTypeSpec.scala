package uk.gov.hmrc.transitmovementsconverter.models

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import uk.gov.hmrc.transitmovementsconverter.base.StreamTestHelpers
import uk.gov.hmrc.transitmovementsconverter.base.TestActorSystem

class MessageTypeSpec extends AnyFreeSpec with ScalaFutures with Matchers with TestActorSystem with StreamTestHelpers {

  "froName" - {

    MessageType.values.foreach {
      messageType =>
        s"for a valid value, test we can get ${messageType.name}" in {
          MessageType.fromName(messageType.name) mustBe Some(messageType)
        }
    }

    "for an invalid value, test we get None back" in {
      MessageType.fromName("asdfghjh") mustBe None
    }
  }

}

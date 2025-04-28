import play.core.PlayVersion
import play.core.PlayVersion.current
import play.sbt.PlayImport._
import sbt.Keys.libraryDependencies
import sbt._

object AppDependencies {

  val catsVersion      = "2.12.0"
  val bootstrapVersion = "9.11.0"

  val compile = Seq(
    "uk.gov.hmrc"      %% "bootstrap-backend-play-30" % bootstrapVersion,
    "org.apache.pekko" %% "pekko-connectors-xml"      % "1.0.2",
    "org.apache.pekko" %% "pekko-stream"              % "1.1.2",
    "org.typelevel"    %% "cats-core"                 % catsVersion,
    // required for scalaxb
    "org.scala-lang.modules" %% "scala-parser-combinators" % "2.4.0",
    "javax.xml.bind"          % "jaxb-api"                 % "2.3.1",
    "org.apache.pekko" %% "pekko-protobuf-v3" % "1.1.2",
    "org.apache.pekko" %% "pekko-serialization-jackson" % "1.1.2",
    "org.apache.pekko" %% "pekko-stream" % "1.1.2",
    "org.apache.pekko" %% "pekko-actor-typed" % "1.1.2",
  )

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"       %% "bootstrap-test-play-30" % bootstrapVersion,
    "org.scalatestplus" %% "scalacheck-1-18" % "3.2.19.0",
    "org.mockito"          % "mockito-core"           % "5.15.2"
  ).map(_ % Test)
}

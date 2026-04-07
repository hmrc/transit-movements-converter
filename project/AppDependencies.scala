import play.sbt.PlayImport.*
import sbt.*

object AppDependencies {

  val catsVersion      = "2.13.0"
  val bootstrapVersion = "10.7.0"
  val pekkoVersion = "1.4.0"

  val compile = Seq(
    "uk.gov.hmrc"      %% "bootstrap-backend-play-30" % bootstrapVersion,
    "org.apache.pekko" %% "pekko-connectors-xml"      % "1.3.0",
    "org.apache.pekko" %% "pekko-stream"              % pekkoVersion,
    "org.typelevel"    %% "cats-core"                 % catsVersion,
    // required for scalaxb
    "org.scala-lang.modules" %% "scala-parser-combinators" % "2.4.0",
    "javax.xml.bind"          % "jaxb-api"                 % "2.3.1",
    "org.apache.pekko" %% "pekko-protobuf-v3" % pekkoVersion,
    "org.apache.pekko" %% "pekko-serialization-jackson" % pekkoVersion,
    "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
  )

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"       %% "bootstrap-test-play-30" % bootstrapVersion,
    "org.scalatestplus" %% "scalacheck-1-18" % "3.2.19.0",
    "org.mockito"          % "mockito-core"           % "5.23.0"
  ).map(_ % Test)
}

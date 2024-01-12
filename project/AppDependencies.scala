import play.core.PlayVersion
import play.core.PlayVersion.current
import play.sbt.PlayImport._
import sbt.Keys.libraryDependencies
import sbt._

object AppDependencies {

  val catsVersion      = "2.9.0"
  val bootstrapVersion = "8.4.0"

  val compile = Seq(
    "uk.gov.hmrc"      %% "bootstrap-backend-play-30" % bootstrapVersion,
    "org.apache.pekko" %% "pekko-connectors-xml"      % "1.0.1",
    "org.apache.pekko" %% "pekko-stream"              % "1.0.1",
    "org.typelevel"    %% "cats-core"                 % catsVersion,
    // required for scalaxb
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    "javax.xml.bind"          % "jaxb-api"                 % "2.3.1"
  )

  val test = Seq(
    "uk.gov.hmrc"         %% "bootstrap-test-play-30" % bootstrapVersion,
    "com.typesafe.play"   %% "play-test"              % "2.9.1",
    "com.vladsch.flexmark" % "flexmark-all"           % "0.36.8",
    "org.scalatestplus"   %% "scalacheck-1-15"        % "3.2.2.0",
    "org.scalatestplus"   %% "mockito-3-2"            % "3.1.2.0"
  ).map(_ % Test)
}

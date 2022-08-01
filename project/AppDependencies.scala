import play.core.PlayVersion
import play.core.PlayVersion.current
import play.sbt.PlayImport._
import sbt.Keys.libraryDependencies
import sbt._

object AppDependencies {

  val AkkaVersion = "2.6.19"
  val catsVersion = "2.7.0"
  val dispatchV = "1.2.0"
  val bootstrapVersion = "6.4.0"

  val compile = Seq(
    "uk.gov.hmrc"            %% "bootstrap-backend-play-28" % bootstrapVersion,
    "com.lightbend.akka"     %% "akka-stream-alpakka-xml"   % "3.0.4",
    "com.typesafe.akka"      %% "akka-stream"               % AkkaVersion,
    "org.typelevel"          %% "cats-core"                 % catsVersion,

    // required for scalaxb
    "org.scala-lang.modules" %% "scala-xml"                % "1.3.0",
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    "javax.xml.bind"          % "jaxb-api"                 % "2.3.1",
    "org.dispatchhttp"       %% "dispatch-core"            % dispatchV
  )

  val test = Seq(
    "uk.gov.hmrc"         %% "bootstrap-test-play-28" % bootstrapVersion,
    "com.typesafe.play"   %% "play-test"              % current,
    "com.vladsch.flexmark" % "flexmark-all"           % "0.36.8",
    "org.scalatestplus"   %% "scalacheck-1-15"        % "3.2.2.0",
    "org.scalatestplus"   %% "mockito-3-2"            % "3.1.2.0"
  ).map(_ % "test, it")
}

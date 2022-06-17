import play.core.PlayVersion
import play.sbt.PlayImport._
import sbt.Keys.libraryDependencies
import sbt._

object AppDependencies {

  val AkkaVersion = "2.6.14"

  val compile = Seq(
    "uk.gov.hmrc"            %% "bootstrap-backend-play-28" % "5.24.0",
    "com.lucidchart"         %% "xtract"                    % "2.0.0",
    "com.lightbend.akka"     %% "akka-stream-alpakka-xml"   % "3.0.4",
    "org.scala-lang.modules" %% "scala-xml"                 % "1.1.0",
    "com.typesafe.akka"      %% "akka-stream"               % AkkaVersion
  )

  val test = Seq(
    "uk.gov.hmrc"         %% "bootstrap-test-play-28" % "5.24.0" % "test, it",
    "com.vladsch.flexmark" % "flexmark-all"           % "0.36.8" % "test, it"
  )
}

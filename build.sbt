import play.sbt.routes.RoutesKeys

import uk.gov.hmrc.DefaultBuildSettings
import sbtscalaxb.ScalaxbPlugin.*

val appName = "transit-movements-converter"

ThisBuild / majorVersion := 0
ThisBuild / scalaVersion := "3.4.3"

Compile / scalacOptions ++= Seq(
  "-nowarn",
  "-rewrite",
  "-source:3.4-migration"
)

lazy val V2_1 = config("v2_1") extend Compile
lazy val V3_0 = config("v3_0") extend Compile

def customScalaxbSettingsFor(base: String): Seq[Def.Setting[?]] = Seq(
  sourceManaged          := (Compile / sourceManaged).value,
  scalaxbXsdSource       := new File(s"./conf/xsd/$base"),
  scalaxbDispatchVersion := "1.1.3",
  scalaxbPackageName     := s"generated.$base"
)


def customScalaxbSettingsForV2_1: Seq[Def.Setting[?]] =
  inConfig(V2_1)(baseScalaxbSettings ++ inTask(scalaxb)(customScalaxbSettingsFor("v2_1"))) ++
    Seq(
      Compile / sourceGenerators += (V2_1 / scalaxb).taskValue
    )

def customScalaxbSettingsForV3_0: Seq[Def.Setting[?]] =
  inConfig(V3_0)(baseScalaxbSettings ++ inTask(scalaxb)(customScalaxbSettingsFor("v3_0"))) ++
    Seq(
      Compile / sourceGenerators += (V3_0 / scalaxb).taskValue
    )

lazy val microservice = Project(appName, file("."))
  .enablePlugins(PlayScala, SbtDistributablesPlugin, ScalaxbPlugin)
  .settings(
    PlayKeys.playDefaultPort := 9475,
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test,
    RoutesKeys.routesImport ++= Seq(
      "uk.gov.hmrc.transitmovementsconverter.models._",
      "uk.gov.hmrc.transitmovementsconverter.models.Binders._"
    )
  )
  .settings(customScalaxbSettingsForV2_1*)
  .settings(customScalaxbSettingsForV3_0*)
  .settings(CodeCoverageSettings.settings*)
  .settings(inThisBuild(buildSettings))

lazy val it = project
  .enablePlugins(PlayScala)
  .dependsOn(microservice % "test->test") // the "test->test" allows reusing test code and test dependencies
  .settings(DefaultBuildSettings.itSettings())
  .settings(libraryDependencies ++= AppDependencies.test)

// Settings for the whole build
lazy val buildSettings = Def.settings(
  scalafmtOnCompile := true
)

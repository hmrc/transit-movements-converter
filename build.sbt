import play.sbt.routes.RoutesKeys
import uk.gov.hmrc.DefaultBuildSettings
import sbtscalaxb.ScalaxbPlugin.*

val appName = "transit-movements-converter"

ThisBuild / majorVersion := 0
ThisBuild / scalaVersion := "3.4.3"

scalacOptions ++= Seq("-rewrite", "-source:3.4-migration")

lazy val Transitional = config("transitional") extend(Compile)
lazy val V2_1 = config("v2_1") extend(Compile)

def customScalaxbSettingsFor(base: String): Seq[Def.Setting[_]] = Seq(
  sourceManaged := (Compile / sourceManaged).value,
  scalaxbXsdSource := new File(s"./conf/xsd/$base"),
  scalaxbDispatchVersion := "1.1.3",
  scalaxbPackageName := s"generated.$base"
)

def customScalaxbSettings: Seq[Def.Setting[_]] =
  inConfig(Transitional)(baseScalaxbSettings ++ inTask(scalaxb)(customScalaxbSettingsFor("transitional"))) ++
    inConfig(V2_1)(baseScalaxbSettings ++ inTask(scalaxb)(customScalaxbSettingsFor("v2_1"))) ++
    Seq(
      Compile / sourceGenerators += (Transitional / scalaxb).taskValue,
      Compile / sourceGenerators += (V2_1 / scalaxb).taskValue
    )

lazy val microservice = Project(appName, file("."))
  .enablePlugins(PlayScala, SbtDistributablesPlugin, ScalaxbPlugin)
  .settings(
    PlayKeys.playDefaultPort := 9475,
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test,
    scalacOptions += "-Wconf:src=routes/.*:s"
  )
  .settings(customScalaxbSettings: _*)
  .settings(resolvers += Resolver.jcenterRepo)
  .settings(CodeCoverageSettings.settings: _*)
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

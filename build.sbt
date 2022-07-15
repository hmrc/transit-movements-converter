import play.sbt.routes.RoutesKeys
import uk.gov.hmrc.DefaultBuildSettings.integrationTestSettings
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin.publishingSettings

val appName = "transit-movements-converter"

val silencerVersion = "1.7.7"

lazy val microservice = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtDistributablesPlugin, ScalaxbPlugin)
  .settings(
    majorVersion                     := 0,
    scalaVersion                     := "2.12.15",
    PlayKeys.playDefaultPort         := 9475,
    libraryDependencies              ++= AppDependencies.compile ++ AppDependencies.test,
    // ***************
    // Use the silencer plugin to suppress warnings
    scalacOptions += "-P:silencer:pathFilters=routes;src_managed",
    libraryDependencies ++= Seq(
      compilerPlugin("com.github.ghik" % "silencer-plugin" % silencerVersion cross CrossVersion.full),
      "com.github.ghik" % "silencer-lib" % silencerVersion % Provided cross CrossVersion.full
    ),
    // ***************
    // scalaxb
    Compile / scalaxb / scalaxbDispatchVersion := AppDependencies.dispatchV,
    Compile / scalaxb / scalaxbPackageName := "generated",
    // Play routing
    RoutesKeys.routesImport ++= Seq(
      "uk.gov.hmrc.transitmovementsconverter.models._",
      "uk.gov.hmrc.transitmovementsconverter.models.Binders._"
    )
  )
  .settings(publishingSettings: _*)
  .configs(IntegrationTest)
  .settings(integrationTestSettings(): _*)
  .settings(resolvers += Resolver.jcenterRepo)
  .settings(CodeCoverageSettings.settings: _*)

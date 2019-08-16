name := "xlr8r-Lagom"

version := "0.1"

scalaVersion := "2.13.0"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `xlr8r` = (project in file("."))
  .aggregate(`lagom-api`, `lagom-impl`)

lazy val `lagom-api` = (project in file("lagom-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )
.dependsOn(common)

lazy val `lagom-impl` = (project in file("lagom-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`lagom-api`)
  .dependsOn(common)

lazy val common = (project in file("common"))
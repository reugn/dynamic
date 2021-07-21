name := "dynamic"
organization := "io.github.reugn"

scalaVersion := "2.12.14"
crossScalaVersions := Seq(scalaVersion.value, "2.13.6")

libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "com.softwaremill.quicklens" %% "quicklens" % "1.7.4",
    "org.scalatest" %% "scalatest" % "3.2.9" % Test
)

scalacOptions := Seq(
    "-target:jvm-1.8",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-encoding", "utf8",
)

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

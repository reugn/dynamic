name := "dynamic"
organization := "com.github.reugn"

scalaVersion := "2.12.8"
crossScalaVersions := Seq("2.11.12", scalaVersion.value)

libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "com.softwaremill.quicklens" %% "quicklens" % "1.4.12",
    "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

scalacOptions := Seq(
    "-target:jvm-1.8",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-encoding", "utf8",
)

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

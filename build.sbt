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

publishArtifact / test := false

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
description := "A Scala library that allows copying a case class using a dynamic property name."
homepage := Some(url("https://github.com/reugn/dynamic"))
scmInfo := Some(ScmInfo(url("https://github.com/reugn/dynamic"), "git@github.com:reugn/dynamic.git"))
developers := List(Developer("reugn", "reugn", "reugpro@gmail.com", url("https://github.com/reugn")))
publishMavenStyle := true

pomIncludeRepository := { _ => false }

publishTo := {
    val nexus = "https://s01.oss.sonatype.org/"
    if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
    else
        Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

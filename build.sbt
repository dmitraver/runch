scalaVersion := "2.12.1"

name := "runch-bot"
organization := "com.github.dmitraver"
version := "1.0"

parallelExecution in ThisBuild := false

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.6",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.6",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)


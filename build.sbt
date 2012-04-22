name := "puyo-scala-wrapper"

organization := "org.nisshiee"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.9.1"

libraryDependencies := Seq(
   "org.scalaz" %% "scalaz-core" % "6.0.4"
  ,"org.specs2" %% "specs2" % "1.9" % "test"
  ,"org.mockito" % "mockito-all" % "1.9.0" % "test"
  ,"junit" % "junit" % "4.10" % "test"
  ,"org.pegdown" % "pegdown" % "1.1.0" % "test"
)

testOptions in (Test, test) += Tests.Argument("console", "html", "junitxml")

initialCommands := """
import scalaz._
import Scalaz._
import Path._
"""

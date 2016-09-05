name := "scala-fp"

version := "1.0"

scalaVersion := "2.11.8"

isSnapshot := true

libraryDependencies += "org.specs2" %% "specs2" % "2.3.12"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

libraryDependencies += "org.typelevel" %% "cats" % "0.7.2"

initialCommands in console += "import cats._;import cats.instances.all._"


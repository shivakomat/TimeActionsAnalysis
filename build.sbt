name := "Glassdoor Assignment"

version := "0.1"

scalaVersion := "2.12.2"

val projectMainClass = "AppMain"

mainClass in (Compile, run) := Some(projectMainClass)
mainClass in assembly := Some(projectMainClass)


// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % Test

libraryDependencies += "joda-time" % "joda-time" % "2.10.6"

// https://mvnrepository.com/artifact/com.github.scopt/scopt
libraryDependencies += "com.github.scopt" %% "scopt" % "3.5.0"
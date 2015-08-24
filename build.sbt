import com.lihaoyi.workbench.Plugin._

enablePlugins(ScalaJSPlugin)

workbenchSettings

name := "React and scalajs"

version := "1.0"

scalaVersion := "2.11.7"

val scalaJSReactVersion = "0.8.2"

val scalaCssVersion = "0.2.0"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.8.1",
  "com.lihaoyi" %%% "utest" % "0.3.0" % "test",
  "be.doeraene" %%% "scalajs-jquery" % "0.8.0",
  "com.github.japgolly.scalajs-react" %%% "core" % scalaJSReactVersion,
  "com.github.japgolly.scalajs-react" %%% "extra" % scalaJSReactVersion,
  "com.github.japgolly.scalacss" %%% "core" % scalaCssVersion,
  "com.github.japgolly.scalacss" %%% "ext-react" % scalaCssVersion
)

jsDependencies ++= Seq(
  RuntimeDOM, // This allow to modify DOM during runtime
  "org.webjars" % "react" % "0.13.3" / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React"
)

scalaJSStage in Global := FastOptStage

// Put all dependencies into one bundle
skip in packageJSDependencies := false

// Include test framework
testFrameworks += new TestFramework("utest.runner.Framework")

// copy  javascript files to js folder,that are generated using fastOptJS/fullOptJS

crossTarget in (Compile, fullOptJS) := file("js")

crossTarget in (Compile, fastOptJS) := file("js")

crossTarget in (Compile, packageJSDependencies) := file("js")

crossTarget in (Compile, packageScalaJSLauncher) := file("js")

crossTarget in (Compile, packageMinifiedJSDependencies) := file("js")

artifactPath in (Compile, fastOptJS) := ((crossTarget in (Compile, fastOptJS)).value /
  ((moduleName in fastOptJS).value + "-opt.js"))



scalacOptions += "-feature"


ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"


bootSnippet := "reactAndScala.webapp.ReactAndScalaJS().main(document.getElementById('application');"

refreshBrowsers <<= refreshBrowsers.triggeredBy(fastOptJS in Compile)

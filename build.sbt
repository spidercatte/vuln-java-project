name := "SimpleSparkJackson"
version := "0.1"
scalaVersion := "2.12.10"

val sparkVersion = "3.2.0" // Or a newer compatible version

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.13.5",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.5",
  "org.scalatest" %% "scalatest" % "3.2.11" % "test"
)

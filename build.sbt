name := "SimpleSparkJackson"
version := "0.1"
scalaVersion := "2.12.10"

val sparkVersion = "3.2.0" // Or a newer compatible version

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.13",
  "org.scalatest" %% "scalatest" % "3.2.11" % "test" // Ensure this line is present
)

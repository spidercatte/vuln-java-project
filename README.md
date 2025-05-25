# Simple Spark Jackson Project

This is a simple Spark project that demonstrates how to use Jackson Mapper ASL (`org.codehaus.jackson:jackson-mapper-asl:1.9.13`) to parse JSON data within a Spark application. It also includes examples of organizing Scala code into packages and unit testing with ScalaTest.

## Project Structure

-   `src/main/scala/SimpleApp.scala`: Main application demonstrating Spark and `JsonProcessor` usage.
-   `src/main/scala/com/example/domain/Person.scala`: Defines the `Person` case class.
-   `src/main/scala/com/example/domain/Car.scala`: Defines the `Car` case class.
-   `src/main/scala/com/example/services/JsonProcessor.scala`: Defines the `JsonProcessor` class for parsing `Person` and `Car` JSON.
-   `src/test/scala/com/example/services/JsonProcessorSpec.scala`: Contains unit tests for `JsonProcessor`.
-   `data/sample.json`: Sample JSON data file (demonstrates line-delimited JSON objects).
-   `build.sbt`: sbt build configuration file.

## Prerequisites

-   Java Development Kit (JDK) 8 or later
-   sbt (Scala Build Tool)

## Building the Project

To build the project, run the following command in the project root directory:

```bash
sbt clean compile
```

## Running the Application

The `SimpleApp.scala` demonstrates:
1.  Parsing a list of JSON strings into `Person` objects using Spark's map operation and a basic Jackson `ObjectMapper`.
2.  Using the `JsonProcessor` (from `com.example.services`) to parse individual `Person` and `Car` JSON strings.
3.  Using the `JsonProcessor` within a Spark `map` operation to parse a list of `Car` JSON strings.

To run the Spark application, use the `sbt run` command:

```bash
sbt run
```
(This assumes `SimpleApp.scala` is in the default package or your `build.sbt` is configured to run it as the main class).

### Using the Sample JSON File

The `SimpleApp.scala` can be modified to read from `data/sample.json` for its Spark operations. (Refer to commented sections or adapt data loading as needed).

## Running Tests

Unit tests for `JsonProcessor` are written using ScalaTest. To run the tests:

```bash
sbt test
```

## Dependencies

-   Spark Core (`org.apache.spark:spark-core_2.12`)
-   Spark SQL (`org.apache.spark:spark-sql_2.12`)
-   Jackson Mapper ASL (`org.codehaus.jackson:jackson-mapper-asl:1.9.13`)
-   ScalaTest (`org.scalatest:scalatest_2.12`) for unit testing.
```

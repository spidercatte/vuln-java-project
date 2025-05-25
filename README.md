# Simple Spark Jackson Project

This is a simple Spark project that demonstrates how to use Jackson Mapper ASL to parse JSON data.

## Prerequisites

- Java Development Kit (JDK) 8 or later
- sbt (Scala Build Tool)

## Building the Project

To build the project, run the following command in the project root directory:

```bash
sbt clean compile
```

## Running the Application

To run the Spark application, use the `sbt run` command:

```bash
sbt run
```

This will execute the `SimpleApp` main class. The application currently uses an inline JSON list.

### Using the Sample JSON File

The `SimpleApp.scala` can be modified to read from `data/sample.json`. To do this, you would change the data loading part of the `SimpleApp.scala` from:

```scala
    // Sample data: List of JSON strings
    val jsonStrings = Seq(
      """{"name":"Alice","age":30}""",
      """{"name":"Bob","age":25}""",
      """{"name":"Charlie","age":35}"""
    )

    val data = spark.createDataset(jsonStrings)
```

to something like:

```scala
    val data = spark.read.textFile("data/sample.json")
```

And then you would re-run `sbt run`.

## Dependencies

- Spark Core
- Spark SQL
- Jackson Module Scala
```

// No package statement to keep it in the default package for sbt run

import org.apache.spark.sql.SparkSession
import com.fasterxml.jackson.databind.ObjectMapper // Updated
import com.fasterxml.jackson.module.scala.DefaultScalaModule // Added
import com.example.domain.{Person, Car} // Import domain classes
import com.example.services.JsonProcessor // Import service class

object SimpleApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("SimpleSparkJacksonApp")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    // --- Original SimpleApp functionality (parsing a list of JSON strings for Person) ---
    val jsonPersonStrings = Seq(
      """{"name":"Alice","age":30}""",
      """{"name":"Bob","age":25}""",
      """{"name":"Charlie","age":35}"""
    )

    val personData = spark.createDataset(jsonPersonStrings)
    val personMapper = new ObjectMapper().registerModule(DefaultScalaModule) // New way

    println("--- Original Spark App: Parsed Person objects from inline list ---")
    val peopleFromSparkList = personData.map(jsonString => {
      personMapper.readValue(jsonString, classOf[Person])
    })
    peopleFromSparkList.show()

    // --- Demonstration of JsonProcessor ---
    val carMapper = new ObjectMapper().registerModule(DefaultScalaModule) // New way
    val jsonProcessor = new JsonProcessor(personMapper, carMapper)

    val testPersonJson = """{"name":"Processor Test Person","age":55}"""
    val testCarJson = """{"make":"Processor Test Car","model":"SUV","year":2024}"""

    val parsedPerson = jsonProcessor.parsePerson(testPersonJson)
    val parsedCar = jsonProcessor.parseCar(testCarJson)

    println(s"--- JsonProcessor Demo ---")
    println(s"Parsed Person via JsonProcessor: $parsedPerson")
    println(s"Parsed Car via JsonProcessor: $parsedCar")

    // Example of using JsonProcessor with Spark (if desired)
    // This is similar to the original app logic but using the processor
    val carJsonStrings = Seq(
      """{"make":"Toyota","model":"Camry","year":2021}""",
      """{"make":"Honda","model":"Civic","year":2022}"""
    )
    val carData = spark.createDataset(carJsonStrings)

    println("--- JsonProcessor with Spark: Parsed Car objects ---")
    val carsFromSparkList = carData.map(jsonString => {
      jsonProcessor.parseCar(jsonString) // Using the carMapper via JsonProcessor
    })
    carsFromSparkList.show()

    spark.stop()
  }
}

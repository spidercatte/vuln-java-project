import org.apache.spark.sql.SparkSession
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

// Define the case class
case class Person(name: String, age: Int)

object SimpleApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("SimpleSparkJacksonApp")
      .master("local[*]") // Use local master for simplicity
      .getOrCreate()

    import spark.implicits._

    // Sample data: List of JSON strings
    val jsonStrings = Seq(
      """{"name":"Alice","age":30}""",
      """{"name":"Bob","age":25}""",
      """{"name":"Charlie","age":35}"""
    )

    val data = spark.createDataset(jsonStrings)

    // Create an ObjectMapper instance
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)

    // Parse JSON strings into Person objects
    val people = data.map(jsonString => {
      mapper.readValue(jsonString, classOf[Person])
    })

    println("Parsed Person objects:")
    people.show()

    spark.stop()
  }
}

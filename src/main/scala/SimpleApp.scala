import org.apache.spark.sql.SparkSession
import org.codehaus.jackson.map.ObjectMapper // Changed import

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

    // Create an ObjectMapper instance (org.codehaus.jackson.map.ObjectMapper)
    val mapper = new ObjectMapper()
    // DefaultScalaModule is not used with Jackson 1.x's jackson-mapper-asl

    val people = data.map(jsonString => {
      // Attempt to parse JSON string to Person case class
      // Jackson 1.x might have limitations with direct Scala case class mapping
      // compared to Jackson 2.x with jackson-module-scala.
      // This will deserialize to a Map if direct case class binding fails without further config.
      // For this example, we'll keep it as is and see Spark's behavior.
      // A more robust solution for Jackson 1.x with Scala might involve Java Beans or custom deserializers.
      mapper.readValue(jsonString, classOf[Person])
    })

    println("Parsed Person objects:")
    people.show()

    spark.stop()
  }
}

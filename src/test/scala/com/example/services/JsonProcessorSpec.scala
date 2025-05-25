package com.example.services

import org.scalatest.funsuite.AnyFunSuite
import org.codehaus.jackson.map.ObjectMapper
import com.example.domain.{Person, Car} // Corrected import for domain classes

class JsonProcessorSpec extends AnyFunSuite {

  val personMapper = new ObjectMapper()
  val carMapper = new ObjectMapper()
  val jsonProcessor = new JsonProcessor(personMapper, carMapper)

  test("JsonProcessor should parse a valid Person JSON string") {
    val personJson = """{"name":"Test Alice","age":42}"""
    val expectedPerson = Person("Test Alice", 42)
    val actualPerson = jsonProcessor.parsePerson(personJson)
    assert(actualPerson == expectedPerson)
  }

  test("JsonProcessor should parse a valid Car JSON string") {
    val carJson = """{"make":"TestMake","model":"TestModel","year":2023}"""
    val expectedCar = Car("TestMake", "TestModel", 2023)
    val actualCar = jsonProcessor.parseCar(carJson)
    assert(actualCar == expectedCar)
  }

  test("JsonProcessor should throw an exception for invalid Person JSON (e.g. wrong type for age)") {
    val invalidJson = """{"name":"Test Bob","age":"not-an-age"}"""
    assertThrows[Exception] { // More specific: org.codehaus.jackson.map.JsonMappingException
      jsonProcessor.parsePerson(invalidJson)
    }
  }

  test("JsonProcessor should handle missing fields in Car JSON according to Jackson 1.x defaults") {
    val incompleteCarJson = """{"make":"TestMakeMissingModel"}"""
    val parsedCar = jsonProcessor.parseCar(incompleteCarJson)
    assert(parsedCar.make == "TestMakeMissingModel")
    assert(parsedCar.model == null) // Default for Object types
    assert(parsedCar.year == 0)    // Default for Int
  }

  test("JsonProcessor should parse Person JSON with extra fields (Jackson 1.x default behavior)") {
    val personJsonWithExtra = """{"name":"Test Carol","age":30,"city":"TestCity"}"""
    val expectedPerson = Person("Test Carol", 30) // Extra field "city" should be ignored
    val actualPerson = jsonProcessor.parsePerson(personJsonWithExtra)
    assert(actualPerson == expectedPerson)
  }
}

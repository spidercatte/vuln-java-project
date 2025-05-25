package com.example.services

import org.scalatest.funsuite.AnyFunSuite
import com.fasterxml.jackson.databind.ObjectMapper // Updated
import com.fasterxml.jackson.module.scala.DefaultScalaModule // Added
import com.example.domain.{Person, Car}

class JsonProcessorSpec extends AnyFunSuite {

  val personMapper = new ObjectMapper().registerModule(DefaultScalaModule) // Updated
  val carMapper = new ObjectMapper().registerModule(DefaultScalaModule) // Updated
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

  test("JsonProcessor should throw JsonMappingException for invalid Person JSON (e.g. wrong type for age)") {
    val invalidJson = """{"name":"Test Bob","age":"not-an-age"}"""
    assertThrows[com.fasterxml.jackson.databind.JsonMappingException] {
      jsonProcessor.parsePerson(invalidJson)
    }
  }

  test("JsonProcessor should throw JsonMappingException for Car JSON with missing non-Option fields") {
    val incompleteCarJson = """{"make":"TestMakeMissingModel"}""" // model and year are missing
    assertThrows[com.fasterxml.jackson.databind.JsonMappingException] {
      jsonProcessor.parseCar(incompleteCarJson)
    }
  }

  test("JsonProcessor should parse Person JSON with extra fields (Jackson 2.x default behavior)") {
    val personJsonWithExtra = """{"name":"Test Carol","age":30,"city":"TestCity"}"""
    val expectedPerson = Person("Test Carol", 30) // Extra field "city" should be ignored by default
    val actualPerson = jsonProcessor.parsePerson(personJsonWithExtra)
    assert(actualPerson == expectedPerson)
  }
}

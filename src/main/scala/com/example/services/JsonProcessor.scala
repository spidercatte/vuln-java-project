package com.example.services

import com.example.domain.{Car, Person}
import com.fasterxml.jackson.databind.ObjectMapper // Updated import

class JsonProcessor(personMapper: ObjectMapper, carMapper: ObjectMapper) {

  def parsePerson(jsonString: String): Person = {
    personMapper.readValue(jsonString, classOf[Person])
  }

  def parseCar(jsonString: String): Car = {
    carMapper.readValue(jsonString, classOf[Car])
  }
}

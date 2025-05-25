package com.example.services

import com.example.domain.{Car, Person} // Import Person and Car from domain package
import org.codehaus.jackson.map.ObjectMapper

class JsonProcessor(personMapper: ObjectMapper, carMapper: ObjectMapper) {

  def parsePerson(jsonString: String): Person = {
    personMapper.readValue(jsonString, classOf[Person])
  }

  def parseCar(jsonString: String): Car = {
    carMapper.readValue(jsonString, classOf[Car])
  }
}

package com.dkv.omint.enviroment

import com.dkv.omint.enviroment.model.Data
import com.dkv.omint.enviroment.model.Scenario
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

val mapper = ObjectMapper()
private val config = File("src/test/resources/omintest/omnitest.json")

fun getEnviroment(): Map<String, Map<String, Any>> {
    return mapper.readValue(config, Data::class.java).environment
}

fun getScenarios(): List<Scenario> {
    return mapper.readValue(config, Data::class.java).scenarios
}
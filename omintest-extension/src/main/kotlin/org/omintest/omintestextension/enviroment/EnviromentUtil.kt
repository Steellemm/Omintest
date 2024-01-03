package org.omintest.omintestextension.enviroment

import com.fasterxml.jackson.databind.ObjectMapper
import org.omintest.omintestextension.enviroment.model.Data
import org.omintest.omintestextension.enviroment.model.Scenario
import java.io.File

val mapper = ObjectMapper()
private val root = "src/test/resources/omintest/"
private val config = File("src/test/resources/omintest/omnitest.json")

fun getEnvironment(file: String): Map<String, Map<String, Any>> {
    return mapper.readValue(File(root + file), Data::class.java).environment
}

fun getScenarios(file: String): List<Scenario> {
    return mapper.readValue(File(root + file), Data::class.java).scenarios
}
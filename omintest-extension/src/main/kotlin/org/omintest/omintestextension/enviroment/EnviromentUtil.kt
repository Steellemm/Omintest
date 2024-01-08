package org.omintest.omintestextension.enviroment

import com.fasterxml.jackson.databind.ObjectMapper
import org.omintest.api.model.DataBaseSetInfo
import org.omintest.api.model.SpringBootServiceInfo
import org.omintest.omintestextension.enviroment.model.Data
import org.omintest.omintestextension.enviroment.model.Scenario
import java.io.File

val mapper = ObjectMapper()
private val root = "src/test/resources/omintest/"
private val config = File("src/test/resources/omintest/omnitest.json")

fun getEnvironment(file: String): Map<String, Map<String, Any>> {
    return mapper.readValue(File(root + file), Data::class.java).environment
}

fun getSpringBootServiceInfo(environment: Map<String, Map<String, Any>>): SpringBootServiceInfo {
    return SpringBootServiceInfo(
        set = (environment["service"] as Map<String, Map<String, Any>>)["set"] as Map<String, String>,
        dbs =
        ((environment["service"] as Map<String, Map<String, Any>>)["database"] as List<Map<String, String>>).associate {
            it["field-name"]!! to
                    DataBaseSetInfo(
                        urlPath = it["url-path"]!!,
                        userPath = it["user-path"]!!,
                        passwordPath = it["password-path"]!!
                    )
        }
    )
}

fun getScenarios(file: String): List<Scenario> {
    return mapper.readValue(File(root + file), Data::class.java).scenarios
}
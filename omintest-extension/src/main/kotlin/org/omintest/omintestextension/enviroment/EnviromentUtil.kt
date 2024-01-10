package org.omintest.omintestextension.enviroment

import com.fasterxml.jackson.databind.ObjectMapper
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepField
import org.omintest.api.model.DataBaseSetInfo
import org.omintest.api.model.SpringBootServiceInfo
import org.omintest.omintestextension.enviroment.model.Data
import org.omintest.omintestextension.enviroment.model.Scenario
import org.reflections.Reflections
import java.io.File

val mapper = ObjectMapper()
private val root = "src/test/resources/omintest/"

fun getEnvironment(file: String): Map<String, Map<String, Any>> {
    return mapper.readValue(File(root + file), Data::class.java).environment
}

fun getSpringBootServiceInfo(environment: Map<String, Any>): SpringBootServiceInfo {
    return SpringBootServiceInfo(
        set = getSet(environment),
        dbs = getDbs(environment)
    )
}

private fun getDbs(environment: Map<String, Any>): Map<String, DataBaseSetInfo> =
    if ((environment["database"]) == null) emptyMap() else {
        (environment["database"] as List<Map<String, String>>).associate {
            it["field-name"]!! to
                    DataBaseSetInfo(
                        urlPath = it["url-path"]!!,
                        userPath = it["user-path"]!!,
                        passwordPath = it["password-path"]!!
                    )
        }
    }

private fun getSet(environment: Map<String, Any>): Map<String, String> = getSetMapFromList(
    environment.getOrDefault(
        "set",
        emptyList<Any>()
    ) as List<Map<String, String>>
)

private fun getSetMapFromList(list: List<Map<String, String>>): Map<String, String> {
    return list.associate { it["key"]!! to it["values"]!! }
}

fun getScenarios(file: String): List<Scenario> {
    return mapper.readValue(File(root + file), Data::class.java).scenarios
}

val stepsConstructors = run {
    val reflections = Reflections("org")
    val classes: Set<Class<out SpringExtensionStep>> = reflections.getSubTypesOf(SpringExtensionStep::class.java)
    classes.associate { clazz ->
        clazz.getDeclaredConstructor(Map::class.java).newInstance(emptyMap<String, StepField>())
            .id() to clazz.getDeclaredConstructor(Map::class.java)
    }
}

val StepFieldsConstructors = run {
    val reflections = Reflections("org")
    val classes: Set<Class<out StepField>> = reflections.getSubTypesOf(StepField::class.java)
    classes.associate { it.simpleName to it.getDeclaredConstructor(Any::class.java)  }
}
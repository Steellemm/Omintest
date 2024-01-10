package org.omintest.omintestextension.step.stepfields

import org.omintest.api.ScenarioContext
import org.omintest.api.StepField

class StringField(val value: Any) : StepField {
    override fun getValue(scenarioContext: ScenarioContext): Any {
        return value as String
    }
}

class AssertMapField(val value: Any) : StepField {
    override fun getValue(scenarioContext: ScenarioContext): Any {
        val map = value as List<Map<String, Any>>
        val results = mutableMapOf<String, String>()
        for (item in map) {
            results[getFromContext(item["actual"]!!.toString(), scenarioContext)] = getFromContext(item["expected"]!!.toString(), scenarioContext)
        }
        return results
    }
}

class LinkedField(val value: Any) : StepField {
    override fun getValue(scenarioContext: ScenarioContext): Any {
        return getFromContext(value.toString(), scenarioContext)
    }
}

class ObjectField(val value: Any) : StepField {
    override fun getValue(scenarioContext: ScenarioContext): Any {
        return scenarioContext.getStepContext(value.toString())
    }
}

class MapField(val value: Any): StepField{
    override fun getValue(scenarioContext: ScenarioContext): Any {
        val map = value as List<Map<String, Any>>
        val results = mutableMapOf<String, String>()
        for (item in map) {
            results[getFromContext(item["key"]!!.toString(), scenarioContext)] = getFromContext(item["value"]!!.toString(), scenarioContext)
        }
        return results
    }
}


fun getFromContext(link: String, scenarioContext: ScenarioContext): String {
    var result = link
    val variables =
        "\\$\\{([\\w:.]+)}".toRegex().findAll(link).map { it.groupValues[1] }.toList()
    variables.forEach { //it s1:ip.some.some
        val id = it.split(".").first()
        val keys = it.split(".").toMutableList().apply { removeFirst() }.joinToString(".")
        result = link.replace(
            "\${$it}",
            scenarioContext.getStepContext(id).getValue(keys).toString()
        )
    }
    return result
}
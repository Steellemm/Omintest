package org.omintest.step.fields

import org.omintest.api.ScenarioContext
import org.omintest.api.StepField
import org.omintest.step.getFromContext

class StringField(private val value: Any) : StepField {
    override fun getValue(scenarioContext: ScenarioContext): Any {
        return value as String
    }
}

class AssertMapField(private val value: Any) : StepField {
    override fun getValue(scenarioContext: ScenarioContext): Any {
        val map = value as List<Map<String, Any>>
        val results = mutableMapOf<String, String>()
        for (item in map) {
            results[getFromContext(item["actual"]!!.toString(), scenarioContext)] = getFromContext(item["expected"]!!.toString(), scenarioContext)
        }
        return results
    }
}

class LinkedField(private val value: Any) : StepField {
    override fun getValue(scenarioContext: ScenarioContext): Any {
        return getFromContext(value.toString(), scenarioContext)
    }
}

class MapField(private val value: Any): StepField {
    override fun getValue(scenarioContext: ScenarioContext): Any {
        val map = value as List<Map<String, Any>>
        val results = mutableMapOf<String, String>()
        for (item in map) {
            results[getFromContext(item["key"]!!.toString(), scenarioContext)] = getFromContext(item["value"]!!.toString(), scenarioContext)
        }
        return results
    }
}
package org.omintest.omintestextension.logic

import org.omintest.omintestextension.enviroment.model.StepData
import org.springframework.context.ApplicationContext
import org.springframework.test.context.TestContext

class OmintTestTestContext {
    var testContext: TestContext? = null
    var enviroment: Map<String, Map<String, Any>> = emptyMap()
    var applicationContext: ApplicationContext? = null
    val results: MutableMap<String, Map<String, Any>> = mutableMapOf()

    fun get(stepData: StepData): Any {
        try {
        return when (stepData.type) {
            "assertMap" -> getAssertMap(stepData.value as List<Map<String, String>>)
            "map" -> getMap(stepData.value as List<Map<String, String>>) as Map<String, String>
            "link" -> getFromContext(stepData.value as String)
            else -> stepData.value
        }}
        catch (ex: Exception){
            throw ex
        }
    }

    private fun getFromContext(link: String): String {
        val variables = "\\$\\{([\\w:.]+)}".toRegex().findAll(link).map { it.groupValues[1] }.toList()
        var result = link
        for (v in variables) {
            result = if (v.split(".").first().contains(":")) {
                result.replace("\${$v}", getValue(results, v.split(".")))
            } else {
                result.replace("\${$v}", getValue(enviroment, v.split(".")))
            }
        }
        return result
    }

    private fun getValue(map: Map<String, Any>, keys: List<String>): String {
        return if (keys.size == 1) {
            map[keys[0]].toString()
        } else {
            val key = keys[0]
            val remainingKeys = keys.subList(1, keys.size)
            val nestedMap = map[key] as? Map<String, Any>
            if (nestedMap != null) {
                getValue(nestedMap, remainingKeys)
            } else {
                throw Exception("$key is not in context")
            }
        }
    }

    private fun getMap(map: List<Map<String, Any>>): Map<String, String> {
        val result = mutableMapOf<String, String>()
        for (item in map){
            result[getFromContext(item["key"]!!.toString())] = getFromContext(item["value"]!!.toString())
        }
        return result
    }

    private fun getAssertMap(map: List<Map<String, Any>>): Map<String, String>{
        val result = mutableMapOf<String, String>()
        for (item in map){
            result[getFromContext(item["actual"]!!.toString())] = getFromContext(item["expected"]!!.toString())
        }
        return result
    }
}

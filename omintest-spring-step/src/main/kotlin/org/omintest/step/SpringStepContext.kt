package org.omintest.step

import org.omintest.api.StepContext

open class SpringStepContext(
    private val values: Map<String, Any>
): StepContext {
    override fun getValue(key: String): Any {
        return getValue(values, key.split("."))
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


}
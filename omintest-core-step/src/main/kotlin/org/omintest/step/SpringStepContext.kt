package org.omintest.step

import org.omintest.api.StepContext

open class SpringStepContext(
    private val values: Map<String, Any?>
): StepContext {
    override fun getValue(key: String): Any? {
        return getValue(values, key.toKeysArray())
    }

    fun getValue(obj: Any, keys: List<String>): Any {
        var current = obj
        for (key in keys) {
            val index = key.toIntOrNull() ?: key
            current = if (current is Map<*, *>) {
                current[index]!!
            } else if ((current is List<*>) && (index is Int)) {
                current.getOrNull(index)!!
            } else {
                throw IllegalStateException("getValue exception")
            }
        }
        return current
    }
}
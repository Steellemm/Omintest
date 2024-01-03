package org.omintest.api

interface ScenarioContext {
    fun getStepContext(id: String): Map<String, Any?>
}
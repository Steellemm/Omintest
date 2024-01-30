package org.omintest.api

interface ScenarioContext {

    fun getStepContext(id: String): StepContext

    fun getEnvironment(): Environment
}
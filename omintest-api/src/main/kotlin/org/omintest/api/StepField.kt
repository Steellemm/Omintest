package org.omintest.api

interface StepField {
    fun getValue(scenarioContext: ScenarioContext): Any
}
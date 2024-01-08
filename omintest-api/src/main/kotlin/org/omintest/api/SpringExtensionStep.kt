package org.omintest.api

interface SpringExtensionStep: Step {

    val input: Map<String, StepField>

    fun execute(context: ScenarioContext): StepContext

}
package org.omintest.step.springstep

import org.omintest.api.ScenarioContext
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepContext
import org.omintest.api.StepField

class DataBase(override val input: Map<String, StepField>) : SpringExtensionStep {
    override fun execute(context: ScenarioContext): StepContext {
        TODO("Not yet implemented")
    }
}
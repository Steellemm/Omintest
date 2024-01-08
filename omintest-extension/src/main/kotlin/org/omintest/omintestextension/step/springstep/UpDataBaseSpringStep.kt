package org.omintest.omintestextension.step.springstep

import org.omintest.api.ScenarioContext
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepContext
import org.omintest.api.StepField
import org.omintest.omintestextension.step.DBStepContext
import org.omintest.omintestextension.step.OmintestTestContext

class UpDataBaseSpringStep(override val input: Map<String, StepField>) : SpringExtensionStep {
    override fun execute(context: ScenarioContext): StepContext {
        val name = input["database-name"]!!.getValue(context).toString()
        context as OmintestTestContext
        val databseSettings = context.environment["database"]!![name] as Map<String, String>

        return DBStepContext(emptyMap(), "url", "user", "pass")
    }

    override fun type(): String {
        return "UpDB"
    }
}
package org.omintest.omintestextension.step.springstep

import org.omintest.api.ScenarioContext
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepContext
import org.omintest.api.StepField
import org.omintest.omintestextension.enviroment.model.StepData
import org.omintest.omintestextension.step.SpringStepContext
import org.omintest.omintestextension.step.StepFieldsConstructors

class AssertSpringStep(override val input: Map<String, StepField>) : SpringExtensionStep {

    override fun execute(context: ScenarioContext): StepContext {
        val asserts = input["asserts"]!!.getValue(context) as Map<String, String>
        for (pair in asserts) {
            try {
                assert(pair.key == pair.value)
            } catch (er: AssertionError) {
                println("Expected: ${pair.value}")
                println("Actual: ${pair.key}")
                throw er
            }
        }
        return SpringStepContext(asserts)
    }

    override fun type(): String = "Assert"
}
package org.omintest.omintestextension.logic.step.assert

import org.omintest.omintestextension.enviroment.model.StepData
import org.omintest.omintestextension.logic.OmintTestTestContext
import org.omintest.omintestextension.logic.step.Step

class AssertStep(override val id: String, val step: Map<String, StepData>) : Step {
    override fun execute(omintTestContext: OmintTestTestContext) {
        println("Executing $id")
        val asserts = omintTestContext.get(step["asserts"]!!) as Map<String, String>

        for (pair in asserts) {
            try {
                assert(pair.key == pair.value)
            } catch (er: AssertionError) {
                println("Expected: ${pair.value}")
                println("Actual: ${pair.key}")
                throw er
            }
        }
        omintTestContext.results.putAll(emptyMap())
    }
}
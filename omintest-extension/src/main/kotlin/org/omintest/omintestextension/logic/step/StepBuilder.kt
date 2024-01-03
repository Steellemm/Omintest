package org.omintest.omintestextension.logic.step

import org.omintest.omintestextension.engine.OmintTestDescriptor
import org.omintest.omintestextension.enviroment.model.StepInfo
import org.omintest.omintestextension.logic.OmintTestTestContext
import org.omintest.omintestextension.logic.step.assert.AssertStep
import org.omintest.omintestextension.logic.step.http.HttpRequestStep
import org.omintest.omintestextension.logic.step.up.UpStep
import org.junit.jupiter.api.DynamicTest
import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.TestSource
import org.junit.platform.engine.UniqueId

class StepBuilder {
    val omintTestContext = OmintTestTestContext()

    fun build() {
        //val scenarios = getScenarios()
    }

    fun buildScenario(source: TestSource, name: String, steps: List<StepInfo>): OmintTestDescriptor {
        val stepList = mutableListOf<Step>()
        for (step in steps) {
            val stepType = step.uses as String
            if (stepType == "Rest") {
                stepList.add(HttpRequestStep(step.id, step.with))
            }
            if (stepType == "Up") {
                stepList.add(UpStep(step.id, step.with))
            }
            if (stepType == "Assert") {
                stepList.add(AssertStep(step.id, step.with))
            }
        }
        val mainDescriptor = createContainerDescriptor(source, name)
        stepList.forEach {
            mainDescriptor.addChild(createTestDescriptor(
                source,
                it.id,
                DynamicTest.dynamicTest(name) { ->
                    it.execute(
                        omintTestContext
                    )
                }
            ))
        }
        return mainDescriptor
    }

    private fun createTestDescriptor(source: TestSource, stepName: String, test: DynamicTest): OmintTestDescriptor {
        return OmintTestDescriptor(
            UniqueId.parse("[$stepName]"),
            source,
            stepName,
            test,
            TestDescriptor.Type.TEST
        )
    }

    private fun createContainerDescriptor(source: TestSource, name: String): OmintTestDescriptor {
        return OmintTestDescriptor(
            UniqueId.parse("[omintest:$name]"),
            source,
            name,
            DynamicTest.dynamicTest("main") {},
            TestDescriptor.Type.CONTAINER
        )
    }

    companion object {
        //val stepsType = mapOf<String, KClass<out Step>>("RestRequest" to HttpRequestStep::class)
    }
}
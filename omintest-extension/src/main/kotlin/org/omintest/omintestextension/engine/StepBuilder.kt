package org.omintest.omintestextension.engine

import org.junit.jupiter.api.DynamicTest
import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.TestSource
import org.junit.platform.engine.UniqueId
import org.omintest.api.StepField
import org.omintest.omintestextension.enviroment.StepFieldsConstructors
import org.omintest.omintestextension.enviroment.model.StepData
import org.omintest.omintestextension.enviroment.model.StepInfo
import org.omintest.omintestextension.enviroment.stepsConstructors
import org.omintest.step.OmintestTestContext

class StepBuilder {
    val omintTestContext = OmintestTestContext()

    fun buildScenario(source: TestSource, name: String, steps: List<StepInfo>): OmintTestDescriptor {
        val newStepList = steps.associate { it.id to stepsConstructors[it.uses]!!.newInstance(stepToInput(it.with)) }
        val mainDescriptor = createContainerDescriptor(source, name)
        newStepList.forEach { (key, value) ->
            mainDescriptor.addChild(createTestDescriptor(
                source,
                key,
                DynamicTest.dynamicTest(name) { ->
                    omintTestContext.stepContexts[key] = value.execute(
                        omintTestContext
                    )
                }
            ))
        }
        mainDescriptor.addChild(createEndScenarioDescriptor(source))
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

    private fun createEndScenarioDescriptor(source: TestSource): OmintTestDescriptor {
        return OmintTestDescriptor(
            UniqueId.parse("[omintest:end]"),
            source,
            "end:end",
            DynamicTest.dynamicTest("end") {
                omintTestContext.containers.forEach { it.stop() }
            },
            TestDescriptor.Type.TEST
        )
    }

    private fun stepToInput(step: Map<String, StepData>): Map<String, StepField> {
        return step.map { it.key to StepFieldsConstructors[it.value.type]!!.newInstance(it.value.value) }.toMap()
    }
}
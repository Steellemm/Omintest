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
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext

class StepBuilder {
    val omintTestContext = OmintestTestContext()

    fun buildScenario(source: TestSource, name: String, steps: List<StepInfo>): OmintTestDescriptor {
        val stepList = steps.associate { it.id to stepsConstructors[it.uses]!!.newInstance(stepToInput(it.with)) }
        val idToDescription = steps.associate { it.id to it.description }
        val mainDescriptor = createContainerDescriptor(source, name)
        stepList.forEach { (key, value) ->
            mainDescriptor.addChild(createTestDescriptor(
                source,
                mainDescriptor.uniqueId.append(key.split(":").first(), key.split(":").last()),
                idToDescription[key]!!,
                DynamicTest.dynamicTest(name) {
                    omintTestContext.stepContexts[key] = value.execute(
                        omintTestContext
                    )
                }
            ))
        }
        mainDescriptor.addChild(createEndScenarioDescriptor(source, mainDescriptor.uniqueId.append("end", name)))
        return mainDescriptor
    }

    private fun createTestDescriptor(
        source: TestSource,
        stepId: UniqueId,
        displayName: String,
        test: DynamicTest
    ): OmintTestDescriptor {
        return OmintTestDescriptor(
            stepId,
            source,
            displayName,
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

    private fun createEndScenarioDescriptor(source: TestSource, id: UniqueId): OmintTestDescriptor {
        return OmintTestDescriptor(
            id,
            source,
            "end",
            DynamicTest.dynamicTest("end") {
                (omintTestContext.applicationContext as AnnotationConfigServletWebServerApplicationContext).registerShutdownHook()
                (omintTestContext.applicationContext as AnnotationConfigServletWebServerApplicationContext).close()
                omintTestContext.containers.forEach { it.value.stop() }
            },
            TestDescriptor.Type.TEST
        )
    }

    private fun stepToInput(step: Map<String, StepData>): Map<String, StepField> {
        return step.map { it.key to StepFieldsConstructors[it.value.type]!!.newInstance(it.value.value) }.toMap()
    }
}
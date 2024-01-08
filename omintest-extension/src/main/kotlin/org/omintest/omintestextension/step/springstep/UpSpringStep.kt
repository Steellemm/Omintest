package org.omintest.omintestextension.step.springstep

import org.omintest.api.ScenarioContext
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepContext
import org.omintest.api.StepField
import org.omintest.omintestextension.step.OmintestTestContext
import org.omintest.omintestextension.step.SpringStepContext

class UpSpringStep(
    override val input: Map<String, StepField>,
) : SpringExtensionStep {

    override fun execute(context: ScenarioContext): StepContext {
        if (input["type"]!!.getValue(context) == "SERVICE") {
            if (((context as OmintestTestContext).environment["service"] as Map<String, Map<String, Any>>)[input["name"]!!.getValue(
                    context
                )]!!["type"] as String == "spring-boot-application"
            ) {
                context.applicationContext = context.testContext?.applicationContext
                val host = "localhost:" + context.applicationContext?.environment?.getProperty("local.server.port")!!
                return SpringStepContext(mapOf("host" to host))
            }
        }

        throw Exception("No up")
    }

    override fun type(): String = "Up"
}


package org.omintest.omintestextension.logic.step.up

import org.omintest.omintestextension.enviroment.model.StepData
import org.omintest.omintestextension.logic.OmintTestTestContext
import org.omintest.omintestextension.logic.step.Step

class UpStep(override val id: String, private val step: Map<String, StepData>) : Step {
    private val type: UpType

    init {
        this.type = UpType.valueOf(step["type"]?.value as String)
    }

    override fun execute(omintTestContext: OmintTestTestContext){
        println("Executing $id")

        if (type == UpType.SERVICE) {
            if ((omintTestContext.enviroment["service"] as Map<String, Map<String, Any>>)[step["name"]!!.value]!!["type"] as String == "spring-boot-application") {
                omintTestContext.applicationContext = omintTestContext.testContext?.applicationContext
            }
        }
        val host = "localhost:" + omintTestContext.applicationContext?.environment?.getProperty("local.server.port")!!
        omintTestContext.results.putAll(mapOf(id to mapOf("host" to host)))

        //createMockS(omintTestContext.applicationContext?.environment?.getProperty("local.server.port")!!.toInt())
    }

    private enum class UpType {
        SERVICE
    }
}


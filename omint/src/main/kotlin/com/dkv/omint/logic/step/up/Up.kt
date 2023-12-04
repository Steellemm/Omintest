package com.dkv.omint.logic.step.up

import com.dkv.omint.logic.OmintTestTestContext
import com.dkv.omint.logic.step.Step

class Up(val step: Map<String, Map<String, Any>>) : Step {
    private val type: UpType

    init {
        this.type = UpType.valueOf(step["type"]!!["value"] as String)
    }

    override fun execute(omintTestContext: OmintTestTestContext): Map<String, Any> {
        if (type == UpType.SERVICE){
            if (omintTestContext.enviroment[step["name"]!!["value"]]!!["type"] as String == "spring-boot-application"){
                omintTestContext.applicationContext = omintTestContext.testContext?.applicationContext
            }
        }
        return emptyMap()
    }

    private enum class UpType {
        SERVICE
    }
}


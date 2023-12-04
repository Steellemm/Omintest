package com.dkv.omint.logic.step

import com.dkv.omint.enviroment.getScenarios
import com.dkv.omint.enviroment.model.StepInfo
import com.dkv.omint.logic.OmintTestTestContext
import com.dkv.omint.logic.step.http.HttpRequestStep
import org.junit.jupiter.api.DynamicTest
import kotlin.reflect.KClass
import kotlin.reflect.full.allSuperclasses

class StepBuilder {
    val omintTestContext = OmintTestTestContext()

    fun build() {
        val scenarios = getScenarios()
    }

    fun buildScenario(name: String, steps: List<StepInfo>): DynamicTest {
        val stepList = mutableListOf<Step>()
        for (step in steps) {
            val stepType = step.uses as String
            if (stepType == "RestRequest") {
                stepList.add(HttpRequestStep(step.with))
            }
        }
        return DynamicTest.dynamicTest(name) { -> stepList.forEach { it.execute(omintTestContext) } }
    }

    companion object {
        //val stepsType = mapOf<String, KClass<out Step>>("RestRequest" to HttpRequestStep::class)
    }
}
package org.omintest.omintestextension.step

import org.omintest.api.ScenarioContext
import org.omintest.api.StepContext
import org.omintest.api.model.SpringBootServiceInfo
import org.springframework.context.ApplicationContext
import org.springframework.test.context.TestContext

class OmintestTestContext: ScenarioContext {
    var testContext: TestContext? = null
    var environment: Map<String, Map<String, Any>> = emptyMap()
    var applicationContext: ApplicationContext? = null
    val stepContexts: MutableMap<String, StepContext> = mutableMapOf()
    lateinit var springBootServiceInfo: SpringBootServiceInfo

    override fun getStepContext(id: String): StepContext {
        return stepContexts[id]!!
    }
}

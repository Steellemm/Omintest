package org.omintest.step

import org.omintest.api.Environment
import org.omintest.api.ScenarioContext
import org.omintest.api.StepContext
import org.omintest.api.model.SpringBootServiceInfo
import org.springframework.context.ApplicationContext
import org.springframework.test.context.TestContext
import org.testcontainers.containers.GenericContainer

class OmintestTestContext: ScenarioContext {
    var mainClass: Class<*>? = null
    var testClass: Class<*>? = null
    var testContext: TestContext? = null
    var environment: OmintestEnvironment? = null
    var applicationContext: ApplicationContext? = null
    val stepContexts: MutableMap<String, StepContext> = mutableMapOf()
    val containers = mutableMapOf<String, GenericContainer<*>>()

    override fun getStepContext(id: String): StepContext {
        return stepContexts[id]!!
    }

    override fun getEnvironment(): Environment {
        return environment!!
    }
}

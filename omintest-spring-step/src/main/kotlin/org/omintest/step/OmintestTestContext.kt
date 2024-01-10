package org.omintest.step

import org.omintest.api.ScenarioContext
import org.omintest.api.StepContext
import org.omintest.api.model.SpringBootServiceInfo
import org.springframework.context.ApplicationContext
import org.springframework.test.context.TestContext
import org.testcontainers.containers.JdbcDatabaseContainer

class OmintestTestContext: ScenarioContext {
    var testContext: TestContext? = null
    var environment: Map<String, Map<String, Any>> = emptyMap()
    var springService: SpringBootServiceInfo? = null
    var applicationContext: ApplicationContext? = null
    val stepContexts: MutableMap<String, StepContext> = mutableMapOf()
    val containers = mutableListOf<JdbcDatabaseContainer<*>>()

    override fun getStepContext(id: String): StepContext {
        return stepContexts[id]!!
    }
}

package com.dkv.omint.logic

import org.springframework.context.ApplicationContext
import org.springframework.test.context.TestContext

class OmintTestTestContext {
    var testContext: TestContext? = null
    var enviroment: Map<String, Map<String, Any>> = emptyMap()
    var applicationContext: ApplicationContext? = null
}

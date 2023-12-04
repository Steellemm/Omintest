package com.dkv.omint.logic.step.up

import org.springframework.context.ApplicationContext
import org.springframework.test.context.TestContextManager
import java.util.function.Supplier

fun SpringUp(){

}

private fun create(
    testContextManagerSupplier: Supplier<TestContextManager>,
): ApplicationContext {

    val delegate = testContextManagerSupplier.get()
    val testContext = delegate.testContext
    return testContext.applicationContext

}
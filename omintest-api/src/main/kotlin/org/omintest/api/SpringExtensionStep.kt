package org.omintest.api

interface SpringExtensionStep<I: Any> : Step <I> {

    val input: I

    fun type(): String

    fun execute(context: ScenarioContext): StepContext

}
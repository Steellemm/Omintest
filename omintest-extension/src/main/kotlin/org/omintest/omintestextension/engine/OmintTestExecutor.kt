package org.omintest.omintestextension.engine

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DynamicTest
import org.junit.platform.engine.ExecutionRequest
import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.engine.support.descriptor.EngineDescriptor
import org.junit.platform.engine.support.hierarchical.EngineExecutionContext
import org.omintest.omintestextension.engine.OmintTestDescriptor

class OmintTestExecutor(
) : EngineExecutionContext {
    val mapper = ObjectMapper()
    fun execute(request: ExecutionRequest, descriptor: TestDescriptor) {
        //(descriptor as? EngineDescriptor)?.let { executeContainer(request, it) }
        (descriptor as? EngineDescriptor)?.let {
            executeTest(request, it)
        }
    }

    private fun executeTest(request: ExecutionRequest, descriptor: TestDescriptor) {
        val listener = request.engineExecutionListener
        if (descriptor.type == TestDescriptor.Type.CONTAINER) {
            listener.executionStarted(descriptor)
            descriptor.children.forEach { childDescriptor ->
                executeTest(request, childDescriptor)
            }
            listener.executionFinished(descriptor, TestExecutionResult.successful())
        } else {
            listener.executionStarted(descriptor)
            val result = try {
                (descriptor as? OmintTestDescriptor)?.test?.executable?.execute()
                DynamicTest.dynamicTest("1") { DynamicTest.dynamicTest("2") { assert(true) } }.executable.execute()
                TestExecutionResult.successful()
            } catch (ex: AssertionError) {
                TestExecutionResult.failed(ex)
            } catch (ex: Exception) {
                TestExecutionResult.failed(ex)
            }
            listener.executionFinished(descriptor, result)
        }
    }


    private fun executeFileBasedTest(request: ExecutionRequest, descriptor: TestDescriptor): TestExecutionResult {

        return TestExecutionResult.successful()
    }

    private fun executeContainer(request: ExecutionRequest, containerDescriptor: TestDescriptor) {
        request.engineExecutionListener.executionStarted(containerDescriptor)
        containerDescriptor.children
            .forEach { descriptor: TestDescriptor -> execute(request, descriptor) }
        request.engineExecutionListener.executionFinished(containerDescriptor, TestExecutionResult.successful())
    }


}
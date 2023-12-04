package com.dkv.omint.engine

import org.junit.platform.engine.ExecutionRequest
import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.engine.support.descriptor.EngineDescriptor
import org.junit.platform.engine.support.hierarchical.EngineExecutionContext
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper

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
        request.engineExecutionListener.executionStarted(descriptor)
        descriptor.children.forEach {
            executeTest(request, it)
        }

        val result = try {
            (descriptor as? OmintTestDescriptor)?.test?.executable?.execute()
            TestExecutionResult.successful()
        } catch (ex: AssertionError) {
            TestExecutionResult.failed(ex)
        } catch (ex: Exception){
            TestExecutionResult.failed(ex)
        }
        request.engineExecutionListener.executionFinished(descriptor, result)
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

    companion object {

//        fun r2dbcUrl(): String {
//            return "http://${container.host}:${container.getMappedPort(9200)}"
//        }
//
//        private val DEFAULT_IMAGE = "docker.elastic.co/elasticsearch/elasticsearch:7.10.2"
//        private val ELASTICSEARCH_IMAGE = getImage()
//
//
//        private fun getImage():String{
//            val scenario = listFilesWithSubFolders(File("src/test/resources/cabbage/scenarios"))?.get(0)
//            if (scenario != null) {
//                val mappedScenario = mapper.readValue(scenario, Map::class.java)
//                return mappedScenario.getOrDefault("containerImage", DEFAULT_IMAGE) as String
//            }
//
//            return DEFAULT_IMAGE
//        }

    }
}
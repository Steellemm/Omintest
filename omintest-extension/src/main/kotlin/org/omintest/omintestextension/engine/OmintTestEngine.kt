package org.omintest.omintestextension.engine

import org.omintest.omintestextension.enviroment.getEnvironment
import org.omintest.omintestextension.enviroment.getScenarios
import org.junit.platform.commons.support.AnnotationSupport
import org.junit.platform.engine.*
import org.junit.platform.engine.discovery.ClassSelector
import org.junit.platform.engine.support.descriptor.EngineDescriptor
import org.junit.platform.engine.support.descriptor.FileSource
import org.springframework.core.annotation.AnnotatedElementUtils
import org.springframework.test.context.TestContext
import org.springframework.test.context.TestContextManager
import java.io.File
import java.lang.reflect.AnnotatedElement
import java.util.function.Supplier
import org.junit.platform.engine.TestDescriptor


class OmintTestEngine : TestEngine {

    private val stepBuilder = StepBuilder()

    override fun getId(): String = "omint-engine"

    override fun discover(request: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        val engineDescriptor: TestDescriptor = EngineDescriptor(uniqueId, "Omint Tests")

        stepBuilder.omintTestContext.testContext = request.getTestContext()

        request.getSelectorsByType(ClassSelector::class.java)
            .forEach { selector ->
                appendTestsInClass(
                    selector.javaClass,
                    engineDescriptor,
                    uniqueId
                )
            }

        return engineDescriptor
    }

    private fun create(
        testContextManagerSupplier: Supplier<TestContextManager>,
    ): TestContext {
        val delegate = testContextManagerSupplier.get()
        return delegate.testContext
    }

    private fun hasOmintest(stepClass: AnnotatedElement): Boolean {
        return AnnotatedElementUtils.isAnnotated(stepClass, Omintest::class.java)
    }

    private fun appendTestsInClass(javaClass: Class<*>, engineDescriptor: TestDescriptor, uniqueId: UniqueId) {
        if (AnnotationSupport.isAnnotated(javaClass, Omintest::class.java)) {
            val tests = javaClass.getAnnotation(Omintest::class.java)?.tests!!
            stepBuilder.omintTestContext.environment = getEnvironment(tests.first())
            getScenarios(tests.first()).forEach { scenario ->
                val source = FileSource.from(File("src/test/resources/omintest/${tests.first()}"))
                val testDescriptor =
                    stepBuilder.buildScenario(source, scenario.name, scenario.steps)
                engineDescriptor.addChild(testDescriptor)
            }
        }
    }

    private fun EngineDiscoveryRequest.getTestContext(): TestContext? {
        return this.getSelectorsByType(ClassSelector::class.java)
            .find { selector -> hasOmintest(selector.javaClass) }?.javaClass?.let {
                create {
                    TestContextManager(
                        it
                    )
                }
            }
    }

    override fun execute(request: ExecutionRequest) {
        val root = request.rootTestDescriptor
        OmintTestExecutor().execute(request, root)
    }
}

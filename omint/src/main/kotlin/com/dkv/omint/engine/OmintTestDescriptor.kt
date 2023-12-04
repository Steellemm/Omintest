package com.dkv.omint.engine

import org.junit.jupiter.api.DynamicTest
import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.TestSource
import org.junit.platform.engine.UniqueId
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor
import org.junit.platform.engine.support.descriptor.ClassSource
import java.util.*

class OmintTestDescriptor(
    testClass: Class<*>,
    uniqueId: UniqueId,
    private val methodSource: TestSource,
    name: String,
    val test: DynamicTest
) : AbstractTestDescriptor( //
    uniqueId,  //
    name,  //
    ClassSource.from(testClass) //
) {

    override fun getType(): TestDescriptor.Type = TestDescriptor.Type.TEST

    override fun getSource(): Optional<TestSource> = Optional.of(methodSource)

    override fun mayRegisterTests(): Boolean = true


}
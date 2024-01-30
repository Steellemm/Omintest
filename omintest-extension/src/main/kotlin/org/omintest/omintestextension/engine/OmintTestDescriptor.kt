package org.omintest.omintestextension.engine

import org.junit.jupiter.api.DynamicTest
import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.TestSource
import org.junit.platform.engine.UniqueId
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor
import java.util.*

class OmintTestDescriptor(
    uniqueId: UniqueId,
    private val methodSource: TestSource,
    displayName: String,
    val test: DynamicTest,
    private val type: TestDescriptor.Type
) : AbstractTestDescriptor( //
    uniqueId,  //
    displayName
) {
    override fun getType(): TestDescriptor.Type = type

    override fun getSource(): Optional<TestSource> = Optional.of(methodSource)

    override fun mayRegisterTests(): Boolean = true

    override fun removeFromHierarchy() {
//        Preconditions.condition(!isRoot, "cannot remove the root of a hierarchy")
//        parent.removeChild(this)
//        children.forEach(Consumer { child: TestDescriptor ->
//            child.setParent(
//                null
//            )
//        })
//        children.clear()

        println("Try to clear from ${parent.get().displayName} ")
    }
}
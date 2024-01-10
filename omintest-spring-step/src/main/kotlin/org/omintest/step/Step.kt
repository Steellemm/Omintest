package org.omintest.step

interface Step {
    val id: String
    fun execute(omintTestContext: OmintestTestContext)
}

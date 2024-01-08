package org.omintest.omintestextension.step

interface Step {
    val id: String
    fun execute(omintTestContext: OmintestTestContext)
}

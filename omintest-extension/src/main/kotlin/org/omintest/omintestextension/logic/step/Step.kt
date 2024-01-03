package org.omintest.omintestextension.logic.step

import org.omintest.omintestextension.logic.OmintTestTestContext

interface Step {
    val id: String
    fun execute(omintTestContext: OmintTestTestContext)
}

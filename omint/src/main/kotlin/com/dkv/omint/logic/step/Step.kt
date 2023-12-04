package com.dkv.omint.logic.step

import com.dkv.omint.logic.OmintTestTestContext

interface Step {
    fun execute(omintTestContext: OmintTestTestContext): Map<String, Any>
}
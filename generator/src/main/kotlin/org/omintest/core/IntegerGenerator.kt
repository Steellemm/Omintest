package org.omintest.core

import org.omintest.annotation.GeneratorDoc
import org.omintest.annotation.ParameterDoc
import org.omintest.utils.getDataClass

@GeneratorDoc(description = "Integer generator")
class IntegerGenerator : Generator<Int> {
    override fun generate(parameters: Map<String, Any?>?): Int {
        val params: Parameters = parameters.getDataClass()
        return (params.defaultMin..params.defaultMax).random()
    }


    data class Parameters(
        @ParameterDoc
        val defaultMax: Int = 100,
        @ParameterDoc
        val defaultMin: Int = 0
    )
}
package org.omintest.core

import org.omintest.annotation.GeneratorDoc
import org.omintest.annotation.ParameterDoc
import org.omintest.utils.getDataClass

@GeneratorDoc(description = "String generator class", parameterClass = StringGenerator.Parameters::class)
class StringGenerator : Generator<String> {
    private val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')

    override fun generate(parameters: Map<String, Any?>?): String {
        val a: Parameters = parameters.getDataClass()
        return (a.minLength..a.maxLength)
            .map { allowedChars.random() }
            .joinToString("")
    }

    data class Parameters(
        @ParameterDoc
        val maxLength: Int = 5,
        @ParameterDoc
        val minLength: Int = 1
    )
}
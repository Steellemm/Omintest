package org.omintest.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StringGeneratorTest {
    @Test
    fun `string generator generates correct value`() {
        val generator: Generator<String> = StringGenerator()
        val result = generator.generate(mapOf("minLength" to 5, "maxLength" to 10))
        assertTrue(result.length in (5..10))
    }
}
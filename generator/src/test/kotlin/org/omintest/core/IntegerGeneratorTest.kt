package org.omintest.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IntegerGeneratorTest {
    @Test
    fun `integer generator generates correct value`() {
        val generator: Generator<Int> = IntegerGenerator()
        val result = generator.generate(mapOf("defaultMax" to 50, "defaultMin" to 20))
        assertTrue(result in (20..50))
    }
}
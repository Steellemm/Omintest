package org.omintest.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GeneratorUtilsKtTest {
    @Test
    fun `map to data class should return correct data class`() {
        val dataClass = mapOf<String, Any?>("maxLength" to 4, "minLength" to 2).getDataClass<Parameters>()
        assertEquals(4, dataClass.maxLength)
        assertEquals(2, dataClass.minLength)
    }

    @Test
    fun `map without fields to data should return class with default fields`() {
        val dataClass = mapOf<String, Any?>().getDataClass<Parameters>()
        assertEquals(5, dataClass.maxLength)
        assertEquals(1, dataClass.minLength)
    }

    data class Parameters(
        val maxLength: Int = 5,
        val minLength: Int = 1
    )
}
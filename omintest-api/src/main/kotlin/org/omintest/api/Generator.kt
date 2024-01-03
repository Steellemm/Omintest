package org.omintest.api

interface Generator<T> {
    fun generate(parameters: Map<String, Any?>? = null): T
}
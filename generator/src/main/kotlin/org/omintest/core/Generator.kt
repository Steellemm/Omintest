package org.omintest.core

interface Generator<T> {
    fun generate(parameters: Map<String, Any?>? = null): T
}
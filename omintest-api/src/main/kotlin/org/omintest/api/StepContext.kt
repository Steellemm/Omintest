package org.omintest.api

interface StepContext{
    fun getValue(key: String): Any?
}
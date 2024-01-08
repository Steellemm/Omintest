package org.omintest.api

interface DataBaseStepContext: StepContext {
    fun getUrl(): String
    fun getPassword(): String
    fun getUser(): String
}
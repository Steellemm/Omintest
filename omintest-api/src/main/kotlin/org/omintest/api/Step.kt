package org.omintest.api

import kotlin.reflect.KClass

interface Step<I : Any> {
    fun getInClass(): KClass<I>
}
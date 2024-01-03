package org.omintest.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class GeneratorDoc(
    val description: String = "",
    val parameterClass: KClass<out Any> = Any::class
)
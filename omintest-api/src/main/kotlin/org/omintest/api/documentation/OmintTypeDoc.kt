package org.omintest.api.documentation

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class OmintTypeDoc(
    val description: String = "",
    val parameterClass: KClass<out Any> = Any::class
)

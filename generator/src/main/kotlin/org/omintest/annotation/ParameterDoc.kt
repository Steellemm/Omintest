package org.omintest.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ParameterDoc(
    val description: String = ""
)

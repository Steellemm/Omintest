package org.omintest.api.documentation


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class OmintFieldDoc(
    val description: String = ""
)

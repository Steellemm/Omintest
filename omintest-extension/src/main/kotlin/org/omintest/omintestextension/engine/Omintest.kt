package org.omintest.omintestextension.engine

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.platform.commons.annotation.Testable
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(
    RetentionPolicy.RUNTIME
)
@Testable
annotation class Omintest(val tests: Array<String>)
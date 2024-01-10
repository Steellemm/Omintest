package org.omintest.api

interface Step {

    fun id(): String = this.javaClass.simpleName.decapitalize()
}
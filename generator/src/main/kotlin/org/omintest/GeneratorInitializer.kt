package org.omintest

import org.omintest.core.Generator
import org.reflections.Reflections

object GeneratorInitializer {
    @JvmStatic
    val generators = mutableMapOf<String, Generator<*>>()

    init {
        val reflections = Reflections("org")
        val classes: Set<Class<out Generator<*>>> = reflections.getSubTypesOf(Generator::class.java)
        generators.putAll(classes.map { it.simpleName to it.getDeclaredConstructor().newInstance() })
    }
//        val classes = ArrayList<Class<*>>()
//        val loader = ServiceLoader.load(interfaze)
//        for (clazz in loader) {
//            classes.add(clazz.javaClass)
//        }
//        return classes
}
package org.omintest.coredb

import org.omintest.core.Generator
import org.omintest.core.IntegerGenerator
import org.omintest.core.StringGenerator

object Generators {
    private val integerGenerator = IntegerGenerator()
    private val stringGenerator = StringGenerator()

    fun getIntegerGenerator(): Generator<Int> = org.omintest.coredb.Generators.integerGenerator

    fun getStringGenerator(): Generator<String> = org.omintest.coredb.Generators.stringGenerator
}
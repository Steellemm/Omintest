package org.omintest.coredb.model

import org.omintest.core.Generator

data class DbModel(
    val tables: List<Entity>
)

data class Entity(
    val name: String,
    val foreignKeys: Map<String, FKey>,
    val primaryKey: String,
    val columns: List<Column>
)

data class Column(
    val name: String,
    val type: String,
    val value: Any?,
    val generator: GeneratorModel,
    val primary: Boolean = false
)

data class FKey(
    val table: String,
    val column: String,
    val explicit: Boolean,
    val autoIncrement: Boolean
)

data class GeneratorModel(
    val name: String,
    val params: Map<String, Any?>?
)
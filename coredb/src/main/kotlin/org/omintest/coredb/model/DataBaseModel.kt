package org.omintest.coredb.model

data class DbModel(
    val tables: List<Entity>
)

data class Entity(
    val name: String,
    val foreignKeys: Map<String, FKey> = emptyMap(),
    val primaryKeys: String,
    val columns: List<Column>
)

data class Column(
    val name: String,
    val type: String,
    val value: Any?,
    val generator: GeneratorModel? = null,
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
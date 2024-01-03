package org.omintest.coredb.model

class EntityContext(
    val entitiesMap: MutableMap<String, OmintEntity> = mutableMapOf(),
    val parentIndex: MutableMap<String, Int> = mutableMapOf()
) {
    fun getEntity(tableName: String): OmintEntity? =
        parentIndex[tableName]?.let {
            entitiesMap["$tableName.$it"]
        }
}

data class OmintEntity(
    val saved: Boolean,
    val values: Map<String, Any?>,
    val id: Any
)
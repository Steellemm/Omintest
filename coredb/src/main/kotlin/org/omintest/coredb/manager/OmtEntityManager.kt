package org.omintest.coredb.manager

import org.omintest.coredb.model.OmintEntity

interface OmtEntityManager {
    fun initialize(schema: String)

    fun initialize()

    fun insert(tableName: String, values: Map<String, Any?>?): OmintEntity

    fun select(tableName: String, id: Any): Map<String, Any?>?

    fun select(tableName: String, conditions: Map<String, Any?>): List<Map<String, Any?>>

    fun update(tableName: String, id: Any, values: Map<String, Any?>): Long

    fun update(tableName: String, conditions: Map<String, Any?>, values: Map<String, Any?>): Long

    fun delete(tableName: String, id: Any): Long

    fun delete(tableName: String, conditions: Map<String, Any?>): Long


}
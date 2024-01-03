package org.omintest.coredb.manager

import org.omintest.GeneratorInitializer
import org.omintest.coredb.db.queryForList
import org.omintest.coredb.model.*
import java.lang.RuntimeException
import javax.sql.DataSource

class CommonOmtEntityManager(val dataSource: DataSource, val model: DbModel) :
    OmtEntityManager {
    val entityContext = EntityContext()
    // tableName[0].value
    // "tableName.0" -> Map<String, Any?>
    // parent.0
    // parent.1
    // child.0(parent.1)
    // child.1(parent.1)
    // parent.2
    // map<String, Int(id последнего parent)>

    // в первую очередь создать нормально модули, чтобы все видели и ссылаись друг на друга
    // второе генераторы создаем статически, через рефлексию создаем мапу ключ - класс симпл нэйм, значение - генератор
    // потом инитиализ
    // потом инсерт
    // потом тесты
    override fun initialize() {
        dataSource.connection.use { conn ->
            val statement = model.tables.joinToString(";\n") { table ->
                val fields = table.columns.joinToString(",\n") {
                    "${it.name} ${it.type}"
                }
                "CREATE TABLE ${table.name} ($fields)"
            }

            val preparedStatement = conn.prepareStatement(statement)
            preparedStatement.execute()
        }


    }

    override fun insert(tableName: String, values: Map<String, Any?>?): OmintEntity {
        val table = model.tables.find { it.name == tableName } ?: throw RuntimeException("Table $tableName not found")
        val finalValues = mutableMapOf<String, Any?>()
        table.foreignKeys.forEach { (key, value) ->
            val parentEntity = entityContext.getEntity(value.table) ?: insert(value.table, emptyMap())
            finalValues[key] = parentEntity.id
        }
        finalValues.putAll(table.columns.associate { it.name to calculateValue(table, it, values) })
        val entity = OmintEntity(
            saved = false,
            values = finalValues,
            id = finalValues[table.primaryKeys]!!
        )

        dataSource.connection.use { conn ->
            val columns = finalValues.keys.joinToString(", ")
            val data = finalValues.values.joinToString(separator = ", ") { "?" }
            val statement = conn.prepareStatement(
                """
                INSERT INTO $tableName ($columns) VALUES ($data)
            """.trimIndent()
            )
            var i = 1;
            finalValues.forEach { (_, value) ->
                statement.setObject(i++, value)
            }
            statement.execute()
            entityContext.parentIndex.compute(tableName) { _, v ->
                if (v == null) 0 else v + 1
            }
            entityContext.entitiesMap["$tableName.${entityContext.parentIndex[tableName]}"] = entity
        }

        return entity
    }

    private fun calculateValue(table: Entity, column: Column, values: Map<String, Any?>?): Any? {
        if (values?.containsKey(column.name) == true) {
            return values[column.name]
        }
        if (column.value != null) {
            return column.value
        }
        if (table.foreignKeys.containsKey(column.name)) {
            val foreignColumn = table.foreignKeys[column.name]?.column
            return entityContext.getEntity(table.foreignKeys[column.name]!!.table)?.values?.get(foreignColumn)
        }
        return GeneratorInitializer.generators[column.generator!!.name]!!.generate(column.generator.params)
    }


    override fun select(tableName: String, id: Any): Map<String, Any?>? {
        val table = model.tables.find { it.name == tableName } ?: throw RuntimeException("Table $tableName not found")
        val pKey = table.primaryKeys
        val conditionString = "$pKey = ?"
        val sql = """
            SELECT * FROM
            ${table.name}
            WHERE
            $conditionString
        """
        val keys = table.columns.map { it.name }
        val result = dataSource.queryForList(sql, listOf(id)) { rs ->
            keys.associateWith { rs.getObject("$it") }
        }
        return if (result.isNotEmpty()) {
            result.first()
        } else {
            null
        }
    }

    override fun select(tableName: String, conditions: Map<String, Any?>): List<Map<String, Any?>> {
        val table = model.tables.find { it.name == tableName } ?: throw RuntimeException("Table $tableName not found")
        val conditionsString = conditions.map { "${it.key} = ?" }.joinToString(" AND ")
        val values = conditions.map { it.value }.toList()
        val sql = """
            SELECT * FROM
            ${table.name}
            WHERE
            $conditionsString
        """

        val keys = table.columns.map { it.name }
        return dataSource.queryForList(sql, values) { rs ->
            keys.associateWith { rs.getObject("$it") }
        }
    }

    override fun update(tableName: String, id: Any, values: Map<String, Any?>): Long {
        TODO("Not yet implemented")
    }

    override fun update(tableName: String, conditions: Map<String, Any?>, values: Map<String, Any?>): Long {
        TODO("Not yet implemented")
    }

    override fun delete(tableName: String, id: Any): Long {
        TODO("Not yet implemented")
    }

    override fun delete(tableName: String, conditions: Map<String, Any?>): Long {
        TODO("Not yet implemented")
    }
}


package org.omintest.coredb.db

import java.sql.PreparedStatement
import java.sql.ResultSet
import javax.sql.DataSource

fun <T> DataSource.queryForList(sql: String, rowMapper: (rs: ResultSet) -> T): List<T> {
    this.connection.use {
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet? = null
        preparedStatement = it.prepareStatement(sql)
        resultSet = preparedStatement.executeQuery()

        val results = mutableListOf<T>()
        while (resultSet.next()) {
            val resultObject = rowMapper(resultSet) // You'll need to implement this function
            results.add(resultObject)
        }
        return results
    }
}

fun <T> DataSource.queryForList(sql: String, values: List<Any?>, rowMapper: (rs: ResultSet) -> T): List<T> {
    this.connection.use {
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet? = null
        preparedStatement = it.prepareStatement(sql)

        var i = 1;
        values.forEach { value ->
            preparedStatement.setObject(i++, value)
        }
        resultSet = preparedStatement.executeQuery()

        val results = mutableListOf<T>()
        while (resultSet.next()) {
            val resultObject = rowMapper(resultSet)
            results.add(resultObject)
        }
        return results
    }
}

fun DataSource.insert(sql: String, values: List<Any>){
    this.connection.use { conn ->
        val statement = conn.prepareStatement(
            sql
        )
        var i = 1;
        values.forEach { value ->
            statement.setObject(i++, value)
        }
        statement.execute()
    }
}
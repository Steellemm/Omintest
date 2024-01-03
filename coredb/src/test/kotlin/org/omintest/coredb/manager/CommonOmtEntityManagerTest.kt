package org.omintest.coredb.manager

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.omintest.coredb.containers.PosgreContainer
import org.omintest.coredb.model.DbModel
import java.io.File
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import javax.sql.DataSource

internal class CommonOmtEntityManagerTest {


    val container = PosgreContainer.container()

    @Test
    fun someTest() {
        container.start()

        val source = createDataSource(container.jdbcUrl, container.username, container.password)
        val objectMapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
        val dbModelFromYml: DbModel = objectMapper.readValue(File("src/test/resources/model.yml"), DbModel::class.java)

        val manager = CommonOmtEntityManager(source, dbModelFromYml)

        manager.initialize()

        val name = "Somename"

        manager.insert("employee", null)
        manager.insert("employee", null)
        manager.insert("employee", mapOf("name" to name))


        val employees = source.queryForList("SELECT * from employee") { rs ->
            rs.getInt("dept_id")
        }

        val department = source.queryForList("SELECT * from department") { rs ->
            rs.getInt("id")
        }

        val selectId = manager.select("department", department.first())
        assertTrue(selectId!!.isNotEmpty())

        val selectConditions = manager.select("employee", mapOf("name" to name))
        assertTrue(selectConditions.isNotEmpty())

        val selectIdEmpty = manager.select("department", 500)
        assertTrue(selectIdEmpty == null)

        val selectConditionsEmpty = manager.select("employee", mapOf("name" to "12345678901"))
        assertTrue(selectConditionsEmpty.isEmpty())

        for (employee in employees) {
            assertEquals(department[0], employee)
        }

        container.stop()
    }

    private fun createDataSource(url: String, user: String, pass: String): DataSource {
        val config = HikariConfig().apply {
            jdbcUrl = url
            username = user
            password = pass
        }

        return HikariDataSource(config)
    }

    fun <T> DataSource.queryForList(sql: String, rowMapper: (rs: ResultSet) -> T): List<T> {
        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {
            connection = this.connection
            preparedStatement = connection.prepareStatement(sql)
            resultSet = preparedStatement.executeQuery()

            val results = mutableListOf<T>()
            while (resultSet.next()) {
                val resultObject = rowMapper(resultSet) // You'll need to implement this function
                results.add(resultObject)
            }
            return results
        } finally {
            resultSet?.close()
            preparedStatement?.close()
            connection?.close()
        }
    }
}
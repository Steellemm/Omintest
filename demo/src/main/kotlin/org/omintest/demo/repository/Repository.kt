package org.omintest.demo.repository

import org.omintest.demo.controller.BusinessEntity
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class Repository(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) {
    val entityRowMapper: RowMapper<BusinessEntity> = RowMapper<BusinessEntity> { rs, _ ->
        BusinessEntity(
            rs.getString("id"),
            rs.getString("name")
        )
    }

    fun selectDepatrmentEmployee(id: Int): List<Int> {
        return jdbcTemplate.queryForList(
            """
                SELECT id
                FROM department d JOIN employee e on d.id = e.dept_id
                where d.id = :id
            """,
            mapOf("id" to id),
            Int::class.java
        )
    }

    fun putEntity(entity: BusinessEntity) {
        jdbcTemplate.update(
            """
            INSERT INTO business.entities VALUES (:id, :name)
        """,
            mapOf("id" to entity.id, "name" to entity.name)
        )
    }

    fun getEntity(id: String): BusinessEntity? {
        return jdbcTemplate.queryForObject(
            """
            select id, name
            from business.entities
            where id = :id
        """,
            mapOf("id" to id),
            entityRowMapper
        )
    }
}
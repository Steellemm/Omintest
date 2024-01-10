package org.omintest.demo.repository

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class Repository(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) {

    fun selectFirstDepatrment(): Int? {
        return jdbcTemplate.queryForObject(
            """
                SELECT id 
                FROM department
                LIMIT 1
            """,
            emptyMap<String, String>()
            ,
        Int::class.java
        )
    }
}
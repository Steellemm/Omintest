package org.omintest.step

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.omintest.api.model.DataBaseSetInfo
import org.omintest.api.model.SpringBootServiceInfo
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import javax.sql.DataSource


fun createDataSource(url: String, user: String, pass: String): DataSource {
    val config = HikariConfig().apply {
        jdbcUrl = url
        username = user
        password = pass
    }

    return HikariDataSource(config)
}

private val restTemplate = RestTemplate()

fun query(
    host: String,
    url: String,
    httpMethod: HttpMethod,
    requestEntity: HttpEntity<Any>,
    pathVariables: Map<String, Any>
): ResponseEntity<Any> {
    return restTemplate.exchange(
        "http://$host$url",
        httpMethod,
        requestEntity,
        Any::class.java,
        pathVariables
    )
}

fun getSpringBootServiceInfo(environment: Map<String, Any>): SpringBootServiceInfo {
    return SpringBootServiceInfo(
        set = getSet(environment),
        dbs = getDbs(environment)
    )
}

private fun getDbs(environment: Map<String, Any>): Map<String, DataBaseSetInfo> =
    if ((environment["database"]) == null) emptyMap() else {
        (environment["database"] as List<Map<String, String>>).associate {
            it["field-name"]!! to
                    DataBaseSetInfo(
                        urlPath = it["url-path"]!!,
                        userPath = it["user-path"]!!,
                        passwordPath = it["password-path"]!!
                    )
        }
    }

private fun getSet(environment: Map<String, Any>): Map<String, String> = getSetMapFromList(
    environment.getOrDefault(
        "set",
        emptyList<Any>()
    ) as List<Map<String, String>>
)

private fun getSetMapFromList(list: List<Map<String, String>>): Map<String, String> {
    return list.associate { it["key"]!! to it["values"]!! }
}
package org.omintest.step

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.omintest.api.ScenarioContext
import org.omintest.api.model.DataBaseSetInfo
import org.omintest.api.model.SpringBootServiceInfo
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.testcontainers.containers.GenericContainer
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
        "http://$host$url",//TODO ssl
        httpMethod,
        requestEntity,
        Any::class.java,
        pathVariables
    )
}

fun getFromContext(link: String, scenarioContext: ScenarioContext): String {
    var result = link
    val variables =
        "\\$\\{([\\w:.\\[\\]]+)}".toRegex().findAll(link).map { it.groupValues[1] }.toList()
    variables.forEach {
        val id = it.split(".").first()
        val keys = it.split(".").toMutableList().apply { removeFirst() }.joinToString(".")
        result = link.replace(
            "\${$it}",
            scenarioContext.getStepContext(id).getValue(keys).toString()
        )
    }
    return result
}

fun String.toKeysArray(): List<String> = this.split(".", "[").map{ it.removeSuffix("]") }
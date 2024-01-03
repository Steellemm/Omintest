package org.omintest.omintestextension.logic.step.http

import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

private val restTemplate = RestTemplate()

fun <T> get(url: String, port: String): String {
    return restTemplate.getForObject<String>("http://localhost:$port/$url")
}

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

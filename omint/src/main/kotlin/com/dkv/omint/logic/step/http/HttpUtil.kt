package com.dkv.omint.logic.step.http

import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

var port = "0"

private val restTemplate = RestTemplate()

fun <T> get(url: String, port: String): String {
    return restTemplate.getForObject<String>("http://localhost:$port/$url")
}

fun query(
    url: String,
    httpMethod: HttpMethod,
    requestEntity: HttpEntity<String>,
    pathVariables: Map<String, Any>
): ResponseEntity<String> {
    return restTemplate.exchange(
        "http://localhost:$port/$url",
        httpMethod,
        requestEntity,
        String::class.java,
        pathVariables
    )
}

package org.omintest.omintestextension.step

import org.mockserver.client.MockServerClient
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepField
import org.reflections.Reflections
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

fun getUtil(){}

val stepsConstructors = run {
    val reflections = Reflections("org")
    val classes: Set<Class<out SpringExtensionStep>> = reflections.getSubTypesOf(SpringExtensionStep::class.java)
    classes.associate { clazz ->
        clazz.getDeclaredConstructor(Map::class.java).newInstance(emptyMap<String, StepField>())
            .type() to clazz.getDeclaredConstructor(Map::class.java)
    }
}

val StepFieldsConstructors = run {
    val reflections = Reflections("org")
    val classes: Set<Class<out StepField>> = reflections.getSubTypesOf(StepField::class.java)
    classes.associate { it.simpleName to it.getDeclaredConstructor(Any::class.java)  }
}

fun createMock(port: Int) {
    val mockServerClient =
        MockServerClient("localhost", port) // Connect to the MockServer instance running on localhost:1080

    mockServerClient.`when`(
        HttpRequest.request()
            .withMethod("GET")
            .withPath("/get")
            .withHeader("Content-Type", "application/json")
    ).respond(
        HttpResponse.response()
            .withStatusCode(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{ \"mockedResponse\": \"example\" }")
    )

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
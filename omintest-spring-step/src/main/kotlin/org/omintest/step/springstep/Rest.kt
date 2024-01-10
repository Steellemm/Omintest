package org.omintest.step.springstep

import org.omintest.api.ScenarioContext
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepContext
import org.omintest.api.StepField
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.omintest.step.SpringStepContext
import org.omintest.step.query

class Rest(override val input: Map<String, StepField>) : SpringExtensionStep {

    override fun execute(context: ScenarioContext): StepContext {
        val body = input["body"]?.getValue(context)
        val headers = HttpHeaders()
        input["headers"]?.let {
            (it.getValue(context) as Map<String, String>).forEach { entry ->
                headers.add(
                    entry.key,
                    entry.value
                )
            }
        }

        val req = HttpEntity<Any>(body, headers)

        val response = query(
            input.getValue("host").getValue(context) as String,
            input.getValue("url").getValue(context) as String,
            HttpMethod.valueOf(input.getValue("type").getValue(context) as String),
            req,
            emptyMap()
        )

        return SpringStepContext(
            mapOf(
                "body" to (response.body ?: ""),
                "headers" to (response.headers),
                "status" to response.statusCode.value(),
            )
        )
    }
}



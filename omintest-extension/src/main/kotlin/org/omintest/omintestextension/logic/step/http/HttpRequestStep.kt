package org.omintest.omintestextension.logic.step.http

import org.omintest.omintestextension.enviroment.model.StepData
import org.omintest.omintestextension.logic.OmintTestTestContext
import org.omintest.omintestextension.logic.step.Step
import org.springframework.http.*

class HttpRequestStep(override val id: String, val step: Map<String, StepData>) : Step {

    override fun execute(omintTestContext: OmintTestTestContext) {
        println("Executing $id")
        val body = step["body"]?.let { omintTestContext.get(it) }
        val headers = HttpHeaders()
        step["headers"]?.let {
            (omintTestContext.get(it) as Map<String, String>).forEach { entry ->
                headers.add(
                    entry.key,
                    entry.value
                )
            }
        }

        val req = HttpEntity<Any>(body, headers)

        val response = query(
            omintTestContext.get(step["host"]!!) as String,
            omintTestContext.get(step["url"]!!) as String,
            HttpMethod.valueOf(omintTestContext.get(step["type"]!!) as String),
            req,
            emptyMap()
        )

        omintTestContext.results.putAll(
            mapOf(
                id to mapOf(
                    "body" to (response.body ?: ""),
                    "headers" to (response.headers),
                    "status" to response.statusCode.value(),
                )
            )
        )
    }

    companion object {
        const val stepName = "Up"
    }
}



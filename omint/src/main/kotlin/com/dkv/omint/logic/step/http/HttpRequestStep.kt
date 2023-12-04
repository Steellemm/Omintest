package com.dkv.omint.logic.step.http

import com.dkv.omint.logic.OmintTestTestContext
import com.dkv.omint.logic.step.Step
import org.springframework.http.*
import java.net.URI

class HttpRequestStep(step: Map<String, Any>) : Step {
    private val request: Request
    //private val responseEntity: ExpectedResponse?

    init {
        this.request = Request(
            requestEntity = getRequestEntity(step),
            pathVariables = step.getOrDefault("path_variables", emptyMap<String, Any>()) as Map<String, Any>,
            (step["url"] as String)
        )
        //this.responseEntity = getResponseEntity(step)
    }

    //path, request, example, response
    override fun execute(omintTestContext: OmintTestTestContext): Map<String, Any> {
        val response = query(
            request.requestEntity.url.toString(),
            request.requestEntity.method!!,
            request.requestEntity,
            request.pathVariables
        )
        println("Response done")

        return emptyMap()
    }


    private fun getResponseEntity(step: Map<String, Any>): ExpectedResponse? {
        val checkResponse = step.getOrDefault("check-response", emptyMap<String, Any>()) as Map<String, Any>
        return if (checkResponse.isNotEmpty()) {
            ExpectedResponse(
                //checkResponse.getOrDefault("headers", emptyMap<String, String>()) as MultiValueMap<String, String>,
                HttpStatusCode.valueOf(checkResponse["code"] as Int),
                step.getOrDefault("body", null)?.toString(),
                null
            )
        } else null
    }

    private fun getRequestEntity(step: Map<String, Any>): RequestEntity<String> {
        return RequestEntity<String>(
            step.getOrDefault("body", null)?.toString(),
            HttpMethod.valueOf(step["type"] as String),
            URI((step["url"] as String)),
        )
    }

    private data class ExpectedResponse(
        val statusCode: HttpStatusCode,
        val expectedFields: String?,
        val headers: HttpHeaders?
    )

    companion object {
        const val stepName = "RestRequest"
    }
}



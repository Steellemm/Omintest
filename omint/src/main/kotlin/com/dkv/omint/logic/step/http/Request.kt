package com.dkv.omint.logic.step.http

import org.springframework.http.RequestEntity

data class Request (
    val requestEntity: RequestEntity<String>,
    val pathVariables: Map<String, Any>,
    val url: String
)
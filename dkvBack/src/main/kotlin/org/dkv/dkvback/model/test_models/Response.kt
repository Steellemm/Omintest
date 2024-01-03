package org.dkv.dkvback.model.test_models

data class Response(
    val code: Int,
    val body: Body
)

data class Body(
    val assertEquals: AssertEquals
)

data class AssertEquals(
    val field: String,
    val expectedValue: String
)

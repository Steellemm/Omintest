package org.dkv.dkvback.DTO


data class Scenario(
    val name: String,
    val steps: List<Step>
)

data class Step (
    val uses: String,
    val with: MutableMap<String, Any> = mutableMapOf()
)
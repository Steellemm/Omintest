package org.dkv.dkvback.model

data class Module(
    val displayName: String,
    val type: String,
    val description: String,
    val fields: List<Field>
)

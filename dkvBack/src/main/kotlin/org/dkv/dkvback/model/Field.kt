package org.dkv.dkvback.model

data class Field(
    val displayName: String,
    val required: Boolean = false,
    val name: String? = null,
    val type: String? = null,
    val parameters: List<Parameter>? = null,
    val url: String? = null
)

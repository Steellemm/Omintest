package org.dkv.dkvback.model

data class Parameter(
    val filter: Filter? = null,
    val options: List<Option>? = null,
    val subtype: String? = null,
    val whenTrue: List<Field>? = null,
    val whenFalse: List<Field>? = null,
    val url: String? = null,
    val additionalFields: List<Field>? = null
)

data class Filter(
    val byType: String,
    val byValue: Value
)

data class Option(
    val displayName: String,
    val additionalFileds: List<Field>?,
)

data class Value(
    val filed: String,
    val eq: String
)
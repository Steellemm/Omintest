package org.dkv.dkvback.model.test_models

data class DBSettings(
    val table: String,
    val params: MutableMap<String, Any>
)

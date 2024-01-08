package org.omintest.api.model

data class SpringBootServiceInfo(
    val set: Map<String, String>,
    /**
     * Имя поля в SpringExtensionStep против информации, куда записывать креды БД
     */
    val dbs: Map<String, DataBaseSetInfo>
)

data class DataBaseSetInfo(
    val urlPath: String,
    val userPath: String,
    val passwordPath: String
)

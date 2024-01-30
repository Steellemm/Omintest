package org.omintest.step

import org.omintest.api.Environment
import org.omintest.api.model.DataBaseSetInfo
import org.omintest.api.model.SpringBootServiceInfo

class OmintestEnvironment(val environment: Map<String, Map<String, Any>>): Environment {

    override fun getServiceInfo(name: String): SpringBootServiceInfo {
        val service = (environment["service"] as Map<String, Map<String, Any>>)[name]!!
        return getSpringBootServiceInfo(service)
    }

    override fun getDatabaseInfo(name: String): Map<String, String> {
        return (environment["database"] as Map<String, Map<String, String>>)[name]!!
    }

    private fun getSpringBootServiceInfo(environment: Map<String, Any>): SpringBootServiceInfo {
        return SpringBootServiceInfo(
            set = getSet(environment),
            dbs = getDbs(environment)
        )
    }

    private fun getDbs(environment: Map<String, Any>): Map<String, DataBaseSetInfo> =
        if ((environment["database"]) == null) emptyMap() else {
            (environment["database"] as List<Map<String, String>>).associate {
                it["field-name"]!! to
                        DataBaseSetInfo(
                            urlPath = it["url-path"]!!,
                            userPath = it["user-path"]!!,
                            passwordPath = it["password-path"]!!
                        )
            }
        }

    private fun getSet(environment: Map<String, Any>): Map<String, String> = getSetMapFromList(
        environment.getOrDefault(
            "set",
            emptyList<Any>()
        ) as List<Map<String, String>>
    )

    private fun getSetMapFromList(list: List<Map<String, String>>): Map<String, String> {
        return list.associate { it["key"]!! to it["values"]!! }
    }

}
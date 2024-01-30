package org.omintest.step

import org.testcontainers.containers.GenericContainer

private val user = "test"
private val password = "test"
private val database = "test"

fun createNewContainer(databaseSettings: Map<String, String>): GenericContainer<*> {
    return GenericContainer(databaseSettings["image"]!!).withEnv(
        mapOf(
            databaseSettings["database"]!! to "test",
            databaseSettings["user"]!! to "test",
            databaseSettings["password"]!! to "test"
        )
    ).withExposedPorts(5432)
}

fun GenericContainer<*>.getJdbc(jdbcServer: String): String =
    "jdbc:$jdbcServer://${this.containerIpAddress}:${this.firstMappedPort}/$database"

fun GenericContainer<*>.getUser(): String = user

fun GenericContainer<*>.getPassword(): String = password
package org.omintest.coredb.containers

import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName


object PosgreContainer {

    private const val POSTGRES_IMAGE = "postgres:12-alpine"
//    private const val VERSION_TAG = "12-alpine"  // Replace with the desired version tag
//    private const val REGISTRY = "docker.io/library"  // Assuming you are using the default Docker Hub registry

    @JvmStatic
    fun container(): PostgreSQLContainer<Nothing> = PostgreSQLContainer<Nothing>(POSTGRES_IMAGE)

}
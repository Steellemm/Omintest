package org.omintest.step.springstep

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.omintest.api.ScenarioContext
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepContext
import org.omintest.api.StepField
import org.omintest.coredb.manager.CommonOmtEntityManager
import org.omintest.coredb.model.DbModel
import org.testcontainers.containers.PostgreSQLContainer
import org.omintest.step.DBStepContext
import org.omintest.step.OmintestTestContext
import org.omintest.step.createDataSource
import java.io.File

class UpDB(override val input: Map<String, StepField>) : SpringExtensionStep {
    override fun execute(context: ScenarioContext): StepContext {
        val name = input["database-name"]!!.getValue(context).toString()
        context as OmintestTestContext
        val databseSettings = context.environment["database"]!![name] as Map<String, String>
        context.environment["database"]
        val container = PostgreSQLContainer<Nothing>("postgres:12-alpine")
        container.start()
        context.containers.add(container)

        val source = createDataSource(container.jdbcUrl, container.username, container.password)
        val objectMapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
        val dbModelFromYml: DbModel = objectMapper.readValue(File("src/test/resources/model.yml"), DbModel::class.java)
        val entityManager = CommonOmtEntityManager(source, dbModelFromYml)

        container.start()

        return DBStepContext(mapOf("database-name" to name, "manager" to entityManager), container.jdbcUrl, container.username, container.password)
    }

}
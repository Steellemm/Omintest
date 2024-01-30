package org.omintest.step.springstep

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.omintest.api.ScenarioContext
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepContext
import org.omintest.api.StepField
import org.omintest.api.documentation.OmintStepInfo
import org.omintest.coredb.manager.CommonOmtEntityManager
import org.omintest.coredb.model.DbModel
import org.omintest.step.*
import java.io.File

@OmintStepInfo("upDB")
class UpDBStep(override val input: Map<String, StepField>) : SpringExtensionStep {

    private val objectMapper = ObjectMapper(YAMLFactory()).registerModule(
        KotlinModule.Builder()
            .withReflectionCacheSize(512)
            .configure(KotlinFeature.NullToEmptyCollection, false)
            .configure(KotlinFeature.NullToEmptyMap, false)
            .configure(KotlinFeature.NullIsSameAsDefault, false)
            .configure(KotlinFeature.SingletonSupport, false)
            .configure(KotlinFeature.StrictNullChecks, false)
            .build()
    )

    override fun execute(context: ScenarioContext): StepContext {
        val name = input["database-name"]!!.getValue(context).toString()
        val databaseSettings = context.getEnvironment().getDatabaseInfo(name)
        context as OmintestTestContext
        val container = if (context.containers.containsKey(name)) {
            context.containers[name]!!
        } else {
            createNewContainer(databaseSettings).also { context.containers[name] = it }
        }

        container.start()

        val url = container.getJdbc(databaseSettings["server"]!!)
        val source = createDataSource(url, container.getUser(), container.getPassword())
        val dbModelFromYml: DbModel = objectMapper.readValue(File("src/test/resources/model.yml"), DbModel::class.java)
        val entityManager = CommonOmtEntityManager(source, dbModelFromYml)
        databaseSettings["schema"]?.let { entityManager.initialize(it)} ?: entityManager.initialize()

        return DBStepContext(
            mapOf("database-name" to name, "manager" to entityManager),
            url,
            container.getUser(),
            container.getPassword()
        )
    }


}
package org.omintest.step.springstep

import org.omintest.api.ScenarioContext
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepContext
import org.omintest.api.StepField
import org.omintest.api.documentation.OmintStepInfo
import org.omintest.step.DBStepContext
import org.omintest.step.OmintestTestContext
import org.omintest.step.SpringStepContext
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.test.context.BootstrapUtils

@OmintStepInfo("upService")
class UpServiceStep(
    override val input: Map<String, StepField>,
) : SpringExtensionStep {

    override fun execute(context: ScenarioContext): StepContext {
        if (input["type"]!!.getValue(context) == "SERVICE") {
            val serviceInfo = context.getEnvironment().getServiceInfo(input["name"]!!.getValue(context).toString())
            (context as OmintestTestContext)
            val db =
                input["database-1"]?.getValue(context).toString().let { context.getStepContext(it) as DBStepContext }
            if (db != null && context.applicationContext == null) {
                val dbs = serviceInfo.dbs["database-1"]!!
                System.setProperty(dbs.urlPath, db.getUrl())
                System.setProperty(dbs.userPath, db.getUser())
                System.setProperty(dbs.passwordPath, db.getPassword())
            }

            serviceInfo.set.forEach {
                System.setProperty(it.key, it.value.toString())
            }


            /**
             * Если в нашем контексте нет спринг контекста, то получаем его с помощью тестового контекста,
             * который получаем через тестовый класс. Иначе используем главный класс полученный из этого
             * контекста для получения нового спринг контекста.
             */
            if (context.mainClass == null) {
                context.applicationContext = BootstrapUtils.resolveTestContextBootstrapper(context.testClass)
                    .buildTestContext().applicationContext
                context.mainClass =
                    context.applicationContext!!.getBeansWithAnnotation(SpringBootApplication::class.java)
                        .values
                        .firstOrNull()
                        ?.javaClass
            } else {
                context.applicationContext = SpringApplication(context.mainClass).run()
            }


            val host = "localhost:" + context.applicationContext?.environment?.getProperty("local.server.port")!!
            return SpringStepContext(mapOf("host" to host))

        }

        throw Exception("No up")
    }

}


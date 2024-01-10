package org.omintest.step.springstep

import org.omintest.api.ScenarioContext
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepContext
import org.omintest.api.StepField
import org.omintest.step.DBStepContext
import org.omintest.step.OmintestTestContext
import org.omintest.step.SpringStepContext
import org.omintest.step.getSpringBootServiceInfo

class UpService(
    override val input: Map<String, StepField>,
) : SpringExtensionStep {

    override fun execute(context: ScenarioContext): StepContext {
        if (input["type"]!!.getValue(context) == "SERVICE") {
            val service =
                ((context as OmintestTestContext).environment["service"] as Map<String, Map<String, Any>>)[input["name"]!!.getValue(
                    context
                )]!!
            if (service["type"] as String == "spring-boot-application"
            ) {
                val serviceInfo = getSpringBootServiceInfo(service)
                val db = input["database-1"]?.getValue(context) as DBStepContext
                if (db != null) {
                    val dbs = serviceInfo.dbs["database-1"]!!
                    System.setProperty(dbs.urlPath, db.getUrl())
                    System.setProperty(dbs.userPath, db.getUser())
                    System.setProperty(dbs.passwordPath, db.getPassword())
                }
//                serviceInfo.set.forEach { (k, v) ->
//                    System.setProperty(k.toString(), v.toString())
//                }
                context.applicationContext = context.testContext?.applicationContext
                val host = "localhost:" + context.applicationContext?.environment?.getProperty("local.server.port")!!
                return SpringStepContext(mapOf("host" to host))
            }
        }

        throw Exception("No up")
    }

}


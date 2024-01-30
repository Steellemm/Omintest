package org.omintest.step.springstep

import org.omintest.api.ScenarioContext
import org.omintest.api.SpringExtensionStep
import org.omintest.api.StepContext
import org.omintest.api.StepField
import org.omintest.api.documentation.OmintStepInfo
import org.omintest.coredb.manager.CommonOmtEntityManager
import org.omintest.coredb.manager.OmtEntityManager
import org.omintest.step.SpringStepContext

@OmintStepInfo("dataBase")
class DataBaseStep(override val input: Map<String, StepField>) : SpringExtensionStep {
    override fun execute(context: ScenarioContext): StepContext {

        val manager = context.getStepContext(input["database"]!!.getValue(context).toString())
            .getValue("manager") as OmtEntityManager
        val table = input["table"]!!.getValue(context).toString()
        val values = input["values"]?.let { it.getValue(context) }
        val conditions = input["conditions"]?.let { it.getValue(context) }
        val id = input["id"]?.let { it.getValue(context) }
        val stepContext = when (input["action"]!!.getValue(context).toString()) {
            "add" -> manager.insert(table, values?.let { it as Map<String, Any> }).values
            "get" -> if (id != null) {
                manager.select(table, id)
            } else {
                manager.select(table, values as Map<String, Any?>)
            }
            else -> throw IllegalStateException("no such action with data base")
        }

        return SpringStepContext(mapOf("values" to stepContext))
    }
}
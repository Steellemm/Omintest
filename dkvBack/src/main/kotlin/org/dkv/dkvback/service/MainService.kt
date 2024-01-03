package org.dkv.dkvback.service

import org.dkv.dkvback.DTO.Scenario
import org.dkv.dkvback.DTO.Step
import org.dkv.dkvback.model.test_models.AssertEquals
import org.dkv.dkvback.model.test_models.Body
import org.dkv.dkvback.model.test_models.DBSettings
import org.dkv.dkvback.model.test_models.Response
import org.springframework.stereotype.Service

@Service
class MainService {
    fun processRequest(scenario: Scenario): Scenario {
        for (step in scenario.steps) {
            when (step.uses) {
                "PostgreSQL" -> {
                    processDb(step)
                }

                "RestRequest" -> {
                    processRest(step)
                }
            }
        }

        return scenario
    }

    private fun processRest(step: Step) {
        step.with["url"] = "puppyService/puppy/1"
        step.with["check"] = mutableMapOf<String, Any>()
        (step.with["check"] as MutableMap<String, Any>)["response"] = Response( //тут будет загружаться инфа с генераторов
            200,
             Body(
                 AssertEquals(
                     "name",
                     "name"
                 )
             )
        )
    }

    private fun processDb(step: Step) {
        step.with["add"] = DBSettings(
            "Puppies",
            mutableMapOf(
                "puppy_best_friend" to "puppyName", // сюда сгенеренное значение
                "age" to 4
            )
        )
    }

    fun getType(type: String) {

    }
}
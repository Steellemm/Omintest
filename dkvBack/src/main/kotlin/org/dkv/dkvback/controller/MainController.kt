package org.dkv.dkvback.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.dkv.dkvback.DTO.Scenario
import org.dkv.dkvback.DTO.Step
import org.dkv.dkvback.config.property.ModuleProperties
import org.dkv.dkvback.model.Field
import org.dkv.dkvback.model.Module
import org.dkv.dkvback.model.Parameter
import org.dkv.dkvback.model.Square
import org.dkv.dkvback.service.MainService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController(val mainService: MainService, val module: ModuleProperties) {
    @GetMapping("/squares")
    @CrossOrigin("http://localhost:5173/")
    fun getSquares(): List<Square> {
        return listOf(Square(1, "post"), Square(2, "el"), Square(3, "mongo"))
    }
// делать два эндпоинта, один который отдает квадраты, второй возвращает заполненные
    @PostMapping("/test")
    fun test(): List<Step> {
        val result: Scenario = jacksonObjectMapper().readValue("""
            {
                "name": "Test",
                "steps": [
                    {
                        "uses": "PostgreSQL"
                    },
                    {
                        "uses": "RestRequest"
                    }
                ]
            }
        """.trimIndent())

        return mainService.processRequest(result).steps
    }

    @GetMapping("/modules")
    fun getModuleConfig(): List<Module>? {
        return module.modules
    }

    @GetMapping("/type/{type}")
    fun getType(@PathVariable type: String) {
        mainService.getType(type)
    }
}
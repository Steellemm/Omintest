package org.omintest.demo.controller

import org.omintest.demo.repository.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EntityController(val repository: Repository) {

    @PutMapping("/put/entity")
    fun putEntity(@RequestBody entity: BusinessEntity): Map<String, String> {
        repository.putEntity(entity)
        return mapOf("id" to entity.id)
    }

    @GetMapping("/get/entity/{id}")
    fun getEntity(@PathVariable id: String): BusinessEntity? {
        return repository.getEntity(id)
    }
}

data class BusinessEntity(
    val id: String,
    val name: String
)
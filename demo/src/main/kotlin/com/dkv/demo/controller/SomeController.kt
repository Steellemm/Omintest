package com.dkv.omint.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SomeController {
    var id = 0

    @GetMapping("/someurl")
    fun testGetEndpoint(): Int {
        return id
    }

    @PostMapping("/someurl/{id}")
    fun testPostEndpoint(@PathVariable id: Int) {
         this.id = id
    }

}
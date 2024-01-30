package org.omintest.demo.controller

import org.omintest.demo.servise.MockedService
import org.omintest.demo.servise.SomeFeign
import com.fasterxml.jackson.annotation.JsonProperty
import org.omintest.demo.repository.Repository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class SomeController(
    val mockedService: MockedService,
    val someFeign: SomeFeign,
    val repository: Repository
) {
    var id = 0

    @PostMapping("/someurl")
    fun testGetEndpoint(@RequestBody(required = false) req: TestRequest?): TestResponse {
        println(req)
        return TestResponse(id)
    }

    @PostMapping("/someurl/{id}")
    fun testPostEndpoint(@PathVariable id: Int) {
         this.id = id
    }

    @GetMapping("/someurl/mock")
    fun getFromMock(): String? {
        return mockedService.getFromMock()
    }

    @GetMapping("/get")
    fun getFromFeign(): String? {
        return someFeign.getSome()
    }

    @GetMapping("/testget")
    fun getTest(): ResponseEntity<String> {
        return ResponseEntity<String>("test", null, HttpStatus.OK)
    }

    @GetMapping("/employers/{department}")
    fun getEmployers(@PathVariable("department") department: Int): List<Int> {
        return repository.selectDepatrmentEmployee(department)
    }
}

data class TestResponse(
    val id: Int
)

data class TestRequest(
    @JsonProperty("flag")
    val flag: Boolean,
    @JsonProperty("date")
    val date: LocalDate,
)
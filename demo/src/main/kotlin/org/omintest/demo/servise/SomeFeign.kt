package org.omintest.demo.servise

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "some",
    url = "\${services.feign:some}"
    //url = "http://localhost:1080"
    //url = "http://www.testingmcafeesites.com/"
)
interface SomeFeign {
    @GetMapping("/testget")
    fun getSome(): String
}
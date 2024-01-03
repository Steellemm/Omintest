package org.omintest.demo.servise

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
class MockedService(
    private val restTemplate: RestTemplate,
    @Value("\${services.rest:rest}")
    val restHost: String
) {
    fun getFromMock(): String? {
        return restTemplate.getForObject<String>("http://$restHost/get", String::class.java, emptyMap<String, String>())
    }
}

data class DataFromMock(
    val id: UUID,
    val string: String
)
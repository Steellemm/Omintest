package com.dkv.demo

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.TestPropertySource


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableFeignClients
//@Profile("test")
@TestPropertySource(locations = ["classpath:test.properties"])
class HierarchticalResearch {

    @LocalServerPort
    private var port: Int = 0

    private val restTemplate = TestRestTemplate()

    @Value("\${services.feign}")
    private lateinit var mockServiceUrl: String

    @Test
    fun testYourEndpoint() {
        wireMockServer.start()

        wireMockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/testget"))
                .willReturn(WireMock.aResponse().withStatus(200).withBody("Mocked Response"))
        )

        val a = restTemplate.getForEntity("http://localhost:$port/get", String::class.java)
        println(a)
        wireMockServer.stop()

    }

    companion object {
        private val wireMockServer: WireMockServer = WireMockServer(1080)

        @BeforeAll
        @JvmStatic
        fun setUp() {
            wireMockServer.start()
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            wireMockServer.stop()
        }

    }

}


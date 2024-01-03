package org.omintest.omintestextension.logic.step.up

import org.springframework.context.ApplicationContext
import org.springframework.test.context.TestContextManager
import org.springframework.web.client.RestTemplate
import java.util.function.Supplier


fun SpringUp() {

}

private fun create(
    testContextManagerSupplier: Supplier<TestContextManager>,
): ApplicationContext {

    val delegate = testContextManagerSupplier.get()
    val testContext = delegate.testContext
    return testContext.applicationContext

}

fun createMockS(port: Int) {
//    val mockServer = MockServer(8080)
//    val mockServerClient = MockServerClient( "localhost", 8080)
//
//    val httpRequest = org.mockserver.model.HttpRequest.request()
//        .withMethod("GET")
//        .withPath("/get")
//
//    val httpResponse = HttpResponse.response()
//        .withStatusCode(HttpStatusCode.OK_200.code())
//        .withBody("{\"Mocked\": \"Response\"}")
//
//    mockServerClient
//        .`when`(httpRequest)
//        .respond(httpResponse)
//
//    val proxyServer = DefaultHttpProxyServer.bootstrap()
//        .withPort(80)
//        .withProxyAlias("http://mock")
//        .withFiltersSource(object : HttpFiltersSourceAdapter() {
//            override fun filterRequest(originalRequest: io.netty.handler.codec.http.HttpRequest, ctx: ChannelHandlerContext?): HttpFilters? {
//                // Modify the originalRequest and set the new URL in the Location header
//                if (originalRequest.uri.contains("http://mock")) {
//                    originalRequest.uri =  originalRequest.uri.replace("http://mock", "http://localhost:8080")
//                    return super.filterRequest(originalRequest, ctx)
//                }
//                // If it's not the URL to redirect, pass the request through as is
//                return super.filterRequest(originalRequest, ctx)
//            }
//        })
//        .start()

//    embeddedServer(Netty, port = 8080) {
//        routing {
//            get("/") {
//                call.respondText("Hello, world!")
//            }
//            get("/mock/get") {
//                call.respondText("Handling http://mock/get")
//            }
//        }
//    }.start(wait = false)private val mockServerClient = MockServerClient("localhost", 8080) // Connect to the MockServer instance running on localhost:1080
//
//    fun setUpMockedResponseForDemoServiceRequest() {
//        // Mock the response for the request to "http://someurl/someendpoint"
//        mockServerClient.`when`(
//            request()
//                .withMethod("GET")
//                .withPath("/get")
//                .withHeader("Content-Type", "application/json")
//        ).respond(
//            response()
//                .withStatusCode(200)
//                .withHeader("Content-Type", "application/json")
//                .withBody("{ \"mockedResponse\": \"example\" }")
//        )
//    }



    val restTemplate = RestTemplate()
    val responseEntity = restTemplate.getForEntity("http://mock/get", String::class.java)
    println(responseEntity.body)

}


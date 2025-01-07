package io.kotest.extensions.wiremock

import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.fuel.httpGet
import com.github.tomakehurst.wiremock.WireMockServer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class WiremockListenerPerTestTest : FunSpec({
   val wireMockServer = WireMockServer(9000)

   listener(WireMockListener.perTest(wireMockServer))

   afterSpec {
      val (request, response, result) = "http://localhost:9000/test".httpGet().response()
      response.isSuccessful shouldBe false
   }

   test("should have started wiremock server") {
      wireMockServer.stubOk("/test")

      val (request, response, result) = "http://localhost:9000/test".httpGet().response()
      response.statusCode shouldBe 200
   }

   test("should have started wiremock server for second test as well") {
      wireMockServer.stubOk("/second-test")
      val (request, response, result) = "http://localhost:9000/second-test".httpGet().response()
      response.statusCode shouldBe 200
   }
})

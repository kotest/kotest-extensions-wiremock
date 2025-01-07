package io.kotest.extensions.wiremock

import com.github.kittinunf.fuel.httpGet
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class WiremockListenerPerProjectTest : FunSpec({
   test("should have started wiremock server") {
      stubOk("/test")

      val (request, response, result) = "http://localhost:9001/test".httpGet().response()
      response.statusCode shouldBe 200
   }

   test("should have started wiremock server for second test as well") {
      stubOk("/second-test")
      val (request, response, result) = "http://localhost:9001/second-test".httpGet().response()

      response.statusCode shouldBe 200
   }
})


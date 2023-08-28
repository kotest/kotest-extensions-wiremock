package io.kotest.extensions.wiremock

import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.net.HttpURLConnection
import java.net.URL

@Suppress("BlockingMethodInNonBlockingContext")
class WiremockListenerPerProjectTest : FunSpec({
   test("should have started wiremock server") {
      stubFor(
         get(urlEqualTo("/test"))
            .willReturn(ok())
      )
      val connection = URL("http://localhost:9001/test").openConnection() as HttpURLConnection
      connection.responseCode shouldBe 200
   }

   test("should have started wiremock server for second test as well") {
      stubFor(
         get(urlEqualTo("/second-test"))
            .willReturn(ok())
      )
      val connection = URL("http://localhost:9001/second-test").openConnection() as HttpURLConnection
      connection.responseCode shouldBe 200
   }
})

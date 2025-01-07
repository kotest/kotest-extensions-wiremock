package io.kotest.extensions.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.stubbing.StubMapping

fun stubOk(url: String) {
   stubFor(
      get(urlEqualTo(url))
         .willReturn(ok())
   )
}

fun WireMockServer.stubOk(url: String): StubMapping = stubFor(
   get(urlEqualTo(url))
)

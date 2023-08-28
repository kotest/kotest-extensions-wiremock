package io.kotest.extensions.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import io.kotest.core.config.AbstractProjectConfig

object ProjectConfig : AbstractProjectConfig() {
    const val WIREMOCK_SERVER_PORT = 9001

    val wiremock = WireMockServer(WIREMOCK_SERVER_PORT)
    val wiremockListener = WireMockListener(wiremock, ListenerMode.PER_PROJECT)

    override fun extensions() = listOf(wiremockListener)

   override suspend fun beforeProject() {
      WireMock.configureFor(WIREMOCK_SERVER_PORT)
   }
}

package io.kotest.extensions.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import io.kotest.core.config.AbstractProjectConfig

object ProjectConfig : AbstractProjectConfig() {
    private const val ProjectWiremockServerPort = 9001

    private val projectWiremock = WireMockServer(ProjectWiremockServerPort)
    private val wiremockListener = WireMockListener.perProject(projectWiremock)

    override fun extensions() = listOf(wiremockListener)

   override suspend fun beforeProject() {
      WireMock.configureFor(ProjectWiremockServerPort)
   }
}

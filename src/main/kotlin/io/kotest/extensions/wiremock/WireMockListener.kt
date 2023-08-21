package io.kotest.extensions.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import io.kotest.core.listeners.ProjectListener
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.Spec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult

/**
 * WiremockListener starts the given wiremock server before all project tests or every spec/test
 * and stop that after all project tests or every spec/test based on [listenerMode].
 *
 * @see [ListenerMode]
 * */
class WireMockListener(
   private val server: WireMockServer,
   private val listenerMode: ListenerMode
) : TestListener, ProjectListener {

   override suspend fun beforeTest(testCase: TestCase) {
      if (listenerMode == ListenerMode.PER_TEST) {
         server.start()
      }
   }

   override suspend fun afterTest(testCase: TestCase, result: TestResult) {
      if (listenerMode == ListenerMode.PER_TEST) {
         server.stop()
      }
   }

   override suspend fun beforeSpec(spec: Spec) {
      if (listenerMode == ListenerMode.PER_SPEC) {
         server.start()
      }
   }

   override suspend fun afterSpec(spec: Spec) {
      if (listenerMode == ListenerMode.PER_SPEC) {
         server.stop()
      }
   }

   override suspend fun beforeProject() {
      if (listenerMode == ListenerMode.PER_PROJECT) {
         server.start()
      }
   }

   override suspend fun afterProject() {
      if (listenerMode == ListenerMode.PER_PROJECT) {
         server.stop()
      }
   }

   companion object {
      fun perSpec(wireMockServer: WireMockServer) = WireMockListener(wireMockServer, ListenerMode.PER_SPEC)

      fun perTest(wireMockServer: WireMockServer) = WireMockListener(wireMockServer, ListenerMode.PER_TEST)

      fun perProject(wireMockServer: WireMockServer) = WireMockListener(wireMockServer, ListenerMode.PER_PROJECT)
   }
}

enum class ListenerMode {
   PER_TEST,
   PER_SPEC,
   PER_PROJECT
}

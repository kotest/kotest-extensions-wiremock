package io.kotest.extensions.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import io.kotest.assertions.json.schema.jsonSchema
import io.kotest.assertions.json.schema.number
import io.kotest.assertions.json.schema.obj
import io.kotest.assertions.json.schema.string
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.StringSpec
import io.restassured.RestAssured
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When

@OptIn(ExperimentalKotest::class)
@Suppress("BlockingMethodInNonBlockingContext")
class MatchJsonSchemaPatternTest : StringSpec(
   {
      RestAssured.baseURI = "http://localhost:9000"

      val wireMockServer = WireMockServer(9000)
      listener(WireMockListener.perSpec(wireMockServer))

      val personSchema =
         jsonSchema {
            obj {
               withProperty("name", true) { string() }
               withProperty("age", true) { number() }
               withProperty("eye-color") { string() }
            }
         }

      wireMockServer.stubFor(
         WireMock.post("/match-schema-1")
            .withRequestBody(matchJsonSchema(personSchema))
            .willReturn(WireMock.created())
      )

      "body matches schema" {
         Given {
            body(
               """
               {
                  "name": "cassandra",
                  "age": 2
               }
               """.trimIndent()
            )
         } When {
            post("/match-schema-1")
         } Then {
            statusCode(201)
         }
      }

      "Failure is handled ok" {
         Given {
            body(
               """
               {
                  "_name": "cassandra",
                  "age": 2
               }
               """.trimIndent()
            )
         } When {
            post("/match-schema-1")
         } Then {
            statusCode(404)
         }
      }
   }
)

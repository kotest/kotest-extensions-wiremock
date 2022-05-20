@file:Suppress("RedundantVisibilityModifier")

package io.kotest.extensions.wiremock

import com.github.tomakehurst.wiremock.matching.MatchResult
import com.github.tomakehurst.wiremock.matching.StringValuePattern
import io.kotest.assertions.json.schema.JsonSchema
import io.kotest.assertions.json.schema.shouldMatchSchema
import wiremock.com.fasterxml.jackson.annotation.JsonProperty

public class MatchJsonSchemaPattern(
   @JsonProperty("schema") private val schema: JsonSchema
) : StringValuePattern("json schema") {
   override fun match(value: String?): MatchResult =
      try {
         value shouldMatchSchema schema
         MatchResult.exactMatch()
      } catch (e: AssertionError) {
         MatchResult.noMatch()
      }
}

public fun matchJsonSchema(schema: JsonSchema) = MatchJsonSchemaPattern(schema)

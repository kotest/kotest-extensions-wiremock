import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
   `java-library`
   signing
   `maven-publish`
   kotlin("jvm") version "1.9.21"
}

group = "io.kotest.extensions"
version = Ci.version

repositories {
   mavenCentral()
   maven {
      url = uri("https://oss.sonatype.org/content/repositories/snapshots")
   }
}

dependencies {
   api(libs.wiremock)
   implementation(libs.kotest.api)
   testImplementation(libs.kotest.runner)
   testImplementation(libs.kotest.assertions)
}

tasks.test {
   useJUnitPlatform()
   testLogging {
      showExceptions = true
      showStandardStreams = true
      exceptionFormat = TestExceptionFormat.FULL
   }
}

tasks.withType<KotlinCompile> {
   kotlinOptions.jvmTarget = "11"
}

val signingKey: String? by project
val signingPassword: String? by project

val publications: PublicationContainer = (extensions.getByName("publishing") as PublishingExtension).publications

signing {
   useGpgCmd()
   if (signingKey != null && signingPassword != null) {
      @Suppress("UnstableApiUsage")
      useInMemoryPgpKeys(signingKey, signingPassword)
   }
   if (Ci.isRelease) {
      sign(publications)
   }
}

java {
   withJavadocJar()
   withSourcesJar()
   toolchain {
      languageVersion.set(JavaLanguageVersion.of(11))
   }
}

publishing {
   repositories {
      maven {
         val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
         val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
         name = "deploy"
         url = if (Ci.isRelease) releasesRepoUrl else snapshotsRepoUrl
         credentials {
            username = System.getenv("OSSRH_USERNAME") ?: ""
            password = System.getenv("OSSRH_PASSWORD") ?: ""
         }
      }
   }

   publications {
      register("mavenJava", MavenPublication::class) {
         from(components["java"])
         pom {
            name.set("kotest-extensions-wiremock")
            description.set("Kotest extension for wiremock")
            url.set("https://www.github.com/kotest/kotest-extensions-wiremock")

            scm {
               connection.set("scm:git:http://www.github.com/kotest/kotest-extensions-wiremock")
               developerConnection.set("scm:git:http://github.com/sksamuel")
               url.set("https://www.github.com/kotest/kotest-extensions-wiremock")
            }

            licenses {
               license {
                  name.set("The Apache 2.0 License")
                  url.set("https://opensource.org/licenses/Apache-2.0")
               }
            }

            developers {
               developer {
                  id.set("sksamuel")
                  name.set("Stephen Samuel")
                  email.set("sam@sksamuel.com")
               }
            }
         }
      }
   }
}

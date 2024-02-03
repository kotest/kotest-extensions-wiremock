# kotest-extensions-wiremock

A Kotest extension to use [wiremock](https://github.com/tomakehurst/wiremock) through test lifecycles.

See [docs](https://kotest.io/docs/extensions/wiremock.html).

Please create issues on the main kotest [board](https://github.com/kotest/kotest/issues).

[![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/kotest/kotest-extensions-wiremock/master.yml?label=master&logo=github)](https://github.com/kotest/kotest-extensions-wiremock/actions/workflows/master.yml)
[![Maven Central](https://img.shields.io/maven-central/v/io.kotest.extensions/kotest-extensions-wiremock?label=latest%20release)](https://search.maven.org/artifact/io.kotest.extensions/kotest-extensions-wiremock)
[![License](https://img.shields.io/github/license/kotest/kotest-extensions-wiremock)](https://github.com/kotest/kotest-extensions-wiremock/blob/master/LICENSE)
[![kotest @ kotlinlang.slack.com](https://img.shields.io/static/v1?label=kotlinlang&message=kotest&color=blue&logo=slack)](https://kotlinlang.slack.com/archives/CT0G9SD7Z)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.kotest.extensions/kotest-extensions-wiremock?label=latest%20snapshot&server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/snapshots/io/kotest/extensions/kotest-extensions-wiremock/)

## Compatibility

| Extension Version | WireMock version | Min. Java version |
|-------------------|------------------|-------------------|
| 2.x               | >=2, < 3         | 8                 |
| 3.x               | >= 3             | 11                |


### Changelog

#### 3.0.1
* Reverted to using wiremock-standalone to prevent dependency conflicts

#### 3.0.0
* Upgraded to wiremock 3.3.1
* Min java version is now 11 (wiremock 3.x requires java 11)

#### 2.0.1

* Add per project listener mode

#### 2.0.0

* Bumps Kotest to 5.5.5
* Bumps wiremock to 2.35.0

#### 1.0.3

* JDK8 compatible
* Bumped wiremock to 2.31.0

#### 1.0.2

* Fixed issue with dependencies

#### 1.0.1

* Version bumps

#### 1.0.0

* Migrated from main Kotest repo.

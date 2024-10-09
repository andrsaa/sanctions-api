import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL

repositories {
  mavenCentral()
}

plugins {
  java
  war
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  id("io.freefair.lombok")
}

group = "ee.andrsaa"
version = "1.0"

allprojects {
  apply(plugin = "java-library")
  apply(plugin = "io.freefair.lombok")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")

  repositories {
    mavenCentral()
  }

  dependencies {
    testImplementation(Libs.assertj_core)
    testImplementation(Libs.mockito_core)
    testImplementation(Libs.junit_jupiter_api)
    testImplementation(Libs.junit_platform_runner)
    testImplementation(Libs.mockito_junit_jupiter)
    testRuntimeOnly(Libs.junit_jupiter_engine)
  }

  java {
    toolchain {
      languageVersion = JavaLanguageVersion.of(21)
    }
  }

  tasks.jar {
    enabled = true
  }

  tasks.test {
    useJUnitPlatform()
    testLogging {
      showStackTraces = true
      exceptionFormat = FULL
    }
  }

  tasks.register<Test>("testUnit") {
    useJUnitPlatform {
      excludeTags("integration-test")
    }
    testLogging {
      showStackTraces = true
      exceptionFormat = FULL
    }
    outputs.upToDateWhen { false }
  }
}

subprojects {
  tasks.bootJar {
    enabled = false
  }
}

springBoot {
  mainClass.set("ee.andrsaa.sanctions.SanctionsApiApplication")
}

dependencies {
  implementation(project(":app-main"))
  implementation(project(":app-domain"))
  implementation(project(":adapter:jpa"))
  implementation(project(":adapter:web"))
  implementation(project(":liquibase"))

  compileOnly(Libs.spring_boot_starter_tomcat)
}

tasks.withType<Test> {
  useJUnitPlatform()
}


tasks.war {
  enabled = true
  archiveFileName.set("sanctions-api.war")
}
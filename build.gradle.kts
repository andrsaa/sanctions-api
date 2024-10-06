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

group = "ee.asaarep"
version = "0.0.1-SNAPSHOT"

springBoot {
  mainClass.set("ee.asaarep.sanctions.SanctionsApiApplication")
}


allprojects {
  apply(plugin = "java-library")
  apply(plugin = "io.freefair.lombok")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")

  repositories {
    mavenCentral()
  }

  dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
  }

  java {
    toolchain {
      languageVersion = JavaLanguageVersion.of(21)
    }
  }

  tasks.jar {
    enabled = true
  }
}

subprojects {
  tasks.bootJar {
    enabled = false
  }
}

dependencies {
  implementation(project(":app-main"))
  implementation(project(":app-domain"))
  implementation(project(":adapter:jpa"))
  implementation(project(":adapter:web"))
  implementation(project(":liquibase"))
}

tasks.withType<Test> {
  useJUnitPlatform()
}


tasks.war {
  enabled = true
  archiveFileName.set("sanctions-api.war")
}
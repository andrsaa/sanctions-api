pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }
}

plugins {
  id("de.fayard.refreshVersions") version "0.60.5"
}

rootProject.name = "sanctions-api"

include("app-main")
include("app-domain")
include("adapter:jpa")
include("adapter:web")
include("liquibase")
include("integration-test")

plugins {
  java
  id("org.liquibase.gradle")
}

dependencies {
  api(Libs.liquibase_core)
  liquibaseRuntime(Libs.liquibase_core)
  liquibaseRuntime(Libs.postgresql)
//  liquibaseRuntime(Libs.slf4j_api)
//  liquibaseRuntime(Libs.logback_core)
//  liquibaseRuntime(Libs.logback_classic)
}

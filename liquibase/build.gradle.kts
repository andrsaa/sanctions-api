plugins {
  java
  id("org.liquibase.gradle")
}

dependencies {
  liquibaseRuntime(Libs.liquibase_core)
  liquibaseRuntime(Libs.postgresql)
  liquibaseRuntime(Libs.picocli)
}

val liquibaseConf = mapOf(
  "changelogFile" to "db/changelog-master.xml",
  "classpath" to "$projectDir/src/main/resources/",
  "liquibaseSchemaName" to "public",
  "url" to project.extra["liquibaseDbUrl"],
  "username" to project.extra["liquibaseUser"],
  "password" to project.extra["liquibasePassword"]
)

liquibase {
  activities.register("main") {
    this.arguments = liquibaseConf
  }
  runList = "main"
}

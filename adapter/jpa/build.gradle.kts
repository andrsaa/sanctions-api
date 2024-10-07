dependencies {
    implementation(Libs.spring_boot_starter_data_jpa)
    implementation(Libs.liquibase_core)
    implementation(Libs.postgresql)
    implementation(Libs.hypersistence_utils_hibernate_62)

    implementation(project(":app-domain"))
}

tasks.jar {
    archiveFileName.set("sanctions-jpa.jar")
}
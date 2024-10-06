dependencies {
    implementation(Libs.spring_boot_starter_data_jpa)
    implementation(Libs.liquibase_core)
    implementation(Libs.postgresql)
}

tasks.jar {
    archiveFileName.set("sanctions-jpa.jar")
}
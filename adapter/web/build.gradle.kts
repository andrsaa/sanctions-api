dependencies {
    implementation(project(":app-domain"))
    implementation(Libs.spring_tx)
    implementation(Libs.spring_boot_starter_web)
    implementation(Libs.spring_boot_start_security)
    implementation(Libs.spring_data_commons)
}

tasks.jar {
    archiveFileName.set("sanctions-web.jar")
}

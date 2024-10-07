dependencies {
    implementation(Libs.spring_boot_starter)
    implementation(Libs.spring_tx)
}

tasks.jar {
    archiveFileName.set("sanctions-domain.jar")
}
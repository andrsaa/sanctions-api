dependencies {
    implementation(Libs.spring_boot_starter)
    implementation(Libs.spring_tx)
    implementation(Libs.commons_lang3)
}

tasks.jar {
    archiveFileName.set("sanctions-domain.jar")
}
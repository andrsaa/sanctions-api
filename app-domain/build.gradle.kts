dependencies {
    implementation(Libs.spring_boot_starter)
    implementation(Libs.spring_boot_starter_web)
    implementation(Libs.spring_tx)
    implementation(Libs.commons_lang3)
    implementation(Libs.commons_csv)
}

tasks.jar {
    archiveFileName.set("sanctions-domain.jar")
}
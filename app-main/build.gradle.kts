dependencies {
    implementation(project(":app-domain"))
    implementation(project(":adapter:jpa"))
    implementation(project(":adapter:web"))

    implementation(Libs.spring_boot_starter_web)
}

tasks.jar {
    archiveFileName.set("sanctions-main.jar")
//    manifest {
//        attributes("Main-Class" to "ee.andrsaa.sanctions.SanctionsApiApplication")
//    }
}

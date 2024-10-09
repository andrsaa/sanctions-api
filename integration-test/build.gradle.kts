import org.gradle.api.tasks.testing.logging.TestExceptionFormat

dependencies {
    testImplementation(project(":liquibase"))
    testImplementation(project(":adapter:jpa"))
    testImplementation(project(":adapter:web"))
    testImplementation(project(":app-domain"))

    testImplementation(Libs.spring_boot_starter_web)
    testImplementation(Libs.spring_boot_starter_data_jpa)
    testImplementation(Libs.spring_boot_starter_test)
    testImplementation(Libs.testContainers)
    testImplementation(Libs.testContainers_postgresql)
    testImplementation(Libs.testContainers_junit)
    testImplementation(Libs.commons_lang3)
}

tasks.register<Test>("testIntegration") {
    maxHeapSize = "2g"
    useJUnitPlatform {
        includeTags("integration-test")
    }
    testLogging {
        showStackTraces = true
        exceptionFormat = TestExceptionFormat.FULL
    }
}
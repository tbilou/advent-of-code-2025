plugins {
    kotlin("jvm") version "2.2.21"
}

dependencies {
    // Use the Kotlin test library
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    // Enable JUnit Platform for JUnit 5
    useJUnitPlatform()
}

sourceSets {
    main {
        kotlin.srcDir("src/main/kotlin")
    }
    test {
        kotlin.srcDir("src/test/kotlin")
    }
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}

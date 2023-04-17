import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    `maven-publish`
}

group = "com.meisterplan"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.gitlab.arturbosch.detekt:detekt-api:1.22.0")

    testImplementation("io.gitlab.arturbosch.detekt:detekt-test:1.22.0")
    testImplementation("io.kotest:kotest-assertions-core:5.6.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    systemProperty("junit.jupiter.testinstance.lifecycle.default", "per_class")
    systemProperty("compile-snippet-tests", project.hasProperty("compile-test-snippets"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/meisterplan/detekt-rules")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

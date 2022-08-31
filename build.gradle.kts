import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.7.10" apply false
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
    id("org.owasp.dependencycheck") version "7.1.1"
    id("com.github.ben-manes.versions") version "0.42.0"
    jacoco
}

allprojects {

    group = "com.stringconcat"

    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.owasp.dependencycheck")
    apply(plugin = "com.github.ben-manes.versions")
    apply(plugin = "jacoco")
    apply(plugin = "base")


    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    configurations.all {
        resolutionStrategy {
            eachDependency {

                val versionIsSnapShot = requested.version?.contains("snapshot", true)

                versionIsSnapShot?.run {
                    if (this) {
                        throw GradleException("Snapshot version is forbidden: ${requested.name} ${requested.version}")
                    }
                }
            }

        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
            allWarningsAsErrors = true
        }
    }

    detekt {
        buildUponDefaultConfig = true
        allRules = true
        config = files("$rootDir/detekt/config.yml")
    }

    jacoco {
        toolVersion = "0.8.7"
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        jvmTarget = "1.8"
        reports {
            html.required.set(true)
        }
    }

    tasks {

        val validateDependencies = named<DefaultTask>("check")
        val dependencyUpdate = named<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask>("dependencyUpdates")

        dependencyUpdate {
            revision = "release"
            outputFormatter = "txt"
            checkForGradleUpdate = true
            outputDir = "$buildDir/reports/dependencies"
            reportfileName = "updates"
        }

        dependencyUpdate.configure {

            fun isNonStable(version: String): Boolean {
                val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
                val regex = "^[0-9,.v-]+(-r)?$".toRegex()
                val isStable = stableKeyword || regex.matches(version)
                return isStable.not()
            }

            rejectVersionIf {
                isNonStable(candidate.version) && !isNonStable(currentVersion)
            }
        }

        dependencyCheck {
            failBuildOnCVSS = 2f
            cveValidForHours = 3
        }


        validateDependencies {
            finalizedBy(dependencyUpdate)
        }

    }

}

java.sourceCompatibility = JavaVersion.VERSION_1_8


dependencies {

    // spring modules
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation(project(":presentation"))
    implementation(project(":persistence"))
    implementation(project(":useCasePeople"))
    implementation(project(":businessPeople"))
    implementation(project(":quoteGarden"))
    implementation(project(":avatarsDicebear"))

    // dev tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    //persistance
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.liquibase:liquibase-core:4.15.0")

    // tests
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
}


tasks.jacocoTestReport {
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

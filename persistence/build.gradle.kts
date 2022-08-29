plugins {
    kotlin("jvm")
    id("io.spring.dependency-management")
    id("org.jetbrains.kotlin.plugin.jpa")
}

java.sourceCompatibility = JavaVersion.VERSION_1_8

dependencies {
    implementation(project(":businessPeople"))
    implementation(project(":useCasePeople"))

    implementation("javax.persistence:javax.persistence-api:2.2")
    implementation("org.apache.logging.log4j:log4j-core:2.18.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.3")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // tests
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

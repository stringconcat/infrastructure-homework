plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("io.spring.dependency-management")
}

java.sourceCompatibility = JavaVersion.VERSION_1_8

dependencies {
    implementation(project(":businessPeople"))
    implementation(project(":useCasePeople"))

    // spring modules
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.3")
    implementation("org.springframework.boot:spring-boot-starter-data-rest:2.7.3")

    // tools
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")

    // view
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // tests
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.3") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test:3.4.22")
}

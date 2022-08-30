import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.7.10"
	kotlin("plugin.spring") version "1.7.10"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.7.10" apply false
	id("io.gitlab.arturbosch.detekt") version "1.21.0"
}

 allprojects {
	group = "com.stringconcat"
	version = "0.0.1"

	repositories {
		mavenCentral()
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
	}


	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
			allWarningsAsErrors = true
		}
	}


	apply(plugin = "io.gitlab.arturbosch.detekt")

	detekt {
		buildUponDefaultConfig = true
		allRules = true
		config = files("$rootDir/detekt/config.yml")
	}

	tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
		reports {
			html.required.set(true)
		}
	}

	tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
		jvmTarget = "1.8"
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

tasks.test {
	useJUnitPlatform()
}



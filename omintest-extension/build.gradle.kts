import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.7"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	application
}

group = "org.omintest"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":omintest-api"))
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
	implementation("org.junit.platform:junit-platform-engine:1.9.2")
	implementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
	implementation("org.junit.jupiter:junit-jupiter:5.9.2")
	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation("org.springframework:spring-web")
	implementation("org.testcontainers:postgresql:1.15.1")
	implementation("org.mock-server:mockserver-client-java:5.11.2")
	implementation("org.reflections:reflections:0.10.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-base:latest")
}

tasks.register("prepareKotlinBuildScriptModel"){}

application {
	mainClass.set("MainKt")
}
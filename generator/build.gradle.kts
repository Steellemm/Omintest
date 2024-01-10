import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
    application
}

group = "org.omintest"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(kotlin("test"))
    implementation("org.reflections:reflections:0.10.2")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
        //implementation("org.slf4j:slf4j-api:2.0.9")
// https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    //implementation("org.slf4j:slf4j-simple:2.0.9")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("MainKt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
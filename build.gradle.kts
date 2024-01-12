plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "me.topilov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://mvn.mchv.eu/repository/mchv/")
    }
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
    implementation(platform("it.tdlight:tdlight-java-bom:3.2.2+td.1.8.21"))
    implementation(group = "it.tdlight", name = "tdlight-java")
    implementation(group = "it.tdlight", name = "tdlight-natives", classifier = "windows_amd64")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}
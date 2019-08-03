import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.71"
}

group = "gin.kotlin.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(group = "com.github.seratch", name = "jslack-api-client", version = "1.7.7")
    compile(group = "javax.websocket", name = "javax.websocket-api", version = "1.1")
    compile(group = "org.glassfish.tyrus.bundles", name = "tyrus-standalone-client", version = "1.13")
    compile(group = "com.google.inject", name = "guice", version = "4.2.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
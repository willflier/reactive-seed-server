import com.palantir.gradle.docker.DockerExtension
import com.palantir.gradle.docker.DockerRunExtension
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/** Start for EAP version **/
val kotlinVersion: String by System.getProperties()
val springCloudVersion: String by System.getProperties()
/*
buildscript {
    val kotlinVersion: String by System.getProperties()
    repositories {
        mavenCentral()
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
        classpath(kotlin("allopen", kotlinVersion))
        classpath(kotlin("noarg", kotlinVersion))
        classpath("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:$kotlinVersion")
    }
}
apply{
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("kotlin-jpa")
}
*/
/** End for EAP version **/

plugins {
    val kotlinVersion: String by System.getProperties()
    val springBootVersion: String by System.getProperties()

    java
    application
    eclipse

    /**Start for release version **/
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    kotlin("plugin.noarg") version kotlinVersion
    /**End for release version **/

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version "1.0.5.RELEASE"
    id("com.palantir.docker") version "0.19.2"
    id("com.palantir.docker-run") version "0.19.2"
}

group = "com.ingo.seed"
version = "0.0.1-SNAPSHOT"

configure<JavaPluginConvention> {
    setSourceCompatibility(1.8)
    setTargetCompatibility(1.8)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
}

java.sourceSets {
    getByName("main").java.srcDirs("src/main/kotlin")
}

repositories {
    maven ("http://maven.aliyun.com/nexus/content/groups/public")
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://repo.spring.io/milestone")
}

dependencies {

    /** Start for target kotlin version **/
    /*
    compile(kotlin("stdlib-jdk8", kotlinVersion))
    compile(kotlin("stdlib", kotlinVersion))
    compile(kotlin("reflect", kotlinVersion))
    */
    /** End for target version **/

    /** Start for latest release kotlin version **/
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("stdlib"))
    compile(kotlin("reflect"))
    /** End for latest release version **/
    compile("org.springframework.boot:spring-boot-starter-actuator")
    compile("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    compile("org.springframework.boot:spring-boot-starter-webflux")
    compile("org.springframework.hateoas:spring-hateoas")
    compile("org.springframework.boot:spring-boot-starter-json")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")

    compile("org.springframework.cloud:spring-cloud-starter-zookeeper-config")
    compile("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    compile("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")

    testCompile("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testCompile("io.projectreactor:reactor-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}
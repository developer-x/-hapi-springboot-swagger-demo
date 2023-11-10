plugins {
    java
    id("org.springframework.boot") version "3.2.0-M3"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "developerx"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    implementation("ca.uhn.hapi.fhir:hapi-fhir-base:6.8.3")
    implementation("ca.uhn.hapi.fhir:hapi-fhir-structures-r5:6.8.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "uk.co.spiraltechnology.experiment"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// https://mvnrepository.com/artifact/org.keycloak/keycloak-server-spi
	compileOnly("org.keycloak:keycloak-server-spi:19.0.1")

	// https://mvnrepository.com/artifact/org.keycloak/keycloak-model-legacy
	implementation("org.keycloak:keycloak-model-legacy:19.0.1")



	// https://mvnrepository.com/artifact/org.keycloak/keycloak-common
	//implementation("org.keycloak:keycloak-common:19.0.1")

	// https://mvnrepository.com/artifact/org.keycloak/keycloak-adapter-core
	//implementation("org.keycloak:keycloak-adapter-core:19.0.1")



	// https://mvnrepository.com/artifact/org.keycloak/keycloak-core
	implementation("org.keycloak:keycloak-core:19.0.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

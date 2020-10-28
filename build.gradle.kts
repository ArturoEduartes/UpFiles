// Api Token Task 99553c836c83ef690fedc783877f28b8f61d0b05
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
    kotlin("kapt") version "1.4.10"
    id("org.jetbrains.dokka") version "1.4.10.2"
    id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
}

group = "me.user"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    kapt("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    coloredOutput.set(true)
    // add for Arturo
    debug.set(false)
    android.set(false)
    outputColorName.set("RED")
    ignoreFailures.set(false)
    enableExperimentalRules.set(false)
    disabledRules.set(setOf("final-newline", "import-ordering", "indent",
        "parameter-list-wrapping"))
    // End Add
    reporters {
        // reporter(ReporterType.CHECKSTYLE)|
        // reporter(ReporterType.JSON)
        reporter(ReporterType.HTML)
    }
    // kotlinScriptAdditionalPaths {
    // include(fileTree("src"))
    // }
    filter {
        exclude("**/style-violations.kt")
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

plugins {
    kotlin("jvm") version "2.3.0-RC"
}

group = "tms"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    compilerOptions {
        languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_3)
        freeCompilerArgs.add("-Xreturn-value-checker=full")
        freeCompilerArgs.add("-Xdata-flow-based-exhaustiveness")
        freeCompilerArgs.add("-Xallow-reified-type-in-catch")
        freeCompilerArgs.add("-Xallow-contracts-on-more-functions")
        freeCompilerArgs.add("-Xallow-condition-implies-returns-contracts")
        freeCompilerArgs.add("-Xallow-holdsin-contract")
        freeCompilerArgs.add("-Xwhen-expressions=indy")
        freeCompilerArgs.add("-Xcontext-sensitive-resolution")
        freeCompilerArgs.add("-Xnested-type-aliases")
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

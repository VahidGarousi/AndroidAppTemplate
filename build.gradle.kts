// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(libs.detekt.gradle.plugin)
        classpath(libs.gradle)
        classpath(libs.gradle.versions.plugin)
        classpath(libs.kotlin.gradle.plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    // https://github.com/jeremymailen/kotlinter-gradle/releases
    id("org.jmailen.kotlinter") version "3.13.0" apply false
}

apply(from = "buildscripts/githooks.gradle")
apply(from = "buildscripts/setup.gradle")

subprojects {
    apply(from = "../buildscripts/detekt.gradle")
    apply(from = "../buildscripts/versionsplugin.gradle")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks.named("clean") {
        dependsOn(":installGitHooks")
    }
}

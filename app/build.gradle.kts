val projectJvmTarget = 17

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
    jacoco
}

android {
    namespace = "dev.shtanko.skeleton"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.shtanko.skeleton"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = projectJvmTarget.toString()
    }
    buildFeatures {
        compose = true
    }
}

spotless {
    kotlin {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.kt"),
                    "exclude" to listOf("**/build/**", "**/spotless/*.kt"),
                ),
            ),
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        val delimiter = "^(package|object|import|interface|internal|@file|//startfile)"
        val licenseHeaderFile = rootProject.file("spotless/copyright.kt")
        licenseHeaderFile(licenseHeaderFile, delimiter)
    }
}

subprojects {
    apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
}

tasks {
    withType<io.gitlab.arturbosch.detekt.Detekt> {
        description = "Runs over whole code base without the starting overhead for each module."
        parallel = true
        baseline.set(file("$rootDir/config/detekt/detekt-baseline.xml"))
        config.from(file("$rootDir/config/detekt/detekt.yml"))
        jvmTarget = "$projectJvmTarget"

        setSource(files("src/main/kotlin", "src/test/kotlin"))
        setOf(
            "**/*.kt",
            "**/*.kts",
            ".*/resources/.*",
            ".*/build/.*",
            "/versions.gradle.kts",
        ).forEach {
            include(it)
        }

        reports {
            reports.apply {
                listOf(xml, html, txt, md).map { it.required }.forEach {
                    it.set(true)
                }
            }
        }
    }
}

dependencies {
    libs.apply {
        androidx.apply {
            implementation(core.ktx)
            implementation(lifecycle.runtime.ktx)
            implementation(activity.compose)
            implementation(platform(compose.bom))
            implementation(ui)
            implementation(ui.graphics)
            implementation(ui.tooling.preview)
            implementation(material3)

            androidTestImplementation(junit)
            androidTestImplementation(espresso.core)
            androidTestImplementation(platform(compose.bom))
            androidTestImplementation(ui.test.junit4)

            debugImplementation(ui.tooling)
            debugImplementation(ui.test.manifest)
        }

        kotlin.apply {
            implementation(stdlib)
            implementation(reflect)
            implementation(coroutines)
            implementation(coroutines.debug)
        }

        testImplementation(junit)
        testImplementation(assertj)
        testImplementation(mockito)
        testImplementation(mockito.kotlin)
        testImplementation(kotest)
        testImplementation(kotest.assertions)
        testImplementation(kotest.property)
        testImplementation(okhttp.mockwebserver)
    }
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlinx:kover:0.6.1")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.4.2").apply(false)
    id("com.android.library").version("7.4.2").apply(false)
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
    kotlin("android").version("1.8.0").apply(false)
    kotlin("multiplatform").version("1.8.0").apply(false)
    id("io.gitlab.arturbosch.detekt").version("1.23.0-RC1")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

koverMerged {
    enable()

    filters {
        classes {
            excludes += listOf(
                "*.databinding.*",
                "*.BuildConfig"
            )
        }
    }
}

repositories {
    mavenCentral()
}

detekt {
    toolVersion = "1.23.0-RC1"

    source = files(
        "androidApp/src",
        "shared/src"
    )

    parallel = false
    config = files("$projectDir/detekt.yml")
    buildUponDefaultConfig = false
    allRules = false
    disableDefaultRuleSets = false
    debug = false

    ignoreFailures = false
    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("production")
    ignoredVariants = listOf("productionRelease")

    basePath = "$projectDir"
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(true)
    }
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependency.KOTLIN_GRADLE_PLUGIN)
        classpath(Dependency.GRADLE)
        classpath(Dependency.KOTLIN_SERIALIZATION)
        classpath(Dependency.BUILD_KONFIG)
        classpath(Dependency.KOVER)
    }
}

plugins {
    id(Plugin.KOVER_FULL_PATH) version (Version.KOVER)
	
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
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

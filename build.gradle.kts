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

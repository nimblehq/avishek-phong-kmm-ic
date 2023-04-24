import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile

plugins {
    kotlin(Plugin.MULTIPLATFORM)
    kotlin(Plugin.COCOAPODS)
    kotlin(Plugin.KOTLIN_SERIALIZATION)
    id(Plugin.BUILD_KONFIG)
    id(Plugin.ANDROID_LIBRARY)
    id(Plugin.KOTLINX_SERIALIZATION)
    id(Plugin.KOVER)
    id(Plugin.KSP).version(Version.KSP)
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
        xcodeConfigurationToNativeBuildType[XcodeConfiguration.DEBUG_STAGING] =
            NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType[XcodeConfiguration.DEBUG_PRODUCTION] =
            NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType[XcodeConfiguration.RELEASE_STAGING] =
            NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType[XcodeConfiguration.RELEASE_PRODUCTION] =
            NativeBuildType.RELEASE
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependency.COROUTINES_CORE)

                implementation(Dependency.KTOR_CORE)
                implementation(Dependency.KTOR_SERIALIZATION)
                implementation(Dependency.KTOR_LOGGING)
                implementation(Dependency.KTOR_CIO)
                implementation(Dependency.KTOR_CONTENT_NEGOTIATION)
                implementation(Dependency.KTOR_JSON)
                implementation(Dependency.KTOR_AUTH)

                implementation(Dependency.NAPIER)

                // settings
                implementation(Dependency.SETTINGS)
                implementation(Dependency.SETTINGS_SERIALIZATION)

                // Json API
                implementation(Dependency.JSON_API)

                // Koin
                implementation(Dependency.KOIN_CORE)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Dependency.KOIN_TEST)
                implementation(Dependency.COROUTINES_TEST)
                implementation(Dependency.MOCKATIVE)
                implementation(Dependency.KOTEST_FRAMEWORK)
                implementation(Dependency.KOTEST_ASSERTIONS)
                implementation(Dependency.KOTEST_PROPERTY)
                implementation(Dependency.TURBINE)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependency.KTOR_ANDROID)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Dependency.KTOR_IOS)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

dependencies {
    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, Dependency.MOCKATIVE_PROCESSOR)
        }
}

ksp {
    arg("mockative.stubsUnitByDefault", "true")
}

android {
    namespace = "co.nimblehq.avishek.phong.kmmic"
    compileSdk = 33
    defaultConfig {
        minSdk = 23
        targetSdk = 33
    }

    testOptions {
        unitTests.all {
            if (it.name != "testDebugUnitTest") {
                it.extensions.configure(kotlinx.kover.api.KoverTaskExtension::class) {
                    isDisabled.set(true)
                }
            }
        }
    }
}
val buildKonfigProperties = rootDir.loadGradleProperties("buildKonfig.properties")
buildkonfig {
    packageName = "co.nimblehq.avishek.phong.kmmic"

    // Default for Flavors.STAGING
    defaultConfigs {
        buildConfigField(
            STRING,
            "CLIENT_ID",
            buildKonfigProperties.getProperty("STAGING_CLIENT_ID")
        )
        buildConfigField(
            STRING,
            "CLIENT_SECRET",
            buildKonfigProperties.getProperty("STAGING_CLIENT_SECRET")
        )
        buildConfigField(
            STRING,
            "BASE_URL",
            buildKonfigProperties.getProperty("STAGING_BASE_URL")
        )
    }

    defaultConfigs(Flavors.PRODUCTION) {
        buildConfigField(
            STRING,
            "CLIENT_ID",
            buildKonfigProperties.getProperty("PRODUCTION_CLIENT_ID")
        )
        buildConfigField(
            STRING,
            "CLIENT_SECRET",
            buildKonfigProperties.getProperty("PRODUCTION_CLIENT_SECRET")
        )
        buildConfigField(
            STRING,
            "BASE_URL",
            buildKonfigProperties.getProperty("PRODUCTION_BASE_URL")
        )
    }
}

tasks.withType<com.google.devtools.ksp.gradle.KspTask>().configureEach {
    when (this) {
        is com.google.devtools.ksp.gradle.KspTaskNative -> {
            this.compilerPluginOptions.addPluginArgument(
                tasks
                    .named<org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile>(compilation.compileKotlinTaskName)
                    .get()
                    .compilerPluginOptions
            )
        }
    }
}

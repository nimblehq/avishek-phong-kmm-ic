plugins {
    id("com.android.application")
    kotlin("android")
    id("kover")
}

val keystoreProperties = rootDir.loadGradleProperties("signing.properties")
android {
    namespace = "co.nimblehq.avishek.phong.kmmic.android"
    compileSdk = Version.ANDROID_COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = "co.nimblehq.avishek.phong.kmmic.android"
        minSdk = Version.ANDROID_MIN_SDK_VERSION
        targetSdk = Version.ANDROID_TARGET_SDK_VERSION
        versionCode = Version.ANDROID_VERSION_CODE
        versionName = Version.ANDROID_VERSION_NAME
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {
        create(BuildTypes.RELEASE) {
            storeFile = file("../config/release.keystore")
            storePassword = keystoreProperties.getProperty("KEYSTORE_PASSWORD") as String
            keyPassword = keystoreProperties.getProperty("KEY_PASSWORD") as String
            keyAlias = keystoreProperties.getProperty("KEY_ALIAS") as String
        }

        getByName(BuildTypes.DEBUG) {
            storeFile = file("../config/debug.keystore")
            storePassword = "F8@k7uen"
            keyAlias = "debug-key-alias"
            keyPassword = "F8@k7uen"
        }
    }

    buildTypes {
        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            signingConfig = signingConfigs[BuildTypes.RELEASE]
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName(BuildTypes.DEBUG) {
            // For quickly testing build with proguard, enable this
            isMinifyEnabled = false
            signingConfig = signingConfigs[BuildTypes.DEBUG]
        }
    }

    flavorDimensions += Flavors.DIMENSION_VERSION
    productFlavors {
        create(Flavors.STAGING) {
            applicationIdSuffix = ".staging"
        }

        create(Flavors.PRODUCTION) {}
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.3.1")
    implementation("androidx.compose.ui:ui-tooling:1.3.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.1")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    implementation(Dependency.KOIN_CORE)
    implementation(Dependency.KOIN_ANDROID)
    implementation(Dependency.KOIN_COMPOSE)

    testImplementation(Dependency.JUNIT)
    testImplementation(Dependency.MOCKK)
    testImplementation(Dependency.KOTLIN_COROUTINES_TEST)
    testImplementation(Dependency.KOTEST_ASSERTIONS)
    androidTestImplementation(Dependency.MOCKK_ANDROID)
    androidTestImplementation(Dependency.JUNIT_EXT)
    androidTestImplementation(Dependency.ESPRESSO_CORE)
}

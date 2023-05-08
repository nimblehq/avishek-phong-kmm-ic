plugins {
    with(Plugin) {
        id(ANDROID_APPLICATION)
        kotlin(ANDROID)
        id(KOVER)
        id(GOOGLE_SERVICES)
    }
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

    with(Dependency) {
        implementation(COMPOSE_UI)
        implementation(COMPOSE_UI_TOOLING)
        implementation(COMPOSE_MATERIAL)
        implementation(COMPOSE_ACTIVITY)
        implementation(COMPOSE_NAVIGATION)
        implementation(KOIN_CORE)
        implementation(KOIN_ANDROID)
        implementation(KOIN_COMPOSE)
        implementation(TIMBER)
        implementation(FIREBASE)

        testImplementation(JUNIT)
        testImplementation(MOCKK)
        testImplementation(KOTLIN_COROUTINES_TEST)
        testImplementation(KOTEST_ASSERTIONS)
        androidTestImplementation(MOCKK_ANDROID)
        androidTestImplementation(JUNIT_EXT)
        androidTestImplementation(ESPRESSO_CORE)
    }
}

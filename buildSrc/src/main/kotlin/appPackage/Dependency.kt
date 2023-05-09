object Dependency {
    const val KOTLIN_GRADLE_PLUGIN =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN_VERSION}"
    const val GRADLE = "com.android.tools.build:gradle:${Version.BUILD_GRADLE_VERSION}"

    // BuildKonfig
    const val BUILD_KONFIG =
        "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:${Version.BUILD_KONFIG}"

    // Ktor
    const val KTOR_CORE = "io.ktor:ktor-client-core:${Version.KTOR}"
    const val KTOR_SERIALIZATION = "io.ktor:ktor-client-serialization:${Version.KTOR}"
    const val KTOR_LOGGING = "io.ktor:ktor-client-logging:${Version.KTOR}"
    const val KTOR_CIO = "io.ktor:ktor-client-cio:${Version.KTOR}"
    const val KTOR_CONTENT_NEGOTIATION = "io.ktor:ktor-client-content-negotiation:${Version.KTOR}"
    const val KTOR_JSON = "io.ktor:ktor-serialization-kotlinx-json:${Version.KTOR}"
    const val KTOR_AUTH = "io.ktor:ktor-client-auth:${Version.KTOR}"
    const val KTOR_ANDROID = "io.ktor:ktor-client-android:${Version.KTOR}"
    const val KTOR_IOS = "io.ktor:ktor-client-ios:${Version.KTOR}"

    // Coroutines
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES_CORE}"
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES_CORE}"

    // Napier - For logging API request
    const val NAPIER = "io.github.aakira:napier:${Version.NAPIER}"

    // Settings
    const val SETTINGS = "com.russhwolf:multiplatform-settings:${Version.SETTINGS}"
    const val SETTINGS_SERIALIZATION =
        "com.russhwolf:multiplatform-settings-serialization:${Version.SETTINGS}"
    const val SECURITY_CRYPTO_KTX =
        "androidx.security:security-crypto-ktx:${Version.SECURITY_CRYPTO}"

    // Serialization
    const val KOTLIN_SERIALIZATION =
        "org.jetbrains.kotlin:kotlin-serialization:${Version.KOTLIN_SERIALIZATION}"

    // Kover
    const val KOVER = "org.jetbrains.kotlinx:kover:${Version.KOVER}"

    // Koin
    const val KOIN_CORE = "io.insert-koin:koin-core:${Version.KOIN}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${Version.KOIN_ANDROID}"
    const val KOIN_COMPOSE = "io.insert-koin:koin-androidx-compose:${Version.KOIN_ANDROID}"
    const val KOIN_TEST = "io.insert-koin:koin-test:${Version.KOIN}"

    // JSON API
    const val JSON_API = "co.nimblehq.jsonapi:core:${Version.JSON_API}"

    const val MOCKATIVE = "io.mockative:mockative:${Version.MOCKATIVE}"
    const val MOCKATIVE_PROCESSOR = "io.mockative:mockative-processor:${Version.MOCKATIVE}"
    const val KOTEST_FRAMEWORK = "io.kotest:kotest-framework-engine:${Version.KOTEST}"
    const val KOTEST_ASSERTIONS = "io.kotest:kotest-assertions-core:${Version.KOTEST}"
    const val KOTEST_PROPERTY = "io.kotest:kotest-property:${Version.KOTEST}"
    const val TURBINE = "app.cash.turbine:turbine:${Version.TURBINE}"
    const val MOCKK_ANDROID = "io.mockk:mockk-android:${Version.MOCKK}"
    const val JUNIT = "junit:junit:${Version.JUNIT}"
    const val JUNIT_EXT = "androidx.test.ext:junit:${Version.JUNIT_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}"
    const val KOTLIN_COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES_CORE}"
    const val MOCKK = "io.mockk:mockk:${Version.MOCKK}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${Version.ROBOLECTRIC}"

    // Google Services Gradle Plugin
    const val GOOGLE_SERVICES = "com.google.gms:google-services:${Version.GOOGLE_SERVICES}"

    // Firebase
    const val FIREBASE = "com.google.firebase:firebase-bom:${Version.FIREBASE}"

    // Compose
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Version.COMPOSE}"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${Version.COMPOSE}"
    const val COMPOSE_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Version.COMPOSE}"
    const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:${Version.COMPOSE}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Version.COMPOSE_MATERIAL}"
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${Version.COMPOSE_ACTIVITY}"
    const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:${Version.COMPOSE_NAVIGATION}"
    const val COMPOSE_UI_TEST_JUNIT = "androidx.compose.ui:ui-test-junit4:${Version.COMPOSE_UI_TEST_JUNIT}"
    const val COMPOSE_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Version.COMPOSE_UI_TEST_JUNIT}"

    // Logging
    const val TIMBER = "com.jakewharton.timber:timber:${Version.TIMBER}"

    // Bom
    const val BOM = "org.jetbrains.kotlin:kotlin-bom:${Version.BOM}"
}

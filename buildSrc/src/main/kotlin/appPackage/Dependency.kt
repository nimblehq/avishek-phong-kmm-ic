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
}

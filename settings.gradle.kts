pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "KMM-IC"
include(":androidApp")
include(":shared")
include(":jsonapi-kotlin:core")

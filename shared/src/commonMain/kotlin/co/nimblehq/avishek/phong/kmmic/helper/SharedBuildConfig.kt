package co.nimblehq.avishek.phong.kmmic.helper

import co.nimblehq.avishek.phong.kmmic.BuildKonfig

class SharedBuildConfig {

    class UITestConfig {

        fun email(): String {
            return BuildKonfig.UI_TEST_EMAIL
        }

        fun password(): String {
            return BuildKonfig.UI_TEST_PASSWORD
        }
    }
}

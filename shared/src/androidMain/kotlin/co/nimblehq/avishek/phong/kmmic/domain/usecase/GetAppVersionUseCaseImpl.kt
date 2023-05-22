package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.BuildKonfig

actual class GetAppVersionUseCaseImpl actual constructor() : GetAppVersionUseCase {

    override fun invoke(): String {
        return "v${BuildKonfig.VERSION_NAME} (${BuildKonfig.VERSION_CODE})"
    }
}

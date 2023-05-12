package co.nimblehq.avishek.phong.kmmic.domain.usecase

interface GetAppVersionUseCase {
    operator fun invoke(): String
}

expect class GetAppVersionUseCaseImpl() : GetAppVersionUseCase

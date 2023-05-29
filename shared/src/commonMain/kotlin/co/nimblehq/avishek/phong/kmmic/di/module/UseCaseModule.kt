package co.nimblehq.avishek.phong.kmmic.di.module

import co.nimblehq.avishek.phong.kmmic.domain.usecase.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::CheckLoggedInUseCaseImpl) bind CheckLoggedInUseCase::class
    singleOf(::LogInUseCaseImpl) bind LogInUseCase::class
    singleOf(::GetSurveysUseCaseImpl) bind GetSurveysUseCase::class
    singleOf(::GetUserProfileUseCaseImpl) bind GetUserProfileUseCase::class
    singleOf(::GetSurveyDetailUseCaseImpl) bind GetSurveyDetailUseCase:: class
    singleOf(::GetAppVersionUseCaseImpl) bind GetAppVersionUseCase::class
    singleOf(::SubmitSurveyAnswerUseCaseImpl) bind SubmitSurveyAnswerUseCase::class
}

package co.nimblehq.avishek.phong.kmmic.di.extension

import co.nimblehq.avishek.phong.kmmic.di.initKoin
import co.nimblehq.avishek.phong.kmmic.presentation.module.HomeViewModel
import co.nimblehq.avishek.phong.kmmic.presentation.module.LogInViewModel
import co.nimblehq.avishek.phong.kmmic.presentation.module.SplashViewModel
import co.nimblehq.avishek.phong.kmmic.presentation.module.SurveyDetailViewModel
import org.koin.core.Koin
import org.koin.core.KoinApplication

fun KoinApplication.Companion.start(): KoinApplication = initKoin()

val Koin.splashViewModel: SplashViewModel
    get() = get()
val Koin.logInViewModel: LogInViewModel
    get() = get()
val Koin.homeViewModel: HomeViewModel
    get() = get()
val Koin.surveyDetailViewModel: SurveyDetailViewModel
    get() = get()

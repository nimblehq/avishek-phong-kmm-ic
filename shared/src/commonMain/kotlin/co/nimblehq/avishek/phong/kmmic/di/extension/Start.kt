package co.nimblehq.avishek.phong.kmmic.di.extension

import co.nimblehq.avishek.phong.kmmic.di.initKoin
import co.nimblehq.avishek.phong.kmmic.presentation.module.LogInViewModel
import co.nimblehq.avishek.phong.kmmic.presentation.module.SplashViewModel
import org.koin.core.Koin
import org.koin.core.KoinApplication

fun KoinApplication.Companion.start(): KoinApplication = initKoin()

val Koin.splashViewModel: SplashViewModel
    get() = get()
val Koin.logInViewModel: LogInViewModel
    get() = get()

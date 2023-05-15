package co.nimblehq.avishek.phong.kmmic.di.module

import co.nimblehq.avishek.phong.kmmic.presentation.module.SplashViewModel
import co.nimblehq.avishek.phong.kmmic.presentation.module.LogInViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

actual val viewModelModule: Module
    get() = module {
        viewModelOf(::SplashViewModel)
        viewModelOf(::LogInViewModel)
    }

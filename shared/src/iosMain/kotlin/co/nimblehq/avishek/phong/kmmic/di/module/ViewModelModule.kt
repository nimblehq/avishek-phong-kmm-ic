package co.nimblehq.avishek.phong.kmmic.di.module

import co.nimblehq.avishek.phong.kmmic.presentation.module.*
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val viewModelModule: Module
    get() = module {
        factoryOf(::SplashViewModel)
        factoryOf(::LogInViewModel)
        factoryOf(::HomeViewModel)
        factoryOf(::SurveyDetailViewModel)
        factoryOf(::SurveyQuestionViewModel)
    }

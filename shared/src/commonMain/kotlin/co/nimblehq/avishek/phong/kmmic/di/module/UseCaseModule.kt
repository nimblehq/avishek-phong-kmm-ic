package co.nimblehq.avishek.phong.kmmic.di.module

import co.nimblehq.avishek.phong.kmmic.domain.usecase.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::CheckLoggedInUseCaseImpl) bind CheckLoggedInUseCase::class
}

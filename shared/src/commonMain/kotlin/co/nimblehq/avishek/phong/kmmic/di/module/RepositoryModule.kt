package co.nimblehq.avishek.phong.kmmic.di.module

import co.nimblehq.avishek.phong.kmmic.domain.repository.*
import co.nimblehq.avishek.phong.kmmic.data.repository.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthenticationRepositoryImpl) bind AuthenticationRepository::class
    singleOf(::SurveyRepositoryImpl) bind SurveyRepository::class
    singleOf(::UserRepositoryImpl) bind UserRepository::class
}

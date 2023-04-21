package co.nimblehq.avishek.phong.kmmic.di.module

import co.nimblehq.avishek.phong.kmmic.domain.repository.AuthenticationRepository
import co.nimblehq.avishek.phong.kmmic.data.repository.AuthenticationRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthenticationRepositoryImpl) bind AuthenticationRepository::class
}

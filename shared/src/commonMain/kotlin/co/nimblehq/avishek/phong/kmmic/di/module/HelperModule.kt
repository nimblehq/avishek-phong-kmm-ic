package co.nimblehq.avishek.phong.kmmic.di.module

import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.*
import co.nimblehq.avishek.phong.kmmic.helper.DispatchersProviderImpl
import co.nimblehq.avishek.phong.kmmic.helper.DispatchersProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val helperModule = module {
    singleOf(::DateTimeFormatterImpl) bind DateTimeFormatter::class
    singleOf(::DateTimeImpl) bind DateTime::class
    singleOf(::DispatchersProviderImpl) bind DispatchersProvider::class
}

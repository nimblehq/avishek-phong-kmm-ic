package co.nimblehq.avishek.phong.kmmic.di.module

import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val helperModule = module {
    singleOf(::DateTimeFormatterImpl) bind DateTimeFormatter::class
    singleOf(::DateTimeImpl) bind DateTime::class
}

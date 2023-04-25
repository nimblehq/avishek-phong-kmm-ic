package co.nimblehq.avishek.phong.kmmic.di.module

import co.nimblehq.avishek.phong.kmmic.data.local.datasource.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val localModule = module {
    singleOf(::TokenLocalDataSourceImpl) bind TokenLocalDataSource::class
}

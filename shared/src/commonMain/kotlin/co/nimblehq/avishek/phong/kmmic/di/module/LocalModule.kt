package co.nimblehq.avishek.phong.kmmic.di.module

import co.nimblehq.avishek.phong.kmmic.data.local.datasource.*
import co.nimblehq.avishek.phong.kmmic.data.local.realm.realm
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val localModule = module {
    single { realm }
    singleOf(::TokenLocalDataSourceImpl) bind TokenLocalDataSource::class
    singleOf(::SurveyLocalDataSourceImpl) bind SurveyLocalDataSource::class
}

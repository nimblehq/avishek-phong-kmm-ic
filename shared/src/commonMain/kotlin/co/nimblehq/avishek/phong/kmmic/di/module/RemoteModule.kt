package co.nimblehq.avishek.phong.kmmic.di.module

import co.nimblehq.avishek.phong.kmmic.data.remote.ApiClient
import co.nimblehq.avishek.phong.kmmic.data.remote.datasource.*
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private const val UNAUTHORIZED_API_CLIENT = "UNAUTHORIZED_API_CLIENT"

val remoteModule = module {
    singleOf(::ApiClient)
    single(named(UNAUTHORIZED_API_CLIENT)) { ApiClient(get()) }
    single<TokenRemoteDataSource> { TokenRemoteDataSourceImpl(get(named(UNAUTHORIZED_API_CLIENT))) }
}

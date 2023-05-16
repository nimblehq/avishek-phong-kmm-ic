package co.nimblehq.avishek.phong.kmmic.di

import co.nimblehq.avishek.phong.kmmic.di.module.*
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) : KoinApplication {
    val dataModules = listOf(localModule, remoteModule, repositoryModule)
    val domainModules = listOf(useCaseModule)
    val helperModules = listOf(helperModule)

    return startKoin {
        appDeclaration()
        modules(
            domainModules + dataModules + viewModelModule + platformModule + helperModules
        )
    }
}

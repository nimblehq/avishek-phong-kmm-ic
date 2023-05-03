package co.nimblehq.avishek.phong.kmmic.di.module

import com.russhwolf.settings.*
import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module
import org.koin.dsl.module

private const val SERVICE_NAME = "co.nimblehq.phong.kmmswiftuiic"

@OptIn(ExperimentalSettingsImplementation::class)
actual val platformModule: Module
    get() = module {
        single { Darwin.create() }
        single<Settings> { KeychainSettings(SERVICE_NAME) }
    }

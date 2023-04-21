package co.nimblehq.avishek.phong.kmmic.di.extension

import co.nimblehq.avishek.phong.kmmic.di.initKoin
import org.koin.core.KoinApplication

fun KoinApplication.Companion.start(): KoinApplication = initKoin()

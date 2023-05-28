package co.nimblehq.avishek.phong.kmmic.helper

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
}

expect class DispatchersProviderImpl() : DispatchersProvider

package co.nimblehq.avishek.phong.kmmic.helper

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class DispatchersProviderImpl: DispatchersProvider {

    override val io = Dispatchers.Main
    override val main = Dispatchers.Main
    override val default = Dispatchers.Default
}

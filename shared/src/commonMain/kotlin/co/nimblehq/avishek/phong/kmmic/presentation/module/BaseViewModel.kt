package co.nimblehq.avishek.phong.kmmic.presentation.module

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModel() {

    val vmScope: CoroutineScope

    fun clear()
}

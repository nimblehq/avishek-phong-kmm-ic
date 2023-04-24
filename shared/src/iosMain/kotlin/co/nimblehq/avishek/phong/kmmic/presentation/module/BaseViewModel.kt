package co.nimblehq.avishek.phong.kmmic.presentation.module

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class BaseViewModel {

    actual val viewModelScope = MainScope()

    protected actual open fun onCleared() {}

    fun clear() {
        onCleared()
        viewModelScope.cancel()
    }
}

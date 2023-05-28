package co.nimblehq.avishek.phong.kmmic.presentation.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

actual abstract class BaseViewModel : ViewModel() {

    actual val vmScope: CoroutineScope = viewModelScope

    actual fun clear() {
        vmScope.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        clear()
    }
}

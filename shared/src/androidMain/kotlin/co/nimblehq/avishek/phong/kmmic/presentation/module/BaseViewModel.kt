package co.nimblehq.avishek.phong.kmmic.presentation.module

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

actual abstract class BaseViewModel : ViewModel() {

    actual val viewModelScope: CoroutineScope = MainScope()

    actual fun clear() {
        viewModelScope.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        clear()
    }
}

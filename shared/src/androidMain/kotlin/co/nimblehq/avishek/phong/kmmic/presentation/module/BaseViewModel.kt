package co.nimblehq.avishek.phong.kmmic.presentation.module

import androidx.lifecycle.ViewModel
actual abstract class BaseViewModel : ViewModel() {

    actual val viewModelScope = MainScope()

    protected actual open fun onCleared() {}

    fun clear() {
        onCleared()
        viewModelScope.cancel()
    }
}

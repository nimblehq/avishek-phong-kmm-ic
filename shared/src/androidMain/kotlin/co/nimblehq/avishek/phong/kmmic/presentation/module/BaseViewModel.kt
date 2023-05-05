package co.nimblehq.avishek.phong.kmmic.presentation.module

import androidx.lifecycle.viewModelScope as lifecycleViewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

actual abstract class BaseViewModel : ViewModel() {

    actual val viewModelScope: CoroutineScope = lifecycleViewModelScope

    fun clear() {
        viewModelScope.cancel()
    }
}

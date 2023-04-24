package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.data.remote.helper.extension.toErrorMessage
import co.nimblehq.avishek.phong.kmmic.domain.usecase.LogInUseCase
import co.nimblehq.avishek.phong.kmmic.helper.extension.isValidEmail
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class LogInViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isInvalidEmail: Boolean = false,
    var isInvalidPassword: Boolean = false
) {
    constructor() : this(false)

    constructor(error: String?) : this(false, false, error)
}

class LogInViewModel(private val logInUseCase: LogInUseCase): BaseViewModel() {

    private val mutableViewState: MutableStateFlow<LogInViewState> =
        MutableStateFlow(LogInViewState())

    val viewState: StateFlow<LogInViewState> = mutableViewState

    fun logIn(email: String, password: String) {
        if (!validInput(email, password)) { return }
        setLoadingState()
        viewModelScope.launch {
            logInUseCase(email = email, password = password)
                .catch { error -> handleLoginError(error) }
                .collect { loginSuccess() }
        }
    }

    private fun validInput(email: String, password: String): Boolean {
        val isInvalidEmail = !email.isValidEmail()
        val isInvalidPassword = password.isEmpty()
        if (isInvalidEmail or isInvalidPassword)  {
            mutableViewState.update {
                LogInViewState(
                    isInvalidEmail = isInvalidEmail,
                    isInvalidPassword = isInvalidPassword
                )
            }
            return false
        }
        return true
    }

    private fun setLoadingState() {
        mutableViewState.update {
            LogInViewState(isLoading = true)
        }
    }

    private fun handleLoginError(error: Throwable) {
        mutableViewState.update {
            LogInViewState(isLoading = false, error = error.toErrorMessage())
        }
    }

    private fun loginSuccess() {
        mutableViewState.update { LogInViewState(isSuccess = true, isLoading = false) }
    }
}

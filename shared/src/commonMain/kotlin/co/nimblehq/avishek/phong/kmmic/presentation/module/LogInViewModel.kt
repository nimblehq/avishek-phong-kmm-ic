package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.data.remote.helper.extension.toErrorMessage
import co.nimblehq.avishek.phong.kmmic.domain.usecase.LogInUseCase
import co.nimblehq.avishek.phong.kmmic.helper.DispatchersProvider
import co.nimblehq.avishek.phong.kmmic.helper.extension.isValidEmail
import kotlinx.coroutines.flow.*

data class LogInViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isInvalidEmail: Boolean = false,
    var isInvalidPassword: Boolean = false,
) {
    constructor() : this(false)

    constructor(error: String?) : this(false, false, error)
}

class LogInViewModel(
    private val logInUseCase: LogInUseCase,
    private val dispatchersProvider: DispatchersProvider
) : BaseViewModel() {

    private val _viewState: MutableStateFlow<LogInViewState> =
        MutableStateFlow(LogInViewState())
    val viewState: StateFlow<LogInViewState> = _viewState

    fun logIn(email: String, password: String) {
        if (!validInput(email, password)) return
        logInUseCase(email = email, password = password)
            .flowOn(dispatchersProvider.io)
            .onStart { setLoadingState() }
            .catch { error -> handleLoginError(error) }
            .onEach { loginSuccess() }
            .launchIn(vmScope)
    }

    private fun validInput(email: String, password: String): Boolean {
        val isInvalidEmail = !email.isValidEmail()
        val isInvalidPassword = password.isEmpty()
        if (isInvalidEmail or isInvalidPassword) {
            _viewState.update {
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
        _viewState.update {
            LogInViewState(isLoading = true)
        }
    }

    private fun handleLoginError(error: Throwable) {
        _viewState.update {
            LogInViewState(isLoading = false, error = error.toErrorMessage())
        }
    }

    private fun loginSuccess() {
        _viewState.update { LogInViewState(isSuccess = true, isLoading = false) }
    }

    fun clearError() {
        _viewState.update { _viewState.value.copy(error = "") }
    }
}

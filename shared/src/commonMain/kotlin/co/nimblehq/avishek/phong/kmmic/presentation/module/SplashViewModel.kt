package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.usecase.CheckLoggedInUseCase

class SplashViewModel(private val checkLoggedInUseCase: CheckLoggedInUseCase): BaseViewModel() {

    fun checkIfUserLoggedIn(): Boolean {
        return checkLoggedInUseCase()
    }
}

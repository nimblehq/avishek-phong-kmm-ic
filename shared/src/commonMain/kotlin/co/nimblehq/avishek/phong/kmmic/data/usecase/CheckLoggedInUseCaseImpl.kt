package co.nimblehq.avishek.phong.kmmic.data.usecase

import co.nimblehq.avishek.phong.kmmic.domain.repository.AuthenticationRepository
import co.nimblehq.avishek.phong.kmmic.domain.usecase.CheckLoggedInUseCase

class CheckLoggedInUseCaseImpl(
    private val authenticationRepository: AuthenticationRepository
): CheckLoggedInUseCase {

    override fun invoke(): Boolean {
        return authenticationRepository.hasCachedToken()
    }
}

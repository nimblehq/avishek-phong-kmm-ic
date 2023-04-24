package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.domain.repository.AuthenticationRepository

interface CheckLoggedInUseCase {

    operator fun invoke(): Boolean
}

package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.domain.model.Token
import co.nimblehq.avishek.phong.kmmic.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

interface LogInUseCase {
    operator fun invoke(email: String, password: String): Flow<Token>
}

class LogInUseCaseImpl(private val repository: AuthenticationRepository): LogInUseCase {

    override fun invoke(email: String, password: String): Flow<Token> {
        return repository
            .logIn(email, password)
            .onEach { token -> repository.save(token) }
    }
}

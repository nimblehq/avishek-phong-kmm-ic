package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface LogInUseCase {

    operator fun invoke(email: String, password: String): Flow<Token>
}

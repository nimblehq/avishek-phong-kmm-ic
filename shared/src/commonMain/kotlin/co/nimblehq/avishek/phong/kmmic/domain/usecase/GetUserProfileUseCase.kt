package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.domain.model.User
import co.nimblehq.avishek.phong.kmmic.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

interface GetUserProfileUseCase {
    operator fun invoke(): Flow<User>
}

class GetUserProfileUseCaseImpl(
    private val userRepository: UserRepository
): GetUserProfileUseCase {

    override operator fun invoke(): Flow<User> {
        return userRepository
            .getProfile()
    }
}

package co.nimblehq.avishek.phong.kmmic.domain.repository

import co.nimblehq.avishek.phong.kmmic.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getProfile(): Flow<User>
}

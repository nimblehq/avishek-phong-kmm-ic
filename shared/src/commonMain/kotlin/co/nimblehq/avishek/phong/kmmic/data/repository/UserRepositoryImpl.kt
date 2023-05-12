package co.nimblehq.avishek.phong.kmmic.data.repository

import co.nimblehq.avishek.phong.kmmic.data.remote.datasource.UserRemoteDataSource
import co.nimblehq.avishek.phong.kmmic.data.remote.model.toUser
import co.nimblehq.avishek.phong.kmmic.domain.model.User
import co.nimblehq.avishek.phong.kmmic.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
): UserRepository {

    override fun getProfile(): Flow<User> {
        return userRemoteDataSource
            .getProfile()
            .map { it.toUser() }
    }
}

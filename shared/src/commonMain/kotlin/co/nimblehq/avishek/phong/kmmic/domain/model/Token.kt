package co.nimblehq.avishek.phong.kmmic.domain.model

import co.nimblehq.avishek.phong.kmmic.data.remote.model.TokenApiModel

data class Token(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
    val refreshToken: String,
    val createdAt: Int
)

fun Token.toApiModel() = TokenApiModel(
    id = "-",
    type = "-",
    accessToken = accessToken,
    tokenType = tokenType,
    expiresIn = expiresIn,
    refreshToken = refreshToken,
    createdAt = createdAt,
)

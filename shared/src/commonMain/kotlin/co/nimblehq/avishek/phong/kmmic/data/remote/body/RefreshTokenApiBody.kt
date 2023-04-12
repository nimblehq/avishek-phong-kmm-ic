package co.nimblehq.avishek.phong.kmmic.data.remote.body

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import co.nimblehq.avishek.phong.kmmic.BuildKonfig

private const val GRANT_TYPE_REFRESH_TOKEN = "refresh_token"

@Serializable
data class RefreshTokenApiBody(
    @SerialName("grant_type")
    val grantType: String = GRANT_TYPE_REFRESH_TOKEN,
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("client_id")
    val clientId: String = BuildKonfig.CLIENT_ID,
    @SerialName("client_secret")
    val clientSecret: String = BuildKonfig.CLIENT_SECRET,
)

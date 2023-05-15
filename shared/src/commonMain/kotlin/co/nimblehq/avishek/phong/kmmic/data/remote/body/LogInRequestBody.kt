package co.nimblehq.avishek.phong.kmmic.data.remote.body

import co.nimblehq.avishek.phong.kmmic.BuildKonfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val GRANT_TYPE_PASSWORD = "password"

@Serializable
data class LogInRequestBody(
    @SerialName("grant_type")
    val grantType: String = GRANT_TYPE_PASSWORD,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("client_id")
    val clientId: String = BuildKonfig.CLIENT_ID,
    @SerialName("client_secret")
    val clientSecret: String = BuildKonfig.CLIENT_SECRET,
)

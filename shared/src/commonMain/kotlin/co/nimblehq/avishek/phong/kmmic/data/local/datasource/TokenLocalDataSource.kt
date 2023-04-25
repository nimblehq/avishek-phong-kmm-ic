package co.nimblehq.avishek.phong.kmmic.data.local.datasource

import co.nimblehq.avishek.phong.kmmic.data.remote.model.TokenApiModel
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import kotlinx.serialization.ExperimentalSerializationApi

private const val KEY_ACCESS_TOKEN = "access_token"

interface TokenLocalDataSource {
    fun save(token: TokenApiModel)
    fun getToken(): TokenApiModel?
}

@OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
class TokenLocalDataSourceImpl(private val settings: Settings): TokenLocalDataSource {

    override fun save(token: TokenApiModel) =
        settings.encodeValue(TokenApiModel.serializer(), KEY_ACCESS_TOKEN, token)


    override fun getToken(): TokenApiModel? =
        settings.decodeValueOrNull(TokenApiModel.serializer(), KEY_ACCESS_TOKEN)
}

package co.nimblehq.avishek.phong.kmmic.data.remote

import io.ktor.client.request.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import co.nimblehq.avishek.phong.kmmic.BuildKonfig
import co.nimblehq.avishek.phong.kmmic.domain.model.AppError

fun HttpRequestBuilder.path(path: String) {
    url(BuildKonfig.BASE_URL.plus(path))
}

inline fun <reified T> HttpRequestBuilder.setQueryParameters(parameters: T) {
    try {
        val queryParameters = Json.encodeToJsonElement(parameters).jsonObject
        for ((key, value) in queryParameters) {
            parameter(key, value)
        }
    } catch (e: SerializationException) {
        throw AppError(e.message)
    }
}

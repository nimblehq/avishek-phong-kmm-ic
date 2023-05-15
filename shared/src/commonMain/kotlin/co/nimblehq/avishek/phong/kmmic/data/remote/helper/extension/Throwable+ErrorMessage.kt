package co.nimblehq.avishek.phong.kmmic.data.remote.helper.extension

import co.nimblehq.jsonapi.model.JsonApiException

fun Throwable.toErrorMessage() : String? {
    return when(this) {
        is JsonApiException -> this.errors.firstOrNull()?.detail ?: this.message
        else -> this.message
    }
}

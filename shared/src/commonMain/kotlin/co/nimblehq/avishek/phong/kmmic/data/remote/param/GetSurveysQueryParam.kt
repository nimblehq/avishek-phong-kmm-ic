package co.nimblehq.avishek.phong.kmmic.data.remote.param

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSurveysQueryParam(
    @SerialName("page[number]")
    val pageNumber: Int,
    @SerialName("page[size]")
    val pageSize: Int
)

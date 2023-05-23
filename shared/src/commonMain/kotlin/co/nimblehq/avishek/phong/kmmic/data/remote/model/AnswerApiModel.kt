package co.nimblehq.avishek.phong.kmmic.data.remote.model

import co.nimblehq.avishek.phong.kmmic.domain.model.Answer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnswerApiModel(
    @SerialName("id")
    val id: String,
    @SerialName("text")
    val text: String?,
    @SerialName("display_order")
    val displayOrder: Int,
    @SerialName("input_mask_placeholder")
    var inputMaskPlaceholder: String?
)

fun AnswerApiModel.toAnswer(): Answer = Answer(
    id = id,
    displayOrder = displayOrder,
    content = text,
    placeholder = inputMaskPlaceholder
)

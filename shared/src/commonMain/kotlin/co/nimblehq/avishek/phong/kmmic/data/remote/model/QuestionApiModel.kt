package co.nimblehq.avishek.phong.kmmic.data.remote.model

import co.nimblehq.avishek.phong.kmmic.domain.model.Question
import co.nimblehq.avishek.phong.kmmic.domain.model.QuestionDisplayType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionApiModel(
    @SerialName("id")
    val id: String,
    @SerialName("text")
    val text: String,
    @SerialName("display_order")
    val displayOrder: Int,
    @SerialName("display_type")
    val displayType: String,
    @SerialName("pick")
    val pick: String,
    @SerialName("cover_image_url")
    val coverImageUrl: String,
    @SerialName("answers")
    val answers: List<AnswerApiModel>
)

fun QuestionApiModel.toQuestion(): Question = Question(
    id = id,
    text = text,
    displayOrder = displayOrder,
    displayType = QuestionDisplayType.fromString(displayType.uppercase()),
    pick = pick,
    coverImageUrl = coverImageUrl,
    answers = answers.map { it.toAnswer() }
)

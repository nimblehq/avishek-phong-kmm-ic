package co.nimblehq.avishek.phong.kmmic.data.remote.model

import co.nimblehq.avishek.phong.kmmic.data.local.model.SurveyRealmObject
import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SurveyApiModel(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("cover_image_url")
    val coverImageUrl: String,
    @SerialName("questions")
    val questions: List<QuestionApiModel>? = null
)

fun SurveyApiModel.toSurvey() = Survey(
    id = id,
    title = title,
    description = description,
    isActive = isActive,
    coverImageUrl = coverImageUrl,
    questions = questions?.map { it.toQuestion() }
)

fun SurveyApiModel.toSurveyRealmObject() = SurveyRealmObject(
    id = id,
    type = type,
    title = title,
    description = description,
    isActive = isActive,
    coverImageUrl = coverImageUrl
)

package co.nimblehq.avishek.phong.kmmic.domain.model

data class Survey (
    val id: String,
    val title: String,
    val description: String,
    val isActive: Boolean,
    val coverImageUrl: String,
    val questions: List<Question>? = null
)

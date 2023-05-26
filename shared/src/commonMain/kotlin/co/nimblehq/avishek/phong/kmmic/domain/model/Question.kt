package co.nimblehq.avishek.phong.kmmic.domain.model

enum class QuestionDisplayType {
    INTRO,
    STAR,
    HEART,
    SMILEY,
    CHOICE,
    NPS,
    TEXTAREA,
    TEXTFIELD,
    DROPDOWN,
    OUTRO,
    UNKNOWN;

    companion object {
        fun fromString(value: String): QuestionDisplayType {
            return values().find { it.name.equals(value, true) } ?: UNKNOWN
        }
    }
}

data class Question(
    val id: String,
    val text: String,
    val displayOrder: Int,
    val displayType: QuestionDisplayType,
    val pick: String,
    val coverImageUrl: String,
    val answers: List<Answer>
) {
    val sortedAnswers: List<Answer>
        get() = answers.sortedBy { it.displayOrder }
}

package co.nimblehq.avishek.phong.kmmic.domain.model

data class AnswerInput(
    val id: String,
    val content: String?
)

fun AnswerInput.toAnswerSubmission() = AnswerSubmission(id, content)

fun Set<AnswerInput>.toAnswerSubmissions(): List<AnswerSubmission> {
    return this.map { it.toAnswerSubmission() }
}

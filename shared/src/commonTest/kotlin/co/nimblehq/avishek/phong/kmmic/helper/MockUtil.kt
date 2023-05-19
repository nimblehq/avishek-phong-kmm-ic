package co.nimblehq.avishek.phong.kmmic.helper

import co.nimblehq.avishek.phong.kmmic.data.remote.model.AnswerApiModel
import co.nimblehq.avishek.phong.kmmic.data.remote.model.QuestionApiModel
import co.nimblehq.avishek.phong.kmmic.data.remote.model.SurveyApiModel
import co.nimblehq.avishek.phong.kmmic.data.remote.model.UserApiModel
import co.nimblehq.avishek.phong.kmmic.domain.model.Answer
import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.domain.model.User


object MockUtil {

    val mockThrowable = Throwable(message = "mockThrowable")

    val mockSurvey = Survey(
        "id",
        "title",
        "description",
        true,
        "coverImageUrl"
    )

    val mockSurveys = listOf<Survey>(
        mockSurvey.copy(id = "id-1"),
        mockSurvey.copy(id = "id-2"),
        mockSurvey.copy(id = "id-3"),
        mockSurvey.copy(id = "id-4"),
        mockSurvey.copy(id = "id-5"),
    )

    val mockSurveyApiModel = SurveyApiModel(
        "id",
        "type",
        "title",
        "description",
        true,
        "coverImageUrl",
        listOf(
            QuestionApiModel(
                id = "id",
                text = "question_text",
                displayOrder = 0,
                displayType = "intro",
                pick = "pick",
                coverImageUrl = "coverImageUrl",
                answers = listOf(
                    AnswerApiModel(
                        id= "id",
                        text = "answer_text",
                        displayOrder = 0,
                        inputMaskPlaceholder = "placeholder"
                    )
                ),
            )
        )
    )

    val mockUser = User(
        "email",
        "name",
        "avatarUrl"
    )

    val mockUserApiModel = UserApiModel(
        "id",
        "type",
        "email",
        "name",
        "avatarUrl"
    )

    val mockVersion = "v0.1.0 (1562903885)"

    val mockAnswer = Answer(
        id= "id",
        content = "",
        displayOrder = 0,
        placeholder = "placeholder"
    )
}

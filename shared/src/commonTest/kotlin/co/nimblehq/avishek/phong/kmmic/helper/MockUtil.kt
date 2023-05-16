package co.nimblehq.avishek.phong.kmmic.helper

import co.nimblehq.avishek.phong.kmmic.data.remote.model.SurveyApiModel
import co.nimblehq.avishek.phong.kmmic.data.remote.model.UserApiModel
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
}

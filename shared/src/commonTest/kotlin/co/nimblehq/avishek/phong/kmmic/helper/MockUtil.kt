package co.nimblehq.avishek.phong.kmmic.helper

import co.nimblehq.avishek.phong.kmmic.data.remote.model.SurveyApiModel
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
}

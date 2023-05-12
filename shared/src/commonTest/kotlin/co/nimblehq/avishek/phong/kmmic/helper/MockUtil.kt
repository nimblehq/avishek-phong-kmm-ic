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
        Survey(
            "id-1",
            "title",
            "description",
            true,
            "coverImageUrl"
        ),
        Survey(
            "id-2",
            "title",
            "description",
            true,
            "coverImageUrl"
        ),
        Survey(
            "id-3",
            "title",
            "description",
            true,
            "coverImageUrl"
        ),
        Survey(
            "id-4",
            "title",
            "description",
            true,
            "coverImageUrl"
        ),
        Survey(
            "id-5",
            "title",
            "description",
            true,
            "coverImageUrl"
        )

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

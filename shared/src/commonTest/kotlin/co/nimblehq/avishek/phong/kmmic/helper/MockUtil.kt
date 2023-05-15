package co.nimblehq.avishek.phong.kmmic.helper

import co.nimblehq.avishek.phong.kmmic.data.remote.model.SurveyApiModel
import co.nimblehq.avishek.phong.kmmic.domain.model.Survey


object MockUtil {

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
}

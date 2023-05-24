package co.nimblehq.avishek.phong.kmmic.android.ui.screen.home

import androidx.compose.material.DrawerValue
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyHeaderUiModel
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyUiModel

class HomePreviewParameterProvider : PreviewParameterProvider<HomePreviewParameterProvider.Params> {

    private val surveyHeaderUiModel = SurveyHeaderUiModel(
        name = "Avishek",
        imageUrl = "https://cataas.com/cat/says/hello%20world!",
        dateText = "Wednesday, MAY 10"
    )

    private val surveyUiModels = listOf(
        SurveyUiModel(
            id = "1",
            title = "Scarlett Bangkok",
            description = "We'd love to hear from you!",
            imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
        ),
        SurveyUiModel(
            id = "2",
            title = "ibis Bangkok Riverside",
            description = "We'd love to hear from you!",
            imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/287db81c5e4242412cc0_"
        ),
        SurveyUiModel(
            id = "3",
            title = "21 on Rajah",
            description = "We'd love to hear from you!",
            imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/0221e768b99dc3576210_"
        )
    )

    override val values = sequenceOf(
        Params(
            isLoading = false,
            isRefreshing = false,
        ),
        Params(
            isLoading = true,
            isRefreshing = false,
        ),
        Params(
            isLoading = false,
            isRefreshing = true,
        ),
        Params(
            isLoading = false,
            isRefreshing = false,
            drawerState = DrawerValue.Open,
        )
    )

    inner class Params(
        val isLoading: Boolean,
        val isRefreshing: Boolean,
        val drawerState: DrawerValue = DrawerValue.Closed,
        val appVersion: String = "v0.5.0",
        val surveyHeader: SurveyHeaderUiModel = surveyHeaderUiModel,
        val surveys: List<SurveyUiModel> = surveyUiModels,
    )
}

package co.nimblehq.avishek.phong.kmmic.presentation.uimodel

import co.nimblehq.avishek.phong.kmmic.domain.model.User
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateFormat
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTimeFormatter
import kotlinx.datetime.LocalDate

data class SurveyHeaderUiModel (
    val imageUrl: String?,
    val dateText: String,
)

fun User.toSurveyHeaderUiModel(
    date: LocalDate,
    dateTimeFormatter: DateTimeFormatter
): SurveyHeaderUiModel {
    val formattedDate = dateTimeFormatter.getFormattedString(
        date,
        DateFormat.WeekDayMonthDay
    )
    return SurveyHeaderUiModel(
        imageUrl = avatarUrl,
        dateText = formattedDate
    )
}

package co.nimblehq.avishek.phong.kmmic.domain.platform.datetime

import kotlinx.datetime.LocalDate

actual class DateTimeFormatterImpl actual constructor() : DateTimeFormatter {
    actual override fun getFormattedString(
        localDate: LocalDate,
        format: DateFormat
    ): String {
        TODO("Not yet implemented")
    }
}

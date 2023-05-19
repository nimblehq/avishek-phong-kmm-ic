package co.nimblehq.avishek.phong.kmmic.domain.platform.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate

actual class DateTimeFormatterImpl actual constructor() : DateTimeFormatter {
    actual override fun getFormattedString(
        localDate: LocalDate,
        format: DateFormat,
    ): String {
        val formatter = java.time.format.DateTimeFormatter.ofPattern(format.value)
        return localDate.toJavaLocalDate().format(formatter)
    }
}

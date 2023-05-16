package co.nimblehq.avishek.phong.kmmic.domain.platform.datetime

import kotlinx.datetime.LocalDate
import platform.Foundation.*

actual class DateTimeFormatterImpl: DateTimeFormatter {

    actual override fun getFormattedString(
        localDate: LocalDate,
        format: DateFormat
    ): String {
        val date = DateFormatter.yearMonthDay.dateFromString(localDate.toString()) ?: return ""
        return DateFormatter.getBy(format).stringFromDate(date)
    }
}

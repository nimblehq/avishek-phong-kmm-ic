package co.nimblehq.avishek.phong.kmmic.domain.platform.datetime

import kotlinx.datetime.LocalDate

interface DateTimeFormatter {
    fun getFormattedString(localDate: LocalDate, format: DateFormat): String
}

expect class DateTimeFormatterImpl() : DateTimeFormatter {

    override fun getFormattedString(localDate: LocalDate, format: DateFormat): String
}

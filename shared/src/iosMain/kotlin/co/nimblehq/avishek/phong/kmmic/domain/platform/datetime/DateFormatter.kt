package co.nimblehq.avishek.phong.kmmic.domain.platform.datetime

import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.autoupdatingCurrentLocale
import platform.Foundation.localTimeZone

class DateFormatter {

    companion object {
        val weekDayMonthDay: NSDateFormatter = NSDateFormatter()
            .also {
                it.timeZone = NSTimeZone.localTimeZone
                it.locale = NSLocale.autoupdatingCurrentLocale
                it.dateFormat = DateFormat.WeekDayMonthDay.value
            }

        val yearMonthDay: NSDateFormatter = NSDateFormatter()
            .also {
                it.timeZone = NSTimeZone.localTimeZone
                it.locale = NSLocale.autoupdatingCurrentLocale
                it.dateFormat = DateFormat.YearMonthDay.value
            }
    }
}

fun DateFormatter.Companion.getBy(dateFormat: DateFormat): NSDateFormatter {
    return when (dateFormat) {
        is DateFormat.WeekDayMonthDay -> weekDayMonthDay
        is DateFormat.YearMonthDay -> weekDayMonthDay
    }
}

package co.nimblehq.avishek.phong.kmmic.domain.platform.datetime

sealed class DateFormat(val value: String) {

    object WeekDayMonthDay: DateFormat("EEEE, MMMM d")
    object YearMonthDay: DateFormat("yyyy-MM-dd")
}

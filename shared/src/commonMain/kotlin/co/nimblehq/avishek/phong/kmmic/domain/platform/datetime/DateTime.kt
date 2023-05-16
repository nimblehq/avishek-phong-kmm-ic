package co.nimblehq.avishek.phong.kmmic.domain.platform.datetime

import kotlinx.datetime.*

interface DateTime {
    fun today(): LocalDate
}

class DateTimeImpl: DateTime {

    override fun today(): LocalDate {
        return Clock.System.todayIn(TimeZone.currentSystemDefault())
    }
}

package com.goen.utils.model

import com.goen.utils.enums.FirstDayOfWeek
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

internal data class Month private constructor(
    val yearMonth: YearMonth,
    val today: LocalDate
) {

    companion object {
        fun of(date: LocalDate) = Month(YearMonth.of(date.year, date.month), date)

        fun of(monthIndex: Int): Month {
            val isDecember = monthIndex % 12 == 0
            val year = if (isDecember) {
                monthIndex /12 -1
            } else {
                monthIndex /12
            }

            val month = if (isDecember) {
                12
            }else {
                monthIndex % 12
            }

            return of(LocalDate.of(year, month, 1))
        }
    }

    val weeksOfMonth: List<Week> get() {
        val firstDayOfMonth = LocalDate.of(yearMonth.year, yearMonth.month, 1)
        val firstDayWeek = firstDayOfMonth.dayOfWeek
        val lackDate = calcLackDateOfWeek(firstDayWeek, CalendarManager.firstDayOfWeek)

        val firstDayOfFirstWeek = firstDayOfMonth.minusDays(lackDate.toLong())
        var weekRowCount = (lackDate + yearMonth.lengthOfMonth()) / 7

        if ((lackDate + yearMonth.lengthOfMonth()) % 7 == 0) {
            weekRowCount--
        }

        return (0..weekRowCount).map {
            Week.of(firstDayOfFirstWeek.plusDays((it * 7).toLong()))
        }
    }

    private fun calcLackDateOfWeek(dayOfWeek: DayOfWeek, firstDayOfWeek: FirstDayOfWeek): Int =
        when (firstDayOfWeek) {
            FirstDayOfWeek.SUNDAY -> {
                if(dayOfWeek == DayOfWeek.SUNDAY) {
                    0
                } else {
                    dayOfWeek.value
                }
            }
            FirstDayOfWeek.MONDAY -> dayOfWeek.value - 1
        }



}

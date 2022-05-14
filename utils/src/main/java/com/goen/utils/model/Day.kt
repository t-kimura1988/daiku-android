package com.goen.utils.model

import com.goen.utils.enums.DayOfMonthType
import com.goen.utils.model.horidayLogic.HolidayStrategy
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate


internal data class Day(
    val day: LocalDate
) {
    fun dayOfMonthType(thisMonth: Month, holidayStrategy: HolidayStrategy): DayOfMonthType {
        if(day.month != thisMonth.yearMonth.month) {
            return DayOfMonthType.DAY_OF_OTHER_MONTH
        }
        if(holidayStrategy.isHoliday(day)) {
            return DayOfMonthType.HOLIDAY
        }
        return when (day.dayOfWeek) {
            DayOfWeek.SUNDAY -> DayOfMonthType.SUNDAY
            DayOfWeek.SATURDAY -> DayOfMonthType.SATURDAT
            else -> DayOfMonthType.WEEKDAY
        }
    }
}
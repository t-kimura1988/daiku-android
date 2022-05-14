package com.goen.utils.model.horidayLogic

import org.threeten.bp.LocalDate


interface HolidayStrategy {
    fun isHoliday(date: LocalDate): Boolean
}

class DefaultHolidayStrategy : HolidayStrategy{
    override fun isHoliday(date: LocalDate): Boolean = false
}
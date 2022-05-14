package com.goen.utils.model

import org.threeten.bp.LocalDate


internal data class Week(
    val days: List<Day>
) {
    companion object{
        fun of(firstDayOfWeek: LocalDate): Week {
            val weekDays = (0..6).map {
                val day = firstDayOfWeek.plusDays(it.toLong())
                Day(day)
            }
            return Week(weekDays)
        }
    }
}
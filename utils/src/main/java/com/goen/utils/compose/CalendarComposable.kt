package com.goen.utils.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.goen.utils.compose.calendar.CalendarHeader
import com.goen.utils.compose.calendar.CalendarTable
import com.goen.utils.compose.calendar.WeekdayLabel
import com.goen.utils.model.Day
import com.goen.utils.model.Month
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

@Composable
fun CalendarComposable(
    selectedDate: LocalDate = LocalDate.now(),
    onChangePage: (YearMonth) -> Unit = {},
    onSelectDay: (LocalDate) -> Unit = {}
) {
    val month: MutableState<Month> = remember { mutableStateOf(Month.of(selectedDate)) }
    val rememberSelectedDate = remember { mutableStateOf(selectedDate) }
    CalendarLayout(
        month = month.value,
        today = selectedDate,
        selectedDay = rememberSelectedDate.value,
        onChangeMonth = {
            month.value = it
            onChangePage(it.yearMonth)
        },
        onSelectDay = {
            rememberSelectedDate.value = it.day
            onSelectDay(it.day)
        }
    )
}

@Composable
private fun CalendarLayout(
    modifier: Modifier = Modifier,
    month: Month,
    today: LocalDate? = null,
    selectedDay: LocalDate? = null,
    onChangeMonth: (Month) -> Unit = {},
    onSelectDay: (Day) -> Unit = {}
    ) = Column(modifier = modifier){
        CalendarHeader(
            month,
            onChangeMonth
        )
        Divider()
        WeekdayLabel()
        CalendarTable(
            month = month,
            today = today,
            selectedDay = selectedDay,
            onSelectDay = onSelectDay
        )
    }
package com.goen.utils.compose.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.goen.utils.enums.DayOfMonthType
import com.goen.utils.model.CalendarManager
import com.goen.utils.model.Day
import com.goen.utils.model.Month
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@Composable
internal fun CalendarTable(
    month: Month,
    today: LocalDate? = null,
    selectedDay: LocalDate? = null,
    onChangePage: (Month) -> Unit = {},
    onSelectDay: (Day) -> Unit = {}
) = Column (
    verticalArrangement = Arrangement.SpaceAround,
    modifier = Modifier.fillMaxSize()
        ) {
    month.weeksOfMonth.forEach { week ->
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            week.days.forEach { day ->
                DayCell(
                    day = day,
                    dayOfMonthType = day.dayOfMonthType(month, CalendarManager.holidayStrategy),
                    isToday = day.day == today,
                    isSelected = day.day == selectedDay,
                    onSelect = {onSelectDay(it)}
                )
            }
        }
    }
}
@Composable
private fun DayCell(
    day: Day,
    dayOfMonthType: DayOfMonthType,
    isToday: Boolean = false,
    isSelected:Boolean = false,
    onSelect: (Day) -> Unit = {}
) {
    val textColor = when {
        isSelected -> MaterialTheme.colors.surface
        isToday -> CalendarManager.Colors.Today
        else -> when(dayOfMonthType) {
            DayOfMonthType.WEEKDAY -> CalendarManager.Colors.Weekday
            DayOfMonthType.SUNDAY -> CalendarManager.Colors.Sunday
            DayOfMonthType.SATURDAT -> CalendarManager.Colors.Saturday
            DayOfMonthType.HOLIDAY -> CalendarManager.Colors.Holiday
            DayOfMonthType.DAY_OF_OTHER_MONTH -> Color.LightGray
        }
    }

    val backgroundColor = if (isSelected) {
        CalendarManager.Colors.Selected
    } else {
        Color.Unspecified
    }

    Box(contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day.day.format(DateTimeFormatter.ofPattern(CalendarManager.Localizable.DATE_FORMAT)),
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable(enabled = !isSelected, onClick = {onSelect(day)})
                    .background(backgroundColor, CalendarManager.Layout.selectedBackground)
                    .widthIn(40.dp)
                    .padding(8.dp)
            )
        }
    }


}

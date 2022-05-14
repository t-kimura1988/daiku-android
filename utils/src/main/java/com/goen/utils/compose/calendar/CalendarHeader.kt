package com.goen.utils.compose.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.goen.utils.model.Month
import org.threeten.bp.format.DateTimeFormatter
import com.goen.utils.model.CalendarManager
import java.time.LocalDate

@Composable
internal fun CalendarHeader(
    month: Month,
    changeMonth: (Month) -> Unit
) = Row(
    horizontalArrangement = Arrangement.SpaceAround,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
) {
    var nextMonth = Month.of(date = month.today.plusMonths(1))
    var backMonth = Month.of(date = month.today.minusMonths(1))

    TextButton(onClick = { changeMonth(backMonth) }) {
        Text(text = "＜" + backMonth.yearMonth.format(DateTimeFormatter.ofPattern(CalendarManager.Localizable.MONTH_FORMAT)))
    }
    Text(
        text = month.yearMonth.format(DateTimeFormatter.ofPattern(CalendarManager.Localizable.YEAR_MONTH_FORMAT)),
        fontWeight = FontWeight.Bold
    )
    TextButton(onClick = { changeMonth(nextMonth) }) {
        Text(text = nextMonth.yearMonth.format(DateTimeFormatter.ofPattern(CalendarManager.Localizable.MONTH_FORMAT))+ "＞")
    }
}
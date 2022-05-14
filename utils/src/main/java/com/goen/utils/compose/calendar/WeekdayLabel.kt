package com.goen.utils.compose.calendar

import android.graphics.Paint
import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goen.utils.enums.FirstDayOfWeek
import com.goen.utils.model.CalendarManager

@Composable
internal fun WeekdayLabel() {
    var weekdayList = listOf(
        CalendarManager.Localizable.MON_LABEL,
        CalendarManager.Localizable.THU_LABEL,
        CalendarManager.Localizable.WED_LABEL,
        CalendarManager.Localizable.TUE_LABEL,
        CalendarManager.Localizable.FRI_LABEL,
        CalendarManager.Localizable.SAT_LABEL,
        CalendarManager.Localizable.SUN_LABEL,
    )

    if (CalendarManager.firstDayOfWeek == FirstDayOfWeek.SUNDAY) {
        weekdayList = listOf(CalendarManager.Localizable.SUN_LABEL) + weekdayList.slice(0..5)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ){
        weekdayList.forEach{label ->
            val color = when (label) {
                CalendarManager.Localizable.SUN_LABEL -> CalendarManager.Colors.Sunday
                CalendarManager.Localizable.SAT_LABEL -> CalendarManager.Colors.Saturday
                else -> CalendarManager.Colors.Weekday
            }
            Text(text = label, color = color)
        }
    }

}
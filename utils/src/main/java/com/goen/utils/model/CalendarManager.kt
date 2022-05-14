package com.goen.utils.model

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.goen.utils.enums.FirstDayOfWeek
import com.goen.utils.enums.SwipeDirection
import com.goen.utils.model.horidayLogic.DefaultHolidayStrategy
import com.goen.utils.model.horidayLogic.HolidayStrategy

object CalendarManager {
    var holidayStrategy: HolidayStrategy = DefaultHolidayStrategy()

    var firstDayOfWeek = FirstDayOfWeek.SUNDAY

    object Colors {
        var Sunday = Color.Red
        var Saturday = Color.Blue
        var Holiday = Sunday
        var Weekday = Color.Gray
        var Today = Color.Magenta
        var Selected = Color.Blue
    }

    object Layout {
        var calendarHeight = 320.dp
        var selectedBackground: Shape = RoundedCornerShape(12.dp)
        var swipeDirection: SwipeDirection = SwipeDirection.HORIZONTAL
    }

    object Localizable {
        var YEAR_MONTH_FORMAT = "yyyy年MM月"
        var MONTH_FORMAT = "MM月"
        var DATE_FORMAT = "d"
        var SUN_LABEL = "日"
        var MON_LABEL = "月"
        var THU_LABEL = "火"
        var WED_LABEL = "水"
        var TUE_LABEL = "木"
        var FRI_LABEL = "金"
        var SAT_LABEL = "土"
    }
}
package com.goen.utils.extentions

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


fun String?.toLocalDateParse(
): LocalDate {
    if (this == null) {
        return LocalDate.now()
    }

    return LocalDate.parse(this, DateTimeFormatter.ISO_DATE)
}

fun String?.toDueDateFormat() : String {
    if(this == null || this == "") {
        return "設定なし"
    }
    val date = LocalDate.parse(this, DateTimeFormatter.ISO_DATE)
    return date.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日まで"))
}
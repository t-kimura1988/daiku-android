package com.goen.utils.extentions

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

fun Date?.format(
    dtf: String
): String {
    return this?.format(dtf) ?: ""
}

fun String?.YYYYMMDD_JP(): String {
    if (this == "") { return ""}
    var data = LocalDate.parse(
        this,
        DateTimeFormatter.ISO_DATE
    )

    var formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日")

    return data.format(formatter)
}
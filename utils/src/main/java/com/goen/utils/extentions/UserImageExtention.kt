package com.goen.utils.extentions

import com.goen.utils.R
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


fun String?.userImage(
): Any {

    return this ?: R.drawable.samurai
}
package com.goen.utils.extentions

import java.util.*

fun Date?.format(
    dtf: String
): String {
    return this?.format(dtf) ?: ""
}
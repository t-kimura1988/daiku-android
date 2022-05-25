package com.goen.utils.extentions

import com.goen.utils.R


fun String?.userImage(
): Any {

    return this ?: R.drawable.samurai
}
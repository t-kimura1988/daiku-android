package com.goen.utils.validate

import android.util.Log

open class BaseValidate {

    fun size(item: String, size: Int): Boolean{
        return item.length > size
    }

    fun require(item: String?): Boolean {
        return item != null && item != ""
    }
}
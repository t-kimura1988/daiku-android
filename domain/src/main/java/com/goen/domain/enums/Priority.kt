package com.goen.domain.enums

import android.content.res.Resources
import android.util.Log
import androidx.compose.ui.graphics.Color
import java.lang.Exception

enum class Priority(
    val code: String,
    val value: String,
    val backgroundColor: Color,
    val textColor: Color
) {
    ROW("0", "低", Color.Cyan, Color.Black),
    NORMAL("1", "中", Color.Green, Color.White),
    HEIGHT("2", "高", Color.Magenta, Color.White),
    TOP("3", "最優先", Color.Red, Color.White),
    NEW("99", "新規作成", Color.Red, Color.White);

    companion object {
        fun of(code: String?): Priority{
            if(code == null || code == "") {
                return of("99")
            }
            return values().find { it.code == code } ?: throw Exception("enum error")
        }
    }
}
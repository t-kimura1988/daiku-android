package com.goen.domain.enums

import android.content.res.Resources
import android.util.Log
import androidx.compose.ui.graphics.Color
import java.lang.Exception

enum class ProcessStatus(
    val code: String,
    val value: String,
    val backgroundColor: Color,
    val textColor: Color
) {
    NEW("0", "新規", Color.Yellow, Color.Black),
    DOING("1", "対応", Color.Blue, Color.White),
    PROBLEM("2", "問題", Color.Red, Color.White),
    COMPLETE("3", "完了", Color.Gray, Color.White);

    companion object {
        fun of(code: String?): ProcessStatus{
            if(code == null || code == "") {
                return of("0")
            }
            return values().find { it.code == code } ?: throw Exception("enum error")
        }
    }
}
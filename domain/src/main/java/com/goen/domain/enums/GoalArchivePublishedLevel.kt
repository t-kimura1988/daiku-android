package com.goen.domain.enums

import androidx.compose.ui.graphics.Color

enum class GoalArchivePublishedLevel(
    val code: String,
    val value: String,
    val backgroundColor: Color,
    val textColor: Color
) {
    OWN("0", "自分のみ", Color.Yellow, Color.Black),
    ONLY_GOAL("1", "目標のみ", Color.Blue, Color.White),
    ALL("2", "全体公開", Color.Red, Color.White);

    companion object {
        fun of(code: String?): GoalArchivePublishedLevel{
            if(code == null || code == "") {
                return of("0")
            }
            return values().find { it.code == code } ?: throw Exception("enum error")
        }
    }
}
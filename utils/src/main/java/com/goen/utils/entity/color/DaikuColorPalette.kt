package com.goen.utils.entity.color

import androidx.compose.ui.graphics.Color
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import com.goen.utils.entity.DaikuDarkAppColor
import com.goen.utils.entity.DaikuLightAppColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

interface DaikuColorPalette {
    val primary: Color
    val text: Color
    val materialColors: Colors
    val topAppBarTitle: Color
    val topAppBarColor: Color
}

fun DaikuLightColorPalette(): DaikuColorPalette = object: DaikuColorPalette {
    override val primary: Color = DaikuLightAppColor.primary
    override val text: Color = DaikuLightAppColor.text
    override val topAppBarTitle: Color = DaikuLightAppColor.topAppBarTitle
    override val topAppBarColor: Color = DaikuLightAppColor.topAppBarColor
    override val materialColors: Colors = lightColors(
        primary = DaikuLightAppColor.primary,
        background = DaikuLightAppColor.background,
    )

}

fun DaikuDarkColorPalette(): DaikuColorPalette = object: DaikuColorPalette {
    override val primary: Color = DaikuDarkAppColor.primary
    override val text: Color = DaikuDarkAppColor.text
    override val topAppBarTitle: Color = DaikuDarkAppColor.topAppBarTitle
    override val topAppBarColor: Color = DaikuLightAppColor.topAppBarColor
    override val materialColors: Colors = darkColors(
        primary = DaikuDarkAppColor.primary,
        background = DaikuDarkAppColor.background
    )
}
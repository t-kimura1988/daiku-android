package com.goen.utils.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.goen.utils.entity.*
import com.goen.utils.entity.color.DaikuColorPalette
import com.goen.utils.entity.color.DaikuDarkColorPalette
import com.goen.utils.entity.color.DaikuLightColorPalette
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DaikuAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colors = if (darkTheme) DaikuDarkColorPalette() else DaikuLightColorPalette()
    val systemUiController = rememberSystemUiController()
    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = Color.Transparent
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }
    CompositionLocalProvider(
        LocalDaikuColors provides colors) {
        MaterialTheme(
            colors = colors.materialColors,
            content = content
        )
    }
}

object DaikuAppTheme {
    val colors: DaikuColorPalette
        @Composable
        @ReadOnlyComposable
        get() = LocalDaikuColors.current
}


internal val LocalDaikuColors = staticCompositionLocalOf { DaikuLightColorPalette() }
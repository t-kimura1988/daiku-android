package com.goen.utils.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImgPlaceholder(@androidx.annotation.DrawableRes resourceId: Int) {

    Image(
        modifier = Modifier
            .padding(
                PaddingValues(
                    start = 11.25.dp,
                    top = 9.25.dp,
                    end = 9.25.dp,
                    bottom = 9.25.dp
                )
            )
            .fillMaxSize(),
        painter = painterResource(id = resourceId),
        contentDescription = null
    )
}
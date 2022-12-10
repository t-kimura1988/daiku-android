package com.goen.utils.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.goen.utils.R


@Composable
fun DaikuAccountCompose(
    accountName: String,
    accountImg: String?
) {
    var data = accountImg ?: R.drawable.samurai

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier
                .size(50.dp),
            painter = rememberImagePainter(
                data = data,
                builder = {
                    placeholder(R.drawable.samurai)
                    transformations(CircleCropTransformation())
                },
            ),
            contentDescription = accountName
        )
        Text(
            text = accountName,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}
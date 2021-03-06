package com.goen.account.ui.comp.detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.goen.account.R
import com.goen.domain.model.result.GoalSearchResult
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun accountDetailGoalSearchItemCompose(
    item: GoalSearchResult,
    onClickItem: (goalId: Int, createDate: String) -> Unit,
    clickFavorite: (goalId: Int, createDate: String) -> Unit
) {
    var favoriteFlg = remember{ mutableStateOf(false)}
    var data = item.createdAccountImg ?: R.drawable.samurai

    LaunchedEffect(key1 = item, block = {
        favoriteFlg.value = item.favoriteId > 0
    })

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = {
                onClickItem(item.id, item.createDateString)
            })
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)) {
            Text(
                text = item.title,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.purpose,
                color = Color.Gray,
                fontSize = 15.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
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
                contentDescription = item.accountName
            )
            Text(
                text = item.accountName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
    Divider(startIndent = 8.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        IconToggleButton(
            checked = favoriteFlg.value,
            onCheckedChange = {
                favoriteFlg.value = it

                clickFavorite(item.id, item.createDateString)
            }
        ) {
            val tint = animateColorAsState(
                if (favoriteFlg.value) Color(0xFFEC407A) else Color(0xFFB0BEC5)
            )
            Icon(Icons.Filled.Favorite, contentDescription = "???????????????",
                tint = tint.value)
        }
    }
    Divider(thickness = 6.dp)
}

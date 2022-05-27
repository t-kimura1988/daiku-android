package com.goen.account.ui.comp.detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.goen.account.R
import com.goen.domain.model.result.GoalSearchResult
import com.goen.utils.extentions.YYYYMMDD_JP

@Composable
fun AccountDetailGoalSearchItemCompose(
    item: GoalSearchResult,
    onClickItem: (goalId: Int, createDate: String) -> Unit,
    clickFavorite: (goalId: Int, createDate: String) -> Unit
) {
    var favoriteFlg = remember{ mutableStateOf(false)}
    var data = item.createdAccountImg ?: R.drawable.samurai

    LaunchedEffect(key1 = item, block = {
        favoriteFlg.value = item.isFavorite()
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
            Row {
                Text(
                    text = "期間: ${item.dueDate.YYYYMMDD_JP()}まで",
                    fontSize = 13.sp,
                    color = Color.LightGray,
                )

            }
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
            Icon(Icons.Filled.Favorite, contentDescription = "お気に入り",
                tint = tint.value)
        }
    }
    Divider(thickness = 6.dp)
}

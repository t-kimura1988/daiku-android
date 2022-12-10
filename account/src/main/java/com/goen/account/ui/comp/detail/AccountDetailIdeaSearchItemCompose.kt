package com.goen.account.ui.comp.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.goen.account.R
import com.goen.domain.model.result.idea.IdeaSearchResult

@Composable
fun AccountDetailIdeaSearchItemCompose(
    item: IdeaSearchResult,
    onClickItem: (storyId: Int, ideaId: Int) -> Unit
) {
    var favoriteFlg = remember{ mutableStateOf(false)}
    var data = item.createdAccountImg ?: R.drawable.samurai


    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = {
                onClickItem(item.getStoryId, item.id)
            })
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)) {
            Text(
                text = item.body,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
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
                contentDescription = item.createdAccountName
            )
            Text(
                text = item.createdAccountName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
    Divider(thickness = 6.dp)
}

package com.goen.home.ui.comp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Colors
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.goen.domain.model.result.goal.GoalArchiveSearchResult
import com.goen.home.R
import com.goen.utils.extentions.userImage

@Composable
fun GoalArchiveListItemCompose(
    goalArchive: GoalArchiveSearchResult,
    gotoArchiveDetailPage: (
        archiveId: Int,
        archiveCreateDate: String,
        accountId: Int
    ) -> Unit,
    accountId: Int
) {
    var moreThoughts: MutableState<Boolean> = remember{ mutableStateOf(false)}

    var thoughtsLine: Int = if(moreThoughts.value)  Int.MAX_VALUE else 3

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    gotoArchiveDetailPage(
                        goalArchive.id,
                        goalArchive.archiveCreateDate,
                        accountId
                    )
                }
                .fillMaxSize()
        ) {
            Column() {
                Text(
                    text = goalArchive.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = goalArchive.thoughts,
                    maxLines = thoughtsLine,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 16.sp
                )
            }
        }
        Text(
            text = if (moreThoughts.value) "折りたたむ" else "もっと見る",
            fontSize = 13.sp,
            color = Color.LightGray,
            modifier = Modifier.clickable {
                moreThoughts.value = !moreThoughts.value
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End)
        ) {
            Image(
                modifier = Modifier
                    .size(45.dp),
                painter = rememberImagePainter(
                    data = goalArchive.userImage.userImage(),
                    builder = {
                        placeholder(R.drawable.samurai)
                        transformations(CircleCropTransformation())
                    },
                ),
                contentDescription = goalArchive.accountName
            )
            Text(
                text = goalArchive.accountName,
                fontSize = 16.sp)
        }
        Text(
            text = "プロセス数:${goalArchive.processCount}回",
            color = Color.LightGray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
    Divider(thickness = 6.dp)
}
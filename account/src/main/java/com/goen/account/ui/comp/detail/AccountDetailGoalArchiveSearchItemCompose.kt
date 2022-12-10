package com.goen.account.ui.comp.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goen.domain.model.result.goal.GoalArchiveSearchResult

@Composable
fun AccountDetailGoalArchiveSearchItemCompose(
    item: GoalArchiveSearchResult,
    loginId: Int,
    onClickItem: (archiveId: Int, archiveCreateDate: String, loginId: Int) -> Unit
) {

    LaunchedEffect(key1 = item, block = {
    })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = {
                onClickItem(item.id, item.archiveCreateDate, loginId)
            })
    ) {

        Text(
            text = item.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(
            text = item.thoughts,
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 6.dp),
            maxLines = 4
        )

        Surface(
            color = Color.Green,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(
                width = 1.dp,
                color = item.publishedLevel.backgroundColor
            ),
            elevation = 8.dp,
            modifier = Modifier
                .padding(end = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = item.publishedLevel.value,
                color = item.publishedLevel.textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(color = item.publishedLevel.backgroundColor)
                    .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
            )
        }
    }

}

package com.goen.utils.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goen.utils.extentions.YYYYMMDD_JP


@Composable
fun GoalListItemComposable(
    goalTitle: String,
    goalDueDate: String?,
    goalPurpose: String,
    isArchive: Boolean,
    goalCreateDate: String,
    makiSortNum: String
) {

        Text(
            text = goalTitle,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        if(isArchive) {
            Surface(
                color = Color.Green,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.White
                ),
                elevation = 8.dp,
                modifier = Modifier
                    .padding(end = 8.dp, bottom = 8.dp)
                    .width(80.dp)
            ) {
                Text(
                    text = "達成済",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(color = Color.Green)
                        .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
                )
            }
        }

        Column (
            modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)
        ){
            Text(
                text = "完了予定日：${goalDueDate.YYYYMMDD_JP()}",
                color = Color.Gray,
                fontSize = 12.sp
            )
            Text(
                text = "作成日：${goalCreateDate.YYYYMMDD_JP()}",
                color = Color.Gray,
                fontSize = 12.sp
            )

        }


        Text(
            text = makiSortNum,
            color = Color.LightGray,
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Text(
            text = goalPurpose,
            color = Color.Gray,
            fontSize = 15.sp
        )

}
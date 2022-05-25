package com.goen.goal.ui.compose.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.goen.domain.model.result.goal.GoalDetailResult
import com.goen.goal.R
import com.goen.utils.extentions.userImage

@Composable
fun GoalDetailCompose(
    goalInfo: GoalDetailResult
) {

    var data = goalInfo.createdAccountImg.userImage()
    Column(
        modifier = Modifier.padding(8.dp)
    ) {

        Text(
            modifier = Modifier.padding(8.dp),
            text = goalInfo.title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Row(
            modifier = Modifier.padding(8.dp)
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
                contentDescription = goalInfo.accountName
            )
            Text(
                text = goalInfo.accountName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
        Divider(
        )

        Text(
            text = stringResource(id = R.string.goal_detail_purpose_label),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = goalInfo.purpose,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )

        Text(
            text = stringResource(id = R.string.goal_detail_aim_label),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = goalInfo.aim,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )

        Text(
            text = stringResource(id = R.string.goal_detail_due_date_label),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = goalInfo.dueDateFormatter,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )

    }
    Divider(
        thickness = 6.dp
    )
}
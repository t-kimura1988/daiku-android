package com.goen.goal.ui.compose.archive

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.goen.domain.model.param.goal.GoalDetailResult
import com.goen.goal.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.goen.domain.model.result.goal.GoalArchiveDetailResult
import com.goen.domain.model.result.goal.GoalArchiveSearchResult
import com.goen.utils.extentions.format
import org.threeten.bp.format.DateTimeFormatter

@Composable
fun GoalArchiveDetailCompose(
    goalArchive: GoalArchiveSearchResult
) {

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "目標達成の感想",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = goalArchive.thoughts,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )

    }
    Divider(
        thickness = 6.dp
    )
}
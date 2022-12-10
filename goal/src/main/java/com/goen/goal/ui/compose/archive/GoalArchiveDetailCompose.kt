package com.goen.goal.ui.compose.archive

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.goen.domain.model.result.goal.GoalArchiveSearchResult

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
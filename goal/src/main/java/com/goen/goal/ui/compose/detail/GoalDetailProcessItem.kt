package com.goen.goal.ui.compose.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goen.domain.enums.ProcessStatus
import com.goen.domain.model.result.process.ProcessResult

@Composable
fun GoalDetailProcessItem(
    processItem: ProcessResult,
    onClickItem: (processId: Int, goalId: Int, goalCreateDate: String) -> Unit ,
    line: Int = 3,
) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = { onClickItem(processItem.id, processItem.goalId, processItem.createDateString) })
    ) {
        val processStatus: ProcessStatus = ProcessStatus.of(processItem.processStatus)
        Row {
            if(processItem.processStatus != "") {
                Surface(
                    color = processStatus.backgroundColor,
                    contentColor = processStatus.backgroundColor,
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
                        text = processStatus.value,
                        color = processStatus.textColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(color = processStatus.backgroundColor)
                            .align(Alignment.CenterVertically)
                            .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
                    )
                }
            }
            Column() {
                Text(
                    text = processItem.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        Text(
            text = processItem.body,
            modifier = Modifier.padding(8.dp),
            maxLines = line
        )
    }
}
package com.goen.process.ui.comp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import com.goen.domain.enums.Priority
import com.goen.domain.enums.ProcessStatus
import com.goen.domain.model.result.process.ProcessResult

@Composable
fun ProcessDetailCompose(
    processDetail: ProcessResult,
    processStatusUpdate: (process: Int, status: Int, priority: Int) -> Unit
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ){
        Text(
            text = processDetail.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = processDetail.body,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        )
        Divider()
        Box(
            modifier = Modifier.clickable {
                processStatusUpdate(processDetail.id, processDetail.processStatus.toInt(), processDetail.priority.toInt()) }
        ) {
            status(
                status = processDetail.processStatus,
                priorityValue = processDetail.priority
            )
        }

    }

    Divider(
        thickness = 5.dp,
    )
}

@Composable
fun status(
    status: String,
    priorityValue: String
) {
    var processStatus: ProcessStatus = ProcessStatus.of(status)
    var priority: Priority = Priority.of(priorityValue)
    Row(
        modifier = Modifier
            .padding(8.dp)
    ) {

        if(status != "" && priorityValue != "") {
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
            Surface(
                color = priority.backgroundColor,
                contentColor = priority.backgroundColor,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.White
                ),
                elevation = 8.dp,
                modifier = Modifier
                    .width(80.dp)
            ) {
                Text(
                    text = priority.value,
                    color = priority.textColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(color = priority.backgroundColor)
                        .align(Alignment.CenterVertically)
                        .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
                )
            }
        }
    }
}
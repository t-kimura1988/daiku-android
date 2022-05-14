package com.goen.process.ui.comp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.goen.domain.enums.Priority
import com.goen.domain.enums.ProcessStatus
import com.goen.domain.model.result.process.ProcessHistoryResult

@Composable
fun ProcessHistoryListItem(
    item: ProcessHistoryResult,
    commentAddPage: (processHistory: Int, goalCreateDate: String) -> Unit) {

    Log.println(Log.INFO, "processDetailId", item.id.toString())
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .defaultMinSize(minHeight = 60.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.weight(2f)
            ) {

                if(item.beforePriority != item.priority) {

                    var priority: Priority = Priority.of(item.priority)
                    var oldPriority: Priority = Priority.of(item.beforePriority)
                    Text(
                        text = "優先度: ${oldPriority.value} -> ${priority.value}",
                        color = Color.Gray )
                }
                if(item.beforeProcessStatus != item.processStatus) {

                    var priority: ProcessStatus = ProcessStatus.of(item.processStatus)
                    var oldPriority: ProcessStatus = ProcessStatus.of(item.beforeProcessStatus)
                    Text(
                        text = "ステータス: ${oldPriority.value} -> ${priority.value}",
                        color = Color.Gray
                    )
                }
                if(item.beforeTitle != "") {
                    Text(
                        text = "タイトル変更",
                        color = Color.Gray
                    )
                }
                if(item.beforeBody != "") {
                    Text(
                        text = "プロセス内容変更",
                        color = Color.Gray
                    )
                }
            }

            if(item.comment != null) {
                TextButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        commentAddPage(item.id, item.createDateString)
                    }
                ) {
                    Text("コメント編集")
                }
            }
        }
        if(item.comment != null) {
            Text(
                text = item.comment,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

    Divider(
        modifier = Modifier.fillMaxWidth(),
        startIndent = 10.dp,
        color = Color.Gray
    )

}
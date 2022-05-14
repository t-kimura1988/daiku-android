package com.goen.goal.ui.compose.edit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.goen.utils.compose.CalendarComposable
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun GoalTermDialog(
    date: LocalDate = LocalDate.now(),
    changeDialog: (Boolean) -> Unit,
    alertFlg: Boolean,
    selectDay: (LocalDate) -> Unit
) {

    TextField(
        value = date.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日まで")),
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { changeDialog(true) },
        enabled = false
    )
    if(alertFlg) {
        AlertDialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = { /*TODO*/ },
            text = {
                CalendarComposable(
                    selectedDate = date,
                    onSelectDay = {
                        selectDay(it)
                        changeDialog(false)
                    }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    selectDay(LocalDate.now())
                    changeDialog(false)
                }) {
                    Text("今日")
                }
                TextButton(onClick = {
                    changeDialog(false)
                }) {
                    Text("閉じる")
                }
            }
        )

    }
}
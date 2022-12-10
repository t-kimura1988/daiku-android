package com.goen.utils.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.goen.utils.R


@Composable
fun TermSearchDialog(
    dialogFlg: Boolean,
    currentKey: Int,
    changeDialog: (flg: Boolean) -> Unit,
    changeKey:(year: Int, month: Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(currentKey) }


    var expandedMonth by remember { mutableStateOf(false) }
    val months = listOf(0,1, 2,3,4,5,6,7,8,9,10,11,12)
    var selectedMonthIndex by remember { mutableStateOf(0) }


    TextButton(onClick = {
        changeDialog(true)
    }) {
        Text(stringResource(id = R.string.term_search_button))
    }

    if(dialogFlg) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            text = {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {

                    Box(modifier = Modifier.padding(9.dp)) {
                        Text("${currentKey}年",modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded = true }))
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .padding()
                        ) {

                            for(key in 2022..currentKey) {
                                DropdownMenuItem(onClick = {
                                    selectedIndex = key
                                    expanded = false
                                }) {
                                    Text(text = "${key}年")
                                }

                            }
                        }
                    }

                    Box(modifier = Modifier.padding(9.dp)) {
                        Text(if(selectedMonthIndex == 0 ) "全ての月" else "${months[selectedMonthIndex]}月",modifier = Modifier.fillMaxWidth().clickable(onClick = { expandedMonth = true }))
                        DropdownMenu(
                            expanded = expandedMonth,
                            onDismissRequest = { expandedMonth = false },
                            modifier = Modifier
                                .padding()
                        ) {
                            months.forEachIndexed { index, s ->
                                DropdownMenuItem(onClick = {
                                    selectedMonthIndex = index
                                    expandedMonth = false
                                }) {
                                    if(s == 0) {
                                        Text(text = "全ての月")
                                    } else {
                                        Text(text = "${s}月")
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    System.out.println(selectedIndex)
                    System.out.println(selectedMonthIndex)
                    changeDialog(false)
                    changeKey(selectedIndex, selectedMonthIndex)
                }) {
                    Text("閉じる")
                }
            }
        )
    }
}
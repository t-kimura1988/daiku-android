package com.goen.utils.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun DaikuRadioDialog(
    options: Map<Int, String>,
    key: Int,
    dialogFlg: Boolean,
    changeDialog: (flg: Boolean) -> Unit,
    changeKey: (key: Int) -> Unit
) {
    TextField(
        value = options[key]!!,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { changeDialog(true) },
        enabled = false
    )
    if(dialogFlg) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            text = {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    options.forEach{option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = option.key == key,
                                    onClick = {
                                        changeKey(option.key)
                                    }
                                )
                        ) {
                            RadioButton(
                                // inside this method we are
                                // adding selected with a option.
                                selected = (option.key == key),
                                modifier = Modifier.padding(all = Dp(value = 8F)),
                                onClick = {
                                    // inide on click method we are setting a
                                    // selected option of our radio buttons.
                                    changeKey(option.key)

                                }
                            )
                            // below line is use to add
                            // text to our radio buttons.
                            Text(
                                text = option.value,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }

            },
            confirmButton = {
                TextButton(onClick = {
                    changeDialog(false)
                }) {
                    Text("閉じる")
                }
            }
        )
    }
}
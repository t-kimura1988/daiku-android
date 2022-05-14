package com.goen.account.ui.comp.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.goen.account.R


@Composable
fun termSearchDialog(
    dialogFlg: Boolean,
    currentKey: Int,
    changeDialog: (flg: Boolean) -> Unit,
    changeKey: (key: Int) -> Unit
) {

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
                    for(key in 2022..currentKey) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = key == currentKey,
                                    onClick = {
                                        changeKey(key)
                                    }
                                )
                        ) {
                            RadioButton(
                                // inside this method we are
                                // adding selected with a option.
                                selected = (key == currentKey),
                                modifier = Modifier.padding(all = Dp(value = 8F)),
                                onClick = {
                                    // inide on click method we are setting a
                                    // selected option of our radio buttons.
                                    changeKey(key)

                                }
                            )
                            // below line is use to add
                            // text to our radio buttons.
                            Text(
                                text = key.toString(),
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
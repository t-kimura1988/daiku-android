package com.goen.processhistory.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.goen.processhistory.param.ProcessHistoryCreateDisplayParam
import com.goen.processhistory.view_model.ProcessHistoryCreateInput
import com.goen.processhistory.view_model.ProcessHistoryCreateViewModel
import com.goen.utils.compose.DaikuAppTheme
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProcessHistoryCreatePage(
    navHostController: NavHostController,
    input: ProcessHistoryCreateDisplayParam,
) {

    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
    var viewModel: ProcessHistoryCreateViewModel = hiltViewModel()

    viewModel.changeStatus(input.status)
    viewModel.changePriority(input.priority)

    DaikuAppTheme() {

        Scaffold(
            topBar = {
                _topbar(
                    viewModel = viewModel,
                    keyboardController = keyboardController,
                    navHostController = navHostController,
                    processId = input.processId
                )
            },
        ) {
            _form(viewModel = viewModel)
            if(viewModel.errorDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        viewModel.changeErrorFlg(false)
                    },
                    text = {
                        Text(text = "登録に失敗しました。再度入力してください。")
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.changeErrorFlg(false)
                        }) {
                            Text("閉じる")
                        }
                    }
                )
            }
            if(viewModel.success.value) {
                AlertDialog(
                    onDismissRequest = {
                        viewModel.changeErrorFlg(false)
                    },
                    text = {
                        Text(text = "登録完了")
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            keyboardController!!.hide()
                            navHostController.popBackStack()
                        }) {
                            Text("閉じる")
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun _topbar(
    viewModel: ProcessHistoryCreateViewModel,
    keyboardController: SoftwareKeyboardController?,
    navHostController: NavHostController,
    processId: Int
) {

    TopAppBar(
        title = {
            Text(
                text = "プロセスコメント",
                color = DaikuAppTheme.colors.topAppBarTitle) },
        actions = {
            TextButton(
                onClick = {
                    viewModel.create(processId = processId)
                    if(!viewModel.loading.value) {
                        if(viewModel.success.value) {

                            keyboardController!!.hide()
                            navHostController.popBackStack()
                        }
                    }
                },
                enabled = viewModel.chkEnableButton()
            ) {
                Text("保存")
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                viewModel.init()
                keyboardController!!.hide()
                navHostController.popBackStack() })
            {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor

    )
}

@Composable
fun _form(
    viewModel: ProcessHistoryCreateViewModel
) {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize()
        .width(100.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .statusBarsPadding()
                .navigationBarsWithImePadding()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            OutlinedTextField(
                value = viewModel.input.comment,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = { viewModel.changeComment(it) },
                label = { Text("コメントを入力") },
                singleLine = false,
                isError = viewModel.input.isCommentError
            )
            if (viewModel.input.isCommentError) {
                Text(
                    text = viewModel.input.commentError!!,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            // ステータス
            processDialog(
                changeDialog = {flg ->
                    viewModel.changeStatusAlert(flg)
                },
                options = viewModel.statusOptions,
                dialogFlg = viewModel.statusAlertFlg.value,
                key = viewModel.input.statusOptionKey.value,
                changeKey = {key ->
                    viewModel.changeStatus(key)
                }
            )
            // 優先度
            processDialog(
                changeDialog = {flg ->
                    viewModel.changePriorityAlert(flg)
                },
                options = viewModel.priorityOptions,
                dialogFlg = viewModel.priorityAlertFlg.value,
                key = viewModel.input.priorityOptionKey.value,
                changeKey = {key ->
                    viewModel.changePriority(key)
                }
            )

        }
    }
}


@Composable
fun processDialog(
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
            .padding(8.dp)
            .fillMaxWidth()
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
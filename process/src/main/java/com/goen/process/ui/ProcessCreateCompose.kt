package com.goen.process.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.goen.process.R
import com.goen.process.ui.comp.ProcessEditForm
import com.goen.process.view_model.ProcessCreateViewModel
import com.goen.utils.compose.DaikuAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProcessCreateCompose(
    goalId: Int,
    goalCreateDate: String,
    navController: NavHostController
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var viewModel: ProcessCreateViewModel = hiltViewModel()

    val createProcess: () -> Unit = {
        viewModel.createProcess(goalId = goalId, goalCreateDate = goalCreateDate)
    }

    DaikuAppTheme() {

        Scaffold(
            topBar = { TopBar(viewModel = viewModel, createProcess =  createProcess) },
            content = {
                ProcessEditForm(viewModel = viewModel)
                if(viewModel.failureFlg.value) {
                    AlertDialog(
                        onDismissRequest = {
                            viewModel.changeFailure(false)
                        },
                        text = {
                            Text(text = stringResource(id = R.string.process_failure_message_text))
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                viewModel.changeFailure(false)
                            }) {
                                Text(stringResource(id = R.string.process_failure_retry_button))
                            }
                        }
                    )
                } else if(viewModel.success.value) {

                    AlertDialog(
                        onDismissRequest = { /*TODO*/ },
                        title = { Text(stringResource(id = R.string.process_success_message_text)) },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    keyboardController!!.hide()
                                    focusManager.clearFocus()
                                    navController.popBackStack()
                                }
                            ) {
                                Text("OK")
                            }
                        }
                    )
                }
            }
        )
    }
}

@Composable
private fun TopBar(viewModel: ProcessCreateViewModel, createProcess: () -> Unit) {

    TopAppBar(
        title = { Text(
            text = stringResource(id = R.string.process_create_tab_name),
            color = DaikuAppTheme.colors.topAppBarTitle
        ) },
        actions = {
            TextButton(
                onClick = {createProcess()},
                enabled = viewModel.input.enableButton
            )

            {
                Text(stringResource(id = R.string.process_save_button))
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
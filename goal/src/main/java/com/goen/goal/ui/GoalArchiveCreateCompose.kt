package com.goen.goal.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.goen.goal.R
import com.goen.goal.ui.compose.archive.GoalArchiveForm
import com.goen.goal.view_model.GoalArchiveViewModel
import com.goen.utils.compose.DaikuAppTheme

@ExperimentalComposeUiApi
@Composable
fun GoalArchiveCreateCompose(
    goalId: Int,
    createDate: String,
    navController: NavHostController
) {
    var viewModel: GoalArchiveViewModel = hiltViewModel()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    DaikuAppTheme() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(
                        text = stringResource(id = R.string.goal_archive_tab_name),
                        color = DaikuAppTheme.colors.topAppBarTitle)},
                    actions = {
                        TextButton(
                            onClick = {
                                viewModel.createGoalArchive(
                                    goalId = goalId,
                                    createDate = createDate
                                )
                            },
                            enabled = viewModel.chkEnableButton()
                        ) {
                            Text(stringResource(id = R.string.goal_save_button))
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            keyboardController!!.hide()
                            navController.popBackStack()
                        })
                        {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    backgroundColor = DaikuAppTheme.colors.topAppBarColor

                )
            },
        ) {
            GoalArchiveForm(viewModel = viewModel)
            if(!viewModel.loading.value) {
                if(viewModel.success.value) {
                    AlertDialog(
                        onDismissRequest = { /*TODO*/ },
                        title = { Text(stringResource(id = R.string.goal_success_message_text)) },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    keyboardController!!.hide()
                                    focusManager.clearFocus()
                                    viewModel.init()
                                    navController.popBackStack()
                                }
                            ) {
                                Text("OK")
                            }
                        }
                    )
                } else if(viewModel.errorDialog.value){
                    AlertDialog(
                        onDismissRequest = { /*TODO*/ },
                        title = { Text(stringResource(id = R.string.goal_failure_message_text)) },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    keyboardController!!.hide()
                                    focusManager.clearFocus()
                                    viewModel.init()
                                }
                            ) {
                                Text(stringResource(id = R.string.goal_failure_retry_button))
                            }
                        }
                    )
                }
            }
        }
    }
}
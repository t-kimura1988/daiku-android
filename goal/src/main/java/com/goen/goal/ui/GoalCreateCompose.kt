package com.goen.goal.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goen.goal.R
import com.goen.goal.ui.compose.edit.GoalEditForm
import com.goen.goal.view_model.GoalCreateViewModel
import com.goen.utils.compose.DaikuAppTheme
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@ExperimentalComposeUiApi
@Composable
fun goalCreateCompose(
    close: () -> Unit
) {
    var viewModel: GoalCreateViewModel = hiltViewModel()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    DaikuAppTheme() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.goal_create_tab_name),
                            color = DaikuAppTheme.colors.topAppBarTitle)},
                    actions = {
                        TextButton(
                            onClick = {
                                viewModel.createGoal()
                                if(!viewModel.loading.value) {

                                    if(viewModel.success.value) {
                                        close()
                                    } else {
                                    }
                                }
                            },
                            enabled = viewModel.chkEnableButton()
                        ) {
                            Text(stringResource(id = R.string.goal_save_button))
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            viewModel.init()
                            keyboardController!!.hide()
                            close()
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
            GoalEditForm(viewModel = viewModel)

            if(!viewModel.loading.value) {

                if(viewModel.success.value) {
                    AlertDialog(
                        onDismissRequest = { /*TODO*/ },
                        title = { Text(stringResource(id = R.string.goal_success_message_text)) },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    viewModel.init()
                                    keyboardController!!.hide()
                                    focusManager.clearFocus()
                                    close()
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
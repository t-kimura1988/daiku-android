package com.goen.maki.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.goen.maki.R
import com.goen.maki.ui.comp.MakiEditForm
import com.goen.maki.view_model.MakiCreateViewModel
import com.goen.maki.view_model.MakiDetailViewModel
import com.goen.utils.compose.DaikuAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MakiCreateMainCompose(
    close: () -> Unit
) {

    var viewModel: MakiCreateViewModel = hiltViewModel()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val saveMaki: () -> Unit = {
        viewModel.createMaki()
    }
    DaikuAppTheme {
        Scaffold(
            topBar = {
                TopBar(
                    save = saveMaki
                )
            }
        ) { padding ->
            MakiEditForm(
                viewModel = viewModel,
                padding = padding
            )

            if(!viewModel.loading.value) {

                if(viewModel.success.value) {
                    AlertDialog(
                        onDismissRequest = { /*TODO*/ },
                        title = { Text(stringResource(id = R.string.maki_success_message_text)) },
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
                        title = { Text(stringResource(id = R.string.maki_failure_message_text)) },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    keyboardController!!.hide()
                                    focusManager.clearFocus()
                                }
                            ) {
                                Text(stringResource(id = R.string.maki_failure_retry_button))
                            }
                        }
                    )
                }
            }
        }
    }

}

private fun load(viewModel: MakiDetailViewModel, makiId: Int) {
    viewModel.getMakiDetail(makiId = makiId)
    viewModel.getGoalOfMaki(makiId = makiId)
}
@Composable
private fun TopBar(
    save:() -> Unit
) {
    TopAppBar(
        title = {
            Text(
//                text = stringResource(id = R.string.goal_detail_tab_name),
                text = "詳細",
                color = DaikuAppTheme.colors.topAppBarTitle) },
        actions = {
            TextButton(
                onClick = {
                    save()
                },
                enabled = true
            ) {
                Text(stringResource(id = R.string.maki_save_button))
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
package com.goen.story.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.goen.story.R
import com.goen.story.ui.comp.StoryBodyEditForm
import com.goen.story.view_model.StoryBodyUpdateViewModel
import com.goen.utils.compose.DaikuAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StoryBodyUpdateCompose(
    ideaId: Int,
    storyId: Int,
    navController: NavHostController
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var viewModel: StoryBodyUpdateViewModel = hiltViewModel()

    LaunchedEffect(key1 = viewModel.input, block = {
        viewModel.getStoryDetail(storyId = storyId, ideaId = ideaId)
    })

    val updateStoryBody: () -> Unit = {
        viewModel.update(storyId = storyId, ideaId = ideaId)
    }

    DaikuAppTheme() {

        Scaffold(
            topBar = { TopBar(viewModel = viewModel, updateStoryBody =  updateStoryBody) },
            content = {
                StoryBodyEditForm(viewModel = viewModel)
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
private fun TopBar(viewModel: StoryBodyUpdateViewModel, updateStoryBody: () -> Unit) {

    TopAppBar(
        title = { Text(
            text = stringResource(id = R.string.process_create_tab_name),
            color = DaikuAppTheme.colors.topAppBarTitle
        ) },
        actions = {
            TextButton(
                onClick = { updateStoryBody()},
                enabled = viewModel.input.enableButton
            )

            {
                Text(stringResource(id = R.string.process_save_button))
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
package com.goen.account.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.goen.account.R
import com.goen.account.ui.comp.form.AccountEditForm
import com.goen.account.view_model.AccountCreateViewModel
import com.goen.utils.compose.DaikuAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AccountUpdateCompose(
    navController: NavHostController
) {

    val viewModel: AccountCreateViewModel = hiltViewModel()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = viewModel.input, block = {
        viewModel.accountDetail()
    })

    DaikuAppTheme {
        Scaffold(
            topBar = { UpdateTopBar(viewModel = viewModel) }
        ) {
            AccountEditForm(viewModel = viewModel)
            if(!viewModel.loading.value) {

                if(viewModel.success.value) {
                    AlertDialog(
                        onDismissRequest = { /*TODO*/ },
                        title = { Text(stringResource(id = R.string.account_success_message_text)) },
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
                } else if(viewModel.errorDialog.value){
                    AlertDialog(
                        onDismissRequest = { /*TODO*/ },
                        title = { Text(stringResource(id = R.string.account_failure_message_text)) },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    keyboardController!!.hide()
                                    focusManager.clearFocus()
                                    viewModel.changeErrorDialog(false)
                                }
                            ) {
                                Text(stringResource(id = R.string.account_failure_retry_button))
                            }
                        }
                    )
                }
            }
        }
    }

}


@Composable
fun UpdateTopBar(viewModel: AccountCreateViewModel) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.account_update_tab_name),
                color = DaikuAppTheme.colors.topAppBarTitle) },
        actions = {
            TextButton(
                onClick = { viewModel.updateAccount() },
                enabled = viewModel.input.enableButton
            )

            {
                Text(stringResource(id = R.string.account_save_button))
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
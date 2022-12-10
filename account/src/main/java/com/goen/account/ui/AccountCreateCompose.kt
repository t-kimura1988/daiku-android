package com.goen.account.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.goen.account.R
import com.goen.account.ui.comp.form.AccountEditForm
import com.goen.account.view_model.AccountCreateViewModel
import com.goen.auth.presentation.view_model.AccountExistViewModel
import com.goen.utils.compose.DaikuAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AccountCreateCompose(
    accountVM: AccountExistViewModel
) {
    val viewModel: AccountCreateViewModel = hiltViewModel()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    DaikuAppTheme {

        Scaffold(
            topBar = { TopBar(viewModel = viewModel, accountVM = accountVM) }
        ) { padding ->
            AccountEditForm(viewModel = viewModel, padding = padding)
            if(!viewModel.loading.value) {

                if(viewModel.errorDialog.value){
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
private fun TopBar(viewModel: AccountCreateViewModel, accountVM: AccountExistViewModel) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.account_create_tab_name),
                color = DaikuAppTheme.colors.topAppBarTitle
            ) },
        actions = {
            TextButton(
                onClick = { viewModel.createAccount(accountVM = accountVM) },
                enabled = viewModel.input.enableButton
            )

            {
                Text(stringResource(id = R.string.account_save_button))
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}

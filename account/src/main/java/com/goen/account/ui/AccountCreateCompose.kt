package com.goen.account.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.goen.account.R
import com.goen.account.ui.comp.form.AccountEditForm
import com.goen.account.view_model.AccountCreateViewModel
import com.goen.auth.presentation.view_model.AccountExistViewModel
import com.goen.utils.compose.DaikuAppTheme
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AccountCreateCompose(
    accountVM: AccountExistViewModel
) {
    var viewModel: AccountCreateViewModel = hiltViewModel()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    DaikuAppTheme() {

        Scaffold(
            topBar = { topBar(viewModel = viewModel, accountVM = accountVM) }
        ) {
            AccountEditForm(viewModel = viewModel)
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
private fun topBar(viewModel: AccountCreateViewModel, accountVM: AccountExistViewModel) {
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

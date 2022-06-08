package com.goen.daiku

import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.goen.account.ui.AccountCreateCompose
import com.goen.auth.presentation.view_model.AccountExistViewModel
import com.goen.daiku.router.NavigationSetUp

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AccountExistCompose(
) {
    var viewModel: AccountExistViewModel = hiltViewModel()
    val navController = rememberNavController()

    LaunchedEffect(key1 = viewModel.accountState, block = {
        viewModel.isExistAccount()
    })

    if(viewModel.accountState.value.error == "E0001") {

        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            title = { Text(text = "アカウントは削除済みですが、アカウントを復活しますか？") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.reUpdate()
                    }
                ) {
                    Text(text = "復活する")
                }
                TextButton(
                    onClick = {
                        viewModel.changeBackSignIn()
                    }
                ) {
                    Text(text = "戻る")
                }
            }
        )
    }


    if(viewModel.accountState.value.isLoading) {
        return
    }

    if(viewModel.accountState.value.account == null) {
        AccountCreateCompose(viewModel)
        return
    }

    if(viewModel.accountState.value.account != null) {
        NavigationSetUp(
            navController = navController,
            account = viewModel.accountState.value.account!!
        )
    }


}
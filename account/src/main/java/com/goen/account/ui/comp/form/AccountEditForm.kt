package com.goen.account.ui.comp.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goen.account.view_model.AccountCreateViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding


@Composable
fun AccountEditForm(viewModel: AccountCreateViewModel) {
    val scrollState = rememberScrollState()
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
            value = viewModel.input.familyName,
            onValueChange = { viewModel.changeFamilyName(it) },
            label = { Text("氏名") },
            singleLine = true,
            isError = viewModel.input.isFamilyNameError
        )
        if (viewModel.input.isFamilyNameError) {
            Text(
                text = viewModel.input.familyNameError!!,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        OutlinedTextField(
            value = viewModel.input.givenName,
            onValueChange = { viewModel.changeGivenName(it) },
            label = { Text("名前") },
            singleLine = true,
            isError = viewModel.input.isGivenNameError
        )
        if (viewModel.input.isGivenNameError) {
            Text(
                text = viewModel.input.givenNameError!!,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        OutlinedTextField(
            value = viewModel.input.nickName,
            onValueChange = { viewModel.changeNickName(it) },
            label = { Text("ニックネーム") },
            singleLine = true,
            isError = viewModel.input.isNickNameError
        )
        if (viewModel.input.isNickNameError) {
            Text(
                text = viewModel.input.nickNameError!!,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }


    }
}
package com.goen.goal.ui.compose.archive

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goen.goal.view_model.GoalArchiveViewModel
import com.goen.goal.view_model.GoalCreateViewModel
import com.goen.utils.compose.DaikuRadioDialog
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
internal fun GoalArchiveForm(viewModel: GoalArchiveViewModel) {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize()
        .width(100.dp)) {
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
                value = viewModel.input.thoughts,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = { viewModel.changeThoughts(item = it) },
                label = { Text("感想") },
                singleLine = false,
                isError = viewModel.input.isThoughtsError
            )
            if (viewModel.input.isThoughtsError) {
                Text(
                    text = viewModel.input.thoughtsError!!,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            DaikuRadioDialog(
                changeDialog = {flg ->
                    viewModel.changePublishDialogAlert(flg)
                },
                options = viewModel.publishOptions,
                dialogFlg = viewModel.publishDialogFlg.value,
                key = viewModel.input.publishOptionKey.value,
                changeKey = {key ->
                    viewModel.changePublishKey(key)
                }
            )

        }
    }
}
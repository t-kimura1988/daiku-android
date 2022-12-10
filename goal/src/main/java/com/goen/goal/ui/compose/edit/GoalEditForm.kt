package com.goen.goal.ui.compose.edit

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
import com.goen.goal.view_model.GoalCreateViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
internal fun GoalEditForm(viewModel: GoalCreateViewModel) {
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = viewModel.input.title,
                onValueChange = { viewModel.changeTitle(it) },
                label = { Text("題名") },
                singleLine = false,
                isError = viewModel.input.isTitleError
            )
            if (viewModel.input.isTitleError) {
                Text(
                    text = viewModel.input.titleError!!,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = viewModel.input.purpose,
                onValueChange = { viewModel.changePurpose(it) },
                label = { Text("目的") },
                singleLine = false,
                isError = viewModel.input.isPurposeError
            )
            if (viewModel.input.isPurposeError) {
                Text(
                    text = viewModel.input.purposeError!!,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                value = viewModel.input.aim,
                onValueChange = { viewModel.changeAim(it) },
                label = { Text("どのように実現する") },
                singleLine = false,
                isError = viewModel.input.isAimError
            )
            if (viewModel.input.isAimError) {
                Text(
                    text = viewModel.input.aimError!!,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            GoalTermDialog(
                date = viewModel.input.dueDateM.value.date!!,
                changeDialog = {flg ->
                    viewModel.changeTermDialog(flg)
                },
                alertFlg = viewModel.goalTermAlertFlg.value,
                selectDay = {
                    viewModel.changeDueDate(it)
                }
            )

        }
    }
}
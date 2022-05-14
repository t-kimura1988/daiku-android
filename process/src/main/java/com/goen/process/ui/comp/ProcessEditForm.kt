package com.goen.process.ui.comp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.goen.process.view_model.ProcessCreateViewModel
import com.goen.utils.compose.DaikuRadioDialog
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding


@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ProcessEditForm(
    viewModel: ProcessCreateViewModel
) {
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
            value = viewModel.input.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onValueChange = { viewModel.changeTitle(it) },
            label = { Text("題名") },
            singleLine = false,
            isError = viewModel.input.isTitleError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
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
            value = viewModel.input.body,
            onValueChange = { viewModel.changeBody(it) },
            label = { Text("内容") },
            singleLine = false,
            isError = viewModel.input.isBodyError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
        if (viewModel.input.isBodyError) {
            Text(
                text = viewModel.input.bodyError!!,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        // ステータス
        DaikuRadioDialog(
            changeDialog = {flg ->
                viewModel.changeStatusAlert(flg)
            },
            options = viewModel.statusOptions,
            dialogFlg = viewModel.statusAlertFlg.value,
            key = viewModel.input.statusOptionKey.value,
            changeKey = {key ->
                viewModel.changeStatus(key)
            }
        )
        // 優先度
        DaikuRadioDialog(
            changeDialog = {flg ->
                viewModel.changePriorityAlert(flg)
            },
            options = viewModel.priorityOptions,
            dialogFlg = viewModel.priorityAlertFlg.value,
            key = viewModel.input.priorityOptionKey.value,
            changeKey = {key ->
                viewModel.changePriority(key)
            }
        )
    }
}

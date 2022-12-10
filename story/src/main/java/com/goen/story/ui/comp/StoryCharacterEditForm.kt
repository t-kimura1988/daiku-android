package com.goen.story.ui.comp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.goen.story.view_model.StoryCharacterCreateViewModel
import com.goen.utils.compose.DaikuRadioDialog
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding


@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun StoryCharacterEditForm(
    viewModel: StoryCharacterCreateViewModel,
    paddingValues: PaddingValues
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .statusBarsPadding()
            .navigationBarsWithImePadding()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        OutlinedTextField(
            value = viewModel.input.charaName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onValueChange = { viewModel.changeCharaName(it) },
            label = { Text("キャラ名") },
            singleLine = false,
            isError = viewModel.input.isCharaNameError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        if (viewModel.input.isCharaNameError) {
            Text(
                text = viewModel.input.charaNameError!!,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        OutlinedTextField(
            value = viewModel.input.charaDesc,
            onValueChange = { viewModel.changeCharaDesc(it) },
            label = { Text("内容") },
            singleLine = false,
            isError = viewModel.input.isCharaDescError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
        if (viewModel.input.isCharaDescError) {
            Text(
                text = viewModel.input.charaDescError!!,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        // 主役フラグ
        DaikuRadioDialog(
            changeDialog = {flg ->
                viewModel.changeLeaderFlgAlert(flg)
            },
            options = viewModel.leaderOptions,
            dialogFlg = viewModel.leaderAlertFlg.value,
            key = viewModel.input.leaderM.value,
            changeKey = {key ->
                viewModel.changeLeaderFlg(key)
            }
        )
    }
}

package com.goen.maki.ui.comp

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
import com.goen.maki.view_model.MakiCreateViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
internal fun MakiEditForm(
    viewModel: MakiCreateViewModel,
    padding: PaddingValues
) {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize()
        .width(100.dp)
        .padding(padding)
    ) {
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
                value = viewModel.input.makiTitle,
                onValueChange = { viewModel.changeMakiTitle(it) },
                label = { Text("巻名") },
                singleLine = false,
                isError = viewModel.input.isMakiTitleError
            )
            if (viewModel.input.isMakiTitleError) {
                Text(
                    text = viewModel.input.makiTitleError!!,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = viewModel.input.makiKey,
                onValueChange = { viewModel.changeMakiKey(it) },
                label = { Text("目的") },
                singleLine = false,
                isError = viewModel.input.isMakiKeyError
            )
            if (viewModel.input.isMakiKeyError) {
                Text(
                    text = viewModel.input.makiKeyError!!,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                value = viewModel.input.makiDesc,
                onValueChange = { viewModel.changeMakiDesc(it) },
                label = { Text("どのように実現する") },
                singleLine = false,
                isError = viewModel.input.isMakiDescError
            )
            if (viewModel.input.isMakiDescError) {
                Text(
                    text = viewModel.input.makiDescError!!,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

        }
    }
}
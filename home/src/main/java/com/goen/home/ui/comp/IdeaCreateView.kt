package com.goen.home.ui.comp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goen.home.view_model.HomeViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
internal fun IdeaCreateView(
    viewModel: HomeViewModel,
    save: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsWithImePadding()
        ,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,

        ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = viewModel.input.body,
            onValueChange = { viewModel.changeBody(it) },
            label = { Text("閃きの題名") },
            singleLine = false,
            isError = viewModel.input.isBodyError
        )
        if (viewModel.input.isBodyError) {
            Text(
                text = viewModel.input.bodyError!!,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Button(
            onClick = {
                save()
            },
            enabled = viewModel.enableSaveButton()
        ) {
            Text("追加")
        }
    }
}
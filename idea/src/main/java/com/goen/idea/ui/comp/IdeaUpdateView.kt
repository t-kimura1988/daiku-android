package com.goen.idea.ui.comp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goen.idea.view_model.IdeaDetailViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
internal fun IdeaUpdateView(
    viewModel: IdeaDetailViewModel,
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
            value = viewModel.ideaUpdateInput.body,
            onValueChange = { viewModel.changeIdeaBody(it) },
            label = { Text("閃きの題名") },
            singleLine = false,
            isError = viewModel.ideaUpdateInput.isBodyError
        )
        if (viewModel.ideaUpdateInput.isBodyError) {
            Text(
                text = viewModel.ideaUpdateInput.bodyError!!,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Button(onClick = {
            save()
        }) {
            Text("追加")
        }
    }
}
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
internal fun StoryCreateView(
    viewModel: IdeaDetailViewModel
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
            value = viewModel.input.title,
            onValueChange = { viewModel.changeTitle(it) },
            label = { Text("物語の題名") },
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

        Button(onClick = {
            viewModel.createStory()
        }) {
            Text("追加")
        }
    }
}
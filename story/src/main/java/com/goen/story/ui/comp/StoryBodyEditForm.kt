package com.goen.story.ui.comp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.goen.story.view_model.StoryBodyUpdateViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
internal fun StoryBodyEditForm(
    viewModel: StoryBodyUpdateViewModel
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
            value = viewModel.input.storyBody,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onValueChange = { viewModel.changeStoryBody(it) },
            label = { Text("物語詳細") },
            singleLine = false,
            isError = viewModel.input.isStoryBodyError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        if (viewModel.input.isStoryBodyError) {
            Text(
                text = viewModel.input.storyBodyError!!,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

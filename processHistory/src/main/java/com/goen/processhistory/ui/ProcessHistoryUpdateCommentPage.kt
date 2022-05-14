package com.goen.processhistory.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goen.processhistory.param.ProcessHistoryCreateDisplayParam
import com.goen.processhistory.param.ProcessHistoryUpdateCommentDisplayParam
import com.goen.processhistory.view_model.ProcessHistoryCreateInput
import com.goen.processhistory.view_model.ProcessHistoryCreateViewModel
import com.goen.processhistory.view_model.ProcessHistoryUpdateCommentViewModel
import com.goen.utils.compose.DaikuAppTheme
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProcessHistoryUpdateCommentPage(
    close: () -> Unit,
    input: ProcessHistoryUpdateCommentDisplayParam,
) {

    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
    var viewModel: ProcessHistoryUpdateCommentViewModel = hiltViewModel()

    LaunchedEffect(key1 = viewModel.input, block =  {
        viewModel.getDetail(param = input)
    })

    DaikuAppTheme() {

        Scaffold(
            topBar = {
                _updateCommentTopbar(
                    viewModel = viewModel,
                    keyboardController = keyboardController,
                    close = close,
                    processHistoryId = input.processHistoryId
                )
            },
        ) {
            _updateCommentForm(viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun _updateCommentTopbar(
    viewModel: ProcessHistoryUpdateCommentViewModel,
    keyboardController: SoftwareKeyboardController?,
    close: () -> Unit,
    processHistoryId: Int
) {

    TopAppBar(
        title = {
            Text(
                text = "プロセスコメント",
                color = DaikuAppTheme.colors.topAppBarTitle) },
        actions = {
            TextButton(
                onClick = {
                    viewModel.updateComment(processHistoryId = processHistoryId)
                    if(!viewModel.loading.value) {

                        if(viewModel.success.value) {
                        } else {
                        }
                    }
                },
                enabled = viewModel.chkEnableButton()
            ) {
                Text("保存")
            }
        },
        navigationIcon = {
            IconButton(onClick = {

                viewModel.init()
                keyboardController!!.hide()
                close() })
            {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}

@Composable
fun _updateCommentForm(
    viewModel: ProcessHistoryUpdateCommentViewModel
) {
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
                value = viewModel.input.comment,
                onValueChange = { viewModel.changeComment(it) },
                label = { Text("コメントを入力") },
                singleLine = false,
                isError = viewModel.input.isCommentError
            )
            if (viewModel.input.isCommentError) {
                Text(
                    text = viewModel.input.commentError!!,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

        }
    }
}
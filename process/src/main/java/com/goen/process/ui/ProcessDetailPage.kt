package com.goen.process.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.goen.process.R
import com.goen.process.ui.comp.ProcessDetailCompose
import com.goen.process.ui.comp.ProcessHistoryListItem
import com.goen.process.view_model.ProcessDetailViewModel
import com.goen.utils.compose.DaikuAppTheme

@Composable
fun ProcessDetailPage(
    processId: Int,
    goalId: Int,
    goalCreateDate: String,
    navController: NavHostController,
    gotoProcessHistoryCreate: (process: Int, status: Int, priority: Int) -> Unit,
    updateComment: (processHistoryId: Int, goalCreateDate: String) -> Unit,
    processUpdate: (processId: Int, goalId: Int, createDate: String) -> Unit,
    processStatusUpdate: (process: Int, status: Int, priority: Int) -> Unit
) {
    
    var processDetailVM: ProcessDetailViewModel = hiltViewModel()
    
    LaunchedEffect(key1 = processDetailVM.processDetailResult, block =  {
        processDetailVM.getDetail(processId = processId, goalCreateDate = goalCreateDate)
        processDetailVM.getProcessHistory(processId = processId)
    })

    DaikuAppTheme() {

        Scaffold(
            topBar = {
                topbar(
                    navController = navController,
                    onClickItem = { gotoProcessHistoryCreate(processId, processDetailVM.processDetailResult.value.processDetail.processStatus.toInt(), processDetailVM.processDetailResult.value.processDetail.priority.toInt()) })
            }
        ) {
            LazyColumn {
                item {
                    Box(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        OutlinedButton(onClick = { processUpdate(processId, goalId, goalCreateDate) }) {
                            Text(stringResource(id = R.string.process_edit_button_name))
                        }
                    }
                }
                item {
                    ProcessDetailCompose(
                        processDetail = processDetailVM.processDetailResult.value.processDetail,
                        processStatusUpdate = processStatusUpdate
                    )
                }

                items(processDetailVM.processHistoryList.value.processHistoryList) {item ->
                    ProcessHistoryListItem(
                        item = item,
                        commentAddPage = updateComment
                    )
                }
            }
        }
    }
}

@Composable
private fun topbar(
    navController: NavHostController,
    onClickItem: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.process_detail_tab_name),
                color = DaikuAppTheme.colors.topAppBarTitle) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Open drawer")
            }
        },
        actions = {
            TextButton(onClick = { onClickItem() }) {
                Text("コメント追加")
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
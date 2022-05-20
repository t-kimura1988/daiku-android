package com.goen.goal.ui

import androidx.compose.foundation.layout.*
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
import com.goen.goal.R
import com.goen.goal.ui.compose.detail.goalDetailCompose
import com.goen.goal.ui.compose.detail.goalDetailProcessItem
import com.goen.goal.view_model.GoalDetailViewModel
import com.goen.utils.compose.DaikuAppTheme

@Composable
fun goalDetailMainCompose(
    goalId: Int,
    createDate: String,
    navController: NavHostController,
    selectProcessDetail: (processId: Int, goalId: Int, goalCreateDate: String) -> Unit,
    gotoProcessCreate: (goalId: Int, goalCreateDate: String) -> Unit,
    goalUpdatePage: (goalId: Int, createDate: String) -> Unit,
    goalArchivePage: (goalId: Int, createDate: String) -> Unit,
    goalArchiveUpdatePage: (Int, String) -> Unit,
) {

    var goalDetailViewModel: GoalDetailViewModel = hiltViewModel()

    LaunchedEffect(key1 = goalDetailViewModel.goalDetailResult, block = {
        load(viewModel = goalDetailViewModel, goalId = goalId, createDate = createDate)
    })

    DaikuAppTheme {
        Scaffold(
            topBar = {
                topbar(
                    navController = navController,
                    onClickItem = { gotoProcessCreate(goalId, createDate) },
                    isUpdating = goalDetailViewModel.goalDetailResult.value.goalDetail.isUpdating)
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        if(goalDetailViewModel.goalDetailResult.value.goalDetail.archiveId == 0) {
                            Box(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                OutlinedButton(onClick = { goalUpdatePage(goalId, createDate) }) {
                                    Text(stringResource(id = R.string.goal_edit_button_name))
                                }
                            }
                            Box(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                OutlinedButton(onClick = { goalArchivePage(goalId, createDate) }) {
                                    Text(stringResource(id = R.string.goal_archive_button_name))
                                }
                            }
                        } else {
                            if(goalDetailViewModel.goalDetailResult.value.goalDetail.isUpdating) {
                                Box(
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    OutlinedButton(onClick = {
                                        goalArchiveUpdatePage(
                                            goalDetailViewModel.goalDetailResult.value.goalDetail.goalArchiveId(),
                                            goalDetailViewModel.goalDetailResult.value.goalDetail.archivesCreateDate)
                                    }) {
                                        Text(stringResource(id = R.string.goal_archive_update_button_name))
                                    }
                                }
                            }else {
                                Box(
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    OutlinedButton(onClick = { goalDetailViewModel.updatingFlg(goalId, createDate) }) {
                                        Text(stringResource(id = R.string.goal_archive_updating_button_name))
                                    }
                                }
                            }

                        }
                    }
                }
                item {
                    goalDetailCompose(
                        goalInfo = goalDetailViewModel.goalDetailResult.value.goalDetail
                    )
                }
                items(goalDetailViewModel.processListResultList.value.list) {item ->
                    goalDetailProcessItem(
                        processItem = item,
                        onClickItem = selectProcessDetail
                    )
                }
            }
        }
    }

}

fun load(viewModel : GoalDetailViewModel, goalId: Int, createDate: String) {
    viewModel.getGoalDetail(goalId = goalId, createDate = createDate)
    viewModel.getProcessList(goalId = goalId, createDate = createDate)
}

@Composable
private fun topbar(
    navController: NavHostController,
    onClickItem: () -> Unit,
    isUpdating: Boolean
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.goal_detail_tab_name),
                color = DaikuAppTheme.colors.topAppBarTitle) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Open drawer")
            }
        },
        actions = {
            TextButton(
                onClick = { onClickItem() },
                enabled = isUpdating
            ) {
                Text("プロセス追加")
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
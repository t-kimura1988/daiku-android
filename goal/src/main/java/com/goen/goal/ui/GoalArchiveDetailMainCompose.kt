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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.goen.goal.R
import com.goen.goal.ui.compose.archive.GoalArchiveDetailCompose
import com.goen.goal.ui.compose.detail.GoalDetailCompose
import com.goen.goal.ui.compose.detail.GoalDetailProcessItem
import com.goen.goal.view_model.GoalArchiveDetailViewModel
import com.goen.utils.compose.DaikuAppTheme
import timber.log.Timber

@Composable
fun GoalArchiveDetailMainCompose(
    archiveId: Int,
    archiveCreateDate: String,
    navController: NavHostController,
    gotoUpdateArchive: (Int, String) -> Unit,
    loginAccountId: Int
) {

    val vm: GoalArchiveDetailViewModel = hiltViewModel()

    LaunchedEffect(key1 = vm.goalArchiveDetail, block = {
        vm.callGoalArchiveDetail(archiveId = archiveId, archiveCreateDate = archiveCreateDate)
    })

    val selectProcessItem: (Int, Int, String) -> Unit = {processId: Int, goalId: Int, goalCreateDate: String ->
        Timber.i("processId: $processId")
        Timber.i("goalId: $goalId")
        Timber.i("goalCreateDate: $goalCreateDate")
    }

    DaikuAppTheme {
        Scaffold(
            topBar = {
                TopBar(
                    navController = navController)
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    if(vm.isGoalIdEqLoginAccount(loginAccountId)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            OutlinedButton(onClick = {
                                gotoUpdateArchive(archiveId, archiveCreateDate)
                            }) {
                                Text(stringResource(id = R.string.goal_edit_button_name))
                            }
                        }
                    }
                    GoalArchiveDetailCompose(goalArchive = vm.goalArchiveDetail.value.goalArchiveInfo)
                }
                item {
                    Column {
                        Text(
                            text = "やり遂げたいこと",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        GoalDetailCompose(
                            goalInfo = vm.goalArchiveDetail.value.goalInfo
                        )
                    }
                }
                if(vm.goalArchiveDetail.value.processInfo != null) {
                    item{
                        Text(
                            text = "目標達成までのプロセス",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    items(vm.goalArchiveDetail.value.processInfo!!) {item ->
                        GoalDetailProcessItem(
                            processItem = item,
                            onClickItem = selectProcessItem,
                            line = Int.MAX_VALUE
                        )
                    }
                } else {
                    item{
                        Text(
                            text = "プロセスは公開されていません。",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    navController: NavHostController
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.goal_archive_detail_tab_name),
                 color = DaikuAppTheme.colors.topAppBarTitle) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Open drawer")
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
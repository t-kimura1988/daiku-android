package com.goen.goal.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.goen.goal.R
import com.goen.goal.ui.compose.archive.GoalArchiveDetailCompose
import com.goen.goal.ui.compose.detail.goalDetailCompose
import com.goen.goal.ui.compose.detail.goalDetailProcessItem
import com.goen.goal.view_model.GoalArchiveDetailViewModel
import com.goen.utils.compose.DaikuAppTheme

@Composable
fun GoalArchiveDetailMainCompose(
    archiveId: Int,
    archiveCreateDate: String,
    navController: NavHostController,
    gotoUpdateArchive: (Int, String) -> Unit,
    loginAccountId: Int
) {

    var vm: GoalArchiveDetailViewModel = hiltViewModel()

    var moreText: MutableState<Boolean> = remember{ mutableStateOf(false) }
    var line: Int = if(moreText.value)  Int.MAX_VALUE else 3

    LaunchedEffect(key1 = vm.goalArchiveDetail, block = {
        vm.callGoalArchiveDetail(archiveId = archiveId, archiveCreateDate = archiveCreateDate)
    })

    val selectProcessItem: (Int, Int, String) -> Unit = {it1: Int, it2: Int, it3: String ->
        moreText.value = !moreText.value
    }

    DaikuAppTheme() {
        Scaffold(
            topBar = {
                topBar(
                    navController = navController,
                    onClickItem = {  })
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
                    Column() {
                        Text(
                            text = "やり遂げたいこと",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        goalDetailCompose(
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
                        goalDetailProcessItem(
                            processItem = item,
                            onClickItem = selectProcessItem,
                            line = line
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
private fun topBar(
    navController: NavHostController,
    onClickItem: () -> Unit
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
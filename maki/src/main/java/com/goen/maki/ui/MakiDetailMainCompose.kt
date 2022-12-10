package com.goen.maki.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.goen.maki.R
import com.goen.maki.ui.comp.MakiAddGoalList
import com.goen.maki.view_model.MakiDetailViewModel
import com.goen.utils.compose.DaikuAccountCompose
import com.goen.utils.compose.DaikuAppTheme
import com.goen.utils.compose.GoalListItemComposable

@Composable
fun MakiDetailMainCompose(
    makiId: Int,
    navController: NavHostController,
    onClickItem: (goalId: Int, createDate: String) -> Unit,
) {

    val makiDetailViewModel: MakiDetailViewModel = hiltViewModel()

    LaunchedEffect(key1 = makiDetailViewModel.makiDetail, block = {
        load(viewModel = makiDetailViewModel, makiId = makiId)
    })
    
    val openDialog: () -> Unit = {
        makiDetailViewModel.openGoalListDialog()
    }

    val maki = makiDetailViewModel.makiDetail.value

    DaikuAppTheme {
        Scaffold(
            topBar = {
                TopBar(
                    navController = navController,
                    openDialog = openDialog
                )
            }
        ) {padding ->
            LazyColumn(modifier = Modifier
                .padding(padding)){
                item {
                    Column(
                        modifier = Modifier.padding(6.dp)
                    ) {
                        DaikuAccountCompose(
                            accountName = maki.createdAccountName,
                            accountImg = maki.createdAccountImg)
                        Text(
                            text = maki.makiTitle,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = maki.makiKey,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.LightGray
                        )
                        Text(
                            text = maki.makiDesc,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )

                    }
                    Divider(thickness = 6.dp)
                }
                item {
                    Text(
                        text = "目標一覧",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(thickness = 3.dp)
                }
                items(makiDetailViewModel.makiGoalList.value) { item ->

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable(onClick = {
                                onClickItem(item.id, item.createDateString)
                            })
                    ) {
                        GoalListItemComposable(
                            goalTitle = item.title,
                            goalCreateDate = item.createDateString,
                            goalDueDate = item.dueDate,
                            goalPurpose = item.purpose,
                            isArchive = item.isArchive(),
                            makiSortNum = item.makiKeySortNum()
                        )
                    }
                    Divider()
                }
            }

        }
        
        goalListDialog(makiDetailViewModel = makiDetailViewModel)
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun goalListDialog(
    makiDetailViewModel: MakiDetailViewModel
) {
    
    if(makiDetailViewModel.goalListDialog.value) {
        Dialog(
            onDismissRequest = {
                makiDetailViewModel.closeGoalListDialog()
            },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                MakiAddGoalList(viewModel = makiDetailViewModel)
            }
        }
    }
}

private fun load(viewModel: MakiDetailViewModel, makiId: Int) {
    viewModel.getMakiDetail(makiId = makiId)
    viewModel.getGoalOfMaki(makiId = makiId)
}
@Composable
private fun TopBar(
    navController: NavHostController,
    openDialog: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
//                text = stringResource(id = R.string.goal_detail_tab_name),
                text = "詳細",
                color = DaikuAppTheme.colors.topAppBarTitle) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Open drawer")
            }
        },
        actions = {

            TextButton(
                onClick = {
                    openDialog()
                },
                enabled = true
            ) {
                Text(stringResource(id = R.string.maki_goal_list_dialog_open))
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
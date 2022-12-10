package com.goen.idea.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.goen.idea.ui.comp.IdeaDetailStoryCompose
import com.goen.idea.ui.comp.IdeaUpdateView
import com.goen.idea.ui.comp.StoryCreateView
import com.goen.idea.view_model.IdeaDetailViewModel
import com.goen.utils.compose.DaikuAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IdeaDetailMainCompose(
    storyId: Int,
    ideaId: Int,
    navController: NavHostController,
    gotoCharaCreate: (Int, Int) -> Unit,
    gotoStoryBodyUpdate:(Int, Int) -> Unit
) {

    val ideaDetailViewModel: IdeaDetailViewModel = hiltViewModel()

    LaunchedEffect(key1 = ideaDetailViewModel.ideaDetail, block = {
        load(viewModel = ideaDetailViewModel, storyId = storyId, ideaId = ideaId)
    })

    var idea = ideaDetailViewModel.ideaDetail.value

    val clickCharaCreate: () -> Unit = {
        gotoCharaCreate(ideaId, storyId)
    }

    val clickUpdateStoryBody: () -> Unit = {
        gotoStoryBodyUpdate(ideaId, storyId)
    }

    val clickOpenIdeaUpdate: () -> Unit = {
        ideaDetailViewModel.openIdeaUpdateAlert()
    }

    DaikuAppTheme {
        Scaffold(
            topBar = {
                TopBar(
                    navController = navController,
                    openUpdateIdea = clickOpenIdeaUpdate
                )
            }
        ) {padding ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
            ) {
                Text(
                    text = idea.body,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                )
                if (idea.storyId == null || idea.storyId == 0) {
                    Button(onClick = {
                        ideaDetailViewModel.openStoryCreateAlert()
                    }) {
                        Text("物語を作成する")
                    }
                } else {
                    IdeaDetailStoryCompose(
                        storyTitle = idea.getIdeaTitle,
                        storyBody = idea.storyBody,
                        clickCharaCreate = clickCharaCreate,
                        clickUpdateStoryBody = clickUpdateStoryBody
                    )
                }

            }

            if (ideaDetailViewModel.charaDetailAlert.value) {


                AlertDialog(
                    onDismissRequest = { /*TODO*/ },
                    title = { Text(
                        text = ideaDetailViewModel.storyCharaDetailState.value.getCharaInfo()
                    ) },
                    text = {Text(
                        text = ideaDetailViewModel.storyCharaDetailState.value.charaDesc
                    )},
                    confirmButton = {
                        TextButton(
                            onClick = {
                                ideaDetailViewModel.closeCharaDetailAlert()
                            }
                        ) {
                            Text("閉じる")
                        }
                    }
                )
            }
        }
        StoryCreateDialog(viewModel = ideaDetailViewModel)
        IdeaUpdateDialog(viewModel = ideaDetailViewModel)
    }
}

private fun load(viewModel: IdeaDetailViewModel, storyId: Int, ideaId: Int) {
    viewModel.getIdeaDetail(ideaId)
    viewModel.getStoryChara(storyId = storyId, ideaId = ideaId)
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun StoryCreateDialog(
    viewModel: IdeaDetailViewModel
) {

    if(viewModel.storyCreateAlert.value) {
        Dialog(
            onDismissRequest = {
                viewModel.closeStoryCreateAlert()
            },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                StoryCreateView(viewModel = viewModel)
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun IdeaUpdateDialog(
    viewModel: IdeaDetailViewModel
) {
    val updateIdea: () -> Unit = {
        viewModel.updateIdea()
    }

    if(viewModel.ideaUpdateAlert.value) {
        Dialog(
            onDismissRequest = {
                viewModel.closeIdeaUpdateAlert()
            },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                IdeaUpdateView(viewModel = viewModel, save = updateIdea)
            }
        }
    }
}

@Composable
private fun TopBar(
    navController: NavHostController,
    openUpdateIdea: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
//                text = stringResource(id = R.string.goal_detail_tab_name),
                text = "閃詳細",
                color = DaikuAppTheme.colors.topAppBarTitle) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Open drawer")
            }
        },
        actions = {
            TextButton(
                onClick = {
                          openUpdateIdea()
                },
            ) {
                Text("編集")
            }

        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
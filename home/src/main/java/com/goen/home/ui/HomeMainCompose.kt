package com.goen.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.goen.domain.entity.Account
import com.goen.home.R
import com.goen.home.ui.comp.GoalArchiveListItemCompose
import com.goen.home.view_model.HomeMainViewModel
import com.goen.utils.compose.DaikuAppTheme

@Composable
fun homeMainCompose(
    createGoalPage: () -> Unit,
    innerPadding: PaddingValues,
    gotoArchiveDetailPage: (
        archiveId: Int,
        archiveCreateDate: String,
        accountId: Int
    ) -> Unit,
    account: Account
) {
    val viewModel: HomeMainViewModel = hiltViewModel()

    LaunchedEffect(key1 = viewModel.goalArchiveList, block = {
        viewModel.getGoalArchiveList()
    })

    DaikuAppTheme {
        Scaffold(
            topBar = {topBar(
                createGoalPage = createGoalPage
            )}
        ) {
            Box(modifier = Modifier.padding(innerPadding)) {

                LazyColumn(
                ) {
                    items(viewModel.goalArchiveList.value) {item ->
                        GoalArchiveListItemCompose(
                            goalArchive = item,
                            gotoArchiveDetailPage = gotoArchiveDetailPage,
                            accountId = account.id
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun topBar(
    createGoalPage: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.home_tab_name),
                color = DaikuAppTheme.colors.topAppBarTitle
            ) },
        actions = {
            TextButton(
                onClick = {
                    createGoalPage()
                }
            )

            {
                Text(
                    text = stringResource(id = R.string.add_project_button_name),
                    color = DaikuAppTheme.colors.topAppBarTitle)
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
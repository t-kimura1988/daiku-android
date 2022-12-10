package com.goen.home.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goen.domain.model.entity.Account
import com.goen.home.R
import com.goen.home.ui.comp.GoalArchiveListItemCompose
import com.goen.home.view_model.HomeMainViewModel
import com.goen.utils.compose.DaikuAppTheme
import com.goen.utils.compose.TermSearchDialog
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeMainCompose(
    createGoalPage: () -> Unit,
    createMakiPage: () -> Unit,
    innerPadding: PaddingValues,
    gotoArchiveDetailPage: (
        archiveId: Int,
        archiveCreateDate: String,
        accountId: Int
    ) -> Unit,
    account: Account
) {
    val viewModel: HomeMainViewModel = hiltViewModel()
//    val bottomSheetState = rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = viewModel.goalArchiveList, block = {
        viewModel.getGoalArchiveList()
    })

    val clickBottom: () -> Unit = {
        coroutineScope.launch{
            sheetState.show()
        }
    }

    val searchKeyChange: (year: Int, month: Int) -> Unit = {year: Int, month: Int ->
        viewModel.changeTermSearchKey(year = year, month = month)
    }

    val tapSearch: (flg: Boolean) -> Unit = {flg: Boolean ->
        viewModel.changeTermSearchAlert(flg)
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetElevation = 500.dp,
        sheetContent = {
                       Column(
                           modifier = Modifier
                               .height(500.dp)
                               .padding(8.dp)
                       ) {
                           Button(onClick = {
                               createGoalPage()
                           }) {
                               Text (text = "目標の作成")
                           }
                           Text(
                               modifier = Modifier.padding(8.dp),
                               text = "１つの目標を作成します。"
                           )
                           Button(onClick = {
                               createMakiPage()
                           }) {
                               Text (text = "巻の作成")
                           }
                           Text(
                               modifier = Modifier.padding(8.dp),
                               text = "複数の目標を１つにまとめる巻を作成します。最終目標まで簡単に管理できます。"
                           )
                       }
        },
    ) {

        DaikuAppTheme {
            Scaffold(
                topBar = {TopBar(
                    createGoalPage = createGoalPage,
                    bottomSheetButton = clickBottom
                )},
            ) {
                Box(modifier = Modifier.padding(innerPadding)) {

                    LazyColumn(
                    ) {
                        item {
                            TermSearchDialog(
                                dialogFlg = viewModel.termSearchDialogFlg.value,
                                currentKey = LocalDate.now().year,
                                changeKey = searchKeyChange,
                                changeDialog = tapSearch,

                                )
                        }
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

}

@Composable
private fun TopBar(
    createGoalPage: () -> Unit,
    bottomSheetButton: () -> Unit
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
                    bottomSheetButton()
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
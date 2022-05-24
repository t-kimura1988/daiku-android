package com.goen.account.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.goen.account.R
import com.goen.account.sealed.AccountMenu
import com.goen.account.ui.comp.AccountDetailTabCompose
import com.goen.account.ui.comp.AccountTopBar
import com.goen.account.ui.comp.detail.AccountDetailGoalSearchItemCompose
import com.goen.account.ui.comp.detail.TermSearchDialog
import com.goen.account.view_model.AccountDetailViewModel
import com.goen.domain.model.entity.Account
import com.goen.utils.compose.DaikuAppTheme
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate

@Composable
fun AccountDetailMainCompose(
    selectGoalDetail: (goalId: Int, createDate: String) -> Unit,
    gotoEditAccountInfo: () -> Unit,
    innerPadding: PaddingValues
) {
    val accountDetailVM: AccountDetailViewModel = hiltViewModel()

    LaunchedEffect(key1 = accountDetailVM.goalList, block = {
        load(accountDetailVM = accountDetailVM)
    })

    val createFavorite: (Int, String) -> Unit = {goalId: Int, createDate: String ->
        accountDetailVM.favorite(goalId = goalId, createDate = createDate)
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val clickMenu: () -> Unit = {
        scope.launch {
            scaffoldState.drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }

    val clickMenuItem: (menu: AccountMenu) -> Unit = { menu: AccountMenu ->
        when(menu) {
            AccountMenu.Logout->{
                accountDetailVM.logout()
            }
            AccountMenu.Delete->{
                accountDetailVM.changeDeleteDialogFlg()
            }
        }
    }

    val tapSearch: (flg: Boolean) -> Unit = {flg: Boolean ->
        accountDetailVM.changeTermSearchAlert(flg)
    }

    val searchKeyChange: (key: Int) -> Unit = {key: Int ->
        accountDetailVM.changeTermSearchKey(key)
    }

    DaikuAppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                AccountTopBar(onClickMenu = clickMenu)
            },
            bottomBar = {},
            drawerContent = {
                DrawerContent(
                    clickMenu = clickMenuItem
                )
            }
        ) {
            Box(modifier = Modifier.padding(innerPadding)) {

                LazyColumn(
                ) {
                    item {
                        AccountInfo(
                            account = accountDetailVM.accountInfo.value.account,
                            gotoEditAccountInfo = gotoEditAccountInfo
                        )
                    }

                    item {
                        AccountDetailTabCompose()
                    }

                    when(accountDetailVM.selectedTabIndex.value) {
                        0 -> {
                            item {
                                TermSearchDialog(
                                    dialogFlg = accountDetailVM.termSearchDialogFlg.value,
                                    currentKey = LocalDate.now().year,
                                    changeKey = searchKeyChange,
                                    changeDialog = tapSearch)
                            }
                            items(accountDetailVM.goalList.value) {item ->
                                AccountDetailGoalSearchItemCompose(
                                    item = item,
                                    onClickItem = selectGoalDetail,
                                    clickFavorite = createFavorite
                                )
                            }

                            if(accountDetailVM.goalList.value.size == accountDetailVM.input.pageCount) {
                                item{
                                    LoadingIndicator(accountVm = accountDetailVM)
                                }
                            }

                        }

                    }
                }
            }

            if(accountDetailVM.deleteDialogFlg.value) {

                AlertDialog(
                    onDismissRequest = { /*TODO*/ },
                    title = { Text(stringResource(id = R.string.goal_delete_confirm_message_text)) },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                accountDetailVM.changeDeleteDialogFlg()
                                accountDetailVM.deleteAccount()
                            }
                        ) {
                            Text(
                                text = "削除",
                                color = Color.Red
                            )
                        }

                        TextButton(
                            onClick = {
                                accountDetailVM.changeDeleteDialogFlg()
                            }
                        ) {
                            Text("閉じる")
                        }
                    }
                )
            }
        }
    }
}

fun load(accountDetailVM: AccountDetailViewModel) {
    accountDetailVM.getAccountDetail()
    accountDetailVM.getGoalInfo()

}

@Composable
fun AccountInfo(
    account: Account,
    gotoEditAccountInfo: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Image(
            modifier = Modifier
                .size(75.dp),
            painter = rememberImagePainter(
                data = account.userImage ?: R.drawable.samurai,
                builder = {
                    placeholder(R.drawable.samurai)
                    transformations(CircleCropTransformation())
                },
            ),
            contentDescription = account.accountName
        )

        Text(
            text = account.accountName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = account.nickName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        
        OutlinedButton(onClick = {
            gotoEditAccountInfo()
        }) {
            Text(stringResource(id = R.string.account_edit_button))
        }
    }
}

@Composable
fun DrawerContent(
    clickMenu: (menu: AccountMenu) -> Unit
) {

    val items = listOf(
        AccountMenu.Logout,
        AccountMenu.Delete
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEach{item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        clickMenu(item)
                    }
            ) {
                Text(
                    text = stringResource(id = item.title),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = stringResource(id = item.title)
                )
            }
        }
    }
}

@Composable
fun LoadingIndicator(accountVm: AccountDetailViewModel) {

    TextButton(onClick = {
        accountVm.upPageCounter()
    }) {
        Text(text = "もっと見る")
    }

}

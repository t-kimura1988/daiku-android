package com.goen.account.ui.comp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.goen.account.R
import com.goen.account.view_model.AccountDetailViewModel
import com.goen.utils.compose.DaikuAppTheme

@Composable
fun AccountDetailTabCompose() {
    val accountDetailViewModel: AccountDetailViewModel = hiltViewModel()

    TabRow(
        selectedTabIndex = accountDetailViewModel.selectedTabIndex.value,
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    ) {
        Tab(
            selected = accountDetailViewModel.selectedTabIndex.value == 0,
            onClick = { accountDetailViewModel.changeSelectedIndex(0) },
            text = {Text(text = stringResource(id = R.string.account_sub_tab_goal_button))}
        )
    }
}
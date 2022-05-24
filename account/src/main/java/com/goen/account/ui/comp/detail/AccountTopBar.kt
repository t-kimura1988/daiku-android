package com.goen.account.ui.comp

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.goen.account.R
import com.goen.utils.compose.DaikuAppTheme


@Composable
internal fun AccountTopBar(
    onClickMenu: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.account_tab_name),
                color = DaikuAppTheme.colors.topAppBarTitle) },
        navigationIcon =  {
            IconButton(onClick = { onClickMenu() }) {
                Icon(Icons.Filled.Menu, contentDescription = "open drawer")
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
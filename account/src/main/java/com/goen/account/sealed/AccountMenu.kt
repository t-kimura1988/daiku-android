package com.goen.account.sealed

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.ui.res.stringResource
import com.goen.account.R

sealed class AccountMenu(var title: Int, var icon: Int) {
    object Logout : AccountMenu(R.string.account_logout_button, R.drawable.ic_logout)
    object Delete : AccountMenu(R.string.account_delete_button, R.drawable.ic_delete)
}

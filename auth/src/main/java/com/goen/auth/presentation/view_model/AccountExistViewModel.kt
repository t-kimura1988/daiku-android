package com.goen.auth.presentation.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.entity.Account
import com.goen.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AccountExistViewModel @Inject constructor(
    val accountRepository: AccountRepository
): ViewModel() {
    var accountState: MutableState<AccountExistState> = mutableStateOf(AccountExistState())

    fun isExistAccount() {
        viewModelScope.launch {

            accountRepository.isExistAccount(
                onStart = {},
                onComplate = {},
                onError = {error ->
                    accountState.value = accountState.value.copy(error = error.errorCd)
                }
            ).collect { account: Account? ->
                accountState.value = accountState.value.copy(account = account, error = null)

            }
        }
    }

    fun reUpdate() {
        viewModelScope.launch {
            accountRepository.reUpdate(
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect { account: Account? ->
                accountState.value = accountState.value.copy(account = account, error = null)

            }
        }
    }

    fun setAccount(newAccount: Account) {
        accountState.value = accountState.value.copy(account = newAccount)
    }

    fun changeBackSignIn() {
        accountRepository.logout()
    }
}

data class AccountExistState(
    var account: Account? = null,
    var error: String? = null
){
    val isLoading = account == null && error == null
}

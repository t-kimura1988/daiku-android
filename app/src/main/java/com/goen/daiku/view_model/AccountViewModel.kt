package com.goen.daiku.view_model

import androidx.lifecycle.ViewModel
import com.goen.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    val accountRepository: AccountRepository
) : ViewModel(){
    fun createAccount() {

    }
}
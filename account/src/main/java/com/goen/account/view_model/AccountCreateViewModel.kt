package com.goen.account.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.auth.presentation.view_model.AccountExistViewModel
import com.goen.domain.model.entity.Account
import com.goen.domain.model.param.account.AccountCreateParameter
import com.goen.domain.repository.AccountRepository
import com.goen.utils.entity.FormObj
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountCreateViewModel @Inject constructor(
    private val accountRepository: AccountRepository
):ViewModel(){
    var input: AccountCreateInput = AccountCreateInput()
    var enableButton: MutableState<Boolean> = mutableStateOf(chkEnableButton())


    var success: MutableState<Boolean> = mutableStateOf(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)
    var errorDialog: MutableState<Boolean> = mutableStateOf(false)

    fun changeFamilyName(item: String) {
        if(item == "") {
            input.familyNameM.value = input.familyNameM.value.copy(error = "氏名を入力してください")
        } else {
            input.familyNameM.value = input.familyNameM.value.copy(error = "")

        }
        input.familyNameM.value = input.familyNameM.value.copy(value  = item)
    }

    fun changeGivenName(item: String) {
        if(item == "") {
            input.givenNameM.value = input.givenNameM.value.copy(error = "名前を入力してください")
        } else {
            input.givenNameM.value = input.givenNameM.value.copy(error = "")
        }
        input.givenNameM.value = input.givenNameM.value.copy(value = item)
    }

    fun changeNickName(item: String) {
        if(item == "") {
            input.nickNameM.value = input.nickNameM.value.copy(error = "ニックネームを入力してください")
        }else{
            input.nickNameM.value = input.nickNameM.value.copy(error = "")
        }
        input.nickNameM.value = input.nickNameM.value.copy(value  = item)
    }

    fun changeErrorDialog(flg: Boolean) {
        errorDialog.value = flg
    }

    fun createAccount(accountVM: AccountExistViewModel) {
        viewModelScope.launch {
            accountRepository.createAccount(
                param = input.parameter,
                onStart = {
                          loading.value = true
                },
                onComplate = {
                    loading.value = false
                    success.value = true
                },
                onError = {
                    loading.value = false
                    changeErrorDialog(true)
                })
                .collect { account: Account? ->
                    accountVM.setAccount(account!!)
                }
        }
    }

    fun updateAccount() {
        viewModelScope.launch {
            accountRepository.updateAccount(
                param = input.parameter,
                onStart = {
                    loading.value = true},
                onComplate = {
                    loading.value = false
                    success.value = true},
                onError = {
                    changeErrorDialog(true)
                })
                .collect {
                }
        }
    }
    fun accountDetail() {
        viewModelScope.launch {
            accountRepository.getAccountInfo(
                onStart = {},
                onComplate = {},
                onError = {_ ->
                })
                .collect { account: Account? ->
                    input.familyNameM.value = input.familyNameM.value.copy(value = account!!.familyName, error = "")
                    input.givenNameM.value = input.givenNameM.value.copy(value = account.givenName, error = "")
                    input.nickNameM.value = input.nickNameM.value.copy(value = account.nickName, error = "")
                }
        }
    }
    private fun chkEnableButton(): Boolean {

        return input.familyNameM.value.error == "" && input.givenNameM.value.error == "" && input.nickNameM.value.error == ""
    }
}

data class AccountCreateInput (
    var familyNameM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var givenNameM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var nickNameM: MutableState<FormObj> = mutableStateOf(FormObj())
){
    val familyName: String get() = familyNameM.value.value!!
    val givenName: String get() = givenNameM.value.value!!
    val nickName: String get() = nickNameM.value.value!!

    val familyNameError: String? get() = familyNameM.value.error
    val givenNameError: String? get() = givenNameM.value.error
    val nickNameError: String? get() = nickNameM.value.error

    val isFamilyNameError: Boolean get() = familyNameM.value.error != "" && familyNameM.value.error != null
    val isGivenNameError: Boolean get() = givenNameM.value.error != "" && givenNameM.value.error != null
    val isNickNameError: Boolean get() = nickNameM.value.error != "" && nickNameM.value.error != null

    val enableButton: Boolean get() =
        (familyNameM.value.error != null && givenNameM.value.error != null && nickNameM.value.error != null) && (familyNameM.value.error == "" && givenNameM.value.error == "" && nickNameM.value.error == "")

    val parameter: AccountCreateParameter
        get() = AccountCreateParameter(
        familyName = familyName,
        givenName = givenName,
        nickName = nickName
    )
}
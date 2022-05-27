package com.goen.account.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.entity.Account
import com.goen.domain.model.entity.Goal
import com.goen.domain.model.param.goal.GoalSearchParameter
import com.goen.domain.model.param.goalFavorite.GoalFavoriteCreateParameter
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.repository.AccountRepository
import com.goen.domain.repository.GoalFavoriteRepository
import com.goen.domain.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountDetailViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val goalRepository: GoalRepository,
    private val goalFavoriteRepository: GoalFavoriteRepository
):ViewModel(){
    var favoriteFlg: MutableState<Boolean> = mutableStateOf(false)
    var selectedTabIndex: MutableState<Int> = mutableStateOf(0)
    var accountInfo: MutableState<AccountInfo> = mutableStateOf(AccountInfo())
    var goalList: MutableState<List<GoalSearchResult>> = mutableStateOf(arrayListOf(GoalSearchResult()))
    var input: GoalSearch = GoalSearch()

    var deleteDialogFlg: MutableState<Boolean> = mutableStateOf(false)

    var termSearchDialogFlg: MutableState<Boolean> = mutableStateOf(false)

    val termSearchOptions = {for (i in 2022..2022) {
        mapOf(0 to i)
    } }

    fun getAccountDetail() {
        viewModelScope.launch {
            accountRepository.getAccountInfo(
                onStart = {},
                onComplate = {},
                onError = {}
            ).collect{account: Account? ->
                accountInfo.value = accountInfo.value.copy(account = account!!)
            }
        }
    }

    fun getGoalInfo() {
        input.pageCountM.value = input.pageCountM.value + 20
        viewModelScope.launch {
            goalRepository.getGoal(
                param = input.param,
                onStart = {},
                onComplate = {},
                onError = {_ ->
                })
                .collect { list: List<GoalSearchResult> ->
                    goalList.value = list
                }

        }

    }

    fun favorite(goalId: Int, createDate: String) {
        viewModelScope.launch {
            goalFavoriteRepository.createGoal(
                param = GoalFavoriteCreateParameter(
                    goalId = goalId,
                    goalCreateDate = createDate
                ),
                onError = {},
                onStart = {},
                onComplate = {}
            )
                .collect { _: Unit ->
                }
        }
    }

    fun changeSelectedIndex(index: Int) {
        selectedTabIndex.value = index
    }

    fun changeDeleteDialogFlg() {
        deleteDialogFlg.value = !deleteDialogFlg.value
    }

    fun logout() {
        accountRepository.logout()
    }

    fun deleteAccount() {
        viewModelScope.launch {
            accountRepository.deleteAccount(
                onStart = {},
                onError = {},
                onComplate = {
                    accountRepository.logout()
                }
            ).collect {

            }
        }
    }

    fun changeTermSearchAlert(item: Boolean) {
        termSearchDialogFlg.value = item
    }

    fun changeTermSearchKey(item: Int) {
        input.yearM.value = item
    }

    fun upPageCounter() {
        input.pageCountM.value = input.pageCountM.value + 20
    }
}

data class AccountInfo(
    var account: Account = Account()
)

data class GoalInfo(
    var goal: Goal = Goal()
)

data class GoalSearch(
    var yearM: MutableState<Int> = mutableStateOf(2022),
    var pageCountM: MutableState<Int> = mutableStateOf(0),
) {
    val year: Int get() = yearM.value
    val pageCount: Int get() = pageCountM.value

    val param: GoalSearchParameter
        get() = GoalSearchParameter(
            year = year,
            pageCount = pageCount
    )
}
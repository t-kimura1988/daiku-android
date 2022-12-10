package com.goen.account.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.entity.Account
import com.goen.domain.model.entity.Goal
import com.goen.domain.model.param.goal.GoalSearchParameter
import com.goen.domain.model.param.goal.MyGoalArchiveSearchParameter
import com.goen.domain.model.param.goalFavorite.GoalFavoriteCreateParameter
import com.goen.domain.model.param.idea.MyIdeaListParameter
import com.goen.domain.model.param.maki.MyMakiListSearchParameter
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.goal.GoalArchiveSearchResult
import com.goen.domain.model.result.idea.IdeaSearchResult
import com.goen.domain.model.result.maki.MakiSearchResult
import com.goen.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountDetailViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val goalRepository: GoalRepository,
    private val goalFavoriteRepository: GoalFavoriteRepository,
    private val ideaRepository: IdeaRepository,
    private val makiRepository: MakiRepository
):ViewModel(){
    var favoriteFlg: MutableState<Boolean> = mutableStateOf(false)
    var selectedTabIndex: MutableState<Int> = mutableStateOf(0)
    var accountInfo: MutableState<AccountInfo> = mutableStateOf(AccountInfo())
    var goalList: MutableState<List<GoalSearchResult>> = mutableStateOf(arrayListOf(GoalSearchResult()))
    var ideaList: MutableState<List<IdeaSearchResult>> = mutableStateOf(arrayListOf(IdeaSearchResult()))
    var makiList: MutableState<List<MakiSearchResult>> = mutableStateOf(arrayListOf(MakiSearchResult()))
    var myGoalArchiveList: MutableState<List<GoalArchiveSearchResult>> = mutableStateOf(arrayListOf(GoalArchiveSearchResult()))
    var input: GoalSearch = GoalSearch()
    var inputIdea: MyIdeaSearch = MyIdeaSearch()
    var inputMaki: MyMakiSearch = MyMakiSearch()
    var inputMyArchive: MyGoalArchiveSearchInput = MyGoalArchiveSearchInput()

    var deleteDialogFlg: MutableState<Boolean> = mutableStateOf(false)

    var termSearchDialogFlg: MutableState<Boolean> = mutableStateOf(false)
    var termSearchArchiveDialogFlg: MutableState<Boolean> = mutableStateOf(false)

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
        input.pageM.value = input.pageM.value + 20
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

    fun getMyIdeaList() {
        inputIdea.pageM.value = inputIdea.pageM.value + 20
        viewModelScope.launch {
            ideaRepository.getMyIdeaList(
                param = inputIdea.param,
                onStart = {},
                onComplate = {},
                onError = {_ ->
                })
                .collect { list: List<IdeaSearchResult> ->
                    ideaList.value = list
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

    fun getMakiList() {
        inputMaki.pageM.value = inputMaki.pageM.value + 20
        viewModelScope.launch {
            makiRepository.myMakiList(
                param = inputMaki.param,
                onError = {},
                onStart = {},
                onComplate = {}
            )
                .collect {list: List<MakiSearchResult> ->
                    makiList.value = list

                }
        }
    }

    fun getMyGoalArchive() {
        viewModelScope.launch {
            goalRepository.getMyGoalArchiveList(
                param = inputMyArchive.param,
                onError = {},
                onStart = {},
                onComplate = {},
            ).collect{ it ->
                myGoalArchiveList.value = it
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
    fun changeTermSearchAlertArchive(item: Boolean) {
        termSearchArchiveDialogFlg.value = item
    }

    fun changeTermSearchKey(year: Int, month: Int) {
        input.yearM.value = year
        input.monthM.value = month
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

    fun changeTermSearchKeyArchive(year: Int, month: Int) {
        inputMyArchive.yearM.value = year
        inputMyArchive.monthM.value = month
        viewModelScope.launch {
            goalRepository.getMyGoalArchiveList(
                param = inputMyArchive.param,
                onStart = {},
                onComplate = {},
                onError = {_ ->
                })
                .collect { list: List<GoalArchiveSearchResult> ->
                    myGoalArchiveList.value = list
                }
        }
    }

    fun upPageCounter() {
        input.pageM.value = input.pageM.value + 20
    }

    fun upMyIdeaListPageCounter() {
        inputIdea.pageM.value = inputIdea.pageM.value + 20
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
    var monthM: MutableState<Int> = mutableStateOf(0),
    var pageM: MutableState<Int> = mutableStateOf(0),
) {
    val year: Int get() = yearM.value
    val month: Int get() = monthM.value
    val page: Int get() = pageM.value

    val param: GoalSearchParameter
        get() = GoalSearchParameter(
            year = year,
            month = month,
            page = page
    )
}

data class MyIdeaSearch(
    var pageM: MutableState<Int> = mutableStateOf(10),
) {
    val page: Int get() = pageM.value

    val param: MyIdeaListParameter
        get() = MyIdeaListParameter(
            page = page
        )
}

data class MyMakiSearch(
    var pageM: MutableState<Int> = mutableStateOf(10)
) {
    val page: Int get() = pageM.value

    val param: MyMakiListSearchParameter
        get() = MyMakiListSearchParameter(
        page = page
    )
}

data class MyGoalArchiveSearchInput(
    var yearM: MutableState<Int> = mutableStateOf(2022),
    var monthM: MutableState<Int> = mutableStateOf(0),
) {
    val year: Int get() = yearM.value
    val month: Int get() = monthM.value

    val param: MyGoalArchiveSearchParameter
        get() = MyGoalArchiveSearchParameter(
            year = year,
            month = month
        )
}
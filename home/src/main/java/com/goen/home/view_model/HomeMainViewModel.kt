package com.goen.home.view_model

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.goal.GoalArchiveSearchParameter
import com.goen.domain.model.result.goal.GoalArchiveSearchResult
import com.goen.domain.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMainViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
): ViewModel() {

    var goalArchiveList: MutableState<List<GoalArchiveSearchResult>> = mutableStateOf(arrayListOf(GoalArchiveSearchResult()))
    var input: GoalArchiveSearch = GoalArchiveSearch()
    var termSearchDialogFlg: MutableState<Boolean> = mutableStateOf(false)

    @OptIn(ExperimentalMaterialApi::class)

    fun getGoalArchiveList() {
        input.pageM.value = input.pageM.value + 10
        viewModelScope.launch {
            goalRepository.getGoalArchiveList(
                param = input.param,
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect { list: List<GoalArchiveSearchResult> ->
                    goalArchiveList.value = list
                }
        }
    }

    fun changeTermSearchKey(year: Int, month: Int) {
        input.yearM.value = year
        input.monthM.value = month
        viewModelScope.launch {
            goalRepository.getGoalArchiveList(
                param = input.param,
                onStart = {},
                onComplate = {},
                onError = {_ ->
                })
                .collect { list: List<GoalArchiveSearchResult> ->
                    goalArchiveList.value = list
                }
        }
    }

    fun changeTermSearchAlert(item: Boolean) {
        termSearchDialogFlg.value = item
    }
}

data class GoalArchiveSearch(
    var yearM: MutableState<Int> = mutableStateOf(2022),
    var monthM: MutableState<Int> = mutableStateOf(0),
    var pageM: MutableState<Int> = mutableStateOf(0),
) {
    val year: Int get() = yearM.value
    val month: Int get() = monthM.value
    val page: Int get() = pageM.value

    val param: GoalArchiveSearchParameter
        get() = GoalArchiveSearchParameter(
            year = year,
            month = month,
            page = page
        )
}
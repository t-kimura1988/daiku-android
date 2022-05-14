package com.goen.home.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.goal.GoalArchiveSearchParameter
import com.goen.domain.model.param.goal.GoalSearchParameter
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.goal.GoalArchiveSearchResult
import com.goen.domain.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMainViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
): ViewModel() {

    var goalArchiveList: MutableState<List<GoalArchiveSearchResult>> = mutableStateOf(arrayListOf(GoalArchiveSearchResult()))
    var input: GoalArchiveSearch = GoalArchiveSearch()

    fun getGoalArchiveList() {
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

}

data class GoalArchiveSearch(
    var yearM: MutableState<Int> = mutableStateOf(2022),
    var pageCountM: MutableState<Int> = mutableStateOf(0),
) {
    val year: Int get() = yearM.value
    val pageCount: Int get() = pageCountM.value

    val param: GoalArchiveSearchParameter
        get() = GoalArchiveSearchParameter(
            year = year,
            pageCount = pageCount
        )
}
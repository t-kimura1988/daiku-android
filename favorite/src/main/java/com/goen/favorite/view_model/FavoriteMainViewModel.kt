package com.goen.favorite.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.goalFavorite.FavoriteGoalSearchParameter
import com.goen.domain.model.result.goal_favorite.GoalFavoriteSearchResult
import com.goen.domain.repository.GoalFavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMainViewModel @Inject constructor(
    private val goalFavoriteRepository: GoalFavoriteRepository
): ViewModel() {

    var favoriteGoalList: MutableState<List<GoalFavoriteSearchResult>> = mutableStateOf(arrayListOf(GoalFavoriteSearchResult()))
    var input: FavoriteGoalSearch = FavoriteGoalSearch()

    fun getFavoriteGoalList() {
        viewModelScope.launch {
            goalFavoriteRepository.getFavoriteGoal(
                param = input.param,
                onStart = {},
                onComplate = {}
            ) { _ ->

            }
                .collect { list: List<GoalFavoriteSearchResult> ->
                    favoriteGoalList.value = list
                }
        }
    }

}


data class FavoriteGoalSearch(
    var yearM: MutableState<Int> = mutableStateOf(2022),
    var pageM: MutableState<Int> = mutableStateOf(10),
) {
    val year: Int get() = yearM.value
    val page: Int get() = pageM.value

    val param: FavoriteGoalSearchParameter
        get() = FavoriteGoalSearchParameter(
            year = year,
            page = page
        )
}
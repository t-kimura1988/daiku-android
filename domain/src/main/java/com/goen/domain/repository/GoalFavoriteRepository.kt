package com.goen.domain.repository

import com.goen.domain.model.param.goalFavorite.FavoriteGoalSearchParameter
import com.goen.domain.model.param.goalFavorite.GoalFavoriteCreateParameter
import com.goen.domain.model.result.goal_favorite.GoalFavoriteSearchResult
import com.goen.utils.exception.ApiException
import kotlinx.coroutines.flow.Flow

interface GoalFavoriteRepository {

    fun createGoal(
        param: GoalFavoriteCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ): Flow<Unit>

    fun getFavoriteGoal(
        param: FavoriteGoalSearchParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<GoalFavoriteSearchResult>>

}
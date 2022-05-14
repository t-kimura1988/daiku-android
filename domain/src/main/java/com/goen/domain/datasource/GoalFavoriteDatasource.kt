package com.goen.domain.datasource

import com.goen.domain.model.param.goal.GoalCreateParameter
import com.goen.domain.model.param.goal.GoalDetailParameter
import com.goen.domain.model.param.goal.GoalDetailResult
import com.goen.domain.model.param.goal.GoalSearchParameter
import com.goen.domain.model.param.goalFavorite.FavoriteGoalSearchParameter
import com.goen.domain.model.param.goalFavorite.GoalFavoriteCreateParameter
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.goal_favorite.GoalFavoriteSearchResult

interface GoalFavoriteDatasource {
    suspend fun changeGoalFavorite(parameter: GoalFavoriteCreateParameter)
    suspend fun favoriteGoalSearch(parameter: FavoriteGoalSearchParameter): List<GoalFavoriteSearchResult>
}
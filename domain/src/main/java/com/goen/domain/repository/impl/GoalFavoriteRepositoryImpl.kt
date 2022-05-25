package com.goen.domain.repository.impl

import android.util.Log
import com.goen.domain.datasource.GoalFavoriteDatasource
import com.goen.domain.model.param.goalFavorite.FavoriteGoalSearchParameter
import com.goen.domain.model.param.goalFavorite.GoalFavoriteCreateParameter
import com.goen.domain.model.result.goal_favorite.GoalFavoriteSearchResult
import com.goen.domain.repository.GoalFavoriteRepository
import com.goen.utils.exception.ApiException
import com.goen.utils.extentions.setEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GoalFavoriteRepositoryImpl @Inject constructor(
    private val goalFavoriteDatasource: GoalFavoriteDatasource
): GoalFavoriteRepository {

    override fun createGoal(
        param: GoalFavoriteCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Unit> {
        return flow {
            try {
                emit(goalFavoriteDatasource.changeGoalFavorite(param))
            } catch (e: Exception) {
                Timber.e("create goal favorite error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun getFavoriteGoal(
        param: FavoriteGoalSearchParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<GoalFavoriteSearchResult>> {
        Log.println(Log.INFO, "test", "call favorite goal list repository")
        return flow {
            emit(goalFavoriteDatasource.favoriteGoalSearch(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

}
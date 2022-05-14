package com.goen.domain.datasource.impl

import android.util.Log
import com.goen.domain.datasource.GoalFavoriteDatasource
import com.goen.domain.entity.ErrorResponse
import com.goen.domain.model.param.goalFavorite.FavoriteGoalSearchParameter
import com.goen.domain.model.param.goalFavorite.GoalFavoriteCreateParameter
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.goal_favorite.GoalFavoriteSearchResult
import com.goen.domain.service.GoalFavoriteService
import com.goen.utils.exception.ApiException
import com.google.gson.Gson
import javax.inject.Inject

class GoalFavoriteDatasourceImpl @Inject constructor(
    private val service: GoalFavoriteService
): GoalFavoriteDatasource {
    override suspend fun changeGoalFavorite(parameter: GoalFavoriteCreateParameter) {
        service.changeGoalFavorite(parameter = parameter)
    }

    override suspend fun favoriteGoalSearch(parameter: FavoriteGoalSearchParameter): List<GoalFavoriteSearchResult> {
        var result = service.goalFavoriteList(year = parameter.year)
        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "お気に入り目標の取得に成功")
            return result.body()!!
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.toString(), ErrorResponse::class.java)

        throw ApiException(result.code(), "apiERROR", errRes.errorCd)
    }
}
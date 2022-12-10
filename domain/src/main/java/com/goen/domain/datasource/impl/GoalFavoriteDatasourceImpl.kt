package com.goen.domain.datasource.impl

import com.goen.domain.datasource.GoalFavoriteDatasource
import com.goen.domain.model.entity.ErrorResponse
import com.goen.domain.model.param.goalFavorite.FavoriteGoalSearchParameter
import com.goen.domain.model.param.goalFavorite.GoalFavoriteCreateParameter
import com.goen.domain.model.result.goal_favorite.GoalFavoriteSearchResult
import com.goen.domain.service.GoalFavoriteService
import com.goen.utils.exception.ApiException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class GoalFavoriteDatasourceImpl @Inject constructor(
    private val service: GoalFavoriteService
): GoalFavoriteDatasource {
    var moshi: Moshi = Moshi.Builder().build()
    override suspend fun changeGoalFavorite(parameter: GoalFavoriteCreateParameter) {
        service.changeGoalFavorite(parameter = parameter)
    }

    override suspend fun favoriteGoalSearch(parameter: FavoriteGoalSearchParameter): List<GoalFavoriteSearchResult> {
        var result = service.goalFavoriteList(year = parameter.year, page = parameter.page)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }
}
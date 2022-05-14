package com.goen.domain.service

import com.goen.domain.interceptor.AuthorizationInterceptor
import com.goen.domain.model.param.goal.GoalCreateParameter
import com.goen.domain.model.param.goal.GoalDetailResult
import com.goen.domain.model.param.goalFavorite.GoalFavoriteCreateParameter
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.goal_favorite.GoalFavoriteSearchResult
import retrofit2.Response
import retrofit2.http.*

interface GoalFavoriteService {

    @POST("api/goal-favorite/change")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun changeGoalFavorite(@Body parameter: GoalFavoriteCreateParameter): Response<Unit>

    @GET("api/goal-favorite/search")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun goalFavoriteList(@Query("year") year: Int): Response<List<GoalFavoriteSearchResult>>
}
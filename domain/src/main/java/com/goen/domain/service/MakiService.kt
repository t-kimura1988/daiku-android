package com.goen.domain.service

import com.goen.domain.interceptor.AuthorizationInterceptor
import com.goen.domain.model.param.maki.MakiCreateParameter
import com.goen.domain.model.param.maki.MakiGoalRelationCreateParameter
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.maki.MakiGoalRelationResult
import com.goen.domain.model.result.maki.MakiSearchResult
import retrofit2.Response
import retrofit2.http.*

interface MakiService {

    @GET("api/maki/search")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun searchMaki(@Query("page")page: Int): Response<List<MakiSearchResult>>

    @GET("api/maki/detail")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun detail(@Query("makiId")makiId: Int): Response<MakiSearchResult>

    @GET("api/maki/goal-list")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun makiGoal(@Query("makiId")makiId: Int, @Query("page")page: Int): Response<List<GoalSearchResult>>

    @GET("api/maki/add/goal-list")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun makiAddGoalList(@Query("makiId")makiId: Int): Response<List<GoalSearchResult>>

    @POST("api/maki/create")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun createMaki(@Body parameter: MakiCreateParameter): Response<MakiSearchResult>


    @POST("api/maki/add/goal")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun createMakiGoalRelation(@Body parameter: List<MakiGoalRelationCreateParameter>): Response<List<MakiGoalRelationResult>>

}
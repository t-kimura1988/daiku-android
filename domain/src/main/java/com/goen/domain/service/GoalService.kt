package com.goen.domain.service

import com.goen.domain.interceptor.AuthorizationInterceptor
import com.goen.domain.model.param.goal.GoalArchiveCreateParameter
import com.goen.domain.model.param.goal.GoalCreateParameter
import com.goen.domain.model.param.goal.GoalDetailParameter
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.goal.GoalArchiveDetailResult
import com.goen.domain.model.result.goal.GoalArchiveSearchResult
import com.goen.domain.model.result.goal.GoalDetailResult
import retrofit2.Response
import retrofit2.http.*

interface GoalService {

    @POST("api/goal/create")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun createGoal(@Body parameter: GoalCreateParameter): Response<Unit>

    @POST("api/goal/update")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun updateGoal(@Body parameter: GoalCreateParameter): Response<Unit>

    @GET("api/goal/search")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun searchGoal(@Query("year") year: Int,@Query("month") month: Int, @Query("page") page: Int): Response<List<GoalSearchResult>>

    @GET("api/goal/detail")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun getGoalDetail(@Query("goalId") goalId: Int, @Query("createDate") createDate: String): Response<GoalDetailResult>

    @POST("api/goal/create/archive")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun createGoalArchive(@Body parameter: GoalArchiveCreateParameter): Response<Unit>

    @POST("api/goal/update/archive")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun updateGoalArchive(@Body parameter: GoalArchiveCreateParameter): Response<Unit>

    @GET("api/goal/archive/search")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun searchGoalArchive(@Query("year") year: Int, @Query("page") page: Int, @Query("month") month: Int?): Response<List<GoalArchiveSearchResult>>

    @GET("api/goal/my-archive/list")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun searchMyGoalArchive(@Query("year") year: Int, @Query("month") month: Int?): Response<List<GoalArchiveSearchResult>>

    @GET("api/goal/my-archive/detail")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun getMyGoalArchiveDetail(@Query("archiveId") archiveId: Int, @Query("archiveCreateDate") createDate: String): Response<GoalArchiveDetailResult>

    @GET("api/goal/archive/detail")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun getGoalArchiveDetail(@Query("archiveId") archiveId: Int, @Query("archiveCreateDate") createDate: String): Response<GoalArchiveDetailResult>

    @GET("api/goal/update/archive/display")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun getArchiveUpdateDisp(@Query("archiveId") archiveId: Int, @Query("archiveCreateDate") createDate: String): Response<GoalArchiveSearchResult>


    @POST("api/goal/update/archive/updating-flg")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun updatingFlg(@Body parameter: GoalDetailParameter): Response<GoalDetailResult>
}
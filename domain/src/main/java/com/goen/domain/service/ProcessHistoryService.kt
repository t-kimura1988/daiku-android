package com.goen.domain.service

import com.goen.domain.interceptor.AuthorizationInterceptor
import com.goen.domain.model.param.process.ProcessCreateParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateCommentParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateParameter
import com.goen.domain.model.result.process.ProcessHistoryResult
import com.goen.domain.model.result.process.ProcessResult
import retrofit2.Response
import retrofit2.http.*

interface ProcessHistoryService {

    @GET("api/process-history/list")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun list(@Query("processId") processId: Int): Response<List<ProcessHistoryResult>>

    @GET("api/process-history/detail")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun detail(@Query("processHistoryId") processId: Int, @Query("goalCreateDate") goalCreateDate: String): Response<ProcessHistoryResult>

    @POST("api/process-history/create")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun create(@Body parameter: ProcessHistoryUpdateParameter): Response<Unit>

    @POST("api/process-history/update/comment")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun updateComment(@Body parameter: ProcessHistoryUpdateCommentParameter): Response<Unit>
}
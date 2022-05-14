package com.goen.domain.service

import com.goen.domain.interceptor.AuthorizationInterceptor
import com.goen.domain.model.param.process.ProcessCreateParameter
import com.goen.domain.model.result.process.ProcessResult
import retrofit2.Response
import retrofit2.http.*

interface ProcessService {

    @GET("api/process/list")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun list(@Query("goalId") goalId: Int, @Query("createDate") createDate: String): Response<List<ProcessResult>>

    @POST("api/process/create")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun create(@Body parameter: ProcessCreateParameter): Response<Unit>

    @POST("api/process/update")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun update(@Body parameter: ProcessCreateParameter): Response<Unit>

    @GET("api/process/detail")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun processDetail(@Query("processId") processId: Int): Response<ProcessResult>

}
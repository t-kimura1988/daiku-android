package com.goen.domain.service

import com.goen.domain.interceptor.AuthorizationInterceptor
import com.goen.domain.model.param.idea.IdeaCreateParameter
import com.goen.domain.model.param.idea.IdeaUpdateParameter
import com.goen.domain.model.result.idea.IdeaSearchResult
import retrofit2.Response
import retrofit2.http.*

interface IdeaService {

    @POST("api/idea/create")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun createIdea(@Body parameter: IdeaCreateParameter): Response<IdeaSearchResult>

    @POST("api/idea/update")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun updateIdea(@Body parameter: IdeaUpdateParameter): Response<IdeaSearchResult>

    @GET("api/idea/detail")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun myIdeaDetail(@Query("ideaId")ideaId: Int): Response<IdeaSearchResult>

    @GET("api/idea/my-search")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun myIdeaList(@Query("page") page: Int): Response<List<IdeaSearchResult>>
}
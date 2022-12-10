package com.goen.domain.service

import com.goen.domain.interceptor.AuthorizationInterceptor
import com.goen.domain.model.param.story.StoryBodyUpdateParameter
import com.goen.domain.model.param.story.StoryCreateParameter
import com.goen.domain.model.result.idea.IdeaSearchResult
import com.goen.domain.model.result.story.StorySearchResult
import retrofit2.Response
import retrofit2.http.*

interface StoryService {

    @GET("api/story/detail")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun storyDetail(@Query("storyId") storyId: Int, @Query("ideaId") ideaId: Int): Response<StorySearchResult>

    @POST("api/story/update-story-body")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun storyBodyUpdate(@Body body: StoryBodyUpdateParameter): Response<IdeaSearchResult>


    @POST("api/story/create")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun createStory(@Body body: StoryCreateParameter): Response<StorySearchResult>
}
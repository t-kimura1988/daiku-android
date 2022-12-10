package com.goen.domain.service

import com.goen.domain.interceptor.AuthorizationInterceptor
import com.goen.domain.model.param.story_character.StoryCharacterCreateParameter
import com.goen.domain.model.result.story_character.StoryCharacterSearchResult
import retrofit2.Response
import retrofit2.http.*

interface StoryCharacterService {

    @POST("api/story-character/create")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun createStoryCharacter(@Body parameter: StoryCharacterCreateParameter): Response<StoryCharacterSearchResult>

    @GET("api/story-character/list")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun storyCharaList(@Query("storyId") storyId: Int, @Query("ideaId") ideaId: Int): Response<List<StoryCharacterSearchResult>>
}
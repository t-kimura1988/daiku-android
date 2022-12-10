package com.goen.domain.datasource.impl

import com.goen.domain.datasource.StoryDatasource
import com.goen.domain.model.entity.ErrorResponse
import com.goen.domain.model.param.story.StoryBodyUpdateParameter
import com.goen.domain.model.param.story.StoryCreateParameter
import com.goen.domain.model.param.story.StoryDetailParameter
import com.goen.domain.model.result.idea.IdeaSearchResult
import com.goen.domain.model.result.story.StorySearchResult
import com.goen.domain.service.StoryService
import com.goen.utils.exception.ApiException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class StoryDatasourceImpl @Inject constructor(
    private val service: StoryService
): StoryDatasource {
    var moshi: Moshi = Moshi.Builder().build()
    override suspend fun storyDetail(parameter: StoryDetailParameter): StorySearchResult {
        var result = service.storyDetail(storyId = parameter.storyId, ideaId = parameter.ideaId)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun storyBodyUpdate(parameter: StoryBodyUpdateParameter): IdeaSearchResult {
        var result = service.storyBodyUpdate(body = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun createStory(parameter: StoryCreateParameter): StorySearchResult {
        var result = service.createStory(parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

}
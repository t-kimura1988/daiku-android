package com.goen.domain.datasource.impl

import com.goen.domain.datasource.StoryCharacterDatasource
import com.goen.domain.model.entity.ErrorResponse
import com.goen.domain.model.param.story_character.StoryCharacterCreateParameter
import com.goen.domain.model.param.story_character.StoryCharacterListParameter
import com.goen.domain.model.result.story_character.StoryCharacterSearchResult
import com.goen.domain.service.StoryCharacterService
import com.goen.utils.exception.ApiException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class StoryCharacterDatasourceImpl @Inject constructor(
    private val service: StoryCharacterService
): StoryCharacterDatasource {
    var moshi: Moshi = Moshi.Builder().build()

    override suspend fun storyCharaList(parameter: StoryCharacterListParameter): List<StoryCharacterSearchResult> {
        var result = service.storyCharaList(storyId = parameter.storyId, ideaId = parameter.ideaId)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun createCharacter(parameter: StoryCharacterCreateParameter): StoryCharacterSearchResult {
        var result = service.createStoryCharacter(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }
}
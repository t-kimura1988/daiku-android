package com.goen.domain.repository

import com.goen.domain.model.param.story_character.StoryCharacterCreateParameter
import com.goen.domain.model.param.story_character.StoryCharacterListParameter
import com.goen.domain.model.result.story_character.StoryCharacterSearchResult
import com.goen.utils.exception.ApiException
import kotlinx.coroutines.flow.Flow

interface StoryCharacterRepository {

    fun getStoryCharaList(
        param: StoryCharacterListParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<StoryCharacterSearchResult>>

    fun createStoryChara(
        param: StoryCharacterCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<StoryCharacterSearchResult>
}
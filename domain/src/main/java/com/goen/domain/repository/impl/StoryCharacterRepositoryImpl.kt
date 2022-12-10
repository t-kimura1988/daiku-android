package com.goen.domain.repository.impl

import com.goen.domain.datasource.StoryCharacterDatasource
import com.goen.domain.model.param.story_character.StoryCharacterCreateParameter
import com.goen.domain.model.param.story_character.StoryCharacterListParameter
import com.goen.domain.model.result.story_character.StoryCharacterSearchResult
import com.goen.domain.repository.StoryCharacterRepository
import com.goen.utils.exception.ApiException
import com.goen.utils.extentions.setEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoryCharacterRepositoryImpl @Inject constructor(
    private val storyCharacterDatasource: StoryCharacterDatasource
): StoryCharacterRepository {

    override fun getStoryCharaList(
        param: StoryCharacterListParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<StoryCharacterSearchResult>> {
        return flow {
            emit(storyCharacterDatasource.storyCharaList(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun createStoryChara(
        param: StoryCharacterCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<StoryCharacterSearchResult> {
        return flow {
            emit(storyCharacterDatasource.createCharacter(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

}
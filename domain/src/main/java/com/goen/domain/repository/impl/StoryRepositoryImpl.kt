package com.goen.domain.repository.impl

import com.goen.domain.datasource.StoryDatasource
import com.goen.domain.model.param.story.StoryBodyUpdateParameter
import com.goen.domain.model.param.story.StoryCreateParameter
import com.goen.domain.model.param.story.StoryDetailParameter
import com.goen.domain.model.result.idea.IdeaSearchResult
import com.goen.domain.model.result.story.StorySearchResult
import com.goen.domain.repository.StoryRepository
import com.goen.utils.exception.ApiException
import com.goen.utils.extentions.setEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val storyDatasource: StoryDatasource
): StoryRepository {
    override fun getStoryDetail(
        param: StoryDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<StorySearchResult> {
        return flow {
            emit(storyDatasource.storyDetail(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun updateStoryBody(
        param: StoryBodyUpdateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<IdeaSearchResult> {
        return flow {
            emit(storyDatasource.storyBodyUpdate(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun createStory(
        param: StoryCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<StorySearchResult> {
        return flow {
            emit(storyDatasource.createStory(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }


}
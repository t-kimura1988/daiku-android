package com.goen.domain.repository

import com.goen.domain.model.param.story.StoryBodyUpdateParameter
import com.goen.domain.model.param.story.StoryCreateParameter
import com.goen.domain.model.param.story.StoryDetailParameter
import com.goen.domain.model.result.idea.IdeaSearchResult
import com.goen.domain.model.result.story.StorySearchResult
import com.goen.utils.exception.ApiException
import kotlinx.coroutines.flow.Flow

interface StoryRepository {

    fun getStoryDetail(
        param: StoryDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<StorySearchResult>

    fun updateStoryBody(
        param: StoryBodyUpdateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<IdeaSearchResult>

    fun createStory(
        param: StoryCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<StorySearchResult>
}
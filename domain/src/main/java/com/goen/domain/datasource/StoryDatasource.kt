package com.goen.domain.datasource

import com.goen.domain.model.param.story.StoryBodyUpdateParameter
import com.goen.domain.model.param.story.StoryCreateParameter
import com.goen.domain.model.param.story.StoryDetailParameter
import com.goen.domain.model.result.idea.IdeaSearchResult
import com.goen.domain.model.result.story.StorySearchResult

interface StoryDatasource {
    suspend fun storyDetail(parameter: StoryDetailParameter): StorySearchResult
    suspend fun storyBodyUpdate(parameter: StoryBodyUpdateParameter): IdeaSearchResult
    suspend fun createStory(parameter: StoryCreateParameter): StorySearchResult
}
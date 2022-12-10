package com.goen.domain.datasource

import com.goen.domain.model.param.story_character.StoryCharacterCreateParameter
import com.goen.domain.model.param.story_character.StoryCharacterListParameter
import com.goen.domain.model.result.story_character.StoryCharacterSearchResult

interface StoryCharacterDatasource {
    suspend fun storyCharaList(parameter: StoryCharacterListParameter): List<StoryCharacterSearchResult>
    suspend fun createCharacter(parameter: StoryCharacterCreateParameter): StoryCharacterSearchResult
}
package com.goen.domain.model.param.story_character

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryCharacterListParameter(
    var storyId: Int,
    var ideaId: Int,
)

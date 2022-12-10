package com.goen.domain.model.param.story

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryDetailParameter (
    @Json(name = "story_id") var storyId: Int,
    @Json(name = "idea_id") var ideaId: Int
    )
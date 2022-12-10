package com.goen.domain.model.param.story

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryCreateParameter (
    @Json(name = "idea_id") var ideaId: Int? = null,
    @Json(name = "title") var title: String,
)
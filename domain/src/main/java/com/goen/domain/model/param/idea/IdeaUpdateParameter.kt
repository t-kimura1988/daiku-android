package com.goen.domain.model.param.idea

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IdeaUpdateParameter (
    @Json(name = "idea_id") var ideaId: Int,
    @Json(name = "body") var body: String)
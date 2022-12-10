package com.goen.domain.model.param.idea

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IdeaCreateParameter (
    @Json(name = "body") var body: String)
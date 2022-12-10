package com.goen.domain.model.param.idea

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MyIdeaDetailParameter(
    @Json(name = "ideaId") var ideaId: Int
)

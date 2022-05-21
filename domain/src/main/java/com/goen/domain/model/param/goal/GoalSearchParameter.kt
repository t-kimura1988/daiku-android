package com.goen.domain.model.param.goal

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoalSearchParameter (
    @Json(name = "year") var year: Int,
    @Json(name = "page_count") var pageCount: Int
)
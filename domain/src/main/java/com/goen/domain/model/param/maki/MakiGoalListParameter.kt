package com.goen.domain.model.param.maki

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MakiGoalListParameter (
    @Json(name = "maki_id") var makiId: Int,
    @Json(name = "page") var page: Int
)
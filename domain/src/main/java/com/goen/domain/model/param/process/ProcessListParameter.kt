package com.goen.domain.model.param.process

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProcessListParameter (
    @Json(name = "goal_id") var goalId: Int,
    @Json(name = "create_date") var createDate: String
)
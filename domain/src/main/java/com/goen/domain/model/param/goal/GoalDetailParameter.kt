package com.goen.domain.model.param.goal

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoalDetailParameter(
    @Json(name = "goal_id") var goalId: Int,
    @Json(name = "create_date") var createDate: String
)
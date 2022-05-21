package com.goen.domain.model.param.goalFavorite

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoalFavoriteCreateParameter (
    @Json(name = "goal_id") var goalId: Int,
    @Json(name = "goal_create_date") var goalCreateDate: String)
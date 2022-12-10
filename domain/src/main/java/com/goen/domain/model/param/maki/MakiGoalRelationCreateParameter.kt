package com.goen.domain.model.param.maki

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MakiGoalRelationCreateParameter (
    @Json(name = "maki_id") var makiId: Int = 0,
    @Json(name = "goal_id") var goalId: Int = 0,
    @Json(name = "goal_create_date") var goalCreateDate: String = ""
)
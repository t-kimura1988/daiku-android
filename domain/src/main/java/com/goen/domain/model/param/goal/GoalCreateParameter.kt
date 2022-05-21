package com.goen.domain.model.param.goal

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoalCreateParameter (
    @Json(name = "goal_id") var goalId: Int? = null,
    @Json(name = "create_date") var createDate: String? = null,
    @Json(name = "title") var title: String,
    @Json(name = "purpose") var purpose: String,
    @Json(name = "aim") var aim: String,
    @Json(name = "due_date") var dueDate: String
)
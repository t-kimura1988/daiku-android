package com.goen.domain.model.param.goal

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoalArchiveCreateParameter (
    @Json(name = "archive_id") var archiveId: Int? = null,
    @Json(name = "archive_create_date") var archiveCreateDate: String? = null,
    @Json(name = "goal_id") var goalId: Int? = null,
    @Json(name = "create_date") var createDate: String? = null,
    @Json(name = "thoughts") var thoughts: String,
    @Json(name = "publish") var publish: Int,
)
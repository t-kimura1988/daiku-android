package com.goen.domain.model.param.process

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ProcessCreateParameter (
    @Json(name = "process_id") var processId: Int? = null,
    @Json(name = "goal_create_date") var goalCreateDate: String? = null,
    @Json(name = "goal_id") var goalId: Int,
    @Json(name = "title") var title: String,
    @Json(name = "body") var body: String,
    @Json(name = "priority") var priority: Int,
    @Json(name = "process_status") var processStatus: Int
)
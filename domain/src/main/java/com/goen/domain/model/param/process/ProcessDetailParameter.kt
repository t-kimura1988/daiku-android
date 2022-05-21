package com.goen.domain.model.param.process

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ProcessDetailParameter (
    @Json(name = "process_id") var processId: Int,
    @Json(name = "goal_create_date") var goalCreateDate: String
)
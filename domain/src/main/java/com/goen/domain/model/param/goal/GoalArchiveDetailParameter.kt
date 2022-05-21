package com.goen.domain.model.param.goal

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoalArchiveDetailParameter(
    @Json(name = "archive_id") var archiveId: Int,
    @Json(name = "archive_create_date") var archiveCreateDate: String
)
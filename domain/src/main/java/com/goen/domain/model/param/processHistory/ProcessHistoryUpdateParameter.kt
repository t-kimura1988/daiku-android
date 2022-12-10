package com.goen.domain.model.param.processHistory

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ProcessHistoryUpdateParameter (
    @Json(name = "process_id") var processId: Int,
    @Json(name = "priority") var priority: Int,
    @Json(name = "process_status") var processStatus: Int,
    @Json(name = "comment") var comment: String
)
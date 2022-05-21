package com.goen.domain.model.param.processHistory

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProcessHistoryUpdateStatusParameter (
    @Json(name = "process_id") var processId: Int,
    @Json(name = "priority") var priority: Int,
    @Json(name = "process_status") var processStatus: Int
)
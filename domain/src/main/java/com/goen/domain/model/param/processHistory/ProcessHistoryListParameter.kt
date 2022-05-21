package com.goen.domain.model.param.processHistory

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProcessHistoryListParameter (
    @Json(name = "process_id") var processId: Int
)
package com.goen.domain.model.param.processHistory

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ProcessHistoryUpdateCommentParameter (
    @Json(name = "process_history_id") var processHistoryId: Int,
    @Json(name = "comment") var comment: String
)
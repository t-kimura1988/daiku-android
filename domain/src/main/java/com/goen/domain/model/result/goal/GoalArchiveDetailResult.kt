package com.goen.domain.model.result.goal

import com.goen.domain.model.result.process.ProcessResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoalArchiveDetailResult(
    @Json(name = "goal_archive_info")
    var goalArchiveInfo: GoalArchiveSearchResult = GoalArchiveSearchResult(),
    @Json(name = "goal_info")
    var goalInfo: GoalDetailResult = GoalDetailResult(),
    @Json(name = "process_info")
    var processInfo: List<ProcessResult>? = null,
) {
}

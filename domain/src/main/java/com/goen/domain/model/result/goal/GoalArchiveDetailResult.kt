package com.goen.domain.model.result.goal

import com.goen.domain.model.param.goal.GoalDetailResult
import com.goen.domain.model.result.process.ProcessResult
import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

data class GoalArchiveDetailResult(
    var goalArchiveInfo: GoalArchiveSearchResult = GoalArchiveSearchResult(),
    var goalInfo: GoalDetailResult = GoalDetailResult(),
    @SerializedName("process_info")
    var processInfo: List<ProcessResult>? = null,
) {
}

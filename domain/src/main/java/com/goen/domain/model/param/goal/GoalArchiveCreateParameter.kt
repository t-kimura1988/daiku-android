package com.goen.domain.model.param.goal

import org.threeten.bp.LocalDate

data class GoalArchiveCreateParameter (
    var archiveId: Int? = null,
    var archiveCreateDate: String? = null,
    var goalId: Int? = null,
    var createDate: String? = null,
    var thoughts: String,
    var publish: Int,
)
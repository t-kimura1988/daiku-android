package com.goen.domain.model.param.goal

import org.threeten.bp.LocalDate

data class GoalCreateParameter (
    var goalId: Int? = null,
    var createDate: String? = null,
    var title: String,
    var purpose: String,
    var aim: String,
    var dueDate: String
)
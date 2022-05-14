package com.goen.domain.model.param.process

import java.util.*


data class ProcessCreateParameter (
    var processId: Int? = null,
    var goalCreateDate: String? = null,
    var goalId: Int,
    var title: String,
    var body: String,
    var priority: Int,
    var processStatus: Int
)
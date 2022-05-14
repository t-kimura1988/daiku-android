package com.goen.domain.model.param.processHistory

import java.util.*


data class ProcessHistoryUpdateParameter (
    var processId: Int,
    var priority: Int,
    var processStatus: Int,
    var comment: String
)
package com.goen.domain.model.param.processHistory


data class ProcessHistoryUpdateStatusParameter (
    var processId: Int,
    var priority: Int,
    var processStatus: Int
)
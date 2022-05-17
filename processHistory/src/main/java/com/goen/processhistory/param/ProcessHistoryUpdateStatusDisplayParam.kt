package com.goen.processhistory.param

data class ProcessHistoryUpdateStatusDisplayParam(
    var processId: Int,
    var status: Int,
    var priority: Int
)

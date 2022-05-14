package com.goen.domain.model.param.processHistory

import java.util.*


data class ProcessHistoryUpdateCommentParameter (
    var processHistoryId: Int,
    var comment: String
)
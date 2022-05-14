package com.goen.domain.datasource

import com.goen.domain.model.param.process.ProcessCreateParameter
import com.goen.domain.model.param.process.ProcessDetailParameter
import com.goen.domain.model.param.process.ProcessListParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryDetailParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryListParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateCommentParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateParameter
import com.goen.domain.model.result.process.ProcessHistoryResult
import com.goen.domain.model.result.process.ProcessResult

interface ProcessHistoryDatasource {
    suspend fun processHistoryList(parameter: ProcessHistoryListParameter): List<ProcessHistoryResult>
    suspend fun processHistoryDetail(parameter: ProcessHistoryDetailParameter): ProcessHistoryResult
    suspend fun processHistoryCreate(parameter: ProcessHistoryUpdateParameter): Unit
    suspend fun processHistoryUpdateComment(parameter: ProcessHistoryUpdateCommentParameter): Unit
}
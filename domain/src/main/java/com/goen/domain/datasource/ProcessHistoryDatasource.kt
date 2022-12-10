package com.goen.domain.datasource

import com.goen.domain.model.param.processHistory.*
import com.goen.domain.model.result.process.ProcessHistoryResult

interface ProcessHistoryDatasource {
    suspend fun processHistoryList(parameter: ProcessHistoryListParameter): List<ProcessHistoryResult>
    suspend fun processHistoryDetail(parameter: ProcessHistoryDetailParameter): ProcessHistoryResult
    suspend fun processHistoryCreate(parameter: ProcessHistoryUpdateParameter): Unit
    suspend fun processHistoryUpdateComment(parameter: ProcessHistoryUpdateCommentParameter): Unit
    suspend fun processHistoryUpdateStatus(parameter: ProcessHistoryUpdateStatusParameter): Unit
}
package com.goen.domain.repository

import com.goen.domain.model.param.process.ProcessCreateParameter
import com.goen.domain.model.param.process.ProcessDetailParameter
import com.goen.domain.model.param.process.ProcessListParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryDetailParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryListParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateCommentParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateParameter
import com.goen.domain.model.result.process.ProcessHistoryResult
import com.goen.domain.model.result.process.ProcessResult
import com.goen.utils.exception.ApiException
import kotlinx.coroutines.flow.Flow

interface ProcessHistoryRepository {
    fun getProcessHistoryList(
        param: ProcessHistoryListParameter,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<ProcessHistoryResult>>

    fun getDetailProcessHistory(
        param: ProcessHistoryDetailParameter,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<ProcessHistoryResult>

    fun createHistory(
        param: ProcessHistoryUpdateParameter,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<Unit>


    fun updateComment(
        param: ProcessHistoryUpdateCommentParameter,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<Unit>

}
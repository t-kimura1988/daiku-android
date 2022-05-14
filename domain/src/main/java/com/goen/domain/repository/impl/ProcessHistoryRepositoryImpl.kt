package com.goen.domain.repository.impl

import com.goen.domain.datasource.ProcessDatasource
import com.goen.domain.datasource.ProcessHistoryDatasource
import com.goen.domain.model.param.process.ProcessCreateParameter
import com.goen.domain.model.param.process.ProcessDetailParameter
import com.goen.domain.model.param.process.ProcessListParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryDetailParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryListParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateCommentParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateParameter
import com.goen.domain.model.result.process.ProcessHistoryResult
import com.goen.domain.model.result.process.ProcessResult
import com.goen.domain.repository.ProcessHistoryRepository
import com.goen.domain.repository.ProcessRepository
import com.goen.utils.exception.ApiException
import com.goen.utils.extentions.setEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProcessHistoryRepositoryImpl @Inject constructor(
    private val datasource: ProcessHistoryDatasource
): ProcessHistoryRepository {

    override fun getProcessHistoryList(
        param: ProcessHistoryListParameter,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<ProcessHistoryResult>> {
        return flow {
            emit(datasource.processHistoryList(parameter = param))
        }.setEvent(onStart, onError, onComplete).flowOn(Dispatchers.IO)
    }

    override fun getDetailProcessHistory(
        param: ProcessHistoryDetailParameter,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<ProcessHistoryResult> {
        return flow {
            emit(datasource.processHistoryDetail(parameter = param))
        }.setEvent(onStart, onError, onComplete).flowOn(Dispatchers.IO)
    }

    override fun createHistory(
        param: ProcessHistoryUpdateParameter,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Unit> {
        return flow {
            emit(datasource.processHistoryCreate(parameter = param))
        }.setEvent(onStart, onError, onComplete).flowOn(Dispatchers.IO)
    }

    override fun updateComment(
        param: ProcessHistoryUpdateCommentParameter,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Unit> {
        return flow {
            emit(datasource.processHistoryUpdateComment(parameter = param))
        }.setEvent(onStart, onError, onComplete).flowOn(Dispatchers.IO)
    }


}
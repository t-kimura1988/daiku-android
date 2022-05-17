package com.goen.domain.repository.impl

import com.goen.domain.datasource.ProcessHistoryDatasource
import com.goen.domain.model.param.processHistory.*
import com.goen.domain.model.result.process.ProcessHistoryResult
import com.goen.domain.repository.ProcessHistoryRepository
import com.goen.utils.exception.ApiException
import com.goen.utils.extentions.setEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    override fun updateStatus(
        param: ProcessHistoryUpdateStatusParameter,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Unit> {
        return flow {
            emit(datasource.processHistoryUpdateStatus(parameter = param))
        }.setEvent(onStart, onError, onComplete).flowOn(Dispatchers.IO)
    }


}
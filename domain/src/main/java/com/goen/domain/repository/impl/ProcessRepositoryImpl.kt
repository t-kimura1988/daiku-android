package com.goen.domain.repository.impl

import com.goen.domain.datasource.ProcessDatasource
import com.goen.domain.model.param.process.ProcessCreateParameter
import com.goen.domain.model.param.process.ProcessDetailParameter
import com.goen.domain.model.param.process.ProcessListParameter
import com.goen.domain.model.result.process.ProcessResult
import com.goen.domain.repository.ProcessRepository
import com.goen.utils.exception.ApiException
import com.goen.utils.extentions.setEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProcessRepositoryImpl @Inject constructor(
    private val processDatasource: ProcessDatasource
): ProcessRepository {

    override fun getProcessList(
        param: ProcessListParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<ProcessResult>> {
        return flow {
            emit(processDatasource.processList(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun createProcess(
        param: ProcessCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Unit> {
        return flow {
            emit(processDatasource.processCreate(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun updateProcess(
        param: ProcessCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Unit> {
        return flow {
            emit(processDatasource.processUpdate(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun getProcessDetail(
        param: ProcessDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<ProcessResult> {
        return flow {
            emit(processDatasource.processDetail(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

}
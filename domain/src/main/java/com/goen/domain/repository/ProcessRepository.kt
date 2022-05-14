package com.goen.domain.repository

import com.goen.domain.model.param.process.ProcessCreateParameter
import com.goen.domain.model.param.process.ProcessDetailParameter
import com.goen.domain.model.param.process.ProcessListParameter
import com.goen.domain.model.result.process.ProcessResult
import com.goen.utils.exception.ApiException
import kotlinx.coroutines.flow.Flow

interface ProcessRepository {
    fun getProcessList(
        param: ProcessListParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<ProcessResult>>

    fun createProcess(
        param: ProcessCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<Unit>

    fun updateProcess(
        param: ProcessCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<Unit>

    fun getProcessDetail(
        param: ProcessDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<ProcessResult>
}
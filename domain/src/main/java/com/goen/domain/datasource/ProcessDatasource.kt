package com.goen.domain.datasource

import com.goen.domain.model.param.process.ProcessCreateParameter
import com.goen.domain.model.param.process.ProcessDetailParameter
import com.goen.domain.model.param.process.ProcessListParameter
import com.goen.domain.model.result.process.ProcessResult

interface ProcessDatasource {
    suspend fun processList(parameter: ProcessListParameter): List<ProcessResult>
    suspend fun processCreate(parameter: ProcessCreateParameter): Unit
    suspend fun processUpdate(parameter: ProcessCreateParameter): Unit
    suspend fun processDetail(parameter: ProcessDetailParameter): ProcessResult
}
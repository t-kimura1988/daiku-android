package com.goen.domain.datasource.impl

import android.util.Log
import com.goen.domain.datasource.ProcessDatasource
import com.goen.domain.entity.ErrorResponse
import com.goen.domain.model.param.process.ProcessCreateParameter
import com.goen.domain.model.param.process.ProcessDetailParameter
import com.goen.domain.model.param.process.ProcessListParameter
import com.goen.domain.model.result.process.ProcessResult
import com.goen.domain.service.ProcessService
import com.goen.utils.exception.ApiException
import com.google.gson.Gson
import javax.inject.Inject

class ProcessDatasourceImpl @Inject constructor(
    private val service: ProcessService
): ProcessDatasource {

    override suspend fun processList(parameter: ProcessListParameter): List<ProcessResult> {
        var result = service.list(goalId = parameter.goalId, createDate = parameter.createDate)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.toString(), ErrorResponse::class.java)

        throw ApiException(result.code(), "apiERROR", errRes.errorCd)
    }

    override suspend fun processCreate(parameter: ProcessCreateParameter) {
        var result = service.create(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.toString(), ErrorResponse::class.java)
        throw ApiException(result.code(), "apiERROR", errRes.errorCd)
    }

    override suspend fun processUpdate(parameter: ProcessCreateParameter) {
        var result = service.update(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.toString(), ErrorResponse::class.java)
        throw ApiException(result.code(), "apiERROR", errRes.errorCd)
    }

    override suspend fun processDetail(parameter: ProcessDetailParameter): ProcessResult {
        var result = service.processDetail(processId = parameter.processId)
        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "Processの作成成功")
            return result.body()!!
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.toString(), ErrorResponse::class.java)
        throw ApiException(result.code(), "process detail exception", errRes.errorCd)

    }
}
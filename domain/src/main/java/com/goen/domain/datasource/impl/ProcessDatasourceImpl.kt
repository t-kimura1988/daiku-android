package com.goen.domain.datasource.impl

import android.util.Log
import com.goen.domain.datasource.ProcessDatasource
import com.goen.domain.model.entity.ErrorResponse
import com.goen.domain.model.param.process.ProcessCreateParameter
import com.goen.domain.model.param.process.ProcessDetailParameter
import com.goen.domain.model.param.process.ProcessListParameter
import com.goen.domain.model.result.process.ProcessResult
import com.goen.domain.service.ProcessService
import com.goen.utils.exception.ApiException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class ProcessDatasourceImpl @Inject constructor(
    private val service: ProcessService
): ProcessDatasource {
    var moshi: Moshi = Moshi.Builder().build()

    override suspend fun processList(parameter: ProcessListParameter): List<ProcessResult> {
        var result = service.list(goalId = parameter.goalId, createDate = parameter.createDate)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())
        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun processCreate(parameter: ProcessCreateParameter) {
        var result = service.create(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())
        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun processUpdate(parameter: ProcessCreateParameter) {
        var result = service.update(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())
        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun processDetail(parameter: ProcessDetailParameter): ProcessResult {
        var result = service.processDetail(processId = parameter.processId)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())
        throw ApiException(result.code(), "process detail exception", errRes!!.errorCd)

    }
}
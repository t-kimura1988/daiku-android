package com.goen.domain.datasource.impl

import com.goen.domain.datasource.ProcessHistoryDatasource
import com.goen.domain.model.entity.ErrorResponse
import com.goen.domain.model.param.processHistory.*
import com.goen.domain.model.result.process.ProcessHistoryResult
import com.goen.domain.service.ProcessHistoryService
import com.goen.utils.exception.ApiException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class ProcessHistoryDatasourceImpl @Inject constructor(
    private val service: ProcessHistoryService
): ProcessHistoryDatasource {
    var moshi: Moshi = Moshi.Builder().build()

    override suspend fun processHistoryList(parameter: ProcessHistoryListParameter): List<ProcessHistoryResult> {
        var result = service.list(processId = parameter.processId)
        if(result.isSuccessful) {
            return result.body()!!
        }

        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())
        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun processHistoryDetail(parameter: ProcessHistoryDetailParameter): ProcessHistoryResult {
        var result = service.detail(processId = parameter.processHistoryId, goalCreateDate = parameter.goalCreatedDate)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun processHistoryCreate(parameter: ProcessHistoryUpdateParameter) {
        var result = service.create(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())
        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun processHistoryUpdateComment(parameter: ProcessHistoryUpdateCommentParameter) {
        var result = service.updateComment(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())
        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun processHistoryUpdateStatus(parameter: ProcessHistoryUpdateStatusParameter) {
        var result = service.updateStatus(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())
        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }
}
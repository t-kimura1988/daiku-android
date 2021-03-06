package com.goen.domain.datasource.impl

import android.util.Log
import com.goen.domain.datasource.ProcessHistoryDatasource
import com.goen.domain.entity.ErrorResponse
import com.goen.domain.model.param.processHistory.ProcessHistoryDetailParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryListParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateCommentParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateParameter
import com.goen.domain.model.result.process.ProcessHistoryResult
import com.goen.domain.service.ProcessHistoryService
import com.goen.utils.exception.ApiException
import com.google.gson.Gson
import javax.inject.Inject

class ProcessHistoryDatasourceImpl @Inject constructor(
    private val service: ProcessHistoryService
): ProcessHistoryDatasource {

    override suspend fun processHistoryList(parameter: ProcessHistoryListParameter): List<ProcessHistoryResult> {
        var result = service.list(processId = parameter.processId)
        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "Process Historyの取得に成功")
            return result.body()!!
        }

        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.toString(), ErrorResponse::class.java)
        throw ApiException(result.code(), "apiERROR", errRes.errorCd)
    }

    override suspend fun processHistoryDetail(parameter: ProcessHistoryDetailParameter): ProcessHistoryResult {
        var result = service.detail(processId = parameter.processHistoryId, goalCreateDate = parameter.goalCreatedDate)
        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "Process Historyの取得に成功")
            return result.body()!!
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.toString(), ErrorResponse::class.java)

        throw ApiException(result.code(), "apiERROR", errRes.errorCd)
    }

    override suspend fun processHistoryCreate(parameter: ProcessHistoryUpdateParameter) {
        Log.println(Log.INFO, "update", "update process-history service!!!")
        var result = service.create(parameter = parameter)
        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "ProcessHistoryの更新成功")
            return result.body()!!
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.toString(), ErrorResponse::class.java)
        throw ApiException(result.code(), "apiERROR", errRes.errorCd)
    }

    override suspend fun processHistoryUpdateComment(parameter: ProcessHistoryUpdateCommentParameter) {
        Log.println(Log.INFO, "update", "update process-history service!!!")
        var result = service.updateComment(parameter = parameter)
        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "Process comment update成功")
            return result.body()!!
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.toString(), ErrorResponse::class.java)
        throw ApiException(result.code(), "apiERROR", errRes.errorCd)
    }
}
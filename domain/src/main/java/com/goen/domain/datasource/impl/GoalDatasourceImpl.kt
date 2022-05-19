package com.goen.domain.datasource.impl

import android.util.Log
import com.goen.domain.datasource.GoalDatasource
import com.goen.domain.entity.ErrorResponse
import com.goen.domain.model.param.goal.*
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.goal.GoalArchiveDetailResult
import com.goen.domain.model.result.goal.GoalArchiveSearchResult
import com.goen.domain.model.result.goal.GoalDetailResult
import com.goen.domain.service.GoalService
import com.goen.utils.exception.ApiException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class GoalDatasourceImpl @Inject constructor(
    private val service: GoalService
): GoalDatasource {
    var moshi: Moshi = Moshi.Builder().build()
    override suspend fun createGoal(parameter: GoalCreateParameter) {
        var result = service.createGoal(parameter)

        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "目標作成に成功")
            return
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun updateGoal(parameter: GoalCreateParameter) {
        var result = service.updateGoal(parameter)

        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "目標作成に成功")
            return
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun searchGoal(parameter: GoalSearchParameter): List<GoalSearchResult> {
        var result = service.searchGoal(year = parameter.year, pageCount = parameter.pageCount)
        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "目標の取得に成功")
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun searchGoalArchive(parameter: GoalArchiveSearchParameter): List<GoalArchiveSearchResult> {
        var result = service.searchGoalArchive(year = parameter.year, pageCount = parameter.pageCount)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun getGoalDetail(parameter: GoalDetailParameter): GoalDetailResult {
        var result = service.getGoalDetail(goalId = parameter.goalId, createDate = parameter.createDate)
        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "目標の詳細情報取得に成功")
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun createGoalArchive(param: GoalArchiveCreateParameter) {
        var result = service.createGoalArchive(param)

        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "達成作成に成功")
            return
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun updateGoalArchive(param: GoalArchiveCreateParameter) {
        var result = service.updateGoalArchive(param)

        if(result.isSuccessful) {
            Log.println(Log.INFO, "success", "達成更新に成功")
            return
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun getGoalArchiveDetail(parameter: GoalArchiveDetailParameter): GoalArchiveDetailResult {
        var result = service.getGoalArchiveDetail(archiveId = parameter.archiveId, createDate = parameter.archiveCreateDate)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun getArchiveUpdateDisp(parameter: GoalArchiveDetailParameter): GoalArchiveSearchResult {
        var result = service.getArchiveUpdateDisp(archiveId = parameter.archiveId, createDate = parameter.archiveCreateDate)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun updatingFlg(parameter: GoalDetailParameter): GoalDetailResult {
        var result = service.updatingFlg(parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }
}
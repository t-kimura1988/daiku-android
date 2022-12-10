package com.goen.domain.datasource.impl

import com.goen.domain.datasource.MakiDatasource
import com.goen.domain.model.entity.ErrorResponse
import com.goen.domain.model.param.maki.*
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.maki.MakiGoalRelationResult
import com.goen.domain.model.result.maki.MakiSearchResult
import com.goen.domain.service.MakiService
import com.goen.utils.exception.ApiException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class MakiDatasourceImpl @Inject constructor(
    private val service: MakiService
): MakiDatasource {
    var moshi: Moshi = Moshi.Builder().build()
    override suspend fun myMakiList(parameter: MyMakiListSearchParameter): List<MakiSearchResult> {
        var result = service.searchMaki(page = parameter.page)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun makiDetail(parameter: MakiDetailParameter): MakiSearchResult {
        var result = service.detail(makiId = parameter.makiId)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun makiGoal(parameter: MakiGoalListParameter): List<GoalSearchResult> {
        var result = service.makiGoal(makiId = parameter.makiId, page = parameter.page)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun makiAddGoalList(parameter: MakiAddGoalListParameter): List<GoalSearchResult> {
        var result = service.makiAddGoalList(makiId = parameter.makiId)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }


    override suspend fun createMaki(parameter: MakiCreateParameter): MakiSearchResult {
        var result = service.createMaki(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun createMakiGoalRelation(parameter: List<MakiGoalRelationCreateParameter>): List<MakiGoalRelationResult> {
        var result = service.createMakiGoalRelation(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }
}
package com.goen.domain.datasource.impl

import com.goen.domain.datasource.IdeaDatasource
import com.goen.domain.model.entity.ErrorResponse
import com.goen.domain.model.param.idea.IdeaCreateParameter
import com.goen.domain.model.param.idea.IdeaUpdateParameter
import com.goen.domain.model.param.idea.MyIdeaDetailParameter
import com.goen.domain.model.param.idea.MyIdeaListParameter
import com.goen.domain.model.result.idea.IdeaSearchResult
import com.goen.domain.service.IdeaService
import com.goen.utils.exception.ApiException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class IdeaDatasourceImpl @Inject constructor(
    private val service: IdeaService
): IdeaDatasource {
    var moshi: Moshi = Moshi.Builder().build()

    override suspend fun myIdeaList(parameter: MyIdeaListParameter): List<IdeaSearchResult> {
        var result = service.myIdeaList(page = parameter.page)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun myIdeaDetail(parameter: MyIdeaDetailParameter): IdeaSearchResult {
        var result = service.myIdeaDetail(ideaId = parameter.ideaId)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun createIdea(parameter: IdeaCreateParameter): IdeaSearchResult {
        var result = service.createIdea(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }

    override suspend fun updateIdea(parameter: IdeaUpdateParameter): IdeaSearchResult {
        var result = service.updateIdea(parameter = parameter)
        if(result.isSuccessful) {
            return result.body()!!
        }
        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw ApiException(result.code(), "apiERROR", errRes!!.errorCd)
    }
}
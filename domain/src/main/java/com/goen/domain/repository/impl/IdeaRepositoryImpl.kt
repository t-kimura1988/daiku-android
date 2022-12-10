package com.goen.domain.repository.impl

import com.goen.domain.datasource.IdeaDatasource
import com.goen.domain.model.param.idea.IdeaCreateParameter
import com.goen.domain.model.param.idea.IdeaUpdateParameter
import com.goen.domain.model.param.idea.MyIdeaDetailParameter
import com.goen.domain.model.param.idea.MyIdeaListParameter
import com.goen.domain.model.result.idea.IdeaSearchResult
import com.goen.domain.repository.IdeaRepository
import com.goen.utils.exception.ApiException
import com.goen.utils.extentions.setEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IdeaRepositoryImpl @Inject constructor(
    private val ideaDatasource: IdeaDatasource
): IdeaRepository {

    override fun getMyIdeaList(
        param: MyIdeaListParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<IdeaSearchResult>> {
        return flow {
            emit(ideaDatasource.myIdeaList(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun getIdeaDetail(
        param: MyIdeaDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<IdeaSearchResult> {
        return flow {
            emit(ideaDatasource.myIdeaDetail(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun createIdea(
        param: IdeaCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<IdeaSearchResult> {
        return flow {
            emit(ideaDatasource.createIdea(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun updateIdea(
        param: IdeaUpdateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<IdeaSearchResult> {
        return flow {
            emit(ideaDatasource.updateIdea(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

}
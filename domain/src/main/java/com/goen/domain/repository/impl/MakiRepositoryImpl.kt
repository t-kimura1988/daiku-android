package com.goen.domain.repository.impl

import com.goen.domain.datasource.MakiDatasource
import com.goen.domain.model.param.maki.*
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.maki.MakiGoalRelationResult
import com.goen.domain.model.result.maki.MakiSearchResult
import com.goen.domain.repository.MakiRepository
import com.goen.utils.exception.ApiException
import com.goen.utils.extentions.setEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MakiRepositoryImpl @Inject constructor(
    private val makiDatasource: MakiDatasource
): MakiRepository {

    override fun myMakiList(
        param: MyMakiListSearchParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<MakiSearchResult>> {
        return flow {
            emit(makiDatasource.myMakiList(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun makiDetail(
        param: MakiDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<MakiSearchResult> {
        return flow {
            emit(makiDatasource.makiDetail(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun makiGoal(
        param: MakiGoalListParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<GoalSearchResult>> {
        return flow {
            emit(makiDatasource.makiGoal(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun makiAddGoalList(
        param: MakiAddGoalListParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<GoalSearchResult>> {
        return flow {
            emit(makiDatasource.makiAddGoalList(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun createMakiGoalRelation(
        param: List<MakiGoalRelationCreateParameter>,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<MakiGoalRelationResult>> {
        return flow {
            emit(makiDatasource.createMakiGoalRelation(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun createMaki(
        param: MakiCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<MakiSearchResult> {
        return flow {
            emit(makiDatasource.createMaki(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

}
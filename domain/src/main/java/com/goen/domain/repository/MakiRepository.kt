package com.goen.domain.repository

import com.goen.domain.model.param.maki.*
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.maki.MakiGoalRelationResult
import com.goen.domain.model.result.maki.MakiSearchResult
import com.goen.utils.exception.ApiException
import kotlinx.coroutines.flow.Flow

interface MakiRepository {

    fun createMaki(
        param: MakiCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ): Flow<MakiSearchResult>

    fun myMakiList(
        param: MyMakiListSearchParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<MakiSearchResult>>

    fun makiDetail(
        param: MakiDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<MakiSearchResult>

    fun makiGoal(
        param: MakiGoalListParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<GoalSearchResult>>

    fun makiAddGoalList(
        param: MakiAddGoalListParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<GoalSearchResult>>

    fun createMakiGoalRelation(
        param: List<MakiGoalRelationCreateParameter>,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<MakiGoalRelationResult>>
}
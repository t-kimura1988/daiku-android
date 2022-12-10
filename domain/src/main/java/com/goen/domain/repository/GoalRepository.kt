package com.goen.domain.repository

import com.goen.domain.model.param.goal.*
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.goal.GoalArchiveDetailResult
import com.goen.domain.model.result.goal.GoalArchiveSearchResult
import com.goen.domain.model.result.goal.GoalDetailResult
import com.goen.utils.exception.ApiException
import kotlinx.coroutines.flow.Flow

interface GoalRepository {

    fun createGoal(
        param: GoalCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ): Flow<Unit>

    fun updateGoal(
        param: GoalCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ): Flow<Unit>

    fun getGoal(
        param: GoalSearchParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<GoalSearchResult>>

    fun getGoalArchiveList(
        param: GoalArchiveSearchParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<GoalArchiveSearchResult>>

    fun getMyGoalArchiveList(
        param: MyGoalArchiveSearchParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<GoalArchiveSearchResult>>

    fun getGoalDetail(
        param: GoalDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<GoalDetailResult>

    fun createGoalArchive(
        param: GoalArchiveCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ):Flow<Unit>

    fun updateGoalArchive(
        param: GoalArchiveCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ):Flow<Unit>

    fun getMyGoalArchiveDetail(
        param: GoalArchiveDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<GoalArchiveDetailResult>

    fun getGoalArchiveDetail(
        param: GoalArchiveDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<GoalArchiveDetailResult>

    fun getArchiveUpdateDisp(
        param: GoalArchiveDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<GoalArchiveSearchResult>

    fun updatingFlg(
        param: GoalDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<GoalDetailResult>
}
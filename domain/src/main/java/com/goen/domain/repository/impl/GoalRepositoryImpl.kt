package com.goen.domain.repository.impl

import com.goen.domain.datasource.GoalDatasource
import com.goen.domain.model.param.goal.*
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.goal.GoalArchiveDetailResult
import com.goen.domain.model.result.goal.GoalArchiveSearchResult
import com.goen.domain.model.result.goal.GoalDetailResult
import com.goen.domain.repository.GoalRepository
import com.goen.utils.exception.ApiException
import com.goen.utils.extentions.setEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GoalRepositoryImpl @Inject constructor(
    private val goalDatasource: GoalDatasource
): GoalRepository {
    override fun createGoal(
        param: GoalCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Unit> {
        return flow {
            try {
                emit(goalDatasource.createGoal(param))
            } catch (e: Exception) {
                Timber.e("create goal error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun updateGoal(
        param: GoalCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Unit> {
        return flow {
            try {
                emit(goalDatasource.updateGoal(param))
            } catch (e: Exception) {
                Timber.e("update goal error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun getGoal(
        param: GoalSearchParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<GoalSearchResult>> {
        return flow {
            emit(goalDatasource.searchGoal(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun getGoalArchiveList(
        param: GoalArchiveSearchParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<GoalArchiveSearchResult>> {
        return flow {
            emit(goalDatasource.searchGoalArchive(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun getMyGoalArchiveList(
        param: MyGoalArchiveSearchParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<List<GoalArchiveSearchResult>> {
        return flow {
            emit(goalDatasource.searchMyGoalArchive(parameter = param))
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun getGoalDetail(
        param: GoalDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<GoalDetailResult> {
        return flow {
            emit(goalDatasource.getGoalDetail(parameter = param))
        }
    }

    override fun createGoalArchive(
        param: GoalArchiveCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Unit> {
        return flow {
            try {
                emit(goalDatasource.createGoalArchive(param))
            } catch (e: Exception) {
                Timber.e("create goal archive error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun updateGoalArchive(
        param: GoalArchiveCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Unit> {
        return flow {
            try {
                emit(goalDatasource.updateGoalArchive(param))
            } catch (e: Exception) {
                Timber.e("update goal archive error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun getMyGoalArchiveDetail(
        param: GoalArchiveDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<GoalArchiveDetailResult> {
        return flow {
            try {
                emit(goalDatasource.getMyGoalArchiveDetail(param))
            } catch (e: Exception) {
                Timber.e("get goal archive detail error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun getGoalArchiveDetail(
        param: GoalArchiveDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<GoalArchiveDetailResult> {
        return flow {
            try {
                emit(goalDatasource.getGoalArchiveDetail(param))
            } catch (e: Exception) {
                Timber.e("get goal archive detail error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun getArchiveUpdateDisp(
        param: GoalArchiveDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<GoalArchiveSearchResult> {
        return flow {
            try {
                emit(goalDatasource.getArchiveUpdateDisp(param))
            } catch (e: Exception) {
                Timber.e("get archive update display error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun updatingFlg(
        param: GoalDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<GoalDetailResult> {
        return flow {
            try {
                emit(goalDatasource.updatingFlg(param))
            } catch (e: Exception) {
                Timber.e("updating flg error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

}
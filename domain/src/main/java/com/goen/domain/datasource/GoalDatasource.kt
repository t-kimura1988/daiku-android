package com.goen.domain.datasource

import com.goen.domain.model.param.goal.*
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.goal.GoalArchiveDetailResult
import com.goen.domain.model.result.goal.GoalArchiveSearchResult
import com.goen.domain.model.result.goal.GoalDetailResult

interface GoalDatasource {
    suspend fun createGoal(parameter: GoalCreateParameter)
    suspend fun updateGoal(parameter: GoalCreateParameter)
    suspend fun searchGoal(parameter: GoalSearchParameter): List<GoalSearchResult>
    suspend fun searchGoalArchive(parameter: GoalArchiveSearchParameter): List<GoalArchiveSearchResult>
    suspend fun searchMyGoalArchive(parameter: MyGoalArchiveSearchParameter): List<GoalArchiveSearchResult>
    suspend fun getGoalDetail(parameter: GoalDetailParameter): GoalDetailResult
    suspend fun createGoalArchive(param: GoalArchiveCreateParameter)
    suspend fun updateGoalArchive(param: GoalArchiveCreateParameter)
    suspend fun getMyGoalArchiveDetail(parameter: GoalArchiveDetailParameter): GoalArchiveDetailResult
    suspend fun getGoalArchiveDetail(parameter: GoalArchiveDetailParameter): GoalArchiveDetailResult
    suspend fun getArchiveUpdateDisp(parameter: GoalArchiveDetailParameter): GoalArchiveSearchResult
    suspend fun updatingFlg(parameter: GoalDetailParameter): GoalDetailResult
}
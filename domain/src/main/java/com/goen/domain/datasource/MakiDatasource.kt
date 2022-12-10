package com.goen.domain.datasource

import com.goen.domain.model.param.maki.*
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.maki.MakiGoalRelationResult
import com.goen.domain.model.result.maki.MakiSearchResult

interface MakiDatasource {
    suspend fun myMakiList(parameter: MyMakiListSearchParameter): List<MakiSearchResult>
    suspend fun makiDetail(parameter: MakiDetailParameter): MakiSearchResult
    suspend fun makiGoal(parameter: MakiGoalListParameter): List<GoalSearchResult>
    suspend fun makiAddGoalList(parameter: MakiAddGoalListParameter): List<GoalSearchResult>
    suspend fun createMaki(parameter: MakiCreateParameter): MakiSearchResult
    suspend fun createMakiGoalRelation(parameter: List<MakiGoalRelationCreateParameter>): List<MakiGoalRelationResult>
}
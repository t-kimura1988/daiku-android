package com.goen.domain.datasource

import com.goen.domain.model.param.idea.IdeaCreateParameter
import com.goen.domain.model.param.idea.IdeaUpdateParameter
import com.goen.domain.model.param.idea.MyIdeaDetailParameter
import com.goen.domain.model.param.idea.MyIdeaListParameter
import com.goen.domain.model.result.idea.IdeaSearchResult

interface IdeaDatasource {
    suspend fun myIdeaList(parameter: MyIdeaListParameter): List<IdeaSearchResult>
    suspend fun myIdeaDetail(parameter: MyIdeaDetailParameter): IdeaSearchResult
    suspend fun createIdea(parameter: IdeaCreateParameter): IdeaSearchResult
    suspend fun updateIdea(parameter: IdeaUpdateParameter): IdeaSearchResult
}
package com.goen.domain.repository

import com.goen.domain.model.param.idea.IdeaCreateParameter
import com.goen.domain.model.param.idea.IdeaUpdateParameter
import com.goen.domain.model.param.idea.MyIdeaDetailParameter
import com.goen.domain.model.param.idea.MyIdeaListParameter
import com.goen.domain.model.result.idea.IdeaSearchResult
import com.goen.utils.exception.ApiException
import kotlinx.coroutines.flow.Flow

interface IdeaRepository {

    fun getMyIdeaList(
        param: MyIdeaListParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<List<IdeaSearchResult>>

    fun getIdeaDetail(
        param: MyIdeaDetailParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<IdeaSearchResult>

    fun createIdea(
        param: IdeaCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<IdeaSearchResult>

    fun updateIdea(
        param: IdeaUpdateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<IdeaSearchResult>
}
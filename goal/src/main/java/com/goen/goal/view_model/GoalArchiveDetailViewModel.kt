package com.goen.goal.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.goal.GoalArchiveDetailParameter
import com.goen.domain.model.result.goal.GoalArchiveDetailResult
import com.goen.domain.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalArchiveDetailViewModel @Inject constructor(
    private val repository: GoalRepository
):ViewModel() {
    var goalArchiveDetail: MutableState<GoalArchiveDetailResult> = mutableStateOf(GoalArchiveDetailResult())

    var success: MutableState<Boolean> = mutableStateOf(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)

    fun callGoalArchiveDetail(archiveId: Int, archiveCreateDate: String) {
        loading.value = true
        viewModelScope.launch {
            repository.getGoalArchiveDetail(
                param = GoalArchiveDetailParameter(
                    archiveId = archiveId,
                    archiveCreateDate = archiveCreateDate
                ),
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect {
                goalArchiveDetail.value = goalArchiveDetail.value.copy(
                    goalArchiveInfo = it.goalArchiveInfo,
                    goalInfo = it.goalInfo,
                    processInfo = it.processInfo
                )
            }
        }
    }

    fun isGoalIdEqLoginAccount(loginId: Int): Boolean {
        return goalArchiveDetail.value.goalInfo.accountId == loginId
    }
}
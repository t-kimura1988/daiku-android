package com.goen.goal.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.goal.GoalDetailParameter
import com.goen.domain.model.param.process.ProcessListParameter
import com.goen.domain.model.result.goal.GoalDetailResult
import com.goen.domain.model.result.process.ProcessResult
import com.goen.domain.repository.GoalRepository
import com.goen.domain.repository.ProcessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalDetailViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val processRepository: ProcessRepository
): ViewModel() {
    var goalDetailResult: MutableState<GoalDetail> = mutableStateOf(GoalDetail())
    var processListResultList: MutableState<ProcessList> = mutableStateOf(ProcessList())
    var load: MutableState<Boolean> = mutableStateOf(false)
    fun getGoalDetail(goalId: Int, createDate: String) {
        viewModelScope.launch {
            goalRepository.getGoalDetail(
                param = GoalDetailParameter(goalId = goalId, createDate = createDate),
                onStart = { load.value = true },
                onComplate = {
                    load.value = false
                },
                onError = {_ ->

                })
                .collect { result: GoalDetailResult ->
                    goalDetailResult.value = goalDetailResult.value.copy(goalDetail = result)
                }
        }
    }

    fun getProcessList(goalId: Int, createDate: String) {
        viewModelScope.launch {
            processRepository.getProcessList(
                param = ProcessListParameter(goalId = goalId, createDate = createDate),
                onStart = {},
                onComplate = { },
                onError = {_ ->
                })
                .collect { result: List<ProcessResult> ->
                    processListResultList.value = processListResultList.value.copy(list = result)
                }
        }
    }

    fun updatingFlg(goalId: Int, createDate: String) {
        viewModelScope.launch {
            goalRepository.updatingFlg(
                param = GoalDetailParameter(goalId = goalId, createDate = createDate),
                onStart = { load.value = true },
                onComplate = {
                    load.value = false
                },
                onError = {_ ->
                })
                .collect { result: GoalDetailResult ->
                    goalDetailResult.value = goalDetailResult.value.copy(goalDetail = result)
                }
        }
    }
}

data class GoalDetail(
    var goalDetail: GoalDetailResult = GoalDetailResult()
)

data class ProcessList(
    var list: List<ProcessResult> = arrayListOf(ProcessResult())
)
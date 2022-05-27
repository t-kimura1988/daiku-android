package com.goen.process.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.process.ProcessDetailParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryListParameter
import com.goen.domain.model.result.process.ProcessHistoryResult
import com.goen.domain.model.result.process.ProcessResult
import com.goen.domain.repository.ProcessHistoryRepository
import com.goen.domain.repository.ProcessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcessDetailViewModel @Inject constructor(
    private val processRepository: ProcessRepository,
    private val processHistoryRepository: ProcessHistoryRepository
):ViewModel(){
    var processDetailResult: MutableState<ProcessDetail> = mutableStateOf(ProcessDetail())
    var processHistoryList: MutableState<ProcessHistoryList> = mutableStateOf(ProcessHistoryList())

    fun getDetail(processId: Int, goalCreateDate: String) {
        viewModelScope.launch {
            processRepository.getProcessDetail(
                param = ProcessDetailParameter(processId = processId, goalCreateDate = goalCreateDate),
                onStart = {},
                onComplate = { },
                onError = {_ ->
                })
                .collect { result: ProcessResult ->
                    processDetailResult.value = processDetailResult.value.copy(processDetail = result)
                }
        }
    }

    fun getProcessHistory(processId: Int) {
        viewModelScope.launch {
            processHistoryRepository.getProcessHistoryList(
                param = ProcessHistoryListParameter(processId = processId),
                onStart = {},
                onComplete = {},
                onError = {_ ->
                }
            ).collect{ result: List<ProcessHistoryResult> ->
                processHistoryList.value = processHistoryList.value.copy(processHistoryList = result)
            }
        }
    }
}

data class ProcessDetail(
    val processDetail: ProcessResult = ProcessResult()
)

data class ProcessHistoryList(
    val processHistoryList: List<ProcessHistoryResult> = arrayListOf(ProcessHistoryResult())

)
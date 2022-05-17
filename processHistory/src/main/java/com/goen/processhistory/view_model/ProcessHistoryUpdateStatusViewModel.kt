package com.goen.processhistory.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateStatusParameter
import com.goen.domain.repository.ProcessHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcessHistoryUpdateStatusViewModel @Inject constructor(
    private val processHistoryRepository: ProcessHistoryRepository
): ViewModel() {
    var input: ProcessHistoryUpdateStatusInput = ProcessHistoryUpdateStatusInput()
    var statusAlertFlg: MutableState<Boolean> = mutableStateOf(false)
    var priorityAlertFlg: MutableState<Boolean> = mutableStateOf(false)

    var success: MutableState<Boolean> = mutableStateOf(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)
    var errorDialog: MutableState<Boolean> = mutableStateOf(false)

    var orgStatus: Int = 0;
    var orgPriority: Int = 0;

    val statusOptions = mapOf(0 to "未着手", 1 to "対応する", 2 to "問題", 3 to "完了")

    val priorityOptions = mapOf(0 to "低", 1 to "中", 2 to "高", 3 to "優先的")

    fun initStatus(status: Int, priority: Int) {
        input.statusOptionKey.value = status
        input.priorityOptionKey.value = priority
        orgStatus = status
        orgPriority = priority
    }
    fun changeStatus(item: Int) {
        input.statusOptionKey.value = item
    }

    fun changePriority(item: Int) {
        input.priorityOptionKey.value = item
    }

    fun changeStatusAlert(item: Boolean) {
        statusAlertFlg.value = item
    }

    fun changePriorityAlert(item: Boolean) {
        priorityAlertFlg.value = item
    }

    fun changeErrorFlg(flg: Boolean) {
        errorDialog.value = flg
    }

    fun updateStatus(processId: Int) {
        viewModelScope.launch {
            processHistoryRepository.updateStatus(
                param = input.toParam(
                    processId = processId
                ),
                onComplete = {},
                onError = {
                    loading.value = false
                    errorDialog.value = true
                          },
                onStart = {loading.value = true}
            )
                .collect { it: Unit ->
                    Log.println(Log.INFO, "success", "process-history staus update success!!")
                    loading.value = false
                    success.value = true
                }
        }
    }

    fun chkEnableButton(): Boolean {
        return (input.statusOptionKey.value != orgStatus) ||
                (input.priorityOptionKey.value != orgPriority)
    }
}

data class ProcessHistoryUpdateStatusInput(
    var statusOptionKey: MutableState<Int> = mutableStateOf(0),
    var priorityOptionKey: MutableState<Int> = mutableStateOf(0))
{

    val priority: Int get() = priorityOptionKey.value
    val status: Int get() = statusOptionKey.value

    fun toParam(processId: Int): ProcessHistoryUpdateStatusParameter {
        return ProcessHistoryUpdateStatusParameter(
            processId = processId,
            priority = priority,
            processStatus = status,
        )
    }
}
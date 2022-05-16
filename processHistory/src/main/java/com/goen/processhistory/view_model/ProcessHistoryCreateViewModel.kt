package com.goen.processhistory.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateParameter
import com.goen.domain.repository.ProcessHistoryRepository
import com.goen.utils.entity.FormObj
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcessHistoryCreateViewModel @Inject constructor(
    private val processHistoryRepository: ProcessHistoryRepository
): ViewModel() {
    var input: ProcessHistoryCreateInput = ProcessHistoryCreateInput()
    var statusAlertFlg: MutableState<Boolean> = mutableStateOf(false)
    var priorityAlertFlg: MutableState<Boolean> = mutableStateOf(false)

    var success: MutableState<Boolean> = mutableStateOf(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)
    var errorDialog: MutableState<Boolean> = mutableStateOf(false)

    val statusOptions = mapOf(0 to "未着手", 1 to "対応する", 2 to "問題", 3 to "完了")

    val priorityOptions = mapOf(0 to "低", 1 to "中", 2 to "高", 3 to "優先的")

    init {
        init()
    }

    fun changeComment(item: String) {
        input.commentM.value = input.commentM.value.copy(value = item, isError = false)
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

    fun create(processId: Int) {
        viewModelScope.launch {
            processHistoryRepository.createHistory(
                param = input.toParam(
                    processId = processId
                ),
                onComplete = {
                    Log.println(Log.INFO, "aaaaa", "bbbbbbbb4")
                             },
                onError = {
                    loading.value = false
                    errorDialog.value = true
                          },
                onStart = {loading.value = true}
            )
                .collect { it: Unit ->
                    Log.println(Log.INFO, "success", "process-history create success!!")
                    loading.value = false
                    success.value = true
                }
        }
    }

    fun chkEnableButton(): Boolean {
        return !input.commentM.value.isError!!
    }

    fun init() {
        input.commentM.value = FormObj(value = "", error = "", isError = false)
    }
}

data class ProcessHistoryCreateInput(
    var commentM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var statusOptionKey: MutableState<Int> = mutableStateOf(0),
    var priorityOptionKey: MutableState<Int> = mutableStateOf(0))
{

    val comment: String get() = commentM.value.value!!
    val priority: Int get() = priorityOptionKey.value
    val status: Int get() = statusOptionKey.value

    val commentError: String? get() = commentM.value.error

    val isCommentError: Boolean get() = commentM.value.error != "" && commentM.value.error != null

    fun toParam(processId: Int): ProcessHistoryUpdateParameter {
        return ProcessHistoryUpdateParameter(
            processId = processId,
            priority = priority,
            processStatus = status,
            comment = comment
        )
    }
}
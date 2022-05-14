package com.goen.process.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.process.ProcessCreateParameter
import com.goen.domain.model.param.process.ProcessDetailParameter
import com.goen.domain.model.result.process.ProcessResult
import com.goen.domain.repository.ProcessRepository
import com.goen.utils.entity.FormObj
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcessCreateViewModel @Inject constructor(
    private val processRepository: ProcessRepository
):ViewModel(){
    var input: ProcessCreateInput = ProcessCreateInput()
    var statusAlertFlg: MutableState<Boolean> = mutableStateOf(false)
    var priorityAlertFlg: MutableState<Boolean> = mutableStateOf(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)
    var success: MutableState<Boolean> = mutableStateOf(false)
    var failureFlg: MutableState<Boolean> = mutableStateOf(false)

    val statusOptions = mapOf(0 to "未着手", 1 to "対応する", 2 to "問題", 3 to "完了")

    val priorityOptions = mapOf(0 to "低", 1 to "中", 2 to "高", 3 to "優先的")

    fun changeTitle(item: String) {
        if(item == "") {
            input.titleM.value = input.titleM.value.copy(error = "題名をつけてください")
        } else {
            input.titleM.value = input.titleM.value.copy(error = "")

        }
        input.titleM.value = input.titleM.value.copy(value  = item)
    }

    fun changeBody(item: String) {
        if(item == "") {
            input.bodyM.value = input.bodyM.value.copy(error = "内容を入力してください")
        } else {
            input.bodyM.value = input.bodyM.value.copy(error = "")
        }
        input.bodyM.value = input.bodyM.value.copy(value = item)
    }

    fun changeStatusAlert(item: Boolean) {
        statusAlertFlg.value = item
    }

    fun changeStatus(item: Int) {
        input.statusOptionKey.value = item
    }

    fun changePriorityAlert(item: Boolean) {
        priorityAlertFlg.value = item
    }

    fun changePriority(item: Int) {
        input.priorityOptionKey.value = item
    }

    fun changeFailure(item: Boolean) {
        failureFlg.value = item
    }

    fun getProcessDetail(processId: Int, goalCreateDate: String) {
        viewModelScope.launch {
            processRepository.getProcessDetail(
                param = ProcessDetailParameter(
                    processId = processId,
                    goalCreateDate = goalCreateDate
                ),
                onStart = {},
                onError = {},
                onComplate = {},).collect { it: ProcessResult ->
                    input.titleM.value = input.titleM.value.copy(value = it.title, error = "", isError = false)
                    input.bodyM.value = input.bodyM.value.copy(value = it.body, error = "", isError = false)
                    input.statusOptionKey.value = it.processStatus.toInt()
                    input.priorityOptionKey.value = it.priority.toInt()
            }
        }
    }

    fun createProcess(goalId: Int, goalCreateDate: String) {
        viewModelScope.launch {
            processRepository.createProcess(
                param = input.parameter(goalId = goalId, goalCreateDate = goalCreateDate),
                onStart = {
                    loading.value = true},
                onError = {
                    loading.value = false
                          },
                onComplate = {
                    loading.value = false
                    success.value = true
                }
            )
            .collect { it: Unit ->
            }
        }
    }

    fun updateProcess(
        goalId: Int,
        goalCreateDate: String,
        processId: Int
    ) {
        viewModelScope.launch {
            processRepository.updateProcess(
                param = input.updateParam(
                    goalId = goalId,
                    goalCreateDate = goalCreateDate,
                    processId = processId
                ),
                onStart = {
                    loading.value = true},
                onError = {
                    loading.value = false
                },
                onComplate = {
                    loading.value = false
                    success.value = true
                }
            )
                .collect { it: Unit ->
                }

        }
    }
}

data class ProcessCreateInput (
    var titleM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var bodyM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var statusOptionKey: MutableState<Int> = mutableStateOf(0),
    var priorityOptionKey: MutableState<Int> = mutableStateOf(0)
){
    val title: String get() = titleM.value.value!!
    val body: String get() = bodyM.value.value!!
    val status: Int get() = statusOptionKey.value
    val priority: Int get() = priorityOptionKey.value!!

    val titleError: String? get() = titleM.value.error
    val bodyError: String? get() = bodyM.value.error

    val isTitleError: Boolean get() = titleM.value.error != "" && titleM.value.error != null
    val isBodyError: Boolean get() = bodyM.value.error != "" && bodyM.value.error != null

    val enableButton: Boolean get() =
        (titleM.value.error != null && bodyM.value.error != null) && (titleM.value.error == "" && bodyM.value.error == "")

    fun parameter(goalId: Int, goalCreateDate: String): ProcessCreateParameter {
        return ProcessCreateParameter(
            goalId = goalId,
            goalCreateDate = goalCreateDate,
            title = title,
            body = body,
            processStatus = status,
            priority = priority
        )
    }

    fun updateParam(
        goalId: Int,
        goalCreateDate: String,
        processId: Int
    ): ProcessCreateParameter {
        return ProcessCreateParameter(
            goalId = goalId,
            goalCreateDate = goalCreateDate,
            processId = processId,
            title = title,
            body = body,
            processStatus = status,
            priority = priority
        )
    }
}
package com.goen.processhistory.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goen.domain.model.param.processHistory.ProcessHistoryDetailParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateCommentParameter
import com.goen.domain.model.param.processHistory.ProcessHistoryUpdateParameter
import com.goen.domain.model.result.process.ProcessHistoryResult
import com.goen.domain.repository.GoalRepository
import com.goen.domain.repository.ProcessHistoryRepository
import com.goen.processhistory.param.ProcessHistoryUpdateCommentDisplayParam
import com.goen.utils.entity.FormObj
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcessHistoryUpdateCommentViewModel @Inject constructor(
    private val processHistoryRepository: ProcessHistoryRepository
): ViewModel() {
    var input: ProcessHistoryUpdateCommentInput = ProcessHistoryUpdateCommentInput()
    var processHistoryInfo: MutableState<ProcessHistoryResult> = mutableStateOf(ProcessHistoryResult())

    var success: MutableState<Boolean> = mutableStateOf(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)
    var errorDialog: MutableState<Boolean> = mutableStateOf(false)

    fun changeComment(item: String) {
        input.commentM.value = input.commentM.value.copy(item)
    }

    fun getDetail(param: ProcessHistoryUpdateCommentDisplayParam) {
        viewModelScope.launch {
            processHistoryRepository.getDetailProcessHistory(
                param = ProcessHistoryDetailParameter(
                    processHistoryId = param.processHistoryId,
                    goalCreatedDate = param.goalCreateDate
                ),
                onStart = {},
                onComplete = {},
                onError = {}
            )
                .collect { it: ProcessHistoryResult ->
                    Log.println(Log.INFO, "success", "process-history detail success!!")
                    input.commentM.value = input.commentM.value.copy(value = it.comment, error = "", isError = false)
                }
        }
    }

    fun updateComment(processHistoryId: Int) {
        loading.value = true
        viewModelScope.launch {
            processHistoryRepository.updateComment(
                param = input.toParam(processHistoryId = processHistoryId),
                onStart = {},
                onComplete = {},
                onError = {}
            )
                .collect { it: Unit ->
                    Log.println(Log.INFO, "success", "process-history update comment success!!")
                }
        }
    }

    fun chkEnableButton(): Boolean {
        return !input.commentM.value.isError!!
    }

    fun init() {
        input.commentM.value = FormObj(value = "", error = "", isError = true)
    }
}

data class ProcessHistoryUpdateCommentInput(
    var commentM: MutableState<FormObj> = mutableStateOf(FormObj())
)
{

    val comment: String get() = commentM.value.value!!

    val commentError: String? get() = commentM.value.error

    val isCommentError: Boolean get() = commentM.value.error != "" && commentM.value.error != null

    fun toParam(processHistoryId: Int): ProcessHistoryUpdateCommentParameter {
        return ProcessHistoryUpdateCommentParameter(
            processHistoryId = processHistoryId,
            comment = comment
        )
    }
}
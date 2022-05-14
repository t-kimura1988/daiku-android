package com.goen.goal.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.goal.GoalArchiveCreateParameter
import com.goen.domain.model.param.goal.GoalArchiveDetailParameter
import com.goen.domain.model.param.goal.GoalCreateParameter
import com.goen.domain.model.param.goal.GoalDetailParameter
import com.goen.domain.model.result.goal.GoalArchiveDetailResult
import com.goen.domain.model.result.goal.GoalArchiveSearchResult
import com.goen.domain.repository.GoalRepository
import com.goen.utils.entity.FormObj
import com.goen.utils.extentions.toLocalDateParse
import com.goen.utils.validate.BaseValidate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class GoalArchiveViewModel @Inject constructor(
    private val repository: GoalRepository
):ViewModel() {
    var goalArchiveDetail: MutableState<GoalArchiveSearchResult> = mutableStateOf(GoalArchiveSearchResult())

    var input: GoalArchiveInput = GoalArchiveInput()
    var publishDialogFlg: MutableState<Boolean> = mutableStateOf(false)

    var success: MutableState<Boolean> = mutableStateOf(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)
    var errorDialog: MutableState<Boolean> = mutableStateOf(false)

    val publishOptions = mapOf(0 to "自分のみ", 1 to "公開(目標のみ)", 2 to "公開(プロセスまで)")

    fun changeThoughts(item: String) {
        if(item == "") {
            input.thoughtsM.value = input.thoughtsM.value.copy(error = "内容を入力してください")
        } else {
            input.thoughtsM.value = input.thoughtsM.value.copy(error = "")
        }
        input.thoughtsM.value = input.thoughtsM.value.copy(value = item)
    }

    fun changePublishDialogAlert(item: Boolean) {
        publishDialogFlg.value = item
    }

    fun changePublishKey(item: Int) {
        input.publishOptionKey.value = item
    }

    fun createGoalArchive(goalId: Int, createDate: String) {
        loading.value = true
        viewModelScope.launch {
            repository.createGoalArchive(
                param = input.toRepository(
                    goalId = goalId,
                    createDate = createDate
                ),
                onStart = {},
                onComplate = {
                    loading.value = false },
                onError = {error ->
                    errorDialog.value = true
                }
            ).collect {
                Log.println(Log.INFO, "success", "goal archive ")
                success.value = true
            }
        }
    }

    fun updateGoalArchive(archiveId: Int, archiveCreateDate: String) {
        loading.value = true
        viewModelScope.launch {
            repository.updateGoalArchive(
                param = input.toUpdateArchiveRepository(
                    archiveId = archiveId,
                    archiveCreateDate = archiveCreateDate
                ),
                onStart = {},
                onComplate = {
                    loading.value = false },
                onError = {error ->
                    errorDialog.value = true
                }
            ).collect {
                Log.println(Log.INFO, "success", "goal archive ")
                success.value = true
            }
        }
    }

    fun loadArchive(archiveId: Int, archiveCreateDate: String) {
        viewModelScope.launch {
            repository.getArchiveUpdateDisp(
                param = GoalArchiveDetailParameter(
                    archiveId = archiveId,
                    archiveCreateDate = archiveCreateDate
                ),
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect {
                goalArchiveDetail.value = it

                input.thoughtsM.value = input.thoughtsM.value.copy(value = it.thoughts)
                input.publishOptionKey.value = it.publish.toInt()
            }
        }
    }

    fun init() {
        errorDialog.value = false
        success.value = false
        loading.value = false
    }

    fun chkEnableButton(): Boolean {
//        return !input.titleM.value.isError!! && !input.purposeM.value.isError!! && !input.aimM.value.isError!!
        return true
    }
}

data class GoalArchiveInput (
    var thoughtsM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var publishOptionKey: MutableState<Int> = mutableStateOf(0),
){
    val thoughts: String get() = thoughtsM.value.value!!
    val thoughtsError: String? get() = thoughtsM.value.error
    val isThoughtsError: Boolean get() = thoughtsM.value.error != "" && thoughtsM.value.error != null

    val publishKey: Int get() = publishOptionKey.value!!

    fun toRepository(goalId: Int, createDate: String): GoalArchiveCreateParameter {
        return GoalArchiveCreateParameter(
            goalId = goalId,
            createDate = createDate,
            thoughts = thoughts,
            publish = publishKey
        )
    }

    fun toUpdateArchiveRepository(archiveId: Int, archiveCreateDate: String): GoalArchiveCreateParameter {
        return GoalArchiveCreateParameter(
            archiveId = archiveId,
            archiveCreateDate = archiveCreateDate,
            thoughts = thoughts,
            publish = publishKey
        )
    }
}
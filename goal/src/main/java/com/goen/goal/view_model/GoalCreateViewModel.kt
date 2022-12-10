package com.goen.goal.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.goal.GoalCreateParameter
import com.goen.domain.model.param.goal.GoalDetailParameter
import com.goen.domain.repository.GoalRepository
import com.goen.utils.entity.FormLocalDateObj
import com.goen.utils.entity.FormObj
import com.goen.utils.extentions.toLocalDateParse
import com.goen.utils.validate.BaseValidate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class GoalCreateViewModel @Inject constructor(
    private val repository: GoalRepository
):ViewModel() {
    var input: GoalCreateInput = GoalCreateInput()

    var success: MutableState<Boolean> = mutableStateOf(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)
    var errorDialog: MutableState<Boolean> = mutableStateOf(false)

    var validate: BaseValidate = BaseValidate()

    var goalTermAlertFlg: MutableState<Boolean> = mutableStateOf(false)


    init {
        this.init()
    }

    fun changeTitle(item: String) {
        if(!validate.require(item)) {
            input.titleM.value = input.titleM.value.copy(value = item, error = "題名を入力してください", isError = true)
            return
        }

        if(validate.size(item = item, size = 300)) {
            input.titleM.value = input.titleM.value.copy(value = item, error = "題名は300文字で入力してください", isError = true)
            return
        }
        input.titleM.value = input.titleM.value.copy(value  = item, error = "", isError = false)
    }

    fun changePurpose(item: String) {

        if(validate.size(item = item, size = 3000)) {
            input.purposeM.value = input.purposeM.value.copy(value = item, error = "目的は3000文字で入力してください", isError = true)
            return
        }
        input.purposeM.value = input.purposeM.value.copy(value = item, error = "", isError = false)
    }

    fun changeAim(item: String) {

        if(validate.size(item = item, size = 3000)) {
            input.aimM.value = input.aimM.value.copy(value = item, error = "目的は3000文字で入力してください", isError = true)
            return
        }
        input.aimM.value = input.aimM.value.copy(value  = item, error = "", isError = false)
    }

    fun changeTermDialog(item: Boolean) {
        goalTermAlertFlg.value = item
    }

    fun getGoalDetail(goalId: Int, createDate: String) {
        viewModelScope.launch {
            repository.getGoalDetail(
                param = GoalDetailParameter(goalId = goalId, createDate = createDate),
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect {
                input.titleM.value = input.titleM.value.copy(error = "", value = it.title, isError = false)
                input.purposeM.value = input.purposeM.value.copy(error = "", value = it.purpose, isError = false)
                input.aimM.value = input.aimM.value.copy(error = "", value = it.aim, isError = false)
                if(it.dueDate.toLocalDateParse().isBefore(LocalDate.now())) {
                    input.dueDateM.value =
                        input.dueDateM.value.copy(error = "", date = it.dueDate.toLocalDateParse(), isError = true)

                } else {
                    input.dueDateM.value =
                        input.dueDateM.value.copy(error = "", date = it.dueDate.toLocalDateParse(), isError = false)

                }
            }
        }
    }

    fun createGoal() {
        loading.value = true
        viewModelScope.launch {
            if(!input.isTitleError && !input.isPurposeError && !input.isAimError) {
                repository.createGoal(
                    param = input.parameter,
                    onStart = {},
                    onComplate = {
                        loading.value = false },
                    onError = {error ->
                        errorDialog.value = true
                    })
                    .collect { it: Unit ->
                        success.value = true
                    }
            }
        }
    }

    fun updateGoal(goalId: Int, createDate: String) {
        viewModelScope.launch {
            if(!input.isTitleError && !input.isPurposeError && !input.isAimError) {
                repository.updateGoal(
                    param = input.updateParam(goalId = goalId, createDate = createDate),
                    onStart = {},
                    onComplate = {
                        loading.value = false },
                    onError = {error ->
                        errorDialog.value = true
                    })
                    .collect { it: Unit ->
                        success.value = true
                    }
            }
        }
    }

    fun init() {
        input.titleM.value = FormObj(value = "", error = "")
        input.purposeM.value = FormObj(value = "", error = "")
        input.aimM.value = FormObj(value = "", error = "")
        errorDialog.value = false
        success.value = false
        loading.value = false
    }

    fun chkEnableButton(): Boolean {
        return !input.titleM.value.isError!!
                && !input.purposeM.value.isError!!
                && !input.aimM.value.isError!!
                && !input.dueDateM.value.isError!!
    }

    fun changeDueDate(it: LocalDate) {
        if (it.isBefore(LocalDate.now())) {
            input.dueDateM.value = input.dueDateM.value.copy(date = it, error = "今日より前の日付は設定できません。", isError = true)
            return
        }
        input.dueDateM.value = input.dueDateM.value.copy(date = it, error = "", isError = false)
    }
}

data class GoalCreateInput (
    var titleM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var purposeM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var aimM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var dueDateM: MutableState<FormLocalDateObj> = mutableStateOf(FormLocalDateObj())
){
    val title: String get() = titleM.value.value!!
    val purpose: String get() = purposeM.value.value!!
    val aim: String get() = aimM.value.value!!
    val dueDate: LocalDate get() = dueDateM.value.date!!

    val titleError: String? get() = titleM.value.error
    val purposeError: String? get() = purposeM.value.error
    val aimError: String? get() = aimM.value.error

    val isTitleError: Boolean get() = titleM.value.isError!!
    val isPurposeError: Boolean get() = purposeM.value.isError!!
    val isAimError: Boolean get() = aimM.value.isError!!

    val parameter: GoalCreateParameter
        get() = GoalCreateParameter(
                    title = title,
                    purpose = purpose,
                    aim = aim,
                    dueDate = dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                )

    fun updateParam(goalId: Int, createDate: String): GoalCreateParameter {
        return GoalCreateParameter(
            goalId = goalId,
            createDate = createDate,
            title = title,
            purpose = purpose,
            aim = aim,
            dueDate = dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        )
    }
}
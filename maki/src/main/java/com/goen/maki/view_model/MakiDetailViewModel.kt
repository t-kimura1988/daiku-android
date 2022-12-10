package com.goen.maki.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.maki.MakiAddGoalListParameter
import com.goen.domain.model.param.maki.MakiDetailParameter
import com.goen.domain.model.param.maki.MakiGoalListParameter
import com.goen.domain.model.param.maki.MakiGoalRelationCreateParameter
import com.goen.domain.model.result.GoalSearchResult
import com.goen.domain.model.result.maki.MakiSearchResult
import com.goen.domain.repository.MakiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MakiDetailViewModel @Inject constructor(
    private var makiRepository: MakiRepository
): ViewModel() {
    var makiDetail: MutableState<MakiSearchResult> = mutableStateOf(
        MakiSearchResult()
    )

    var goalListDialog: MutableState<Boolean> = mutableStateOf(false)

    private var makiGoalInput: MakiGoalListInput = MakiGoalListInput()
    var makiGoalList: MutableState<List<GoalSearchResult>> = mutableStateOf(arrayListOf(GoalSearchResult()))
    var makiAddGoalList: MutableState<List<GoalSearchResult>> = mutableStateOf(arrayListOf(GoalSearchResult()))

    var checkAddGoal: MutableList<MutableState<MakiGoalRelationCreateParameter>> = mutableListOf(
        mutableStateOf(MakiGoalRelationCreateParameter())
    )

    fun getMakiDetail(makiId: Int) {
        viewModelScope.launch {
            makiRepository.makiDetail(
                param = MakiDetailParameter(
                    makiId = makiId
                ),
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect {
                makiDetail.value = it
            }
        }
    }

    fun getGoalOfMaki(makiId: Int) {
        makiGoalInput.pageM.value = makiGoalInput.pageM.value + 20
        makiGoalInput.makiIdM.value = makiId
        viewModelScope.launch {
            makiRepository.makiGoal(
                param = makiGoalInput.param,
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect{
                makiGoalList.value = it
            }
        }
    }

    fun getMakiAddGoalList() {
        viewModelScope.launch {
            makiRepository.makiAddGoalList(
                param = MakiAddGoalListParameter(makiId = makiDetail.value.id),
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect{ it ->
                checkAddGoal = MutableList(it.size){ mutableStateOf(MakiGoalRelationCreateParameter()) }
                makiAddGoalList.value = it
            }
        }
    }

    fun addMakiGoalList() {
        var createList: MutableList<MakiGoalRelationCreateParameter> = mutableListOf()

        System.out.println("HHHHHHHHHHHHHHHHHHHHHH1111")
        checkAddGoal.forEach {
            if(it.value.goalId == 0 || it.value.goalCreateDate == "") {
                return@forEach
            }

            createList.add(it.value)
        }

        System.out.println("HHHHHHHHHHHHHHHHHHHHHH22222")
        viewModelScope.launch {
            makiRepository.createMakiGoalRelation(
                param = createList,
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect{
                closeGoalListDialog()
            }
        }
    }

    fun openGoalListDialog() {
        goalListDialog.value = true
    }

    fun closeGoalListDialog() {
        goalListDialog.value = false
    }

    fun changeCheck(index: Int, item: GoalSearchResult) {
        if (checkAddGoal[index].value.goalId == 0) {
            checkAddGoal[index].value = checkAddGoal[index].value.copy(goalId = item.id, goalCreateDate = item.createDateString, makiId = makiDetail.value.id)
        } else {
            checkAddGoal[index].value = MakiGoalRelationCreateParameter()
        }
    }

}

data class MakiGoalListInput(
    var pageM: MutableState<Int> = mutableStateOf(10),
    var makiIdM: MutableState<Int> = mutableStateOf(10),
) {
    val page: Int get() = pageM.value
    val makiId: Int get() = makiIdM.value

    val param: MakiGoalListParameter
        get() = MakiGoalListParameter(
            makiId = makiId,
            page = page
        )
}
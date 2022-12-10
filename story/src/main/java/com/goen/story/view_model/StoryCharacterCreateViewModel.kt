package com.goen.story.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.story_character.StoryCharacterCreateParameter
import com.goen.domain.repository.StoryCharacterRepository
import com.goen.utils.entity.FormObj
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryCharacterCreateViewModel @Inject constructor(
    private val storyCharacterRepository: StoryCharacterRepository
):ViewModel(){
    var input: ProcessCreateInput = ProcessCreateInput()
    var leaderAlertFlg: MutableState<Boolean> = mutableStateOf(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)
    var success: MutableState<Boolean> = mutableStateOf(false)
    var failureFlg: MutableState<Boolean> = mutableStateOf(false)

    val leaderOptions = mapOf(0 to "一般", 1 to "主役")

    fun changeCharaName(item: String) {
        if(item == "") {
            input.charaNameM.value = input.charaNameM.value.copy(error = "キャラ名をつけてください")
        } else {
            input.charaNameM.value = input.charaNameM.value.copy(error = "")

        }
        input.charaNameM.value = input.charaNameM.value.copy(value  = item)
    }

    fun changeCharaDesc(item: String) {
        if(item == "") {
            input.charaDescM.value = input.charaDescM.value.copy(error = "キャラ説明を入力してください")
        } else {
            input.charaDescM.value = input.charaDescM.value.copy(error = "")
        }
        input.charaDescM.value = input.charaDescM.value.copy(value = item)
    }

    fun changeLeaderFlgAlert(item: Boolean) {
        leaderAlertFlg.value = item
    }

    fun changeLeaderFlg(item: Int) {
        input.leaderM.value = item
    }

    fun createChara(ideaId: Int, storyId: Int) {
        viewModelScope.launch {
            storyCharacterRepository.createStoryChara(
                param = input.parameter(ideaId = ideaId, storyId = storyId),
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
            .collect { it ->
            }
        }
    }

    fun changeFailure(item: Boolean) {
        failureFlg.value = item
    }
}

data class ProcessCreateInput (
    var charaNameM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var charaDescM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var leaderM: MutableState<Int> = mutableStateOf(0)
){
    val charaName: String get() = charaNameM.value.value!!
    val charaDesc: String get() = charaDescM.value.value!!
    private val leader: Int get() = leaderM.value

    val charaNameError: String? get() = charaNameM.value.error
    val charaDescError: String? get() = charaDescM.value.error

    val isCharaNameError: Boolean get() = charaNameM.value.error != "" && charaNameM.value.error != null
    val isCharaDescError: Boolean get() = charaDescM.value.error != "" && charaDescM.value.error != null

    val enableButton: Boolean get() =
        (charaNameM.value.error != null && charaDescM.value.error != null) &&
                (charaNameM.value.error == "" && charaDescM.value.error == "")

    fun parameter(ideaId: Int, storyId: Int): StoryCharacterCreateParameter {
        return StoryCharacterCreateParameter(
            ideaId = ideaId,
            storyId = storyId,
            charaName = charaName,
            charaDesc = charaDesc,
            leaderFlg = leader
        )
    }
}
package com.goen.story.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.story.StoryBodyUpdateParameter
import com.goen.domain.model.param.story.StoryDetailParameter
import com.goen.domain.model.result.idea.IdeaSearchResult
import com.goen.domain.model.result.story.StorySearchResult
import com.goen.domain.repository.StoryRepository
import com.goen.utils.entity.FormObj
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryBodyUpdateViewModel @Inject constructor(
    private val storyRepository: StoryRepository
):ViewModel(){
    var input: StoryBodyInput = StoryBodyInput()
    var success: MutableState<Boolean> = mutableStateOf(false)
    var failureFlg: MutableState<Boolean> = mutableStateOf(false)

    var story: MutableState<StorySearchResult> = mutableStateOf(StorySearchResult())
    fun getStoryDetail(storyId: Int, ideaId: Int) {
        viewModelScope.launch {
            storyRepository.getStoryDetail(
                param = StoryDetailParameter(storyId = storyId, ideaId = ideaId),
                onStart = {},
                onError = {},
                onComplate = {}
            )
                .collect{
                    input.storyBodyM.value = input.storyBodyM.value.copy(value = it.storyBody)
                }
        }
    }

    fun changeStoryBody(item: String) {
        if(item == "") {
            input.storyBodyM.value = input.storyBodyM.value.copy(error = "キャラ名をつけてください")
        } else {
            input.storyBodyM.value = input.storyBodyM.value.copy(error = "")

        }
        input.storyBodyM.value = input.storyBodyM.value.copy(value  = item)
    }

    fun update(storyId: Int, ideaId: Int) {
        viewModelScope.launch {
            storyRepository.updateStoryBody(
                param = input.parameter(ideaId = ideaId, storyId = storyId),
                onError = {},
                onStart = {},
                onComplate = {}
            ).collect { it: IdeaSearchResult ->
                success.value = true
            }
        }
    }

    fun changeFailure(item: Boolean) {
        failureFlg.value = item
    }
}

data class StoryBodyInput (
    var storyBodyM: MutableState<FormObj> = mutableStateOf(FormObj())
){
    val storyBody: String get() = storyBodyM.value.value!!

    val storyBodyError: String? get() = storyBodyM.value.error

    val isStoryBodyError: Boolean get() = storyBodyM.value.error != "" && storyBodyM.value.error != null

    val enableButton: Boolean get() =
        (storyBodyM.value.error != null) &&
                (storyBodyM.value.error == "")

    fun parameter(ideaId: Int, storyId: Int): StoryBodyUpdateParameter {
        return StoryBodyUpdateParameter(
            ideaId = ideaId,
            storyId = storyId,
            storyBody = storyBody
        )
    }
}
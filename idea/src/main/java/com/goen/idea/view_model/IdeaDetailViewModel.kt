package com.goen.idea.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.idea.IdeaUpdateParameter
import com.goen.domain.model.param.idea.MyIdeaDetailParameter
import com.goen.domain.model.param.story.StoryCreateParameter
import com.goen.domain.model.param.story_character.StoryCharacterListParameter
import com.goen.domain.model.result.idea.IdeaSearchResult
import com.goen.domain.model.result.story_character.StoryCharacterSearchResult
import com.goen.domain.repository.IdeaRepository
import com.goen.domain.repository.StoryCharacterRepository
import com.goen.domain.repository.StoryRepository
import com.goen.utils.entity.FormObj
import com.goen.utils.validate.BaseValidate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdeaDetailViewModel @Inject constructor(
    private var ideaRepository: IdeaRepository,
    private var storyCharacterRepository: StoryCharacterRepository,
    private var storyRepository: StoryRepository
): ViewModel() {
    var ideaDetail: MutableState<IdeaSearchResult> = mutableStateOf(
        IdeaSearchResult()
    )

    var storyCharaListState: MutableState<List<StoryCharacterSearchResult>> = mutableStateOf(
        arrayListOf(StoryCharacterSearchResult())
    )

    var storyCharaDetailState: MutableState<StoryCharacterSearchResult> = mutableStateOf(
        StoryCharacterSearchResult()
    )

    var charaDetailAlert: MutableState<Boolean> = mutableStateOf(false)
    var storyCreateAlert: MutableState<Boolean> = mutableStateOf(false)
    var ideaUpdateAlert: MutableState<Boolean> = mutableStateOf(false)

    var input: StoryCreateInput = StoryCreateInput()
    var ideaUpdateInput: IdeaUpdateInput = IdeaUpdateInput()
    var validate: BaseValidate = BaseValidate()

    var success: MutableState<Boolean> = mutableStateOf(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)
    var errorDialog: MutableState<Boolean> = mutableStateOf(false)

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

    fun changeIdeaBody(item: String) {
        if(!validate.require(item)) {
            ideaUpdateInput.bodyM.value = ideaUpdateInput.bodyM.value.copy(value = item, error = "題名を入力してください", isError = true)
            return
        }

        if(validate.size(item = item, size = 300)) {
            ideaUpdateInput.bodyM.value = ideaUpdateInput.bodyM.value.copy(value = item, error = "題名は300文字で入力してください", isError = true)
            return
        }
        ideaUpdateInput.bodyM.value = ideaUpdateInput.bodyM.value.copy(value  = item, error = "", isError = false)

    }

    fun getIdeaDetail(ideaId: Int) {
        viewModelScope.launch {
            ideaRepository.getIdeaDetail(
                param = MyIdeaDetailParameter(ideaId = ideaId),
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect {
                ideaDetail.value = it
            }
        }
    }

    fun getStoryChara(storyId: Int, ideaId: Int) {
        if (storyId > 0) {
            viewModelScope.launch {
                storyCharacterRepository.getStoryCharaList(
                    param = StoryCharacterListParameter(
                        storyId = storyId,
                        ideaId = ideaId
                    ),
                    onStart = {},
                    onError = {},
                    onComplate = {}
                ).collect {
                    storyCharaListState.value = it
                }
            }
        }
    }

    fun createStory() {
        viewModelScope.launch {
            storyRepository.createStory(
                param = input.createParam(ideaId = ideaDetail.value.id),
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect{
                closeStoryCreateAlert()
                getIdeaDetail(ideaId = it.ideaId)
                getStoryChara(storyId = it.id, ideaId = it.ideaId)
            }
        }
    }

    fun updateIdea() {
        viewModelScope.launch {
            ideaRepository.updateIdea(
                param = ideaUpdateInput.updateParam(ideaId = ideaDetail.value.id),
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect{
                ideaDetail.value = it
                closeIdeaUpdateAlert()
            }
        }
    }

    fun openCharaDetailAlert(story: StoryCharacterSearchResult) {
        charaDetailAlert.value = true
        storyCharaDetailState.value = story
    }

    fun closeCharaDetailAlert() {
        charaDetailAlert.value = false
        storyCharaDetailState.value = StoryCharacterSearchResult()

    }

    fun openStoryCreateAlert() {
        storyCreateAlert.value = true
    }

    fun closeStoryCreateAlert() {
        storyCreateAlert.value = false
    }

    fun openIdeaUpdateAlert() {
        ideaUpdateInput.bodyM.value = FormObj(value = ideaDetail.value.body, error = "", isError = false)
        ideaUpdateAlert.value = true
    }

    fun closeIdeaUpdateAlert() {
        ideaUpdateAlert.value = false
    }

    init {
        input.titleM.value = FormObj(value = "", error = "")
    }

}

data class StoryCreateInput(
    var titleM: MutableState<FormObj> = mutableStateOf(FormObj()),
) {

    val title: String get() = titleM.value.value!!

    val titleError: String? get() = titleM.value.error

    val isTitleError: Boolean get() = titleM.value.isError!!

    fun createParam(ideaId: Int): StoryCreateParameter {
        return StoryCreateParameter(
            ideaId = ideaId,
            title = title,
        )
    }

}


data class IdeaUpdateInput(
    var bodyM: MutableState<FormObj> = mutableStateOf(FormObj()),
){

    val body: String get() = bodyM.value.value!!

    val bodyError: String? get() = bodyM.value.error

    val isBodyError: Boolean get() = bodyM.value.isError!!

    fun updateParam(ideaId: Int): IdeaUpdateParameter {
        return IdeaUpdateParameter(
            ideaId = ideaId,
            body = body,
        )
    }

}
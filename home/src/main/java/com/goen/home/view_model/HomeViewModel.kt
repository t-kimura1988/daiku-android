package com.goen.home.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.idea.IdeaCreateParameter
import com.goen.domain.repository.IdeaRepository
import com.goen.utils.entity.FormObj
import com.goen.utils.validate.BaseValidate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val ideaRepository: IdeaRepository
): ViewModel() {

    var ideaCreateAlert: MutableState<Boolean> = mutableStateOf(false)

    var input: StoryCreateInput = StoryCreateInput()
    var validate: BaseValidate = BaseValidate()

    init {
        input.bodyM.value = FormObj(value = "", error = "", isError = true)
    }

    fun changeBody(item: String) {

        if(!validate.require(item)) {
            input.bodyM.value = input.bodyM.value.copy(value = item, error = "題名を入力してください", isError = true)
            return
        }

        if(validate.size(item = item, size = 300)) {
            input.bodyM.value = input.bodyM.value.copy(value = item, error = "題名は300文字で入力してください", isError = true)
            return
        }
        input.bodyM.value = input.bodyM.value.copy(value  = item, error = "", isError = false)
    }

    fun createIdea() {
        viewModelScope.launch {
            ideaRepository.createIdea(
                param = input.createParam(),
                onStart = {},
                onError = {},
                onComplate = {}
            ).collect{it ->
                closeIdeaCreateAlert()
            }
        }
    }

    fun enableSaveButton(): Boolean {
        if (input.isBodyError) {
            return false
        }

        return true
    }

    fun openIdeaCreateAlert() {
        ideaCreateAlert.value = true
    }

    fun closeIdeaCreateAlert() {
        ideaCreateAlert.value = false
    }
}

data class StoryCreateInput(
    var bodyM: MutableState<FormObj> = mutableStateOf(FormObj()),
){

    val body: String get() = bodyM.value.value!!

    val bodyError: String? get() = bodyM.value.error

    val isBodyError: Boolean get() = bodyM.value.isError!!

    fun createParam(): IdeaCreateParameter {
        return IdeaCreateParameter(
            body = body,
        )
    }

}
package com.goen.maki.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goen.domain.model.param.maki.MakiCreateParameter
import com.goen.domain.model.result.maki.MakiSearchResult
import com.goen.domain.repository.MakiRepository
import com.goen.utils.entity.FormObj
import com.goen.utils.validate.BaseValidate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MakiCreateViewModel @Inject constructor(
    private val repository: MakiRepository
):ViewModel() {
    var input: MakiCreateInput = MakiCreateInput()

    var success: MutableState<Boolean> = mutableStateOf(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)
    var errorDialog: MutableState<Boolean> = mutableStateOf(false)

    var validate: BaseValidate = BaseValidate()


    init {
        this.init()
    }

    fun changeMakiTitle(item: String) {
        if(!validate.require(item)) {
            input.makiTitleM.value = input.makiTitleM.value.copy(value = item, error = "巻名を入力してください", isError = true)
            return
        }

        if(validate.size(item = item, size = 300)) {
            input.makiTitleM.value = input.makiTitleM.value.copy(value = item, error = "題名は300文字で入力してください", isError = true)
            return
        }
        input.makiTitleM.value = input.makiTitleM.value.copy(value  = item, error = "", isError = false)
    }

    fun changeMakiKey(item: String) {

        if(validate.size(item = item, size = 100)) {
            input.makiKeyM.value = input.makiKeyM.value.copy(value = item, error = "巻キーは100文字で入力してください", isError = true)
            return
        }
        input.makiKeyM.value = input.makiKeyM.value.copy(value = item, error = "", isError = false)
    }

    fun changeMakiDesc(item: String) {

        if(validate.size(item = item, size = 3000)) {
            input.makiDescM.value = input.makiDescM.value.copy(value = item, error = "巻説明は3000文字で入力してください", isError = true)
            return
        }
        input.makiDescM.value = input.makiDescM.value.copy(value  = item, error = "", isError = false)
    }

    fun createMaki() {
        loading.value = true
        viewModelScope.launch {
            if(!input.isMakiTitleError && !input.isMakiKeyError && !input.isMakiDescError) {
                repository.createMaki(
                    param = input.parameter,
                    onStart = {},
                    onComplate = {
                        loading.value = false },
                    onError = {error ->
                        errorDialog.value = true
                    })
                    .collect { it: MakiSearchResult ->
                        success.value = true
                    }
            }
        }
    }

    fun init() {
        input.makiTitleM.value = FormObj(value = "", error = "")
        input.makiKeyM.value = FormObj(value = "", error = "")
        input.makiDescM.value = FormObj(value = "", error = "")
        errorDialog.value = false
        success.value = false
        loading.value = false
    }

    fun chkEnableButton(): Boolean {
        return !input.makiTitleM.value.isError!!
                && !input.makiKeyM.value.isError!!
                && !input.makiDescM.value.isError!!
    }
}

data class MakiCreateInput (
    var makiTitleM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var makiKeyM: MutableState<FormObj> = mutableStateOf(FormObj()),
    var makiDescM: MutableState<FormObj> = mutableStateOf(FormObj())
){
    val makiTitle: String get() = makiTitleM.value.value!!
    val makiKey: String get() = makiKeyM.value.value!!
    val makiDesc: String get() = makiDescM.value.value!!

    val makiTitleError: String? get() = makiTitleM.value.error
    val makiKeyError: String? get() = makiKeyM.value.error
    val makiDescError: String? get() = makiDescM.value.error

    val isMakiTitleError: Boolean get() = makiTitleM.value.isError!!
    val isMakiKeyError: Boolean get() = makiKeyM.value.isError!!
    val isMakiDescError: Boolean get() = makiDescM.value.isError!!

    val parameter: MakiCreateParameter
        get() = MakiCreateParameter(
                    makiTitle = makiTitle,
                    makiKey = makiKey,
                    makiDesc = makiDesc
                )

}
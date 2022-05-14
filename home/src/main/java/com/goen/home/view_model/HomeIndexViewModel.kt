package com.goen.home.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeIndexViewModel @Inject constructor(): ViewModel() {
    var index: MutableState<Int> = mutableStateOf<Int>(0)

    fun changeIndex(item: Int) {
        index.value = item
    }
}
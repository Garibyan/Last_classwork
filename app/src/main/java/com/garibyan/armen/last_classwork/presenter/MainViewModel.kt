package com.garibyan.armen.last_classwork.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garibyan.armen.last_classwork.common.Resource
import com.garibyan.armen.last_classwork.domain.model.Chat
import com.garibyan.armen.last_classwork.domain.usecase.GetAllChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllChatsUseCase: GetAllChatsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<Resource<List<Chat>>>(Resource.Loader)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllChats()
        }
    }

    private suspend fun getAllChats() {
        viewModelScope.launch {
            getAllChatsUseCase.invoke().collect {
                _viewState.value = it
            }
        }
    }

}
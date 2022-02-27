package com.faysal.androidmvvmp3.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.faysal.androidmvvmp3.data.repository.CatRepository
import com.faysal.androidmvvmp3.models.Cat
import com.faysal.androidmvvmp3.ui.uistate.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val catRepository: CatRepository
) : ViewModel() {
    private var currentResult: Flow<PagingData<Cat>>? = null

    @ExperimentalPagingApi
    fun getCats(): Flow<PagingData<Cat>> {
        val newResult: Flow<PagingData<Cat>> =
            catRepository.getCats().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }


/*
    private val _mainState = MutableStateFlow(MainUiState())
    val mainState = _mainState

    private var mainStateJob: Job? = null
    @ExperimentalPagingApi
    fun getCommunityMember(shouldRefresh: Boolean = false) {
        mainStateJob?.cancel()
        mainStateJob = viewModelScope.launch {
            if (shouldRefresh || _mainState.value.list.isEmpty()) {
                _mainState.update { it.copy(isLoading = true) }
                catRepository.getCats().collect { resource ->
                    _mainState.update {
                        it.copy(
                            isLoading = false,
                            list = resource : emptyList(),
                            message = resource.message ?: ""
                        )
                    }
                }
            }
        }
    }*/


}
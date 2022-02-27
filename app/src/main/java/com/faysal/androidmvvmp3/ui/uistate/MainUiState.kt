package com.faysal.androidmvvmp3.ui.uistate

import com.faysal.androidmvvmp3.models.Cat

data class MainUiState (
    val list : List<Cat> = emptyList(),
    val isLoading : Boolean = false,
    val message : String = ""
)
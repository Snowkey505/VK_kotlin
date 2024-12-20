package com.example.vk_hw_02

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GiphyViewModel(private val repository: GiphyRepository) : ViewModel() {
    var uiState by mutableStateOf<UiState>(UiState.Loading)//stateflow отдавать наружу
        private set

    var gifs by mutableStateOf<List<GifData>>(emptyList())
        private set

    private var offset = 0
    private val limit = 20
    private var isLoading = false

    init {
        loadGifs()
    }

    fun loadGifs() {
        if (isLoading) return
        isLoading = true
        uiState = UiState.Loading

        viewModelScope.launch {
            try {
                val newGifs = repository.getTrendingGifs(offset, limit)
                gifs = newGifs
                offset += limit
                uiState = UiState.Success
            } catch (e: Exception) {
                uiState = UiState.Error
            } finally {
                isLoading = false
            }
        }
    }

    fun loadMoreGifs() {
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            try {
                val newGifs = repository.getTrendingGifs(offset, limit)
                gifs = gifs + newGifs
                offset += limit
            } catch (e: Exception) {
            } finally {
                isLoading = false
            }
        }
    }
}
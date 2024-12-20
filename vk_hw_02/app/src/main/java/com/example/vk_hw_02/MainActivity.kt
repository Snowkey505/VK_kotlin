package com.example.vk_hw_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

data class GiphyResponse(val data: List<GifData>)
data class GifData(val images: GifImages)
data class GifImages(val original: ImageUrl)
data class ImageUrl(val url: String)

sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
    object Error : UiState()
}

class MainActivity : ComponentActivity() {

    private val viewModel: GiphyViewModel by viewModels {
        GiphyViewModelFactory(RepositoryProvider.provideGiphyRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiphyAppTheme {
                GiphyApp(viewModel = viewModel)
            }
        }
    }
}
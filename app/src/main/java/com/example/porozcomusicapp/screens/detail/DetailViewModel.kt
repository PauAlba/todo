package com.example.porozcomusicapp.screens.detail


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.porozcomusicapp.models.Album
import com.example.porozcomusicapp.services.ApiClient
import com.example.porozcomusicapp.services.UiState
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi
import java.io.IOException


@OptIn(InternalSerializationApi::class)

class DetailViewModel(private val albumId: String) : ViewModel() {

    var albumDetailState: UiState<Album> by mutableStateOf(UiState.Loading)
        private set

    init {
        fetchAlbumDetail()
    }

    private fun fetchAlbumDetail() {
        viewModelScope.launch {
            albumDetailState = UiState.Loading
            try {
                val album = ApiClient.musicService.getAlbumDetail(albumId)
                albumDetailState = UiState.Success(album)
            } catch (e: IOException) {
                albumDetailState = UiState.Error("Error de red al obtener detalle: ${e.message}")
            } catch (e: Exception) {
                albumDetailState = UiState.Error("Error desconocido al obtener detalle: ${e.message}")
            }
        }
    }

    companion object {
        fun provideFactory(albumId: String): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DetailViewModel(albumId) as T
            }
        }
    }
}
package com.example.porozcomusicapp.screens.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.porozcomusicapp.models.Album
import com.example.porozcomusicapp.services.ApiClient
import com.example.porozcomusicapp.services.UiState
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi
import java.io.IOException
@OptIn(InternalSerializationApi::class)

class HomeViewModel : ViewModel() {

    var albumsState: UiState<List<Album>> by mutableStateOf(UiState.Loading)
        private set

    var recentlyPlayedState: UiState<List<Album>> by mutableStateOf(UiState.Loading)
        private set

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            albumsState = UiState.Loading
            recentlyPlayedState = UiState.Loading
            try {
                val albums = ApiClient.musicService.getAlbums()
                albumsState = UiState.Success(albums)

                recentlyPlayedState = UiState.Success(albums.shuffled())
            } catch (e: IOException) {
                albumsState = UiState.Error("Error de red: ${e.message}")
                recentlyPlayedState = UiState.Error("Error de red: ${e.message}")
            } catch (e: Exception) {
                albumsState = UiState.Error("Error desconocido: ${e.message}")
                recentlyPlayedState = UiState.Error("Error desconocido: ${e.message}")
            }
        }
    }

    val defaultMiniPlayerAlbum = Album(
        id = "default",
        title = "Tales of Ithiria",
        artist = "Haggard",
        description = "...",
        image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEj7ugde1AEMq01vmC8BVEpLdF0Xek6AY9bGlBiOU5KnDJwkIfGTjfPDgNDzOdO_IiigYdzctv_esbkP2J-Q4A7KczJJMotAnqNg7hRCCY..." // Imagen de Tales of Ithiria
    )
}
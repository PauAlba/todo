package com.example.porozcomusicapp.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.porozcomusicapp.components.AlbumCardHorizontal
import com.example.porozcomusicapp.components.AlbumCardVertical
import com.example.porozcomusicapp.components.AppBarHome
import com.example.porozcomusicapp.components.MusicPlayerBar
import com.example.porozcomusicapp.services.UiState
import kotlinx.serialization.InternalSerializationApi


@OptIn(InternalSerializationApi::class)

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onAlbumClick: (String) -> Unit
) {
    Scaffold(
        bottomBar = {
            MusicPlayerBar(currentAlbum = viewModel.defaultMiniPlayerAlbum)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 10.dp)
        ) {
            AppBarHome(name = "Porozco")

            LazyColumn(
                contentPadding = PaddingValues(bottom = 16.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Albums",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                        Text(
                            text = "See more",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp
                        )
                    }

                    when (val state = viewModel.albumsState) {
                        is UiState.Loading -> {
                            Box(Modifier.fillMaxWidth().height(240.dp)) {
                                CircularProgressIndicator(Modifier.align(Alignment.Center))
                            }
                        }
                        is UiState.Success -> {
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp)
                            ) {
                                items(state.data) { album ->
                                    AlbumCardHorizontal(album = album, onClick = onAlbumClick)
                                }
                            }
                        }
                        is UiState.Error -> {
                            Text(
                                text = "Error al cargar álbumes: ${state.message}",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Recently Played",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                        Text(
                            text = "See more",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp
                        )
                    }
                }


                when (val state = viewModel.recentlyPlayedState) {
                    is UiState.Success -> {
                        items(state.data) { album ->
                            AlbumCardVertical(
                                album = album,
                                onClick = onAlbumClick
                            )
                        }
                    }
                    is UiState.Loading -> {
                        items(5) {
                            AlbumCardVertical(
                                album = viewModel.defaultMiniPlayerAlbum,
                                onClick = onAlbumClick
                            )
                        }
                    }
                    is UiState.Error -> {
                        item {
                            Text(
                                text = "Error al cargar recientes: ${state.message}",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
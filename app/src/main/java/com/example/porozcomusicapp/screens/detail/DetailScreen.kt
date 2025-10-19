package com.example.porozcomusicapp.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.porozcomusicapp.components.*
import com.example.porozcomusicapp.models.Album
import com.example.porozcomusicapp.screens.home.HomeViewModel
import com.example.porozcomusicapp.services.UiState
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)

@Composable
fun DetailScreen(
    albumId: String,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = DetailViewModel.provideFactory(albumId)
    )
) {
    val miniPlayerAlbum = (viewModel.albumDetailState as? UiState.Success)?.data
        ?: HomeViewModel().defaultMiniPlayerAlbum

    Scaffold(
        bottomBar = {
            MusicPlayerBar(currentAlbum = miniPlayerAlbum)
        }
    ) { paddingValues ->
        when (val state = viewModel.albumDetailState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                }
            }
            is UiState.Success -> {
                AlbumDetailContent(
                    album = state.data,
                    onBackClick = onBackClick,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}
@OptIn(InternalSerializationApi::class)

@Composable
fun AlbumDetailContent(
    album: Album,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val screenBackgroundColor = Color(0xFFF3E5F5)
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(screenBackgroundColor)
                    .height(350.dp)
            ) {
                AsyncImage(
                    model = album.image,
                    contentDescription = "Album Art",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0x88673AB7), Color(0xDD000000)), // Morado/Negro
                            )
                        )
                )

                // Back Button
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp, start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                ) {
                    Text(
                        text = album.title,
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = album.artist,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0))
                        ) {
                            Icon(Icons.Filled.PlayArrow, contentDescription = "Play")
                            Spacer(Modifier.width(8.dp))
                            Text("Play")
                        }
                        OutlinedButton(
                            onClick = {},
                            border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF9C27B0), Color(0xFF673AB7))
                            ))
                        ) {
                            Icon(Icons.Filled.Shuffle, contentDescription = "Shuffle", tint = Color.White)
                            Spacer(Modifier.width(8.dp))
                            Text("Shuffle", color = Color.White)
                        }
                    }
                }
            }
        }



        item {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "About this album",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E0E32)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = album.description,
                        fontSize = 15.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }

        item {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                CustomChip(text = "Artist: ${album.artist}")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Recently Played",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }


        items(10) { index ->
            TrackItem(album = album, trackNumber = index + 1)
        }
    }
}
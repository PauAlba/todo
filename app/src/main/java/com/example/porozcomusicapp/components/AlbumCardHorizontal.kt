package com.example.porozcomusicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.porozcomusicapp.models.Album
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
@Composable
fun AlbumCardHorizontal(
    onClick: (String) -> Unit,
    album: Album
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .size(width = 180.dp, height = 240.dp)
            .padding(end = 12.dp)
            .clickable { onClick(album.id) }
    ) {

        var imageLoadFailed by remember { mutableStateOf(album.image.isNullOrEmpty()) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF282828)),
            contentAlignment = Alignment.Center
        ) {

            if (imageLoadFailed) {

                Icon(
                    imageVector = Icons.Filled.MusicNote,
                    contentDescription = "Placeholder Icon",
                    modifier = Modifier.size(64.dp),
                    tint = Color.LightGray
                )
            } else {
                AsyncImage(
                    model = album.image,
                    contentDescription = "${album.title} Album Cover",
                    contentScale = ContentScale.Crop,

                    placeholder = null,
                    error = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xCC000000)),
                            startY = 0f,
                            endY = 1000f
                        )
                    )
            )


            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = album.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = album.artist,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            ElevatedButton(
                onClick = {},
                shape = androidx.compose.foundation.shape.CircleShape,
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFF673AB7)),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 12.dp, end = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
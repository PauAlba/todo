package com.example.porozcomusicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun TrackItem(album: Album, trackNumber: Int) {
    val trackTitle = "${album.title} • Track $trackNumber"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable {}
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(vertical = 12.dp, horizontal = 16.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = album.image,
            contentDescription = "Track cover",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = trackTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = album.artist,
                fontSize = 13.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More options",
                tint = Color.Gray
            )
        }
    }
}
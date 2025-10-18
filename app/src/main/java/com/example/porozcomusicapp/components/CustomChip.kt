package com.example.porozcomusicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomChip(text: String) {
    Surface(
        color = (Color.White),
        shape = RoundedCornerShape(9.dp),
        modifier = Modifier
            .padding(vertical = 4.dp)

    ) {
        Text(
            text = text,
            color = Color.Black.copy(alpha = 0.8f),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}
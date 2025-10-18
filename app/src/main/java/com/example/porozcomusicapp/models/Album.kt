package com.example.porozcomusicapp.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.InternalSerializationApi


@InternalSerializationApi
@Serializable
data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val description: String,
    val image: String
)

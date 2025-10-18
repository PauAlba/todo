package com.example.porozcomusicapp.services

import com.example.porozcomusicapp.models.Album
import kotlinx.serialization.InternalSerializationApi
import retrofit2.http.GET
import retrofit2.http.Path

@OptIn(InternalSerializationApi::class)
interface MusicApiService {

    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun getAlbumDetail(@Path("id") id: String): Album
}
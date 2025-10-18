package com.example.porozcomusicapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private const val BASE_URL = "https://music.juanfrausto.com/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val musicService: MusicApiService by lazy {
        retrofit.create(MusicApiService::class.java)
    }
}
package com.example.videoplayer.network

import com.example.videoplayer.model.Video
import retrofit2.http.GET

interface VideoApiService {
    @GET("videos.get")
    suspend fun getVideos(): List<Video>
}
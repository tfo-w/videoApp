package com.example.videoplayer.repository

import android.content.SharedPreferences
import com.example.videoplayer.model.Video
import com.example.videoplayer.network.VideoApiService
import com.google.gson.Gson
import javax.inject.Inject

class VideoRepository @Inject constructor(
    private val videoApiService: VideoApiService,
    private val sharedPreferences: SharedPreferences
) {

    suspend fun getVideos(): List<Video> {
        val cachedVideos = getCachedVideos()
        if (cachedVideos.isNotEmpty()) {
            return cachedVideos
        }

        val videos = videoApiService.getVideos()
        cacheVideos(videos)
        return videos
    }

    private fun getCachedVideos(): List<Video> {
        val json = sharedPreferences.getString("videos", null)
        return if (json != null) {
            Gson().fromJson(json, Array<Video>::class.java).toList()
        } else {
            emptyList()
        }
    }

    private fun cacheVideos(videos: List<Video>) {
        val json = Gson().toJson(videos)
        sharedPreferences.edit().putString("videos", json).apply()
    }
}
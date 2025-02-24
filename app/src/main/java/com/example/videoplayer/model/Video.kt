package com.example.videoplayer.model

data class Video(
    val id: Int,
    val title: String,
    val duration: Int,
    val thumbnailUrl: String,
    val videoUrl: String
)
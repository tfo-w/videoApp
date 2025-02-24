package com.example.videoplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.model.Video
import com.example.videoplayer.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(private val repository: VideoRepository) : ViewModel() {

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> get() = _videos

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchVideos() {
        viewModelScope.launch {
            try {
                val videoList = repository.getVideos()
                _videos.value = videoList
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
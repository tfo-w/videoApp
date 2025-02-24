package com.example.videoplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.videoplayer.ui.VideoAdapter
import com.example.videoplayer.ui.VideoPlayerActivity
import com.example.videoplayer.viewmodel.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: VideoViewModel by viewModels()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        val videoRecyclerView = findViewById<RecyclerView>(R.id.videoRecyclerView)

        videoAdapter = VideoAdapter(emptyList()) { video ->
            val intent = Intent(this, VideoPlayerActivity::class.java)
            intent.putExtra("video_url", video.videoUrl)
            startActivity(intent)
        }

        videoRecyclerView.layoutManager = LinearLayoutManager(this)
        videoRecyclerView.adapter = videoAdapter

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchVideos()
        }

        viewModel.videos.observe(this) { videos ->
            videoAdapter.videos = videos
            videoAdapter.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.fetchVideos()
    }
}
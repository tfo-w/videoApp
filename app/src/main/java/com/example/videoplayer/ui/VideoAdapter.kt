package com.example.videoplayer.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videoplayer.model.Video
import com.example.videoplayer.R

class VideoAdapter(var videos: List<Video>, private val onItemClick: (Video) -> Unit) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        holder.bind(video)
        holder.itemView.setOnClickListener { onItemClick(video) }
    }

    override fun getItemCount(): Int = videos.size

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val durationTextView: TextView = itemView.findViewById(R.id.durationTextView)

        fun bind(video: Video) {
            Glide.with(itemView.context).load(video.thumbnailUrl).into(thumbnailImageView)
            titleTextView.text = video.title
            durationTextView.text = "Duration: ${video.duration} sec"
        }
    }
}
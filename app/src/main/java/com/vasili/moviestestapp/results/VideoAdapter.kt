package com.vasili.moviestestapp.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vasili.moviestestapp.R
import com.vasili.moviestestapp.common.Video
import kotlinx.android.synthetic.main.item_video.view.*

class VideoAdapter(private val items: List<Video>) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.title.text = items[position].title
        Glide.with(holder.view.context).load(items[position].poster).into(holder.view.image)
    }
}